package com.ecommerce.project.springbootecom.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    Long userId;

    @NotBlank
    @Size(max=20)
    @Column(name="username")
    String username;

    @NotBlank
    @Email
    @Column(name="email")
    String email;

    @NotBlank
    @Size(max=120)
    @Column(name="password")
    String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Setter
    @Getter
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    private Set<Product> products;

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable( name="user_address",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
    private List<Address> addresses=new ArrayList<>();
}