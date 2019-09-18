package com.algopractice

import java.io.{File, PrintWriter}

import org.scalacheck.Gen
import org.scalacheck.Prop.{collect, forAll}
import org.scalacheck.util.Pretty.Params
import org.scalacheck.Test.{Parameters, check}


object Generator {

  // A quick hack to generate some random data to check for bound issues

  private val RandomInputFile = "src/main/resources/random_input.txt"
  private val RandomExpectedFile = "src/main/resources/random_expected.txt"

  val MIN_K = 0
  val MAX_K = 100

  val MIN_N = 1000000
  val MAX_N = 1000000

  val MIN_A = -1000000
  val MAX_A = 1000000

  def randomNumber: Gen[Int] = Gen.frequency(
    (5, Gen.choose(MIN_K, MIN_K)),
    (5, Gen.choose(MAX_K, MAX_K)),
    (90, Gen.choose(MIN_K, MAX_K)),
  )

  def randomSizedIntList: Gen[List[Int]] = for {
    n <- Gen.frequency(
      (5, Gen.choose(MIN_N, MIN_N)),
      (5, Gen.choose(MAX_N, MAX_N)),
      (90, Gen.choose(MIN_N, MAX_N)),
    )
    l <- Gen.listOfN(n,
      Gen.frequency(
        (5, Gen.choose(MIN_A, MIN_A)),
        (5, Gen.choose(MAX_A, MAX_A)),
        (90, Gen.choose(MIN_A, MAX_A)),
      )
    )
  } yield l

  def main(args: Array[String]): Unit = {
    val ri = new PrintWriter(new File(RandomInputFile))
    val re = new PrintWriter(new File(RandomExpectedFile))

    val p = forAll(randomNumber, randomSizedIntList) { (n, a) =>

      val arrayStr = a.mkString(" ")

//      ri.write(n.toString)
//      ri.write(" ")

      ri.write("{ ")
      ri.write(arrayStr)
      ri.write(" }\n")

      ///

      re.write(0.toString)
      re.write("\n")

      ///

      val stats = {
        if (a.length == MIN_N) "min"
        else if (a.length == MAX_N) "max"
        else "other"
      }

      collect(stats)(true)
    }

//    val p2 = forAll(Gen.listOfN(MAX_N, Gen.const('('))) { c =>
//      ri.write(c.mkString(""))
//      ri.write("\n")
//
//      //
//
//      re.write(0.toString)
//      re.write("\n")
//      true
//    }

    check(
      Parameters.default.withMinSuccessfulTests(1),
      p
    )

    ri.close()
    re.close()
  }

}
