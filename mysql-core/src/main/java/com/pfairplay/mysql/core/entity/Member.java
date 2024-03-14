package com.pfairplay.mysql.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.unknown;

    @Column(name = "preferred_positions", nullable = false)
    private String preferredPositions = "";

    @Column(name = "access_token", nullable = false)
    private String accessToken = "";

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken = "";

    @OneToMany(mappedBy = "member")
    private List<MemberTeam> memberTeams = new ArrayList<>();

    public void updateMemberInformation(String phoneNumber, String nickname, String preferredPositions) {
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.preferredPositions = preferredPositions;
    }

    public void logout() {
        this.accessToken = "";
        this.refreshToken = "";
    }

}
