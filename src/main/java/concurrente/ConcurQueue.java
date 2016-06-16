package concurrente;

import java.util.*;

public class ConcurQueue<T>  {

    private List<T> elements = new LinkedList<T>();
    private int count = 0 ;


    synchronized public int size() {
        return count;
    }

    synchronized public boolean isEmpty() {
        return count==0 ;
    }

    synchronized public boolean enqueue(T context) {
        boolean result = elements.add(context);
        count ++;
        notify();
        return result;
    }

    synchronized public T dequeue()
    {
        while (count == 0 )
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T result = elements.get(0);
        elements.remove(0);
        count--;

        return result;
    }

}
