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

  def number = rule { capture(digits) ~> (_.toInt) }

  def digits = rule { oneOrMore(CharPredicate.Digit) }

  def newLine = rule ( quiet(atomic("\r\n") | atomic("\n")) )

  def arraySeparator = rule { atomic(",") }

  val wsChar = CharPredicate(" \t\r\n")

  def skipOneOrMoreWS = rule { quiet(oneOrMore(wsChar)) }

  def skipZeroOrMoreWS = rule { quiet(zeroOrMore(wsChar)) }
}

object DatasetParser {

  val SingleNumber = new DatasetParser[List[Int] :: HNil](_: ParserInput) {
    override def datasetFormat = rule { zeroOrMore(number) ~ EOI ~> ((s: Seq[Int]) => s.toList) }
  }

  val ArrayOfInt = new DatasetParser[List[Vector[Int]] :: HNil](_: ParserInput) {
    override def datasetFormat = rule { zeroOrMore(intArray) ~ EOI ~> ((s: Seq[Vector[Int]]) => s.toList) }
  }

  val SingleNumber_ArrayOfInt = new DatasetParser[List[(Int, Vector[Int])] :: HNil](_: ParserInput) {
    override def datasetFormat = rule { zeroOrMore(singleNumberAndIntArray) ~ EOI ~> ((s: Seq[(Int, Vector[Int])]) => s.toList) }
  }

}