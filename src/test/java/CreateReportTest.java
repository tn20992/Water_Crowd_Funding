import model.ConditionOfWater;
import model.Facade;
import model.Location;
import model.TypeOfWater;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


/**
 * @author Hung Do
 * @version 1.0
 *
 */
public class CreateReportTest {
    private Facade facade;

    @Before
    public void setUp() throws Exception {
        Facade.initialize();
        facade = Facade.getFacade();
    }

    @Test
    public void testSizeCreateNewReport() {
        int size = facade.getSourceReports().size();
        Location loc = new Location(21.42, 43.12);
        facade.createSourceReport("user1", loc, TypeOfWater.BOTTLED
                , ConditionOfWater.TREATABLECLEAR);
        assertEquals("Number of reports should be " + (size + 1), size + 1,
                facade.getSourceReports().size());
    }

    @Test
    public void testSizeCreateMultiReport() {
        int size = facade.getSourceReports().size();
        Location loc1 = new Location(21.42, 43.12);
        Location loc2 = new Location(15.34, 23.23);
        Location loc3 = new Location(25.42, 12.12);
        facade.createSourceReport("user1", loc1, TypeOfWater.BOTTLED
                , ConditionOfWater.TREATABLECLEAR);
        facade.createSourceReport("user2", loc2, TypeOfWater.SPRING
                , ConditionOfWater.POTABLE);
        facade.createSourceReport("user3", loc3, TypeOfWater.STREAM
                , ConditionOfWater.WASTE);
        assertEquals("Number of reports should be " + (size + 3), size + 3,
                facade.getSourceReports().size());

        //
    }

    @Test
    public void testFindNoExitReportByNumber() {
        int size = facade.getSourceReports().size();
        assertNull("Report " + (size + 1) + " should not be existed", facade
                .getSourceReportByReportNumber(size + 1));

        Location loc = new Location(21.42, 43.12);
        facade.createSourceReport("user1", loc, TypeOfWater.BOTTLED
                , ConditionOfWater.TREATABLECLEAR);
        assertNull("Report " + (size + 2) + " should not be existed", facade
                .getSourceReportByReportNumber(size + 2));

    }

    @Test
    public void testExceptionCreateNewReport() {
        int size = facade.getSourceReports().size();
        try {
            Location loc = new Location(21.42, 43.12);
            facade.createSourceReport("user1", loc, TypeOfWater.BOTTLED
                    , ConditionOfWater.TREATABLECLEAR);
            facade.getSourceReports().get(size);
        } catch (IndexOutOfBoundsException e) {
            fail("Should not get IndexOutOfBoundsException, report " +
                    size +
                    " should in the list");
        }
    }
}
