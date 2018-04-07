package application.repositories;

import application.model.Attachment;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

public class AttachmentRepository {

    private ArrayList<Attachment> objects;

    public AttachmentRepository(){
        this.objects = new ArrayList<>();
    }

    public ArrayList<Attachment> getAttachments(){
        return this.objects;
    }


    public void add(Attachment object) {
        this.objects.add(object);

    }


    public Attachment get(String id) {
        for (Attachment object : this.objects)
            if(object.getAttachmentId().equals(id)){
                return object;
            }
        return null;
    }



    public void remove(String id) {
        Predicate<Attachment> predicate = e->e.getAttachmentId().equals(id);
        this.objects.removeIf(predicate);
        System.out.println("removed from repository");

    }

    public void cleanUpFiles(){
        for(Attachment object : objects) {
            if (object.getHref() != null) {
                //System.out.println(object.getHref());
                File file = new File(object.getHref());
                if(file.exists()){
                    file.delete();
//                if (file.delete()) {
//                    System.out.println("File " + object.getHref() + " deleted successfully.");
//                } else {
//                    System.out.println("File " + object.getHref() + " failed to delete.");
//                }
                }
            }
        }
    }

    @Override
    public String toString(){
        String str = this.objects.toString();
        return str;
    }

}
