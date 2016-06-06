package secuencial;


public class ConcurDerivative {

	// An array with the vector elements
	private double[] elements;
	
	
	/** Constructor for a ConcurVector.
	 * @param size, the width of the vector.
	 * @precondition size > 0. */
	public ConcurDerivative(int size) {
		elements = new double[size];
	}
	
	
	/** Returns the dimension of this vector, that is, its width. */
	public int dimension() {
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
	public void set(double d) {
		for (int i = 0; i < dimension(); ++i)
			elements[i] = d;
	}
	
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == v.dimension(). */
	public void assign(ConcurDerivative v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, v.get(i));
	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	public void abs() {
		for (int i = 0; i < dimension(); ++i)
			set(i, Math.abs(get(i)));
	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void add(ConcurDerivative v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) + v.get(i));
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void sub(ConcurDerivative v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) - v.get(i));
	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void mul(ConcurDerivative v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) * v.get(i));
	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void div(ConcurDerivative v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) / v.get(i));
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
	
}
