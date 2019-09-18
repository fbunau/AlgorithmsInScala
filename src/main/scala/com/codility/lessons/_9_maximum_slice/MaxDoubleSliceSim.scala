package com.codility.lessons._9_maximum_slice

object MaxDoubleSliceSim {

  def solution(a: Array[Int]): Int = {
    val x = a.tail.init
    val s1 = maxSumSubArray(x)
    val s2 = maxSumSubArray(x.reverse).reverse

    (s1.init zip s2.tail).map {
      case (s1, s2) =>
        Math.max(
          s1 + s2,
          Math.max(s1, s2))
    }.max
  }

  // MaxSliceSum
  def maxSumSubArray(a: Array[Int]): Array[Int] = {
    a.scanLeft(0) {
      case (best, c) =>
        if (best + c < c) c
        else best + c
    }
  }

}
