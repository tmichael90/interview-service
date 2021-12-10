package org.tmichael.interviewservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published", nullable = false)
    private boolean published;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public Tutorial(String title, String description, boolean published, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.comments = comments;
    }
}
