package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest implements IRetryAnalyzer {
    private static int MAX_RETRY_COUNT = 2;
    private static boolean isRerun = false;
    AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryTest.class);

    public boolean isRetryAvailable() {
        return (count.intValue() > 0);
    }

    public int getCount(){
        return MAX_RETRY_COUNT-count.get ();
    }

    public boolean retry(ITestResult result) {
        boolean retry = false;
       if (isRetryAvailable()) {
           LOGGER.info ( "Going to retry test case: " + result.getMethod() + ", " + (MAX_RETRY_COUNT - count.intValue() + 1) + " out of " + MAX_RETRY_COUNT);
            retry = true;
            isRerun = true;
            count.decrementAndGet();

        }
        return retry;
    }
    public boolean getRerun() {
        return isRerun;
    }
}
