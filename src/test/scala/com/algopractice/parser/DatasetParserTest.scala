package com.algopractice.parser

import cats.instances.int._
import cats.instances.vector._
import cats.instances.list._
import cats.instances.tuple._
import org.parboiled2.{ParseError, ParserInput}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}
import org.scalatest.{FreeSpec, Matchers}
import shapeless.{::, HList, HNil}
import cats.{Eq, Show}

import scala.util.{Failure, Success}
import SingleTestDataset._
import com.algopractice.DatasetParser

class DatasetParserTest extends FreeSpec with TableDrivenPropertyChecks with Matchers {

  "Test Array parsing - single test" in {
    forAll(singleArrayTestData) {
      (input, expected) =>
        verifyParse(input, List(expected))(DatasetParser.ArrayOfInt)
    }
  }

  "Test Array parsing - multi test" in {
    forAll(convertToMulti(singleArrayTestData)) {
      (input, expected) =>
        verifyParse(input, expected)(DatasetParser.ArrayOfInt)
    }
  }

  "Test Number + Array parsing - single test" in {
    forAll(singleNumberAndArrayTestData) {
      (input, expected) =>
        verifyParse(input, List(expected))(DatasetParser.SingleNumber_ArrayOfInt)
    }
  }

  "Test Number + Array parsing - multi test" in {
    forAll(convertToMulti(singleNumberAndArrayTestData)) {
      (input, expected) =>
        verifyParse(input, expected)(DatasetParser.SingleNumber_ArrayOfInt)
    }
  }

  private def verifyParse[A: Eq, L <: (A :: HNil)](input: String, expected: A)(buildParser: ParserInput => DatasetParser[L]) = {
    val parser = buildParser(input)

    buildParser(input).datasetFormat.run() match {
      case Success(x) =>
        x match {
          case actual :: HNil => actual should be(expected)
          case _ => fail(s"Unexpected success format")
        }
      case Failure(e: ParseError) => fail(s"Parser error: ${parser.formatError(e)}")
      case Failure(e) => fail(s"Parser error: $e")
    }
  }

  private def convertToMulti[A](t: TableFor2[String, A]) = Table(
    ("input", "expected"),
    foldTestEntries(t.toList)
  )

  private def foldTestEntries[A](entries: List[(String, A)]): (String, List[A]) = {
    entries.foldLeft(("", List[A]())) {
      case (acc, (s, v)) => (acc._1 + s, acc._2 :+ v)
    }
  }

}
