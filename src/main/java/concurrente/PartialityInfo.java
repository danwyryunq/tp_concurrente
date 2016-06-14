package concurrente;



class PartialityInfo
{
    public int from, to, threadIdx;
    public PartialityInfo(int threadIdx, int from , int to)
    {
        this.threadIdx = threadIdx;
        this.from = from ;
        this.to = to ;
    }
}