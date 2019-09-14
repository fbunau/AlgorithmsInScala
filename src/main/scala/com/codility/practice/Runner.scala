package com.codility.practice

import cats.Show
import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.show._
import cats.syntax.flatMap._
import cats.syntax.applicative._
import cats.syntax.apply._
import cats.syntax.traverse._
import cats.instances.list._
import cats.instances.tuple._
import cats.instances.option._
import cats.syntax.eq._
import cats.instances.int._
import cats.effect._
import cats.syntax.functor._
import com.codility.practice.parsing.Parse
import com.codility.practice.parsing.ParseFunctions
import com.codility.practice.solutions.OddOccurrencesInArray
import com.codility.practice.util.DebugUtil
import com.codility.practice.util.DebugUtil.{debug, error, info, log, logIO, logResult, toBinaryString}

import scala.annotation.tailrec
import scala.language.higherKinds

object Runner extends IOApp {

  val InputFile = "src\\main\\resources\\input.txt"
  val ExpectedFile = "src\\main\\resources\\expected.txt"

  //////////////////////////////////////////////////////////////////

  type Input = Array[Int]
  import ParseFunctions.arrayOfNumbers
  import ParseFunctions.singleNumbers
  val solution: Input => Int = OddOccurrencesInArray.solution

  //////////////////////////////////////////////////////////////////

  def run(args: List[String]): IO[ExitCode] = {

    val p = for {
      inputs <- Parse.readData[Input](InputFile)
      outputs <- Parse.readData[Int](ExpectedFile)
      _ <- info(s"Found: [${inputs.size}] tests\n")

      testRuns <- (inputs.zipWithIndex, outputs).zipped.map {
        case ((input, caseNb), output) =>
          for {
            _ <- info(s"Running #${caseNb + 1} ..")

            result <- IO.pure(solution(input))
            _ <- log(input.toList, "INPUT")
            _ <- logResult(result)
            testPass = (result === output)
            _ <- if (testPass) info("SUCCESS\n") else error("FAILED\n")
          } yield testPass
      }.sequence
    } yield testRuns

    p.attempt.flatMap {
      case Left(e) => error(e.getMessage).as(ExitCode.Error)
      case Right(results) =>
        val allTestsCheck = results.fold(true)(_ & _)
        if (allTestsCheck) info("ALL tests SUCCESS").as(ExitCode.Success)
        else error("Some tests FAILED").as(ExitCode.Error)
    }

  }

}



