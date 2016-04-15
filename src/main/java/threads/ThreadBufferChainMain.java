package threads;

import java.io.IOException;


/** 
 * Thread buffer synchronization benchmark.
 * @author Christoph Knabe, {@linkplain http://public.beuth-hochschule.de/~knabe/ }
 * @since 2016-04-15
 */
public class ThreadBufferChainMain {
	
    public static void main(final String[] args) throws InterruptedException, IOException {
    	for(int chainLenghth=10; chainLenghth<20000; chainLenghth*=10){
        	_benchmarkThreadBufferChain(chainLenghth);
    	}
        System.exit(0);
    }

    /**
     * Creates a Thread-Buffer chain with the given number of threads, and sends number messages through this chain.
     * Thus this method causes number*number thread switches.
     * Reports the elapsed time from start of the sending to arrival of the last message at the end of the chain.
     * @param number Number of threads in the Thread-Buffer chain
     */
	private static void _benchmarkThreadBufferChain(final int number)	throws InterruptedException {
		Buffer lastBuf = new Buffer();
        final Consumer consumer = new Consumer(lastBuf);
        consumer.start();
        for(int i=3; i<=number; i++){
          final Buffer newBuf = new Buffer();
          final Forwarder f = new Forwarder(newBuf, lastBuf);
          f.start();
          lastBuf = newBuf;
        }
        final Producer producer = new Producer(lastBuf, number);
        System.out.println("Chain of " + number + " threads built.");
    	final long start = System.currentTimeMillis();
        producer.start();
        consumer.join();
        final long stop = System.currentTimeMillis();
        final long elapsed = stop - start;
        System.out.println("Java: " + 1L*number*number + " thread switchings used " + elapsed + " ms.");
	}
    
}
