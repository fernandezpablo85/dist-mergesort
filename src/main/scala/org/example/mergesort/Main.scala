package org.example.mergesort

import util.Random
import java.util.concurrent.TimeUnit
import annotation.tailrec

object Main extends App {

  val few = (1 to 1000).map(Random.nextInt).toList
  val many = (1 to 5000000).map(Random.nextInt).toList

  Benchmark.time(TimeUnit.MICROSECONDS) {
    Sorter.sort(few)
  }

  // This is slow. About ~35 seconds.
  Benchmark.time(TimeUnit.SECONDS) {
    Sorter.sort(many)
  }

  // Note that our implementation is suboptimal.
  // Scala native order function takes 5-10 seconds to sort the same array.
  Benchmark.time(TimeUnit.SECONDS, disabled = true) {
    many.sortWith(_ < _)
  }
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

  private def merge(left: List[Int], right: List[Int]) = {
    @tailrec
    def loop(left: List[Int], right: List[Int], acc: List[Int]): List[Int] = {
      left match {
        case Nil => acc ::: right
        case _ => {
          right match {
            case Nil => acc ::: left
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
