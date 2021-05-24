// Luis
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    Buffer buffer;
    boolean isRunning = true;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        SchemeOp product;
        // IF available space Compute and sent to buffer
        while (this.isRunning) {
            product = this.buffer.consume();

            if (product != null) {
                SchemeOp.compute(product);
                Buffer.print("Consumer Consumed ID -> " + product.getID() + " Result -> " + product.getResult());
                GUIFrame.tableComplete(product.getID(), product.getOperation(), product.getResult());
            } else {

                try {
                    Thread.sleep(this.buffer.timeProducer);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }

                stopThread();
            }
        }
    }

    // STOP THREAD 
    public void stopThread() {
        this.isRunning = false;
    }
}
