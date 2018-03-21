package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttachmentTest
{

    @Before
    public void setUp() throws Exception {
    }

    /*@Test
    public void t10testCreateAttachment() throws Exception {
        Attachment test = new Attachment(
                "testingId",
                "b8147090-f83a-492c-a230-9a2fba426e26",
                "testTitle" );

        System.out.println("Test: Create an Event");
        System.out.println("------------------------");
        test.createAttachment();

    }*/

    @Test
    public void t11uploadFile() throws Exception{
        Attachment test = new Attachment();

        System.out.println("Test: Get Linked Events");
        System.out.println("------------------------");
        test.createAndUploadAttachment("Place the path of the file you want to upload here!");
    }

    @Test
    public void t12downloadfile() throws Exception{
        Attachment test = new Attachment();

        System.out.println("Test: Get Linked Events");
        System.out.println("------------------------");
        test.downloadAttachment("Place the name of the file that has been uploaded you want to get",
                "Place the path for the file to go to");
    }

    @After
    public void tearDown() throws Exception {
    }
}
