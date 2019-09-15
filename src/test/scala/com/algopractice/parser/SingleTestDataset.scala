package com.algopractice.parser

import org.scalatest.prop.TableDrivenPropertyChecks

object SingleTestDataset extends TableDrivenPropertyChecks {

  val singleArrayTestData = Table(
    ("input", "expected"),
    ("{}", Vector.empty[Int]),
    (
      """{
        |}""".stripMargin,
      Vector.empty[Int]
    ),
    ("{ }", Vector.empty[Int]),
    ("{1,2,3}", Vector(1, 2, 3)),
    ("{1,2,3 }", Vector(1, 2, 3)),
    ("{ 1, 2, 3 }", Vector(1, 2, 3)),

    (
      """{
        |  1, 2, 3
        |}""".stripMargin, Vector(1, 2, 3)),
    (
      """{
        |  1, 2,
        |3
        |}""".stripMargin, Vector(1, 2, 3))
  )

  val singleNumberAndArrayTestData = Table(
    ("input", "expected"),
    ("2{}", (2, Vector.empty[Int])),
    ("2 {}", (2, Vector.empty[Int])),
    (
      """3
        |{
        |}""".stripMargin,
      (3, Vector.empty[Int])
    ),
    ("11 { }", (11, Vector.empty[Int])),
    ("33 {1,2,3}", (33, Vector(1, 2, 3))),
    ("5{1,2,3 }", (5, Vector(1, 2, 3))),
    ("0 { 1, 2, 3 }", (0, Vector(1, 2, 3))),

    (
      """7
        |{
        |  1, 2, 3
        |}""".stripMargin, (7, Vector(1, 2, 3))),
    (
      """6 {
        |  1, 2,
        |3
        |}""".stripMargin, (6, Vector(1, 2, 3)))
  )

}
