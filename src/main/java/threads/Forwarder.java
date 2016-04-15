package threads;

/** 
 * The Forwarder is the most important type of the inner nodes of the Thread chain.
 * The message passing between the Producer and the first Forwarder, between two adjacent Forwarders, 
 * and between the last Forwarder and the Consumer takes place by using a synchronized Buffer 
 * according to the Java Tutorial, Trail Essential Java Classes,
 * Lesson Threads: Doing Two or More Tasks At Once, The Producer/Consumer Example.
 * Findable for example at {@linkplain http://www.eng.nene.ac.uk/~gary/javatut/essential/threads/synchronization.html}
 */
public class Forwarder extends Thread {

    private final Buffer inputBuffer;
    private final Buffer outputBuffer;

    public Forwarder(final Buffer inputBuffer, final Buffer outputBuffer) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    public void run() {
        try {
			for (;;) {
			    final int value = inputBuffer.get();
			    outputBuffer.put(value);
			    //System.out.println("Forwarder passed: " + value);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(getClass().getSimpleName() + " interrupted", e);
		}
    }
}
