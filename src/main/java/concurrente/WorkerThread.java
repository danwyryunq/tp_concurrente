package concurrente;


public class WorkerThread extends Thread {
    private final Barrier barrier;
    public ConcurQueue<ConcurOp> queue;
    private int  threadId;

    public WorkerThread(ConcurQueue<ConcurOp> q, Barrier b, int id) {
        queue = q;
        barrier = b;
        threadId = id;
    }

    public void run()
    {
        boolean goOn = true;
        while ( goOn )
        {
            ConcurOp op = queue.dequeue();
            goOn = op.perform();
        }
    }
}
