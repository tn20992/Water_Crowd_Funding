import model.OverallCondition;
import model.Facade;
import model.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * @author Eugene Chu
 * @version 1.0
 *
 */


public class createPurityReportTest {
    private Facade facade;

    /**
     * initalize facade
     * @throws Exception exception
     */
    @Before
    public void setUp() throws Exception {
        Facade.initialize();
        facade = Facade.getFacade();
    }

    /**
     * check size of list
     */
    @Test
    public void sizeCreateReport() {
        int size = facade.getPurityReports().size();
        Location location = new Location(0,0);
        facade.createPurityReport("user1", location, OverallCondition.SAFE, 0, 0);
        assertEquals("# Reports: " + size+1, size+1,
                facade.getPurityReports().size());
    }

    /**
     *  check multiple reports size
     */
    @Test
    public void sizeCreateMultipleReports() {
        int size = facade.getPurityReports().size();
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(1,1);
        Location loc3 = new Location(2,2);
        facade.createPurityReport("user1", loc1, OverallCondition.TREATABLE,
                1, 1);
        facade.createPurityReport("user2", loc2, OverallCondition.SAFE,
                2, 2);
        facade.createPurityReport("user3", loc3, OverallCondition.UNSAFE,
                3, 3);
        assertEquals("# Reports: " + (size + 3), size + 3,
                facade.getPurityReports().size());
    }

    /**
     * check that it a report does not exist
     */
    @Test
    public void checkNullReport() {
        int size = facade.getPurityReports().size();
        assertNull("Report " + (size + 1) + " should not be existed", facade
                .getPurityReportByReportNumber(size + 1));

        Location loc = new Location(4,4);
        facade.createPurityReport("user1", loc, OverallCondition.SAFE, 4, 4);
        assertNull("Report " + (size + 2) + " should not be existed", facade
                .getPurityReportByReportNumber(size + 2));

    }

    /**
     * Test Exception
     */
    @Test
    public void testExceptionCreateNewReport() {
        int size = facade.getPurityReports().size();
        try {
            Location loc = new Location(6,6);
            facade.createPurityReport("user1", loc, OverallCondition.TREATABLE,
                5, 5);
            facade.getPurityReports().get(size);
        } catch (IndexOutOfBoundsException e) {
            fail("Should not get IndexOutOfBoundsException, report " +
                    size + " should in the list");
        }
    }
}
