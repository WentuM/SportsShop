package model;

import lombok.Data;

@Data
public class User {
    public static final String TABLE_NAME = "user";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "warehouse_id";

    private Long id;
    private String name;
    private String password;
    private String email;
    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
