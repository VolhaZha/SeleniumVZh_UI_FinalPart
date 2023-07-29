package suiteTest;

import generalTests.LogInTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectClasses({CreateAccTest.class, LogInTest.class, AddAdressTest.class, AddToWishListTest.class, AddToCartTest.class})
@SelectPackages("generalTests")
public class SuiteTest {
}
