package com.codility.lessons

/*
  type Input = (Int, Int, Int)
  type Output = Int

  private val inputDatasetParser = IntTuple3
  private val outputDatasetParser = SingleNumber

  private val solution: Input => Output = (FrogJmp.solution _).tupled

  implicit val showTupleInt3 = DebugUtil.tuple3Show[Int, Int, Int]
 */
object FrogJmp {

  def solution(x: Int, y: Int, d: Int): Int = {
    val l = (y - x)
    val full = l / d
    val half = if (l % d > 0) 1 else 0
    full + half
  }

}
