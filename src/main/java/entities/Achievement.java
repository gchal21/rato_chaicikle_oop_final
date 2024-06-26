package entities;

import java.sql.Timestamp;

public class Achievement {
    private String name;
    private String description;
    private String imageUrl;
    private Timestamp acquiredDate; //todo: check the type is it ok?


    public Achievement(String name, String description, String imageUrl, Timestamp acquireDate){
        //this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.acquiredDate=acquireDate;
    }

//    public int getId() {
//        return id;
//    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public Timestamp getAcquiredDate(){
        return acquiredDate;
    }

    public void setName(String newName){
        name=newName;
    }

    public void setDescription(String newDescription){
        description=newDescription;
    }

    public void setImageUrl(String newImageUrl){
        imageUrl=newImageUrl;
    }

    public void setAcquiredDate(Timestamp newAcquiredDate){
        acquiredDate=newAcquiredDate;
    }


}
