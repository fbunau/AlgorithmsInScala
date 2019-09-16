package com.algopractice.util

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

  def findLast[A](la: Seq[A])(f: A => Boolean): Option[A] = {
    la.foldLeft(Option.empty[A]) { (acc, cur) =>
      if (f(cur)) Some(cur)
      else acc
    }
  }

  def adjust[A, B](m: Map[A, B], k: A)(f: B => B): Map[A, B] = m.updated(k, f(m(k)))

}
