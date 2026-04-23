package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/***
 * “I added a TestNG RetryAnalyzer to rerun failed tests once in case of transient UI failures. 
 * I kept retry count low because retries should not hide real product bugs. 
 * This helps reduce false negatives caused by temporary browser or environment issues.”
 */

public class RetryAnalyzer implements IRetryAnalyzer{

	private int retryCount = 0;
    private static final int maxRetryCount = 1;
    
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
		return false;
	}

}
