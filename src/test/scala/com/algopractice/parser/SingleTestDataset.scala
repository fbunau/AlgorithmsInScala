package com.algopractice.parser

import org.scalatest.prop.TableDrivenPropertyChecks

object SingleTestDataset extends TableDrivenPropertyChecks {

  val ArrayTestData = Table(
    ("input", "expected"),
    ("{}", Vector.empty[Int]),
    ("{ }", Vector.empty[Int]),
    ("{1 2 3}", Vector(1, 2, 3)),
    ("{1  2 3 }", Vector(1, 2, 3)),
    ("{ 1    2  3 }", Vector(1, 2, 3)),
    ("{-1 -2 -3}", Vector(-1, -2, -3)),
    ("{-1  -2 -3 }", Vector(-1, -2, -3)),
    ("{ -1    -2  -3 }", Vector(-1, -2, -3))
  )

  val NumberAndArrayTestData = Table(
    ("input", "expected"),
    ("2{}", (2, Vector.empty[Int])),
    ("2 {}", (2, Vector.empty[Int])),
    ("11 { }", (11, Vector.empty[Int])),
    ("33 {1 2 3}", (33, Vector(1, 2, 3))),
    ("5{1 2 3 }", (5, Vector(1, 2, 3))),
    ("0 { 1    2  3 }", (0, Vector(1, 2, 3))),
    ("-2{}", (-2, Vector.empty[Int])),
    ("-2 {}", (-2, Vector.empty[Int])),
    ("-11 { }", (-11, Vector.empty[Int])),
    ("-33 {-1 -2 -3}", (-33, Vector(-1, -2, -3))),
    ("-5{-1 -2 -3 }", (-5, Vector(-1, -2, -3))),
    ("0 { -1    -2  -3 }", (0, Vector(-1, -2, -3)))
  )

  val ComplexFormatTestData = Table(
    ("input", "expected"),
    (("abc { 1 2 3 } {6 7 8}"), ("abc", Vector(1, 2, 3), Vector(3, 4, 5))),
    (("efg{11 22 33} { 33  44    55}"), ("efg", Vector(11, 22, 33), Vector(33, 44, 55))),
  )

}
