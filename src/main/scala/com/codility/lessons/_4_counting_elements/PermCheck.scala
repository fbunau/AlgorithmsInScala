package com.codility.lessons._4_counting_elements

object PermCheck {

  def solution(a: Array[Int]): Int = {
    val bs = a.foldLeft(Set.newBuilder[Int])((b, i) => b += i).result
    if ((1 to a.length).forall(bs.contains)) 1 else 0
  }

}
