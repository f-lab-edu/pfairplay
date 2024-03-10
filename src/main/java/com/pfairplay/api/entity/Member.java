package com.pfairplay.api.entity;

import com.pfairplay.api.dto.member.UpdateMemberDto;
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

    @Column(name = "name", columnDefinition = "varchar", nullable = false)
    private String name;

    @Column(name = "nickname", columnDefinition = "varchar", nullable = false)
    private String nickname;

    @Column(name = "birth_date", columnDefinition = "date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone_number", columnDefinition = "varchar", nullable = false)
    private String phoneNumber;


    @Column(name = "gender", columnDefinition = "varchar", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.unknown;

    @Column(name = "preferred_positions", columnDefinition = "varchar", nullable = false)
    private String preferredPositions = "";

    @Column(name = "access_token", columnDefinition = "varchar", nullable = false)
    private String accessToken = "";

    @Column(name = "refresh_token", columnDefinition = "varchar", nullable = false)
    private String refreshToken = "";

    @OneToMany(mappedBy = "member")
    private List<MemberTeam> memberTeams = new ArrayList<>();

    public void updateMemberInformation(UpdateMemberDto updateMemberDto) {
        this.phoneNumber = updateMemberDto.getPhoneNumber();
        this.nickname = updateMemberDto.getNickname();
        this.preferredPositions = String.join(",", updateMemberDto.getPreferredPositions());
    }

    public void logout() {
        this.accessToken = "";
        this.refreshToken = "";
    }

}
