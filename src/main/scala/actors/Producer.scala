package actors

import akka.actor.{ Actor, ActorRef }
import scala.util.Random

class Producer(outputActor: ActorRef, quantity: Int) {
  def produce() {
    for (i <- 1 to quantity) {
      outputActor ! i
    }
    outputActor ! -1
  }
}
