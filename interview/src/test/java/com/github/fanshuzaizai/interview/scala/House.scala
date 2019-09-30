package com.github.fanshuzaizai.interview.scala

/**
  * @author JiangZY
  * @date Created in 2019/9/7
  */
class House[+T] {

  def say[X >: T](t: X): Unit = {
    println("123")
  }

}

class Father {

}


class Son extends Father {

}

