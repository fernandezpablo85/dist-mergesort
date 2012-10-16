package org.example.mergesort

import util.Random

object Main extends App {

  private val BigArray = (1 to 1000000).map(Random.nextInt).toList
  private val SmallArray = (1 to 500).map(Random.nextInt).toList

  println("Enter a sequence of numbers to sort or use 'small' or 'big' for predefined arrays.")
  println("Type 'exit' to quit.")

  for(line <- io.Source.stdin.getLines()) {
    line match {
      case "exit" => System.exit(0)
      case "big" => sortInts(BigArray)
      case "small" => sortInts(SmallArray)
      case s: String if (s.split(",").size > 1) => sortChars(s.split(","))
      case other => System.err.println("[error] Can't sort: " + other)
    }
  }

  def sortInts(list: List[Int]) = {
    val size = list.size //size is O(n) for list so we better cache it.
    println("about to sort %s elements...".format(size))
    val sorted = Sorter.sort(list)
    println("sorted %s elements!".format(size))
    if (size <= 500) println(sorted.mkString("(",",",")")) else println("(list too big to display)")
  }

  def sortChars(list: Array[String]) = try {
    sortInts(list.map(Integer.parseInt).toList)
  } catch {
    case e: NumberFormatException => println("[error] Cant sort: " + list.mkString(","))
  }
}