package com.algopractice.util

import cats.effect.IO
import cats.syntax.show._
import cats.{Eq, Show}
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

  def tuple3Show[A] = new Show[(A, A, A)] {
    override def show(t: (A, A, A)): String = {
      t.toString
    }
  }

}
