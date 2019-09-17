package com.algopractice

import org.parboiled2.{ParserInput, _}
import shapeless.{::, HList, HNil}

abstract class DatasetParser[L <: HList](val input: ParserInput) extends Parser {

  def datasetFormat: Rule[HNil, L]

  def intArray = rule { nonEmptyIntArray | emptyIntArray }

  def singleNumberAndIntArray = rule { number ~ skipZeroOrMoreWS ~ intArray  ~> ((n: Int, s: Seq[Int]) => (n, s.toVector)) }

  def emptyIntArray = rule { atomic("{") ~ skipZeroOrMoreWS ~ atomic("}") ~> (() => Vector.empty[Int]) }

  def nonEmptyIntArray = rule { atomic("{") ~ skipZeroOrMoreWS ~ oneOrMore(number) ~ skipZeroOrMoreWS ~ atomic("}") ~> ((s: Seq[Int]) => s.toVector) }

  def intTuple3 = rule { number ~ skipOneOrMoreWS ~ number ~ skipOneOrMoreWS ~ number ~ skipZeroOrMoreWS ~> ((a: Int, b: Int, c: Int) => (a, b, c))  }

  def number = rule { capture(optional(atomic("-")) ~ digits) ~ skipZeroOrMoreWS ~> (_.toInt) }

  def alphaString = rule { capture(oneOrMore(CharPredicate.Alpha)) }

  def digits = rule { oneOrMore(CharPredicate.Digit) }

  def newLine = rule ( quiet(atomic("\r\n") | atomic("\n")) )

  val wsChar = CharPredicate(" \t\r\n")

  def skipOneOrMoreWS = rule { quiet(oneOrMore(wsChar)) }

  def skipZeroOrMoreWS = rule { quiet(zeroOrMore(wsChar)) }

}

object DatasetParser {

  val SingleNumber = new DatasetParser[Int :: HNil](_: ParserInput) {
    override def datasetFormat = rule { number ~ EOI }
  }

  val ArrayOfInt = new DatasetParser[Vector[Int] :: HNil](_: ParserInput) {
    override def datasetFormat = rule { intArray ~ EOI }
  }

  val SingleNumber_ArrayOfInt = new DatasetParser[(Int, Vector[Int]) :: HNil](_: ParserInput) {
    override def datasetFormat = rule { singleNumberAndIntArray ~ EOI }
  }

  val IntTuple3 = new DatasetParser[(Int, Int, Int) :: HNil](_: ParserInput) {
    override def datasetFormat = rule { intTuple3 ~ EOI }
  }

  val AlphaString_ArrayOfInt_ArrayOfInt = new DatasetParser[(String,  Vector[Int],  Vector[Int]) :: HNil](_: ParserInput) {
    override def datasetFormat =rule { alphaString ~ skipZeroOrMoreWS ~ intArray ~ skipZeroOrMoreWS ~ intArray ~ EOI ~> ((s: String, v1: Vector[Int], v2: Vector[Int]) => (s, v1, v2)) }
  }

}