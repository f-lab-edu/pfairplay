package com.pfairplay.api.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateMemberDto {

    private String nickname;

    private String phoneNumber;

    private List<String> preferredPositions;

}
