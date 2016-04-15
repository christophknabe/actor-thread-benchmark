package threads;

public class Consumer extends Thread {
	
    private final Buffer buffer;

    public Consumer(final Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
			for (;;) {
			    final int value = buffer.get();
			    //System.out.println("Consumer got: " + value);
			    if(value<0){
			    	//System.out.println("Consumer stops.");
			    	break;
			    }
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(getClass().getSimpleName() + " interrupted", e);
		}
    }
}
