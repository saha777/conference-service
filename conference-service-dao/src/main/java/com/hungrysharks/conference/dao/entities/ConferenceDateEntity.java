package com.hungrysharks.conference.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "conference_date")
public class ConferenceDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "DATE")
    private Date date;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="conference_id", nullable = false)
    private ConferenceEntity conference;

    public ConferenceDateEntity(Date date, ConferenceEntity conference) {
        this.date = date;
        this.conference = conference;
    }
}
