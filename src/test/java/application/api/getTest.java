package application.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetTimeline() throws Exception {
        Get testGet = new Get();
        testGet.get("/Timeline/GetTimeline","TimelineId", "032943");
    }

    @Test
    public void testGetTimelines() throws Exception {
        Get testGet = new Get();
        testGet.get("/Timeline/GetTimelines","", "");
    }

    @Test
    public void testGetEvent() throws Exception{
        Get testGet = new Get();
        testGet.get("/TimelineEvent/GetTimelineEvent","TimelineEventId","123456");
    }

    @After
    public void tearDown() throws Exception {
    }
}