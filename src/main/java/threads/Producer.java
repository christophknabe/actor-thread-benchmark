package threads;

public class Producer extends Thread {
	
    private final Buffer buffer;
    private final int numberOfItems;

    public Producer(final Buffer cubbyHole, final int numberOfItems) {
        this.buffer = cubbyHole;
        this.numberOfItems = numberOfItems;
    }

    public void run() {
        try {
			for (int i = 0; i <= numberOfItems; i++) {
				final int msg = i<numberOfItems ? i : -1;
			    buffer.put(msg);
			    //System.out.println("Producer put: " + msg);
		        //sleep((int)(Math.random() * 100));
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(getClass().getSimpleName() + " interrupted", e);
		}
    }

}
