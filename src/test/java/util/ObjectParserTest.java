package util;

import application.api.Get;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ObjectParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetTimelines() throws Exception {
        Get testGet = new Get();
        testGet.get("/Timeline/GetTimeline","TimelineId", "1234567");
    }

    @After
    public void tearDown() throws Exception {
    }
}