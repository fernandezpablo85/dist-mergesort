package org.example.mergesort

import annotation.tailrec

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

  def merge(left: List[Int], right: List[Int]) = {
    @tailrec def loop(left: List[Int], right: List[Int], acc: List[Int]): List[Int] = {
      left match {
        case Nil => acc.reverse ::: right
        case _ => {
          right match {
            case Nil => acc.reverse ::: left
            case _ =>
              if (left.head < right.head)
                loop(left.tail, right, left.head :: acc)
              else
                loop(left, right.tail, right.head :: acc)
          }
        }
      }
    }
    loop(left, right, Nil)
  }
}
