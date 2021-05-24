mport javax.swing.SwingUtilities;
//Clase principal 
//Producer-Consumer

public class ProducerConsumer {

    public Producer producers[];
    public Consumer consumers[];
    public Buffer buffer;
    public static javax.swing.JProgressBar JProgressBarS;
    public static javax.swing.JSpinner jSpinner;

    public ProducerConsumer(int producerSize, int consumerSize) {

        this.producers = new Producer[producerSize];
        this.consumers = new Consumer[consumerSize];
    }

    // initialize GUI
    public void initProcess(int bufferSize, int timeProducer, int timeConsumer, int minRange, int maxRange, javax.swing.JProgressBar JPBar, javax.swing.JSpinner jSpinner4) {

        Buffer buffer = new Buffer(bufferSize, timeProducer, timeConsumer);
        JProgressBarS = JPBar;
        JProgressBarS.setMaximum(100);
        JProgressBarS.setMinimum(0);

        jSpinner = jSpinner4;

        // Crea Producers
        for (int i = 0; i < this.producers.length; i++) {
            this.producers[i] = new Producer(buffer, minRange, maxRange);
            this.producers[i].start();
        }

        // Create Consumer
        for (int j = 0; j < this.consumers.length; j++) {
            this.consumers[j] = new Consumer(buffer);
            this.consumers[j].start();
        }
    }

    // Link/Assign Progressbar
    public static void setValue(final int progress, final int counter) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JProgressBarS.setValue(progress);
                jSpinner.setValue(counter);

            }
        });
    }

    // Stop processes
    public void stopAllThreads() {

        for (int i = 0; i < this.producers.length; i++) {
            this.producers[i].stopThread();
        }

        for (int j = 0; j < this.consumers.length; j++) {
            this.consumers[j].stopThread();
        }

    }

}

