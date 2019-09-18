package com.codility.lessons._7_stacks_and_queues

object Nesting {

  def solution(s: String): Int = {
    s.foldLeft((List.empty[Char], true)) {
      case ((s, true), '(')  => ('(' :: s, true)
      case (('(' :: t, true), ')') => (t, true)
      case ((s, _), _) => (s, false)
    } match {
      case (s, true) if s.isEmpty => 1
      case _ => 0
    }
  }

}
