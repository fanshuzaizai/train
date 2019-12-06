package com.github.fanshuzaizai.train.scala.AAA

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author Jiangzy
 * @date 2019/11/21
 */
object BBB {


  def main(args: Array[String]): Unit = {

    Traversable()

    var value1: Map[Int, Int] = Map(1 -> 2)

    value1.get(1)

    var value2: Map[Int, Int] = Map(1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6)


    val value3: mutable.Map[Int, Int] = mutable.HashMap(1 -> 2)
    value3(2) = 3

    value3 += (1 -> 2)

    print()

  }

}
