package com.codility.lessons._4_counting_elements

object MissingInteger {

  def solution(a: Array[Int]): Int = {
    a.sorted.distinct.foldLeft(0)(
      (prev, e) =>
        if (prev + 1 == e) e
        else prev
    ) + 1
  }

}
