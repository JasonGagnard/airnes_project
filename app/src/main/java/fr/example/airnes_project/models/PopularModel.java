package fr.example.airnes_project.models;

public class PopularModel {
    String name;
    String description;
    String img_URL;

    PopularModel(String name, String description, String img_URL) {

        this.name = name;
        this.description = description;
        this.img_URL = img_URL;
    }
    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription() {
        this.description = description;
    }
    public String getImg_URL() {
        return img_URL;
    }
    public void setImg_URL() {
        this.img_URL = img_URL;
    }
}
