package concurrente;

/**
 * Created by prospero on 6/13/16.
 */
public class Barrier
{
    int numThreads ;
    int threadsDone = 0 ;

    public Barrier(int numThreads)
    {
        this.numThreads = numThreads;
    }

    synchronized public void waitForIt()
    {
        ++threadsDone;
        while (threadsDone != numThreads)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
        threadsDone = 0;
    }
}
