package packt.java9.by.example.mybusiness.productinformation;

import java.net.URI;

public class ProductInformation {
    String id;
    String title;
    String description;
    final double size[] = new double[3];
    double weight;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double[] getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }
}
