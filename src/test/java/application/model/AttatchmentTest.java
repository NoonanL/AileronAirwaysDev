package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttatchmentTest
{

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void t10testCreateAttatchment() throws Exception {
        Attachment test = new Attachment(
                "testingId",
                "b8147090-f83a-492c-a230-9a2fba426e26",
                "testTitle" );

        System.out.println("Test: Create an Event");
        System.out.println("------------------------");
        test.createAttatchment();

    }

    @After
    public void tearDown() throws Exception {
    }
}
