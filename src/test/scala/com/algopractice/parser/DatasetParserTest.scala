package com.algopractice.parser

import cats.instances.int._
import cats.instances.vector._
import cats.instances.list._
import cats.instances.tuple._
import cats.instances.string._
import org.parboiled2.{ParseError, ParserInput}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}
import org.scalatest.{FreeSpec, Matchers}
import shapeless.{::, HList, HNil}
import cats.{Eq, Show}

import scala.util.{Failure, Success}
import SingleTestDataset._
import com.algopractice.DatasetParser

class DatasetParserTest extends FreeSpec with TableDrivenPropertyChecks with Matchers {

  "Test Array parsing" in {
    forAll(ArrayTestData) {
      (input, expected) =>
        verifyParse(input, expected)(DatasetParser.ArrayOfInt)
    }
  }

  "Test Number + Array parsing" in {
    forAll(NumberAndArrayTestData) {
      (input, expected) =>
        verifyParse(input, expected)(DatasetParser.SingleNumber_ArrayOfInt)
    }
  }

  "Test String + Array + Array + Array parsing" in {
    forAll(ComplexFormatTestData) {
      (input, expected) =>
        verifyParse(input, expected)(DatasetParser.AlphaString_ArrayOfInt_ArrayOfInt)
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


}
