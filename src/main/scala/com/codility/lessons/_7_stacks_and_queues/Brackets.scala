package com.codility.lessons._7_stacks_and_queues

object Brackets {

  def solution(s: String): Int = {

    val pairs = List(
      ('(', ')'),
      ('[', ']'),
      ('{', '}')
    )

    val openBrackets = pairs.map(_._1)

    s.foldLeft((List.empty[Char], true)) {
      case ((s, true), c) if openBrackets.contains(c) =>
        (c :: s, true)
      case ((h :: t, true), c) =>
        (t, pairs.contains((h, c)))
      case ((s, _), _) =>
        (s, false)
    } match {
      case (s, true) if s.isEmpty => 1
      case _ => 0
    }

  }

}
