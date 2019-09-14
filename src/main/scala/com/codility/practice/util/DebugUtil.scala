package com.codility.practice.util

import cats.syntax.show._
import cats.syntax.apply._
import cats.Show
import cats.effect.IO
import com.codility.practice.util.SolutionUtil.binaryFoldRight

object DebugUtil {

  def info[A]: A => IO[Unit] = log(_, header = "INFO")

  def debug[A]: A => IO[Unit] = log(_, header = "DEBUG")

  def logResult[A]: A => IO[Unit] = log(_, header = "RESULT")

  def error[A]: A => IO[Unit] = log(_, header = "ERROR")

  def log[A](x: A, header: String = ""): IO[Unit] = IO {
    println(s"[$header] $x")
  }

  def logIO[A](xIO: IO[A], header: String = ""): IO[A] = for {
    x <- xIO
    _ <- IO { log(x, header) }
  } yield x


  /// Boolean ops

  implicit val showBoolVector: Show[Vector[Boolean]] = new Show[Vector[Boolean]] {
    override def show(t: Vector[Boolean]): String = {
      t.map(if (_) "1" else "0").mkString
    }
  }


  def toBinaryString(n: Int): String = {
    val l = binaryFoldRight((Vector[Boolean](), n)) {
      case (a, acc) =>
        acc :+ a
    }._1

    l.show.reverse
  }


}
