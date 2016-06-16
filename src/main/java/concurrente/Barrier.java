package concurrente;



public class Barrier
{
    int numThreads ;
    private int pendingForReset = 0 ;
    private int resets = 0;

    public Barrier(int ts)
    {
        numThreads = pendingForReset = ts;
    }

    synchronized public void waitForIt()
    {
        --pendingForReset;
        int currentPending = pendingForReset ;

        if (pendingForReset >  0 )
        {
            int currentTanda = resets;

            do {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (currentTanda == resets);

        } else {
            resets++;
            pendingForReset = numThreads;
            notifyAll();
        }
    }
}
