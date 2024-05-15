package api.dto.auth;

import api.dto.member.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

    MemberDto memberInfo;

    JwtTokenDto jwtInfo;

    public static SignInResponseDto create(MemberDto memberDto, JwtTokenDto jwtTokenDto) {
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.memberInfo = memberDto;
        signInResponseDto.jwtInfo = jwtTokenDto;
        return signInResponseDto;

    }
}
