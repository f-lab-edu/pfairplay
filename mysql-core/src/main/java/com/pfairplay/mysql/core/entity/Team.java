package com.pfairplay.mysql.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@Table(name = "team")
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Column(name = "activity_areas", nullable = false)
    private String activityAreas;

    @Column(name = "game_day_of_weeks", nullable = false)
    private String gameDayOfWeeks;

    @Column(name = "join_fee", nullable = false)
    private Integer joinFee;

    @Column(name = "monthly_fee", nullable = false)
    private Integer monthlyFee;

    @Column(name = "home_stadium", nullable = false)
    private String homeStadium;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Member manager;

    @OneToMany(mappedBy = "team")
    private List<MemberTeam> memberTeams = new ArrayList<>();

}
