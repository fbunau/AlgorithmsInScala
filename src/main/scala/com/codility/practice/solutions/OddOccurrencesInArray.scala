package com.codility.practice.solutions

object OddOccurrencesInArray {

  case class Step(current: Int, count: Int, solution: Option[Int])

  def solution(input: Array[Int]): Int = {
    val sortedInput = input.sorted
    val z = Step(sortedInput.head, 1, None)
    input.sorted.tail.foldLeft(z) {
      case (acc@Step(_, _, Some(_)), _) => acc
      case (acc@Step(prev, _, None), current) =>
        if (prev == current) acc.copy(count = acc.count + 1)
        else
          if (acc.count % 2 == 1) Step(current, 1, Some(prev))
          else acc.copy(current = current, count = 1)
    } match {
      case Step(_, _, Some(r)) => r
      case Step(r, _, None) => r
    }
  }

}
