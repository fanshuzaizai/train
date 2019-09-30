package com.github.fanshuzaizai.interview.scala.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.reflect.ClassTag

/**
  * @author JiangZY
  * @date Created in 2019/9/8
  */
class MyActor extends Actor {

  override def receive: Receive = {
    case s: String => println("hello" + s)
    case 0 => {
      println("closing...")
      context.stop(self)
      context.system.terminate()
    }
  }
}

object Test {

  def test[T: ClassTag](): Unit = {

  }

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()
    val actor_1: ActorRef = actorSystem.actorOf(Props[MyActor], "A")
    //    val actor_2 = actorSystem.actorOf(Props[MyActor])

    actor_1 ! "123"
    actor_1 ! 2
    actor_1 ! 0


  }

}
