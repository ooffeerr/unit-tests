package calculator.example.com.calculator;

/**
 * Implemetation for various arithmetics operations
 */
public class Arithmetics implements IArithmetics {

	private final IArithmeticsListener listener;
	private final long sleepTimeout;

	public Arithmetics(IArithmeticsListener listener, long sleepTimout) {
		this.listener = listener;
		this.sleepTimeout = sleepTimout;
	}

	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

	public void slow_add(final int num1, final int num2) {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(sleepTimeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// the long calculation is done on the worker thread.
				final int sum = num1 + num2;
				listener.onSlowAddCompleted(sum);
			}
		}.start();
	}
}
