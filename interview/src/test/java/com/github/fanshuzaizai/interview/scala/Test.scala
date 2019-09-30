package com.github.fanshuzaizai.interview.scala

import scala.concurrent.Future

/**
  * @author JiangZY
  * @date Created in 2019/9/7
  */
object Test {

  def method(h: House[Father]): Unit = {
    println(h.getClass)
  }

  def main(args: Array[String]): Unit = {

    List(1, 2)

    val father = new Father()
    val son = new Son()

    val fatherHouse: House[Father] = new House[Father]()
    val sonHouse: House[Son] = new House[Son]()

    method(fatherHouse)
    method(sonHouse)

    val inclusive: Seq[Int] = 1 to 5

    import scala.concurrent.ExecutionContext.Implicits.global

    val unit = Future.unit
    print(unit)
    println(unit.isCompleted)

    val future: Future[Int] = Future {
      println(12)
      12
    }
    println(1024 * 1.5)

  }

}
