package com.algopractice

import org.parboiled2.{ParserInput, _}
import shapeless.{::, HList, HNil}

abstract class DatasetParser[L <: HList](val input: ParserInput) extends Parser {

  def datasetFormat: Rule[HNil, L]

  def intArray = rule { emptyIntArray | nonEmptyIntArray }

  def singleNumberAndIntArray = rule { number ~ skipZeroOrMoreWS ~ intArray  ~> ((n: Int, s: Seq[Int]) => (n, s.toVector)) }

  def emptyIntArray = rule { atomic("{") ~ skipZeroOrMoreWS ~ atomic("}") ~> (() => Vector.empty[Int]) }

  def nonEmptyIntArray = rule { atomic("{") ~ skipZeroOrMoreWS ~ zeroOrMore(arrayNumber) ~ number ~ skipZeroOrMoreWS ~ atomic("}") ~> ((v: Seq[Int], h: Int) => ((v :+ h)).toVector) }

  def arrayNumber = rule { number ~ arraySeparator ~ skipZeroOrMoreWS }

  def intTuple3 = rule { number ~ skipOneOrMoreWS ~ number ~ skipOneOrMoreWS ~ number ~ skipZeroOrMoreWS ~> ((a: Int, b: Int, c: Int) => (a, b, c))  }

  def number = rule { capture(digits) ~> (_.toInt) }

  def digits = rule { oneOrMore(CharPredicate.Digit) }

  def newLine = rule ( quiet(atomic("\r\n") | atomic("\n")) )

  def arraySeparator = rule { atomic(",") }

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

}