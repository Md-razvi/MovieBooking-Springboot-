package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    private String name;
    private String password;
    private String email;
    private  Role role;

}
