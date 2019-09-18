package com.codility.lessons

object MaxProductOfThree {

  def solution(a: Array[Int]): Int = {
    val as = a.sorted
    val x = as.take(3) ++ as.takeRight(Math.min(3, as.length - 3))
    x.combinations(3).map(_.product).max
  }

}
