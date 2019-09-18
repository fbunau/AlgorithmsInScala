package com.codility.lessons._7_stacks_and_queues

object Fish {

  case class Fish(size: Int, direction: Int)
  val Upstream = 0
  val Downstream = 1

  def solution(a: Array[Int], b: Array[Int]): Int = {
    val (stream, survived) = a.zip(b).map(Fish.tupled).foldLeft((List.empty[Fish], 0)) {
      case ((stream, survived), c@Fish(_, Downstream)) => (c :: stream, survived)
      case ((stream, survived), Fish(size, Upstream)) =>
        val newStream = stream.dropWhile(_.size < size)
        if (newStream.isEmpty) (List(), survived + 1)
        else (newStream, survived)
    }
    stream.length + survived
  }

}
