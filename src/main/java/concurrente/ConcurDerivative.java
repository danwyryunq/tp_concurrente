package concurrente;

import java.util.Arrays;

public class ConcurDerivative extends Thread
{
	protected int threads; 
	protected int load ; 
	// An array with the vector elements
	protected double[] elements;
	protected boolean[] threadRunning ;
	protected boolean fullyDependentTaskRunning = false ;

	class ThreadVectorLimits {
		public int from, to, threadIdx;
		public ThreadVectorLimits(int threadIdx, int from , int to) {
			this.threadIdx = threadIdx;
			this.from = from ;
			this.to = to ;
		}
	}

	abstract class ConcurOpRunnable implements Runnable {
		public ConcurDerivative derivative;
		public ThreadVectorLimits opLimits;
		public ConcurOpRunnable(ConcurDerivative derivative, ThreadVectorLimits opLimits) {
			this.derivative = derivative;
			this.opLimits = opLimits;
		}
	}

	protected ThreadVectorLimits[] threadVectorLimits;

	/** Constructor for a ConcurVector
	 * @param size, the width of the vector.
	 * @precondition size > 0. */
	public ConcurDerivative(int size) 
	{
		elements = new double[size];
	}

	public ConcurDerivative(int size, int threads, int load) 
	{
		this.threads = threads ; 
		this.load = load ; 
		elements = new double[size];
		threadRunning = new boolean[threads];
		Arrays.fill(threadRunning, false);
		initializeOpLimits();
	}

	protected void initializeOpLimits() {

		threadVectorLimits = new ThreadVectorLimits[threads];

		int maxLoad = elements.length / threads ;
		int loadRemainder = elements.length % threads ;

		int from = 0  ;
		int to = maxLoad-1 ;
		if (loadRemainder-- > 0) ++to;
		threadVectorLimits[0] = new ThreadVectorLimits(0,from,to);

		for (int t = 1; t < threads ; t ++)
		{
			from = to+1;
			to = from+maxLoad-1 ;
			if (loadRemainder-- > 0) ++to;
			threadVectorLimits[t] = new ThreadVectorLimits(t,from,to);
		}
	}


	/** Returns the dimension of this vector, that is, its width. */
	public int dimension() 
	{
		return elements.length;
	}
	
	
	/** Returns the element at position i.
	 * @param i, the position of the element to be returned.
	 * @precondition 0 <= i < dimension(). */
	synchronized public double get(int i)
	{
		while (fullyDependentTaskRunning || inRunningThreadLimit(i) )
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("get");
		return elements[i];
	}

	private boolean inRunningThreadLimit(int i) {
		for (int t = 0 ; t < threads ; t++)
			if ( i >= threadVectorLimits[t].from && i <= threadVectorLimits[t].to)
				return threadRunning[ threadVectorLimits[t].threadIdx ];
		return false;
	}


	/** Assigns the value d to the position i of this vector. 
	 * @param i, the position to be set.
	 * @param d, the value to assign at i.
	 * @precondition 0 <= i < dimension. */
	synchronized public void set(int i, double d)
	{
		while (fullyDependentTaskRunning || inRunningThreadLimit(i) )
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("set");
		elements[i] = d;
	}
	
	
	/** Assigns the value d to every position of this vector. 
	 * @param d, the value to assigned. */
	public void set(double d)
	{
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public double d ;
				public ConcurOpRunnable setD(double d) { this.d = d ; return this; }
				public void run() { derivative.partialSet(opLimits,d); }
			}.setD(d);

			new Thread(operation).start();
		}
	}

	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == derivative.dimension(). */
	synchronized public void assign(ConcurDerivative v)
	{
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public ConcurDerivative v ;
				public ConcurOpRunnable setV(ConcurDerivative v) { this.v = v ; return this; }
				public void run() { derivative.partialAssign(opLimits,v); }
			}.setV(v);

			new Thread(operation).start();
		}

	}

	/** Applies the absolute value operation to every element in this vector. */
	synchronized public void abs()
	{
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public void run() { derivative.partialAbs(opLimits); }
			};

			new Thread(operation).start();
		}

	}

	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	synchronized public void add(ConcurDerivative v)
	{
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public ConcurDerivative v ;
				public ConcurOpRunnable setV(ConcurDerivative v) { this.v = v ; return this; }
				public void run() { derivative.partialAdd(opLimits,v); }
			}.setV(v);

			new Thread(operation).start();
		}
	}

	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */

	synchronized public void sub(ConcurDerivative v) {
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public ConcurDerivative v ;
				public ConcurOpRunnable setV(ConcurDerivative v) { this.v = v ; return this; }
				public void run() { derivative.partialSub(opLimits,v); }
			}.setV(v);

			new Thread(operation).start();
		}
	}


	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	synchronized public void mul(ConcurDerivative v) {
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public ConcurDerivative v ;
				public ConcurOpRunnable setV(ConcurDerivative v) { this.v = v ; return this; }
				public void run() { derivative.partialMul(opLimits,v); }
			}.setV(v);

			new Thread(operation).start();
		}
	}

	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	synchronized public void div(ConcurDerivative v) {
		for (int t = 0 ; t < threads ; t++)
		{
			ConcurOpRunnable operation = new ConcurOpRunnable(this, threadVectorLimits[t]) {
				public ConcurDerivative v ;
				public ConcurOpRunnable setV(ConcurDerivative v) { this.v = v ; return this; }
				public void run() { derivative.partialDiv(opLimits,v); }
			}.setV(v);

			new Thread(operation).start();
		}
	}
	
	
	/** Computes the derivative of the function represented by this vector,
	 *  using the finite differences method.
	 *  @param window, an integer with the number of element to each side of
	 *         the current element to be considered by the method.
	 *  @return a ConcurDerivative with the result of the derivative procedure. */
	public ConcurDerivative differentiate() {
		ConcurDerivative result = new ConcurDerivative(dimension() - 2);
		for (int i = 1; i < dimension() - 1; ++i)
			result.set(i - 1, (get(i+1) - get(i-1)) / 2);
		return result;
	}



	synchronized protected void partialSet(ThreadVectorLimits limits, double d)
	{
		while (fullyDependentTaskRunning || threadRunning[limits.threadIdx] )
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		threadRunning[limits.threadIdx] = true;
		System.out.println("set" + limits.threadIdx);

		for (int i = limits.from; i <= limits.to; ++i)
			elements[i] = d;

		threadRunning[limits.threadIdx] = false;
		notify();
	}

	public void partialAssign(ThreadVectorLimits limits, ConcurDerivative v)
	{
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, v.get(limits.from));
	}

	public void partialAbs(ThreadVectorLimits limits)
	{
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, Math.abs(get(limits.from)));
	}

	public void partialAdd(ThreadVectorLimits limits, ConcurDerivative v)
	{
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, get(limits.from) + v.get(limits.from));
	}

	public void partialSub(ThreadVectorLimits limits , ConcurDerivative v) {
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, get(limits.from) - v.get(limits.from));
	}

	public void partialDiv(ThreadVectorLimits limits , ConcurDerivative v) {
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, get(limits.from) / v.get(limits.from));
	}
	public void partialMul(ThreadVectorLimits limits , ConcurDerivative v) {
		for (; limits.from <= limits.to; ++limits.from)
			set(limits.from, get(limits.from) * v.get(limits.from));
	}
}
