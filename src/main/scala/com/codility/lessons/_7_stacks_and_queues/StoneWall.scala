package com.codility.lessons._7_stacks_and_queues

object StoneWall {

  def solution(h: Array[Int]): Int = {
    h.foldLeft((List.empty[Int], 0)) {
      case ((s, r), c) =>
        val ns = s.dropWhile(_ > c)
        if (ns.isEmpty || ns.head != c) {
          (c :: ns, r + 1)
        }
        else (ns, r)
    }._2
  }

}
