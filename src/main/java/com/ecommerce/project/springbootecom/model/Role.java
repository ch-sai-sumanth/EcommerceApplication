package com.ecommerce.project.springbootecom.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(name="role_name")
    private AppRole roleName;


    public Role(AppRole roleName) {
        this.roleName = roleName;
    }


}
