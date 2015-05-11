package org.thoughtcrime.securesms.util;

import org.junit.Test;
import java.util.concurrent.Callable;
import static org.junit.Assert.assertEquals;

public class TaggedFutureTaskTest {

	// don't test methods that aren't changed by TaggedFutureTask
	
	@Test
	public void constructor1() {
		final Object tag = new Object();
		final Callable<String> callable = new Callable<String>() {
			public String call() {return "ARGLEBARGLE";}
		};
		assertEquals(tag, (new TaggedFutureTask<String>(callable, tag)).getTag());
	}
	
	@Test
	public void constructor2() {
		final Object tag = new Object();
		final Runnable runnable = new Runnable() {
			public void run() {}
		};
		assertEquals(tag, (new TaggedFutureTask<String>(runnable, "Words", tag)).getTag());
	}
}
