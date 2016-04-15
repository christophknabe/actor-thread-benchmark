package actors

import akka.actor.{ Actor, ActorRef, Props }
import akka.actor.PoisonPill


object Forwarder {
  def props(number: Int, outputActor: ActorRef): Props =
    Props(classOf[Forwarder], number, outputActor) //untyped argument passing
    /*Props creation by recommendation on http://doc.akka.io/docs/akka/current/scala/actors.html#Recommended_Practices 
     * should work, but produces compile error: Macro has not been expanded.*/
    //Props(new Forwarder(number, outputActor)) //typed argument passing

}

/**Forwards each message of type Int to the referenced outputActor.*/
class Forwarder(number: Int, outputActor: ActorRef) extends Actor {

  if(self.path == outputActor.path){
    throw new IllegalArgumentException(s"Illegal circular reference of actor $self to output $outputActor")
  }
  
  def receive = {
    case value: Int =>
      outputActor ! value
      //println(s"${self.path.name} passes $value to ${outputActor.path.name}")
      if (value < 0) {
        //println(s"Forwarder $number stops.")
        self ! PoisonPill
      }
  }

}
