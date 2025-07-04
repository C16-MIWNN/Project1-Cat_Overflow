package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Have the 'create user form' have a 'confirm password' field
 */

public class NewCookingRecipeUserDTO {

    private String username;
    private String originalUsername;
    private String password;
    private String confirmPassword;
    private String email;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriginalUsername() {
        return originalUsername != null ? originalUsername : username;
    }

    public void setOriginalUsername(String originalUsername) {
        this.originalUsername = originalUsername;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
