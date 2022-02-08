package com.flat.restful.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "pullRequests")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PullRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private int userId;

    private int baseBranchId;

    private int compareBranchId;

    private String status;

    private Boolean conflicts;

    @CreationTimestamp
    @Column(name = "date_created")
    Date dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    Date lastUpdated;

}
