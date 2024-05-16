package com.pfairplay.mysql.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "async_ad")
public class AsyncAd {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "member_id")
    private Long memberId;
}
