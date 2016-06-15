package concurrente;


public class ThreadPool {

    final public ConcurQueue<PartialConcDerOp> q = new ConcurQueue<PartialConcDerOp>();
    protected WorkerThread[] threads ;
    private final Barrier barrier;


    public ThreadPool(final Barrier b, final int numThreads)
    {
        barrier = b;
        threads = new WorkerThread[numThreads];
        for (int i = 0 ; i < numThreads ; i++)
        {
            threads[i] = new WorkerThread(q, barrier);
            threads[i].start();
        }
    }

    synchronized public void distribute(ConcDerOpContext coc)
    {
        for (int t =0 ; t < threads.length ; ++t )
        {
            PartialConcDerOp op = new PartialConcDerOp(coc,barrier,threads.length,t);
            threads[t].queue.enqueue(op);
        }

    }
}
