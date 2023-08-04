package suiteTest;

import generalTests.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CreateAccTest.class, AddToCartTest.class, LogInTest.class, AddAdressTest.class,  AddToWishListTest.class})
//@SelectPackages("generalTests")
public class SuiteTest {
}
