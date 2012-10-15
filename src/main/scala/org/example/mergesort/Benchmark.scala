package org.example.mergesort

import java.util.concurrent.TimeUnit

object Benchmark {

  def time(unit: TimeUnit = TimeUnit.MICROSECONDS, disabled: Boolean = false)(block : => Unit) {
    if (disabled) return
    val before = System.nanoTime
    block
    println("[time] " + unit.convert(System.nanoTime - before, TimeUnit.NANOSECONDS) + " " + unit)
  }
}
