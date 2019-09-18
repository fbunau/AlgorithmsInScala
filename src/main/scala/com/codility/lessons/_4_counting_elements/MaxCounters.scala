package com.codility.lessons._4_counting_elements

import com.algopractice.util.SolutionUtil.adjust

object MaxCounters {

  def solution(n: Int, a: Array[Int]): Array[Int] = {
    def defaultMap(x: Int) = Map.empty[Int, Int].withDefaultValue(x) + (1 -> x)

    val counters = a.foldLeft(defaultMap(0)) {
      case (m, e) =>
        if (e == n + 1) defaultMap(m.values.max)
        else adjust(m, e)(_ + 1)
    }
    Array.tabulate(n)(i => counters(i + 1))
  }

}
