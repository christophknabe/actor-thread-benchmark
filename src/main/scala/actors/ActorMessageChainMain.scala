package actors

import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox}
import scala.io.StdIn
import scala.concurrent.duration.Duration
import scala.concurrent.duration.TimeUnit
import java.util.concurrent.TimeUnit
import scala.concurrent.Await

/** 
 * Actor message passing benchmark.
 * Requests the user to input a number.
 * Then creates an Actor-message chain with the  given number of Actors, and sends number messages through this chain.
 * Thus this method causes number*number Actor switches.
 * Reports the elapsed time from start of the sending to arrival of the last message at the end of the chain.
 * @author Christoph Knabe, {@linkplain http://public.beuth-hochschule.de/~knabe/ }
 * @since 2016-04-15
 */
object ActorMessageChainMain extends App {

  val input = StdIn.readLine(s"How many actors for the chain? ")
  val number = input.toInt  
  val system = ActorSystem("ActorMessageChain")
  
  var lastActor = system.actorOf(Consumer.props, name = "consumer");
  for (i <- number-1 to 1 by -1) {
    val f = system.actorOf(Forwarder.props(i, lastActor),  s"Forwarder-$i")
    lastActor = f;
  }
  val producer = new Producer(lastActor, number)
  println(s"Chain of $number actors built.");
  private var start = System.currentTimeMillis
  producer.produce()

  def stop() {
    val stop = System.currentTimeMillis
    val elapsed = stop - start
    val switchings = number.toLong * number
    println(s"Scala: $switchings actor switchings used $elapsed ms.");
    system.shutdown()
  }

}
