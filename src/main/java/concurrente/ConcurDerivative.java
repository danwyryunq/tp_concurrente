package concurrente;

public class ConcurDerivative
{
	protected int threads; 
	protected int load ; 
	// An array with the vector elements
	public double[] elements;

	private ThreadPool opRunner;
	private Barrier barrier;


	/** Constructor for a ConcurVector
	 * @param size, the width of the vector.
	 * @precondition size > 0 && threads >= 1 && load <= size%threads
	 */
	public ConcurDerivative(int size, int threads, int load)
	{
		this.threads = threads ; 
		this.load = load ; 
		elements = new double[size];

		barrier = new Barrier(threads+1);
		opRunner = new ThreadPool(barrier, threads);
	}

	/** Returns the dimension of this vector, that is, its width. */
	public int dimension() 
	{
		return elements.length;
	}


	/** Returns the element at position i.
	 * @param i, the position of the element to be returned.
	 * @precondition 0 <= i < dimension(). */
	public double get(int i) {
		return elements[i];
	}


	/** Assigns the value d to the position i of this vector.
	 * @param i, the position to be set.
	 * @param d, the value to assign at i.
	 * @precondition 0 <= i < dimension. */
	public void set(int i, double d) {
		elements[i] = d;
	}


	/** Assigns the value d to every position of this vector.
	 * @param d, the value to assigned. */
	public void set(double d)
	{
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.SET, d);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == derivative.dimension(). */
	public void assign(ConcurDerivative v)
	{
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.ASSIGN, v);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Applies the absolute value operation to every element in this vector. */
	public void abs()
	{
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.ABS);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	public void add(ConcurDerivative v)
	{
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.ADD, v);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	public void sub(ConcurDerivative v) {
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.SUB, v);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	public void mul(ConcurDerivative v) {
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.MUL, v);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	public void div(ConcurDerivative v) {
		ConcDerOpContext<Object> context = new ConcDerOpContext<Object>(this, PartialConcDerOp.DIV, v);
		opRunner.distribute(context);
		barrier.waitForIt();
	}


	/** Computes the derivative of the function represented by this vector,
	 *  using the finite differences method.
	 *  @param window, an integer with the number of element to each side of
	 *         the current element to be considered by the method.
	 *  @return a ConcurDerivative with the result of the derivative procedure. */
	public ConcurDerivative differentiate()
	{
		ConcurDerivative result = new ConcurDerivative(dimension() - 2, threads, load);

		ConcDerOpContext context = new ConcDerOpContext<ConcurDerivative>(this, PartialConcDerOp.DIFFERENTIATE);
		context.result = result ;

		opRunner.distribute(context);
		barrier.waitForIt();

		return result ;
	}

	protected void finalize() throws Throwable {
		try {
			opRunner.killThreads();
		} finally {
			super.finalize();
		}
	}
}
