package dto;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

public class UserDto {

    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9@$!%*#?&]+$", message = "Password must contain at least 1 letter, 1 number, and 1 special character")
    private String password;

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
}
