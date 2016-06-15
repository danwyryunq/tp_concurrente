package concurrente;


public class WorkerThread extends Thread {
    private final Barrier barrier;
    public ConcurQueue<PartialConcDerOp> queue;

    public WorkerThread(ConcurQueue<PartialConcDerOp> q, Barrier b) {
        queue = q;
        barrier = b;
    }
    public void run()
    {
        while (true) {
            PartialConcDerOp op = queue.dequeue();
            op.perform();
        }
    }
}
