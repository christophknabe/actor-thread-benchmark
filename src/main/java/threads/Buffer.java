package threads;

public class Buffer {
	
    private int value;
    private boolean available = false;

    public synchronized int get() throws InterruptedException {
        while (!available) {
        	wait();
        }
        available = false;
        notifyAll();
        return value;
    }

    public synchronized void put(final int value) throws InterruptedException {
        while (available) {
            wait();
        }
        this.value = value;
        available = true;
        notifyAll();
    }
    
}
