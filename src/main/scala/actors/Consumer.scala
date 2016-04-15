package actors

import akka.actor.{ Actor, Props }
import akka.actor.PoisonPill

object Consumer {
  /**Props creation by recommendation on http://doc.akka.io/docs/akka/current/scala/actors.html#Recommended_Practices */
  def props: Props = Props(classOf[Consumer])
}

class Consumer extends Actor {

  def receive = {
    case value: Int =>
      //println("Consumer got: " + value)
      if (value < 0) {
        //println("Consumer stops.")
        self ! PoisonPill
        ActorMessageChainMain.stop()
      }
  }

}
