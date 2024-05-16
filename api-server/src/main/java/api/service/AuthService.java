package api.service;

import api.common.auth.JwtProvider;
import api.common.exception.custom.DuplicateKeyException;
import api.common.exception.custom.ForbiddenException;
import api.common.exception.custom.SourceNotFoundException;
import api.dto.auth.JwtTokenDto;
import api.dto.auth.SignInRequestDto;
import api.dto.auth.SignInResponseDto;
import api.dto.member.LoginDto;
import api.dto.member.MemberDto;
import com.pfairplay.mysql.core.entity.Member;
import com.pfairplay.mysql.core.entity.UsageInfo;
import com.pfairplay.mysql.core.repository.member.MemberRepository;
import com.pfairplay.mysql.core.repository.usageInfo.UsageInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final UsageInfoRepository usageInfoRepository;

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        // 이메일로 유저 조회
        Optional<Member> duplicatedMember = memberRepository.findByEmail(signInRequestDto.getPhoneNumber());

        // 이메일 중복 체크
        if (duplicatedMember.isPresent()) {
            throw new DuplicateKeyException("중복된 리소스 입니다.", "이미 사용중인 이메일 입니다.");
        }

        // 회원 가입
        Member savedMember = memberRepository.save(signInRequestDto.toEntity());

        // 토큰 발급
        JwtTokenDto jwtTokenDto = jwtProvider.generateToken(savedMember.getId());

        // 토큰정보 저장
        UsageInfo usageInfo = UsageInfo.create(savedMember, jwtTokenDto.getAccessToken(), jwtTokenDto.getRefreshToken());
        usageInfoRepository.save(usageInfo);

        return SignInResponseDto.create(MemberDto.fromEntity(savedMember), jwtTokenDto);
    }

    // TODO : Member와 엮인 Cascade 설정 [UsageInfo]
    // TODO : Team리더인 멤버가 있을 경우 팀삭제, 리더 변경 후 삭제하도록 요청
    public void signOut(String id) {
        // id로 삭제할 멤버 조회
        Member member = memberRepository
                .findById(id)
                .orElseThrow(SourceNotFoundException::new);

        memberRepository.delete(member);
    }

    public SignInResponseDto login(LoginDto loginDto) {
        // 이메일로 멤버 조회
        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(SourceNotFoundException::new);

        // 비밀번호 확인
        if (!loginDto.getPassword().equals(member.getPassword())) {
            throw new ForbiddenException("올바르지 않은 요청입니다.", "비밀번호를 확인해 주세요.");
        }

        // 토큰 발급
        JwtTokenDto jwtTokenDto = jwtProvider.generateToken(member.getId());

        // 토큰정보 저장
        UsageInfo usageInfo = UsageInfo.create(member, jwtTokenDto.getAccessToken(), jwtTokenDto.getRefreshToken());
        usageInfoRepository.save(usageInfo);

        return SignInResponseDto.create(MemberDto.fromEntity(member), jwtTokenDto);
    }

    public void logout(String id) {
        // id로 로그아웃할 멤버 조회
        UsageInfo usageInfo = usageInfoRepository
                .findByMemberId(id)
                .orElseThrow(SourceNotFoundException::new);

        usageInfo.logout();
    }
}
