#### Algorithms in Scala

My playground project for solving algorithm contest / interview problems

Contains a type-checked [Runner](src/main/scala/com/algopractice/Runner.scala) that can read multiple test cases from file and verify the solution logic results
against the expected results (also read from file)

When working on a new problem the section in the runner must be modified to the correct inputs / outputs

For adding or viewing extra formats use: [DataParser](src/main/scala/com/algopractice/DatasetParser.scala) 

Sample:

```scala
 type Input = (Int, Vector[Int])
 type Output = Vector[Int]

 private val inputDatasetParser = SingleNumber_ArrayOfInt
 private val outputDatasetParser = ArrayOfInt

 private val solution: Input => Output = ((k: Int, v: Vector[Int]) => CyclicRotation.solution(v.toArray, k)).tupled.andThen(_.toVector)
```

or 

```scala
  type Input = (Int, Int, Int)
  type Output = Int

  private val inputDatasetParser = IntTuple3
  private val outputDatasetParser = SingleNumber

  private val solution: Input => Output = (FrogJmp.solution _).tupled

  implicit val showTupleInt3 = DebugUtil.tuple3Show[Int, Int, Int]
```

Sample run output :

```text
[INFO] Found: [3] tests

[INFO] Running #1 ..
[INPUT] Vector(3, 2, 6, -1, 4, 5, -1, 2)
[RESULT] 17
[TIME] 10.3946 ms
[INFO] SUCCESS

[INFO] Running #2 ..
[INPUT] Vector(5, 5, 5)
[RESULT] 0
[TIME] 0.1455 ms
[INFO] SUCCESS

[INFO] Running #3 ..
[INPUT] Vector(1, 1, 1)
[RESULT] 0
[TIME] 0.1152 ms
[INFO] SUCCESS

[INFO] ALL tests SUCCESS
```

While working on a solution a useful utility to use (customize) is the test case  [Generator](src/main/scala/com/algopractice/Generator.scala) 

-----------------------------------

There is a strong bias towards solutions to algorithm problems using imperative programming.

My goal is to use as many algorithm problems using pure functions and functional programming

Here are some of my 100% solutions. Only codility so far, but planning to do more.

### 0. Data structures
   - [Segment tree](src/main/scala/com/algopractice/data_structures/SegmentTree.scala)


### 1. Codility
   - Iterations
      + [BinaryGap](src/main/scala/com/codility/lessons/_1_iterations/BinaryGap.scala)
   - Arrays
      + [OddOccurrencesInArray](src/main/scala/com/codility/lessons/_2_arrays/OddOccurrencesInArray.scala)
      + [CyclicRotation](src/main/scala/com/codility/lessons/_2_arrays/CyclicRotation.scala)
   - Time complexity
      + [FrogJmp](src/main/scala/com/codility/lessons/_3_time_complexity/FrogJmp.scala)
      + [PermMissingElem](src/main/scala/com/codility/lessons/_3_time_complexity/PermMissingElement.scala)
      + [TapeEquilibrium](src/main/scala/com/codility/lessons/_3_time_complexity/TapeEquilibrium.scala)
   - Counting Elements
      + [PermCheck](src/main/scala/com/codility/lessons/_4_counting_elements/PermCheck.scala)
      + [FrogRiverOne](src/main/scala/com/codility/lessons/_4_counting_elements/FrogRiverOne.scala)
      + [MaxCounters](src/main/scala/com/codility/lessons/_4_counting_elements/MaxCounters.scala)
      + [MissingInteger](src/main/scala/com/codility/lessons/_4_counting_elements/MissingInteger.scala)
   - Prefix Sums
      + PassingCars
      + [GenomicRangeQuery](src/main/scala/com/codility/lessons/_5_prefix_sums/GenomicRangeQuery.scala)
      + MinAvgTwoSlice
      + CountDiv
   - Sorting
      + [MaxProductOfThree](src/main/scala/com/codility/lessons/_6_sorting/MaxProductOfThree.scala)
      + [Distinct](src/main/scala/com/codility/lessons/_6_sorting/Distinct.scala)
      + Triangle
      + NumberOfDiscIntersections
   - Stacks and Queues
      + [Brackets](src/main/scala/com/codility/lessons/_7_stacks_and_queues/Brackets.scala)
      + [Fish](src/main/scala/com/codility/lessons/_7_stacks_and_queues/Fish.scala)
      + [Nesting](src/main/scala/com/codility/lessons/_7_stacks_and_queues/Nesting.scala)
      + [StoneWall](src/main/scala/com/codility/lessons/_7_stacks_and_queues/StoneWall.scala)
   - Leader
      + Dominator
      + EquiLeader
   - Maximum slice problem
      + MaxProfit
      + [MaxSliceSum](src/main/scala/com/codility/lessons/_9_maximum_slice/MaxSliceSum.scala)
      + [MaxDoubleSliceSum](src/main/scala/com/codility/lessons/_9_maximum_slice/MaxDoubleSliceSim.scala)
