package concurrente;


import java.util.Arrays;

public class ConcDerOpContext<RESULT_TYPE>
{
    public boolean[] completedSegment;
    public int operation;
    public ConcurDerivative target ;
    public ConcurDerivative concurDerivParam1;
    public double doubleAritParam1;
    public RESULT_TYPE result ;


    public ConcDerOpContext(ConcurDerivative cd, int op)
    {
        operation = op ;
        target = cd ;
        completedSegment = new boolean[cd.threads];
        Arrays.fill(completedSegment, false);
    }

    public ConcDerOpContext(ConcurDerivative cd, int op, ConcurDerivative param1)
    {
        this(cd,op);
        concurDerivParam1 = param1;
    }

    public ConcDerOpContext(ConcurDerivative cd, int op, double param1)
    {
        this(cd,op);
        doubleAritParam1 = param1 ;
    }

    synchronized public void completeSegment(int segment)
    {
        this.completedSegment[segment] = true ;
        this.notifyAll();
    }

    synchronized public void waitForSegmentsCompletion(int segment) {
        while ( ! completedSegment[segment] )
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
