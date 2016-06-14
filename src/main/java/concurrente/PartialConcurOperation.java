package concurrente;

class PartialConcurOperation extends Thread
{
    public ConcurOpContext coc;
    public final Barrier barrier;
    public int segment;
    public int from ;
    public int to ;

    public static final int DIV = 1;
    public static final int MUL = 2;
    public static final int ADD = 3;
    public static final int SET = 4;
    public static final int ABS = 5;
    public static final int SUB = 6;
    public static final int ASSIGN= 7;
    public static final int DIFFERENTIATE = 8;

    public PartialConcurOperation(ConcurOpContext c, Barrier b)
    {
        coc = c ;
        barrier  = b;
    }

    public void run() {
        switch (coc.operation) {
            case DIV:
                partialDiv();
                break;
            case MUL:
                partialMul();
                break;
            case ADD:
                partialAdd();
                break;
            case SUB:
                partialSub();
                break;
            case ABS:
                partialAbs();
                break;
            case SET:
                partialSet();
                break;
            case ASSIGN:
                partialAssign();
                break;
            case DIFFERENTIATE:
                break;
        }
        barrier.waitForIt();
    }

    private void partialAbs() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = Math.abs(coc.target.elements[i]) ;
    }

    private void partialAdd() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.target.elements[i] + coc.concurDerivParam1.elements[i];
    }

    private void partialSub() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.target.elements[i] - coc.concurDerivParam1.elements[i];
    }

    private void partialMul() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.target.elements[i] * coc.concurDerivParam1.elements[i];
    }

    private void partialDiv() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.target.elements[i] / coc.concurDerivParam1.elements[i];
    }

    private void partialSet() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.doubleAritParam1;
    }

    private void partialAssign() {
        for (int i = from; i <= to; ++i)
            coc.target.elements[i] = coc.concurDerivParam1.elements[i];
    }

}
