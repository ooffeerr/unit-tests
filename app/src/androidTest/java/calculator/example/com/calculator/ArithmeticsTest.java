package calculator.example.com.calculator;

import org.mockito.Mock;
import org.mockito.Mockito;

import android.test.AndroidTestCase;
import android.text.method.MovementMethod;

/**
 * Testing for {@link Arithmetics}
 */
public class ArithmeticsTest extends AndroidTestCase {

	private static final int TWO = 2;
	private static final int FOUR = 4;

	public void testMultiplyTwoByTwo_resultIsFour() {
		// notice the AAA pattern : arrange, act, assert
		//arrange
		// one of the dependencies, should hopefully always be
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		// The SUT. always a real instance.
		Arithmetics arithmetics = new Arithmetics(listener, 0);

		//Act.
		int result = arithmetics.multiply(TWO, TWO);

		//Assert.
		assertEquals("product value is wrong", FOUR, result);
	}

	public void testSlowAddTwoPlusTwo_FourIsDelegated() throws InterruptedException {
		//Arrange
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		Arithmetics arithmetics = new Arithmetics(listener, 0);

		// act
		arithmetics.slow_add(TWO, TWO);

		//assert
		Thread.sleep(20);
		Mockito.verify(listener, Mockito.times(1)).onSlowAddCompleted(FOUR);
	}
}
