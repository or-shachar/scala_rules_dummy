package com.example.main

import com.example.other.InternalDependencyOne
import com.twitter.util.Promise

object OneDependency {
  def main(args: Array[String]): Unit = {
    val internalDependencyObj = new InternalDependencyOne
    internalDependencyObj.doSomething()
    val a = new Promise[Int]
    println(a)
  }
}
