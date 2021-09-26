package com.hungrysharks.conference.dao.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString

@Entity
@Table(name = "conference")
public class ConferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String subject;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "conference"
    )
    private List<ConferenceDateEntity> dates;

    private Integer participantCount;
}
