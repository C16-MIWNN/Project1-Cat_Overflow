package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * @author Robyn Blignaut, Bas Folkers
 * A concept of an image which belongs to a recipe and can add multiple or a single image.
 */

@Entity
public class Image {

    @Id @GeneratedValue
    private int imageId;

    private String imageName;

//    TODO Make images work with blobs!!!
    @Lob
    private byte[] imageData;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
