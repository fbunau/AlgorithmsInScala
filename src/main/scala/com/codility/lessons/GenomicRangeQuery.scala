package com.codility.lessons

import com.algopractice.data_structures.SegmentTree

object GenomicRangeQuery {

  def solution(s: String, p: Array[Int], q: Array[Int]): Array[Int] = {
    val default = 5
    val emptyTree = SegmentTree.buildTree(0, s.length, default)

    val genome = "ACGT"

    val numericDNA = s.map(genome.indexOf(_) + 1)

    val tree = numericDNA.zipWithIndex.foldLeft(emptyTree) {
      case (tree, (value, index)) =>
        SegmentTree.insert(tree, index, value, Math.min)._1
    }

    p.zip(q).map {
      case (i, j) => SegmentTree.query(tree, i, j, Math.min)
    }

  }

}
