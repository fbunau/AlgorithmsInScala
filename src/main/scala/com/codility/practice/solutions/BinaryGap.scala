package com.codility.practice.solutions

import scala.collection.JavaConverters._

import com.codility.practice.util.SolutionUtil.binaryFoldRight

object BinaryGap {

  case class Step(best: Int, current: Int, counting: Boolean)

  def solution(input: Int): Int = {
    val z = Step(0, 0, counting = false)
    binaryFoldRight(z, input) {
      case (a, acc) =>
        if (a)
          acc.copy(Math.max(acc.best, acc.current), 0, counting = true)
        else
        if (acc.counting) acc.copy(current = acc.current + 1)
        else acc
    }._1.best
  }

}
