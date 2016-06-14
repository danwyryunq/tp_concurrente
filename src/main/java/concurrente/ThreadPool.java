package concurrente;


public class ThreadPool {

    final public ConcurQueue<PartialConcurOperation> q = new ConcurQueue<PartialConcurOperation>();
    protected Thread[] threads ;

    public ThreadPool(final Barrier barrier, final int numThreads)
    {
        threads = new Thread[numThreads];
        for (int i = 0 ; i < numThreads ; i++)
        {
            threads[i] = new Thread() {
                public void run()
                {
                    while (true) {
                        PartialConcurOperation op = q.dequeue();
                        op.start();
                    }
                }
            };
            threads[i].start();
        }
    }

    synchronized public void enqueueOperation(PartialConcurOperation op) {
        q.queue(op);
    }
}
