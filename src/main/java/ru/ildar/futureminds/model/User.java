package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ildar.futureminds.dto.Role;

import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, max = 32)
    private String email;
    @Size(min = 4, max = 32)
    private String password;

    @Size(min = 2, max = 32)
    private String firstName;
    @Size(min = 0, max = 32)
    private String lastName;
    @Size(min = 0, max = 32)
    private String middleName;
    @Column(name =  "birthday")
    private Date birthday;
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<UserCourse> courses;

    public User(String email, String password, String firstName, String lastName,
                String middleName, Date birthday, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.roles = roles;
    }
}
