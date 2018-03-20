package application.repositories;

import application.model.Attachment;
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
            if(object.getId().equals(id)){
                return object;
            }
        return null;
    }



    public void remove(String id) {
        Predicate<Attachment> predicate = e->e.getId().equals(id);
        this.objects.removeIf(predicate);

    }
    
    @Override
    public String toString(){
        String str = this.objects.toString();
        return str;
    }

}
