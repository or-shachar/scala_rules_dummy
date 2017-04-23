package com.example.main

import java.io.File

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.example.DirTree

@RunWith(classOf[JUnit4])
class DirTreeTest {

  @Test
  def passingTest(): Unit = {
    println(DirTree.calculate(new File(".")))
  }
}