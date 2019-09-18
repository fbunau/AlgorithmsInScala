package com.codility.lessons._3_time_complexity

object FrogJmp {

  def solution(x: Int, y: Int, d: Int): Int = {
    val l = (y - x)
    val full = l / d
    val half = if (l % d > 0) 1 else 0
    full + half
  }

}
