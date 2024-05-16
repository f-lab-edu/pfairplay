package api.service;


import api.common.exception.custom.SourceNotFoundException;
import api.dto.member.MemberDto;
import api.dto.member.UpdateMemberDto;
import com.pfairplay.mysql.core.entity.Member;
import com.pfairplay.mysql.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberDto getMember(String id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(SourceNotFoundException::new);
        return MemberDto.fromEntity(member);

    }

    public MemberDto updateMember(String id, UpdateMemberDto updateMemberDto) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(SourceNotFoundException::new);

        member.updateMemberInformation(
                updateMemberDto.getPhoneNumber(),
                updateMemberDto.getNickname(),
                String.join(",", updateMemberDto.getPreferredPositions())
        );
        return MemberDto.fromEntity(member);
    }

}

