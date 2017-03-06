package com.example.main

import com.example.other.{InternalDependencyOne, InternalDependencyTwo}
import com.twitter.concurrent.Broker
import com.twitter.util.TimeFormat


object ManyDependencies {
  def main(args: Array[String]): Unit = {
    val internalDependencyObj1 = new InternalDependencyOne
    internalDependencyObj1.doSomething()
    val internalDependencyObj2 = new InternalDependencyTwo
    internalDependencyObj2.doSomething()
    val broker = new Broker[Int]
    println(broker)
    val b = new TimeFormat("yyyy/mm/dd")
    println (b)
  }
}
