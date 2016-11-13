/**
 * Testing createPurityReport
 */
import model.Facade;
import model.PurityReport;
import model.Location;
import model.OverallCondition;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GetPurityReportByReportNumberTest {

    Facade facade = null;
    private ArrayList<PurityReport> prs;
    int currSize = 0;

    PurityReport added;
    PurityReport added1;
    PurityReport added2;
    PurityReport added3;

    Location location;
    Location location1;
    Location location2;
    Location location3;

    double virusPPM;
    double virusPPM1;
    double virusPPM2;
    double virusPPM3;

    double contaminantPPM;
    double contaminantPPM1;
    double contaminantPPM2;
    double contaminantPPM3;

    Timestamp beforeTime;
    Timestamp beforeTime1;
    Timestamp beforeTime2;
    Timestamp beforeTime3;

    Timestamp afterTime;
    Timestamp afterTime1;
    Timestamp afterTime2;
    Timestamp afterTime3;

    @Before
    public void setUp() {
        Facade.initialize();
        facade = Facade.getFacade();
        prs = facade.getPurityReports();
        currSize = prs.size();
    }

    @Test
    public void testGetsMultiple() {
        location = new Location(21, 22);
        virusPPM = 1.2;
        contaminantPPM = 2.3;
        beforeTime = new Timestamp(System.currentTimeMillis());
        facade.createPurityReport("user2", location, OverallCondition.SAFE, virusPPM, contaminantPPM);
        currSize++;
        afterTime = new Timestamp(System.currentTimeMillis());

        location1 = new Location(21, 22);
        virusPPM1 = 1.2;
        contaminantPPM1 = 2.3;
        beforeTime1 = new Timestamp(System.currentTimeMillis());
        facade.createPurityReport("user3", location1, OverallCondition.UNSAFE, virusPPM1, contaminantPPM1);
        currSize++;
        afterTime1 = new Timestamp(System.currentTimeMillis());

        location2 = new Location(11, 35);
        virusPPM2 = 0.2;
        contaminantPPM2 = 7.1;
        beforeTime2 = new Timestamp(System.currentTimeMillis());
        facade.createPurityReport("user4", location2, OverallCondition.TREATABLE, virusPPM2, contaminantPPM2);
        currSize++;
        afterTime2 = new Timestamp(System.currentTimeMillis());

        location3 = new Location(5, 70);
        virusPPM3 = 2.3;
        contaminantPPM3 = 0.1;
        beforeTime3 = new Timestamp(System.currentTimeMillis());
        facade.createPurityReport("user2", location3, OverallCondition.SAFE, virusPPM3, contaminantPPM3);
        currSize++;
        afterTime3 = new Timestamp(System.currentTimeMillis());

        added = facade.getPurityReportByReportNumber(currSize-3);
        added1 = facade.getPurityReportByReportNumber(currSize-2);
        added2 = facade.getPurityReportByReportNumber(currSize-1);
        added3 = facade.getPurityReportByReportNumber(currSize);

        assertEquals("U2", added.getReporterName());
        assertEquals(location, added.getLocation());
        assertEquals(OverallCondition.SAFE, added.getOverallCondition());
        assertEquals(virusPPM, added.getVirusPPM(), .0001);
        assertEquals(contaminantPPM, added.getContaminantPPM(), .0001);
        assertTrue(added.getCreated().after(beforeTime));
        assertTrue(added.getCreated().before(afterTime) || added.getCreated().equals(afterTime));

        assertEquals("U3", added1.getReporterName());
        assertEquals(location1, added1.getLocation());
        assertEquals(OverallCondition.UNSAFE, added1.getOverallCondition());
        assertEquals(virusPPM1, added1.getVirusPPM(), .0001);
        assertEquals(contaminantPPM1, added1.getContaminantPPM(), .0001);
        assertTrue(added1.getCreated().after(beforeTime1));
        assertTrue(added1.getCreated().before(afterTime1) || added1.getCreated().equals(afterTime1));

        assertEquals("U4", added2.getReporterName());
        assertEquals(location2, added2.getLocation());
        assertEquals(OverallCondition.TREATABLE, added2.getOverallCondition());
        assertEquals(virusPPM2, added2.getVirusPPM(), .0001);
        assertEquals(contaminantPPM2, added2.getContaminantPPM(), .0001);
        assertTrue(added2.getCreated().after(beforeTime2));
        assertTrue(added2.getCreated().before(afterTime2) || added2.getCreated().equals(afterTime2));

        assertEquals("U2", added3.getReporterName());
        assertEquals(location3, added3.getLocation());
        assertEquals(OverallCondition.SAFE, added3.getOverallCondition());
        assertEquals(virusPPM3, added3.getVirusPPM(), .0001);
        assertEquals(contaminantPPM3, added3.getContaminantPPM(), .0001);
        assertTrue(added3.getCreated().after(beforeTime3));
        assertTrue(added3.getCreated().before(afterTime3) || added3.getCreated().equals(afterTime3));
    }

    @Test
    public void testBadGets() {

        //report index off by one higher
        assertNull(facade.getPurityReportByReportNumber(currSize + 1));
        //no report number 0 should exist
        assertNull(facade.getPurityReportByReportNumber(0));


    }
}
