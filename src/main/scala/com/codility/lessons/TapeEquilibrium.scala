package com.codility.lessons

object TapeEquilibrium {

  def solution(a: Array[Int]): Int = {
    val s = a.sum
    val sumToLeft = a.scanLeft(0)(_ + _).tail
    val z = diff(sumToLeft.head, s-sumToLeft.head)
    sumToLeft.tail.init.foldLeft(z) {
      case (best, sumToLeft) =>
        val sumToRight = s - sumToLeft
        val candidate = diff(sumToLeft, sumToRight)
        if (best > candidate) candidate
        else best
    }
  }

  def diff(left: Int, right: Int): Int = Math.abs(left - right)

}
