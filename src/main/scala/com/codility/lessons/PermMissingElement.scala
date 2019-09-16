package com.codility.lessons

object PermMissingElement {

  def solution(a: Array[Int]): Int = {
    if (a.length == 0) return 1
    val x = (1 to (a.length + 1)).reduce(_ ^ _)
    val y = a.reduce(_ ^ _)
    x ^ y
  }

}
