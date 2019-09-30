package com.github.fanshuzaizai.interview.scala

/**
  * @author JiangZY
  * @date Created in 2019/9/8
  */
class Cat(name: String) {

  def sayHello: Unit = {
    println(name)
  }

}


object Cat {

  val name = "Tom"

  def apple: Cat = {
    new Cat(name)
  }

}
