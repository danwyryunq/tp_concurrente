package concurrente;


public class WorkerThread extends Thread {
    private final Barrier barrier;
    public ConcurQueue<PartialConcDerOp> queue;
    private int  threadId;

    public WorkerThread(ConcurQueue<PartialConcDerOp> q, Barrier b, int id) {
        queue = q;
        barrier = b;
        threadId = id;
    }

    public void run()
    {
        while (true )
        {
            PartialConcDerOp op = queue.dequeue();
            op.perform();
        }
    }
}
