package com.pfairplay.api.entity;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    @Column(name = "name", columnDefinition = "varchar", nullable = false)
    private String name;

    @Column(name = "birth_year", columnDefinition = "integer default -1", nullable = false)
    private Integer birthYear;

    @Column(name = "activity_areas", columnDefinition = "varchar ''", nullable = false)
    private String activityAreas;

    @Column(name = "game_day_of_weeks", columnDefinition = "varchar ''", nullable = false)
    private String gameDayOfWeeks;

    @Column(name = "join_fee", columnDefinition = "integer default -1", nullable = false)
    private Integer joinFee;

    @Column(name = "monthly_fee", columnDefinition = "integer default -1", nullable = false)
    private Integer monthlyFee;

    @Column(name = "home_stadium", columnDefinition = "varchar default ''", nullable = false)
    private String homeStadium;

    @Column(name = "level", columnDefinition = "integer", nullable = false)
    @ColumnDefault("-1")
    private Integer level;

    @Column(name = "introduction", columnDefinition = "varchar default ''", nullable = false)
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Member manager;

    @OneToMany(mappedBy = "team")
    private List<MemberTeam> memberTeams = new ArrayList<>();

}
