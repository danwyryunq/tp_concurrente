package concurrente;



public class ConcurOpContext {
    public int operation;
    public ConcurDerivative target ;
    public ConcurDerivative concurDerivParam1;
    public double doubleAritParam1;

    public ConcurOpContext(int op, ConcurDerivative cd) {
        operation = op ;
        target = cd ;
    }

    public ConcurOpContext(int op, ConcurDerivative cd, ConcurDerivative param1) {
        operation = op ;
        target = cd ;
        concurDerivParam1 = param1;
    }

    public ConcurOpContext(int op, ConcurDerivative cd, double param1) {
        operation = op ;
        target = cd ;
        doubleAritParam1 = param1 ;
    }


}
