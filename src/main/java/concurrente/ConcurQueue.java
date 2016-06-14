package concurrente;

import java.util.*;

public class ConcurQueue<T>  {

    private List<T> contexts = new LinkedList<T>();
    private int count = 0 ;


    synchronized public int size() {
        return count;
    }

    synchronized public boolean isEmpty() {
        return count==0 ;
    }

    synchronized public boolean queue(T context) {
        count ++;
        return contexts.add(context);
    }

    synchronized  public T dequeue()
    {

        while (count == 0 )
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T result = contexts.get(0);
        contexts.remove(0);
        count--;
        notify();

        return result;
    }

}
