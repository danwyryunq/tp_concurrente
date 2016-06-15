package concurrente;



public class ConcDerOpContext {
    public int operation;
    public ConcurDerivative target ;
    public ConcurDerivative concurDerivParam1;
    public double doubleAritParam1;

    public ConcDerOpContext(ConcurDerivative cd, int op) {
        operation = op ;
        target = cd ;
    }

    public ConcDerOpContext(ConcurDerivative cd, int op, ConcurDerivative param1) {
        operation = op ;
        target = cd ;
        concurDerivParam1 = param1;
    }

    public ConcDerOpContext(ConcurDerivative cd, int op, double param1) {
        operation = op ;
        target = cd ;
        doubleAritParam1 = param1 ;
    }


}
