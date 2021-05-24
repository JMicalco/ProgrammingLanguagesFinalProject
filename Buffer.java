// Adolfo
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {

    private SchemeOp[] buffer;
    static int contConsumer;
    static int contProducer;
    public int bufferSize;
    public int timeConsumer;
    public int timeProducer; //
    static int id;
    static int count = 1;
    static Random random = new Random();

    Buffer(int maxSize, int maxTime1, int maxTime2) {
        this.buffer = new SchemeOp[maxSize];
        this.bufferSize = maxSize;
        this.timeConsumer = maxTime1;
        this.timeProducer = maxTime2;
    }

    // Get Producer Operations
    synchronized SchemeOp consume() {
        SchemeOp product = null;
        contConsumer = getRandomNumber(0, this.buffer.length);

        // IF BUFFER IS EMPTY -> WAIT
        while (this.buffer[contConsumer] == null) {
            try {
                wait(this.timeProducer);
                contConsumer = getRandomNumber(0, this.buffer.length);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        product = this.buffer[contConsumer];
        this.buffer[contConsumer] = null;
        count--;
        GUIFrame.removeTasks(product.op);
        ProducerConsumer.setValue((int) Math.round((count * 100) / this.bufferSize), id); // Update progressbar
        notifyAll();
        return product;

    }

    // PRODUCE IF EMPTY
    synchronized void produce(SchemeOp product) {
        contProducer = getRandomNumber(0, this.buffer.length);
        while (this.buffer[contProducer] != null) {
            try {
                wait(this.timeConsumer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.buffer[contProducer] = product;
        product.setID(id);
        id++;
        count++;
        GUIFrame.nextTable(product.ID, product.op); // ADD to Pending Operations
        notifyAll();
    }

    synchronized static void print(String string) {
        System.out.println(string);
    }

    synchronized SchemeOp[] genBufferArray(int size) {
        return this.buffer;
    }

    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
