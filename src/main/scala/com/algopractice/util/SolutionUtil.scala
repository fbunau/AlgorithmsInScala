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

  // Hasn't been useful yet, but nice to have

  case class Zipper[A](current: A, left: Seq[A], right: Seq[A]) {
    def next: Zipper[A] = if (atEnd) this else Zipper(right.head, left :+ current, right.tail)
    def prev: Zipper[A] = if (atStart) this else Zipper(left.last, left.init, current +: right)

    def atStart: Boolean = left.isEmpty
    def atEnd: Boolean = right.isEmpty
  }

  @tailrec
  def foldLeft[A, B](as: Zipper[A])(z: B)(f: (B, Zipper[A]) => B): B = {
    if (as.atEnd) f(z, as)
    else {
      val (head, tail) = (as, as.next)
      foldLeft(tail)(f(z, head))(f)
    }
  }

  def foldRight[A, B](as: Zipper[A])(z: B)(f: (Zipper[A], B) => B): B = {
    if (as.atEnd) f(as, z)
    else {
      val (head, tail) = (as, as.next)
      f(head, foldRight(tail)(z)(f))
    }
  }

}
