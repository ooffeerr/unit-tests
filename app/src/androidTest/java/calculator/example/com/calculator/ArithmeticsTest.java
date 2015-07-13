package calculator.example.com.calculator;

import org.mockito.Mockito;
import android.test.AndroidTestCase;

/**
 * Testing for {@link Arithmetics}
 */
public class ArithmeticsTest extends AndroidTestCase {

	private static final int TWO = 2;
	private static final int FOUR = 4;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// a bug with mockito and dexmaker in Android - http://bytesdonthurt.blogspot.co.il/2015/02/using-mockito-on-android.html
		System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
	}


	public void testMultiplyTwoByTwo_resultIsFour() {
		// notice the AAA pattern : arrange, act, assert
		//arrange
		// The dependencies, should hopefully always be mocked
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		IAddImplSelector selector = Mockito.mock(IAddImplSelector.class);
		// The SUT. always a real instance.
		Arithmetics arithmetics = new Arithmetics(listener, 0, selector);

		//Act.
		int result = arithmetics.multiply(TWO, TWO);

		//Assert.
		assertEquals("product value is wrong", FOUR, result);
	}

	public void testSlowAddTwoPlusTwo_FourIsDelegated() throws InterruptedException {
		//Arrange
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		IAddImplSelector selector = Mockito.mock(IAddImplSelector.class);

		//The real selector has a random behaviour, which we don't want for a deterministic Unit Test.
		// Hence, we'll mock the selector's behaviour so we can choose what flow will be tested.
		Mockito.when(selector.performAdd()).thenReturn(true);

		Arithmetics arithmetics = new Arithmetics(listener, 0, selector);

		// act
		arithmetics.slow_add(TWO, TWO);

		//assert
		Thread.sleep(20);
		Mockito.verify(listener, Mockito.times(1)).onSlowAddCompleted(FOUR);
	}
}
