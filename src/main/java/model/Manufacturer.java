package model;

public class Manufacturer {
    private int id;
    private String name;
    private String description;
    private String manufacturerImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturerImage() {
        return manufacturerImage;
    }

    public void setManufacturerImage(String manufacturerImage) {
        this.manufacturerImage = manufacturerImage;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturerImage='" + manufacturerImage + '\'' +
                '}';
    }
}
