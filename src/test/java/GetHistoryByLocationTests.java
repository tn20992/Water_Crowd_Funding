import model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: Tri Nguyen
 * @version: 1.0
 * Team # 56: THESTORM
 * JUnit Test for getHistoryByLocation method
 */
public class GetHistoryByLocationTests {
    private Facade facade;

    @Before
    public void setUp() throws Exception {
        Facade.initialize();
        facade = Facade.getFacade();
    }

    @Test
    public void testQuantityOfReportAtSelectedLocation() {

        Location location = new Location(2.36, 21.26);

        int numOfReports = facade.getHistoryByLocation(location, 2016).size();

        facade.createPurityReport("user1", location, OverallCondition.SAFE,
                12.4, 23.4);
        facade.createPurityReport("user1", location, OverallCondition.TREATABLE,
                11.3, 2.4);
        facade.createPurityReport("user1", location, OverallCondition.UNSAFE,
                1.4, 3.5);
        facade.createPurityReport("user1", location, OverallCondition.SAFE,
                2.4, 20.4);
        facade.createPurityReport("user1", location, OverallCondition.TREATABLE,
                12.1, 22.0);
        facade.createPurityReport("user1", location, OverallCondition.SAFE,
                9.8, 3.7);



        assertEquals("The number of reports at the selected location is"
                + " mismatched with what we have: ", numOfReports + 6, facade
                .getHistoryByLocation(location, 2016).size());
    }

    @Test
    public void testNoReportAtSelectedLocation() {
        Location locationA = new Location(2.361392, 21.269531);
        boolean status = facade.getHistoryByLocation(locationA, 2016).isEmpty();

        assertTrue("The array that contains the reports at the selected"
                + " location should be empty", status);
    }

}
