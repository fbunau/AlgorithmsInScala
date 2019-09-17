package com.algopractice.data_structures

import org.scalatest.{FreeSpec, Matchers}

class SegmentTreeTest  extends FreeSpec with Matchers {

  "Test range minimum queries" in {
    val values = List(1, 2, 8, 4, 5, 1, 2, 9, 7, 7)
    val n = values.length
    val default = 10000

    val emptyTree = SegmentTree.buildTree(0, n, default)

    val tree = values.zipWithIndex.foldLeft(emptyTree) {
      case (tree, (value, index)) =>
        SegmentTree.insert(tree, index, value, Math.min)._1
    }

    SegmentTree.query(tree, 0, 0, Math.min) shouldBe 1
    SegmentTree.query(tree, 0, 1, Math.min) shouldBe 1
    SegmentTree.query(tree, 0, 2, Math.min) shouldBe 1
    SegmentTree.query(tree, 1, 1, Math.min) shouldBe 2
    SegmentTree.query(tree, 1, 2, Math.min) shouldBe 2
    SegmentTree.query(tree, 2, 4, Math.min) shouldBe 4
    SegmentTree.query(tree, 1, n, Math.min) shouldBe 1
    SegmentTree.query(tree, 1, n, Math.min) shouldBe 1
    SegmentTree.query(tree, 7, n, Math.min) shouldBe 7
  }

}
