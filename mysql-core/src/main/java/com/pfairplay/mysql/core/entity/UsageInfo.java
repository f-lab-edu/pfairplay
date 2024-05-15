package com.pfairplay.mysql.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usage_info")
public class UsageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    public static UsageInfo create(Member member, String accessToken, String refreshToken) {
        UsageInfo usageInfo = new UsageInfo();
        usageInfo.member = member;
        usageInfo.accessToken = accessToken;
        usageInfo.refreshToken = refreshToken;
        return usageInfo;
    }

    public void logout() {
        accessToken = "";
        refreshToken = "";
    }
}
