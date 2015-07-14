package calculator.example.com.calculator;

import junit.framework.TestCase;

import org.mockito.Mockito;

import android.view.View;
import android.widget.EditText;

/**
 * Testing the multiplication click listener
 */
public class MultiplyClickListenerTest extends TestCase {

	private static final int NUM1 = 2;
	private static final int NUM2 = 5;
	private static final int PRODUCT = NUM1 * NUM2;


	public void testValidInput_arithmeticsIsInvoked() {
		//arrange
		EditText number1 = Mockito.mock(EditText.class);
		EditText number2 = Mockito.mock(EditText.class);
		EditText result = Mockito.mock(EditText.class);
		IArithmetics arithmetics = Mockito.mock(IArithmetics.class);
		View view = Mockito.mock(View.class);

		Mockito.when(number1.toString()).thenReturn(String.valueOf(NUM1));
		Mockito.when(number2.toString()).thenReturn(String.valueOf(NUM2));
		Mockito.when(arithmetics.multiply(Mockito.anyInt(), Mockito.anyInt())).thenReturn(PRODUCT);

		//act
		MultiplyClickListener listener = new MultiplyClickListener(number1, number2, arithmetics, result);
		listener.onClick(view);

		//assert - the arithmetics is called for calculation,
		Mockito.verify(arithmetics,  Mockito.times(1)).multiply(Mockito.anyInt(), Mockito.anyInt());

		//assert - the result is set with whatever arithmetics returns
		Mockito.verify(result, Mockito.times(1)).setText(String.valueOf(PRODUCT));
	}
}
