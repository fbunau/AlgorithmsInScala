package com.codility.lessons

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
