package com.codility.practice.parsing

import cats.effect.IO
import cats.syntax.apply._
import cats.syntax.traverse._
import cats.instances.list._
import cats.instances.option._
import com.codility.practice.parsing.ParseFunctions.ParseFunction

import scala.io.Source

object Parse {

  def readData[A](filePath: String)(implicit parseF: ParseFunction[A]): IO[List[A]] = {
    val source = Source.fromFile(filePath)

    parseF(source.getLines) match {
      case Some(x) =>
        IO(source.close) *> IO.pure(x)
      case None =>
        IO.raiseError[List[A]](new Exception(s"Failed to parse data from: $filePath"))
    }
  }

}

object ParseFunctions {
  type ParseFunction[TestInput] = Iterator[String] => Option[List[TestInput]]

  implicit val singleNumbers: ParseFunction[Int] = _.toList.map(_.trim).traverse(x => Option(Integer.parseInt(x)))

  implicit val arrayOfNumbers: ParseFunction[Array[Int]] = _.toList.map(_.trim).map(_.split(" ")).map(
    _.toList.map(x => Option(Integer.parseInt(x))).sequence
  ).sequence.map(_.map(_.toArray))
  
}