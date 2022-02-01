package bg.ava.vendingexample;

import bg.ava.vendingexample.aspects.PerformanceLoggingTest;
import bg.ava.vendingexample.controllers.VendingApiControllerTest;
import bg.ava.vendingexample.controllers.VendingApiAdminControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({VendingApiControllerTest.class, VendingApiAdminControllerTest.class, PerformanceLoggingTest.class})
public class ControllersTestSuit {
}
