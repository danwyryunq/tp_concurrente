package concurrente;

import junit.framework.TestCase;
import org.junit.Test;

public class ConcurDerivativeTest extends TestCase {

	
	/** Returns the dimension of this vector, that is, its width. */
	@Test
	public void test_dimension() {
		assertTrue(true);
	}
	
	
	/** Returns the element at position i.
	 * @param i, the position of the element to be returned.
	 * @precondition 0 <= i < dimension(). */
	@Test
	public void test_get() {
		assertTrue(true);
	}
	
	
	/** Assigns the value d to the position i of this vector. 
	 * @param i, the position to be set.
	 * @param d, the value to assign at i.
	 * @precondition 0 <= i < dimension. */
	@Test
	public void test_set_indexed() {
		assertTrue(true);
	}
	
	
	/** Assigns the value d to every position of this vector. 
	 * @param d, the value to assigned. */
	@Test
	public void test_set() 
	{
		for (int test = 0 ; test < 10000 ; test++) {
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);
			cd.set(5d);
			for (int i = 0; i < 8; i++) {
				assertEquals(5d, cd.get(i));
			}
			cd.set(6d);
			for (int i = 0 ; i < 8 ; i ++ )
			{
				assertEquals(6d, cd.get(i));
			}

//			cd = new ConcurDerivative(8,2,2);
//			cd.set(6d);
//
//			for (int i = 0 ; i < 8 ; i ++ )
//			{
//				assertEquals(6d, cd.get(i));
//			}
////
//			cd = new ConcurDerivative(8,3,2);
//			cd.set(7d);
//
//			for (int i = 0 ; i < 8 ; i ++ )
//			{
//				assertEquals(7d, cd.get(i));
//			}
//
//			cd = new ConcurDerivative(100001,50,15);
//			cd.set(8d);
//
//			for (int i = 0 ; i < 100001 ; i ++ )
//			{
//				assertEquals(8d, cd.get(i));
//			}
		}
	}
	
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_assign() {
		assertTrue(true);
	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	@Test
	public void test_abs() {
		assertTrue(true);
	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_add() {
		assertTrue(true);
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_sub() {
		assertTrue(true);
	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_mul() {
		assertTrue(true);
	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_div() {
		assertTrue(true);
	}
	
	
	/** Computes the derivative of the function represented by this vector,
	 *  using the finite differences method.
	 *  @param window, an integer with the number of element to each side of
	 *         the current element to be considered by the method.
	 *  @return a ConcurDerivative with the result of the derivative procedure. */
	@Test
	public void test_differentiate() {
		assertTrue(true);
	}

}
