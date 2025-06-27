package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Have the 'create user form' have a 'confirm password' field
 */

public class NewCookingRecipeUserDTO {

    private String username;
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
