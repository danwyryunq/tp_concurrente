package concurrente;

class PartialConcDerOp
{
    public final ConcDerOpContext coc;
    public final Barrier barrier;
    public final int segment;
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

    public PartialConcDerOp(ConcDerOpContext c, Barrier b, int numThreads, int seg)
    {
        segment = seg ;
        coc = c ;
        barrier  = b;
        calculateFromTo(coc.target, numThreads);
    }

    protected void calculateFromTo(ConcurDerivative cd, int numThreads)
    {
        int threadLoad = cd.dimension() / numThreads;
        int remainder = cd.dimension() % numThreads;

        from = (segment * threadLoad) + ((remainder <= segment) ? remainder : segment);
        to   = from + ((remainder <= segment) ? threadLoad-1 : threadLoad );
    }

    public void perform() {
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
                partialDifferentiate();
                break;
        }
        barrier.waitForIt();

    }

    private void partialAbs() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, Math.abs(coc.target.get(i))) ;
    }

    private void partialAdd() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.target.get(i) + coc.concurDerivParam1.get(i));
    }

    private void partialSub() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.target.get(i) - coc.concurDerivParam1.get(i));
    }

    private void partialMul() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.target.get(i) * coc.concurDerivParam1.get(i));
    }

    private void partialDiv() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.target.get(i) / coc.concurDerivParam1.get(i));
    }

    private void partialSet() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.doubleAritParam1);
    }

    private void partialAssign() {
        for (int i = from; i <= to; ++i)
            coc.target.set(i, coc.concurDerivParam1.get(i));
    }

    private void partialDifferentiate()
    {
        ConcurDerivative target = coc.target ;
        ConcurDerivative result = (ConcurDerivative) coc.result ;

//        if (segment > 0)
//            coc.waitForSegmentsCompletion(segment-1);

        calculateFromTo(result, coc.target.threads);

        for (int i = from ; i <= to ; ++i)
        {
            result.set(i, (target.get(i + 2) - target.get(i)) / 2);
        }

//        coc.completeSegment(segment);

    }


}

