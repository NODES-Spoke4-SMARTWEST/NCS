package it.univda.nodes.dto;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;


public class UserDto{

    private Long id;

    //@NotEmpty(message = "Name should not be empty")

    //@Email
    private String name;

    //@NotEmpty(message = "Password should not be empty")
    private String password;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}