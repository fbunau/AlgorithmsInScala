package com.algopractice.data_structures

trait SegmentTree[A]

case class SegmentNode[A](left: Int, right: Int, value: A, leftChild: SegmentTree[A], rightChild: SegmentTree[A]) extends SegmentTree[A]
case class SegmentLeaf[A](index: Int, value: A) extends SegmentTree[A]

object SegmentTree {

  private def mid(left: Int, right: Int): Int = (left + right) / 2

  def buildTree[T](left: Int, right: Int, value: T): SegmentTree[T] = {
    if (left != right)
      SegmentNode(
        left, right, value,
        buildTree(left, mid(left, right), value),
        buildTree(mid(left, right) + 1, right, value)
      )
    else
      SegmentLeaf(left, value)
  }

  def insert[T](root: SegmentTree[T], index: Int, value: T, combine:(T, T) => T): (SegmentTree[T], T) = root match {
    case SegmentNode(left, right, prevValue, leftChild, rightChild) =>
      if (index <= mid(left, right)) {
        val (updatedLeftChild, leftValue) = insert(leftChild, index, value, combine)
        val newValue = combine(prevValue, leftValue)

        (SegmentNode(left, right, newValue, updatedLeftChild, rightChild), newValue)
      }
      else {
        val (updatedRight, rightValue) = insert(rightChild, index, value, combine)
        val newValue = combine(prevValue, rightValue)

        (SegmentNode(left, right, newValue, leftChild, updatedRight), combine(prevValue, newValue))
      }

    case SegmentLeaf(leafIndex, _) => (SegmentLeaf(leafIndex, value), value)
  }

  def query[T](root: SegmentTree[T], i: Int, j: Int, combine:(T, T) => T): T = root match {
    case SegmentNode(left, r, value, leftChild, rightChild) =>
      if (left == i & j == r) value
      else if (j <= mid(left, r))
        query(leftChild, i, j, combine)
      else if (i > mid(left, r))
        query(rightChild, i, j, combine)
      else
        combine(
          query(leftChild, i, mid(left, r), combine),
          query(rightChild, mid(left, r) + 1, j, combine)
        )

    case SegmentLeaf(_, value) => value
  }

}