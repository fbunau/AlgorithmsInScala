package com.algopractice.util

import cats.effect.IO
import cats.syntax.show._
import cats.Show
import cats.instances.string.catsStdShowForString
import com.algopractice.util.SolutionUtil.binaryFoldRight

object DebugUtil {

  def info[A: Show](a: A): IO[Unit] = log(a, header = "INFO")

  def debug[A: Show](a: A): IO[Unit] = log(a, header = "DEBUG")

  def logResult[A: Show](a: A): IO[Unit] = log(a, header = "RESULT")

  def error[A: Show](a: A): IO[Unit] = log(a, header = "ERROR")

  def log[A: Show](x: A, header: String = ""): IO[Unit] = IO {
    println(show"[$header] $x")
  }

  def logIO[A: Show](xIO: IO[A], header: String = ""): IO[A] = for {
    x <- xIO
    _ <- IO { log(x, header) }
  } yield x

  def logPerformance(startTime: Long, endTime: Long): IO[Unit] = {
    log(s"${(endTime - startTime) / 1000000.0} ms", header = "TIME")
  }

  def throwableStringStackTrace(t: Throwable): String = {
    import java.io.ByteArrayOutputStream
    import java.io.PrintStream

    val out = new ByteArrayOutputStream
    t.printStackTrace(new PrintStream(out))

    new String(out.toByteArray)
  }

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

  /// Show

  def tuple3Show[A, B, C] = new Show[(A, B, C)] {
    override def show(t: (A, B, C)): String = {
      t.toString
    }
  }

  // easier println

  def ppa[A](a: Array[A]): Unit = {
    println(a.toList.toString)
  }

  def pp(a: Any): Unit = {
    println(a.toString)
  }

}
