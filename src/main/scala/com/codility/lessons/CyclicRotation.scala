package com.codility.lessons

/*
 type Input = (Int, Vector[Int])
 type Output = Vector[Int]

 private val inputDatasetParser = SingleNumber_ArrayOfInt
 private val outputDatasetParser = ArrayOfInt

 private val solution: Input => Output = ((k: Int, v: Vector[Int]) => CyclicRotation.solution(v.toArray, k)).tupled.andThen(_.toVector)
 */
object CyclicRotation {

  def solution(a: Array[Int], k: Int): Array[Int] = {
    rotate(a.toVector, k).toArray
  }

  private def rotate[A](l: Vector[A], pos: Int): Vector[A] = {
    val k = indexRight(pos, l.size)
    val (front, back) = l.splitAt(k)
    back ++ front
  }

  private def indexRight(i: Int, n: Int): Int = {
    if (n == 0) 0
    else {
      val ii = i % n
      (n - ii) % n
    }
  }

}
