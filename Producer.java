public class Producer extends Thread {
    Buffer buffer;
    int min;
    int max;
    boolean isRunning;

    Producer(Buffer buffer, int max, int min) {
        this.buffer = buffer;
        this.max = max;
        this.min = min;
        this.isRunning = true;
    }

    @Override
    public void run() {
        System.out.println("Running Producer...");
        while (this.isRunning) {
            // Create random scheme operation
            SchemeOp schemeOperation = new SchemeOp(this.max, this.min);
            int arreglo[] = schemeOperation.createRandomNumbers();
            String operacionRandom = schemeOperation.createRandomOperation();
            String formatOperation = schemeOperation.toSchemeFormat(arreglo, operacionRandom);
            schemeOperation.setOperation(formatOperation);

            // OPERACIONES -> AL BUFFER
            this.buffer.produce(schemeOperation);

            try {
                Thread.sleep(this.buffer.timeConsumer);
            } catch (InterruptedException ex) {
                System.out.println("ERROR AT PRODUCER " + ex);
            }

        }

    }
    
    // STOP BUTTON
    public void stopThread() {
        this.isRunning = false;
    }

}

