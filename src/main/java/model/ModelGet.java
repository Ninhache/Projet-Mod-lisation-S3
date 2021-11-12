package model;

public class ModelGet {

    private int id;
    private String name, imglink;

    public ModelGet(int id, String name, String imglink) {
        this.id = id;
        this.name = name;
        this.imglink = imglink;
    }

    @Override
    public String toString() {
        return this.name + " " + this.id + " " + this.imglink;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImglink() {
        return imglink;
    }

}
