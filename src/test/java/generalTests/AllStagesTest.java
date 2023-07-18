package generalTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CreateAccTest.class, LogInTest.class, AddAdressTest.class, AddToWishListTest.class, AddToCartTest.class})
public class AllStagesTest {
}
