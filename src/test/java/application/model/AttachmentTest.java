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
        test.downloadAttachment("tokyo.png",
                "C:\\Users\\Superleon\\Desktop\\tokyodownload.png");
    }

    @Test
    public void t13testEditTitle() throws Exception{
        Attachment test = new Attachment("testingId", "1967", "Hello");
        System.out.println(test);
        System.out.println("Testing: Edit Timeline Title");
        System.out.println("------------------------");
        test.editAttachmentTitle("This is the changed title");
        System.out.println(test);

    }

    @Test
    public void t14testCreateAttachment() throws Exception{
        Attachment test = new Attachment(
                "1932",
                "Tokyo Test",
                "Tokyo Test Attach" );

        test.createAttachment("C:\\Users\\Superleon\\Desktop\\tokyo.png");

    }


    @Test
    public void t15testDeleteAttachment() throws Exception{
        Attachment test = new Attachment();
        System.out.println("Testing: Delete Timeline");
        System.out.println("------------------------");
        test.deleteAttachment("Enter the id you wish to delete");

    }

////    @Test
////    public void t14testDeleteAttachment() throws Exception{
////        Attachment test = new Attachment("testingId", "1967", "Hello");
////        System.out.println(test);
////        System.out.println("Testing: Edit Timeline Title");
////        System.out.println("------------------------");
////        test.editAttachmentTitle("This is the changed title");
////        System.out.println(test);
////
////    }



    @After
    public void tearDown() throws Exception {
    }
}
