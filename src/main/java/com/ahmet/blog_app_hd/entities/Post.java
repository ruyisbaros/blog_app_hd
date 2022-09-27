package com.ahmet.blog_app_hd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;
    private String content;
    private Date createdDate;

    @OneToOne(fetch = FetchType.EAGER)
    private Image postImage;

    @ManyToOne
    //@JoinColumn(name = "")
    private User user;

    @ManyToOne
    private Category category;


}
