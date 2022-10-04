package com.ahmet.blog_app_hd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String roleName;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users=new ArrayList<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
