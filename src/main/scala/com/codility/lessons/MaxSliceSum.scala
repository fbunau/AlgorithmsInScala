package com.codility.lessons

object MaxSliceSum {

  val NegInf: Int = -2000000

  def solution(a: Array[Int]): Int = {
    a.scanLeft(NegInf) {
      case (best, c) =>
        if (best + c < c) c
        else best + c
    }.max
  }

}
