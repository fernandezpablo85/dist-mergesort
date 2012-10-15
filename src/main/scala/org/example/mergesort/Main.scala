package org.example.mergesort

object Main extends App {
  val unsorted = List(1,5,6,1,2,4)
  assert(Sorter.sort(unsorted) == List(1,1,2,4,5,6), "List must be sorted")
}

object Sorter {

  def sort(array: List[Int]): List[Int] = {
    // An array of 1 is already sorted.
    if(array.size <= 1) {
      array
    } else {
      // Split in halves, sort and merge those.
      val (left, right) = array.splitAt(array.size / 2)
      merge(sort(left), sort(right))
    }
  }

  private def merge(left: List[Int], right: List[Int]): List[Int] = left match {
    case Nil => right // If left is empty, then right is sorted.
    case _ => {
      right match {
        case Nil => left // If right is empty then left is sorted.
        case _ =>
          if (left.head < right.head)
            left.head :: merge(left.tail, right)
          else
            right.head :: merge(left, right.tail)
      }
    }
  }
}
