package concurrente;

import junit.framework.TestCase;
import org.junit.Test;

public class ConcurDerivativeTest extends TestCase {


	@Test
	public void test_initialization()
	{
		ConcurDerivative cd = new ConcurDerivative(8, 5, 2);
		for (int i = 0 ; i < 8 ; i++)
			assertEquals(cd.get(i), 0d);
	}


	/** Returns the dimension of this vector, that is, its width. */
	@Test
	public void test_dimension()
	{
		ConcurDerivative cd = new ConcurDerivative(8, 5, 2);
		assertEquals(8, cd.dimension());
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
	public void test_set_indexed()
	{
		ConcurDerivative cd = new ConcurDerivative(8, 5, 2);

		assertEquals(0d, cd.get(1));
		cd.set(1,1d);
		assertEquals(1d, cd.get(1));
	}
	
	
	/** Assigns the value d to every position of this vector. 
	 * @param d, the value to assigned. */
	@Test
	public void test_set() 
	{
		for (int test = 0 ; test < 100 ; test++) {
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

			cd = new ConcurDerivative(8,2,2);
			cd.set(6d);

			for (int i = 0 ; i < 8 ; i ++ )
			{
				assertEquals(6d, cd.get(i));
			}

			cd = new ConcurDerivative(8,3,2);
			cd.set(7d);

			for (int i = 0 ; i < 8 ; i ++ )
			{
				assertEquals(7d, cd.get(i));
			}

			cd = new ConcurDerivative(100001,50,15);
			cd.set(8d);

			for (int i = 0 ; i < 100001 ; i ++ )
			{
				assertEquals(8d, cd.get(i));
			}
		}
	}
	
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_assign()
	{
		ConcurDerivative param1 = new ConcurDerivative(8, 1, 2);
		param1.set(5d);

		ConcurDerivative param2 = new ConcurDerivative(8, 3, 2);
		param2.set(5d);

		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);
			cd.assign(param1);

			for (int i = 0; i < 8; i++)
			{
				assertEquals(param1.get(i), cd.get(i));
			}

			cd.assign(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(param2.get(i), cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);
			param1.set(4d);
			cd.assign(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(param1.get(i), cd.get(i));
			}

			cd.assign(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(param2.get(i), cd.get(i));
			}

			cd = new ConcurDerivative(8, 3, 2);
			param1.set(4d);
			cd.assign(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(param1.get(i), cd.get(i));
			}

			cd.assign(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(param2.get(i), cd.get(i));
			}



		}

	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	@Test
	public void test_abs()
	{
		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);
			cd.set(-4d);
			cd.abs();

			for (int i = 0; i < 8; i++)
			{
				assertEquals(4d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);
			cd.set(-4d);
			cd.abs();

			for (int i = 0; i < 8; i++)
			{
				assertEquals(4d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 3, 2);
			cd.set(-4d);
			cd.abs();

			for (int i = 0; i < 8; i++)
			{
				assertEquals(4d, cd.get(i));
			}

		}

	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_add()
	{
		ConcurDerivative param1 = new ConcurDerivative(8, 1, 2);
		param1.set(5d);

		ConcurDerivative param2 = new ConcurDerivative(8, 3, 2);
		param2.set(10d);

		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);

			cd.add(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.add(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(15d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);

			cd.add(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.add(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(15d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 3, 2);

			cd.add(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.add(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(15d, cd.get(i));
			}
		}
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_sub()
	{
		ConcurDerivative param1 = new ConcurDerivative(8, 1, 2);
		param1.set(5d);

		ConcurDerivative param2 = new ConcurDerivative(8, 3, 2);
		param2.set(10d);

		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);

			cd.sub(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-5d, cd.get(i));
			}

			cd.sub(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-15d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);

			cd.sub(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-5d, cd.get(i));
			}

			cd.sub(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-15d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 3, 2);

			cd.sub(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-5d, cd.get(i));
			}

			cd.sub(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(-15d, cd.get(i));
			}
		}

	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_mul() {
		ConcurDerivative param1 = new ConcurDerivative(8, 1, 2);
		param1.set(5d);

		ConcurDerivative param2 = new ConcurDerivative(8, 3, 2);
		param2.set(10d);

		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);
			cd.set(1d);

			cd.mul(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.mul(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(50d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);
			cd.set(1d);

			cd.mul(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.mul(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(50d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 3, 2);
			cd.set(1d);

			cd.mul(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(5d, cd.get(i));
			}

			cd.mul(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(50d, cd.get(i));
			}
		}

	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == derivative.dimension(). */
	@Test
	public void test_div()
	{
		ConcurDerivative param1 = new ConcurDerivative(8, 1, 2);
		param1.set(5d);

		ConcurDerivative param2 = new ConcurDerivative(8, 3, 2);
		param2.set(10d);

		for (int test = 0 ; test < 100 ; test++)
		{
			ConcurDerivative cd = new ConcurDerivative(8, 1, 2);
			cd.set(50d);

			cd.div(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(10d, cd.get(i));
			}

			cd.div(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(1d, cd.get(i));
			}

			cd = new ConcurDerivative(8, 2, 2);
			cd.set(50d);

			cd.div(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(10d, cd.get(i));
			}

			cd.div(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(1d, cd.get(i));
			}


			cd = new ConcurDerivative(8, 3, 2);
			cd.set(50d);

			cd.div(param1);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(10d, cd.get(i));
			}

			cd.div(param2);
			for (int i = 0; i < 8; i++)
			{
				assertEquals(1d, cd.get(i));
			}

		}
	}
	
	
	/** Computes the derivative of the function represented by this vector,
	 *  using the finite differences method.
	 *  @param window, an integer with the number of element to each side of
	 *         the current element to be considered by the method.
	 *  @return a ConcurDerivative with the result of the derivative procedure. */
	@Test
	public void test_differentiate()
	{
		ConcurDerivative result ;

		ConcurDerivative cd = new ConcurDerivative(5, 2, 2);

		for (int i = 0; i < 5; i++)
			cd.set(i,(double)i);

		result = cd.differentiate();

		assertEquals(1d, result.get(0));
		assertEquals(1d, result.get(1));
		assertEquals(1d, result.get(2));


		cd = new ConcurDerivative(5, 1, 2);

		for (int i = 0; i < 5; i++)
			cd.set(i,(double)i);

		result = cd.differentiate();

		assertEquals(1d, result.get(0));
		assertEquals(1d, result.get(1));
		assertEquals(1d, result.get(2));


		cd = new ConcurDerivative(8, 3, 2);

		for (int i = 0; i < 5; i++)
			cd.set(i,(double)i);

		result = cd.differentiate();

		assertEquals(1d, result.get(0));
		assertEquals(1d, result.get(1));
		assertEquals(1d, result.get(2));
	}

}
