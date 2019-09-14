package com.codility.practice.util

object SolutionUtil {

  import scala.annotation.tailrec

  @tailrec
  def binaryFoldRight[A](z: (A, Int))(f: (Boolean, A) => A): (A, Int) = z match {
    case (_, 0) => z
    case (r, n) =>
      val d = (n & 1) == 1
      val newR = f(d, r)
      val newN = n >> 1
      binaryFoldRight((newR, newN))(f)
  }

}
