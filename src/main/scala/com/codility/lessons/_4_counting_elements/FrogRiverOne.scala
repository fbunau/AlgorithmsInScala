package com.codility.lessons._4_counting_elements

import scala.collection.BitSet

object FrogRiverOne {

  case class Step(remaining: Int, seen: BitSet, last: Int)

  def solution(x: Int, a: Array[Int]): Int = {
    val z = Step(x, BitSet(), -1)

    a.zipWithIndex.foldLeft(z) {
      case (acc@Step(t, seen, last), (current, index)) =>
        if (t == 0) acc
        else
          if (!seen.contains(current) && current <= x) Step(t - 1, seen + current, index)
          else Step(t, seen, last)
    } match {
      case Step(0, _, last) => last
      case _ => -1
    }

  }

}
