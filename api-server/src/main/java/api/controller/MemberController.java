package api.controller;

import api.dto.member.MemberDto;
import api.dto.member.UpdateMemberDto;
import api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // TODO : 전체 메소드 JWT기반 Principal을 받아서 처리하도록 필요
    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable String id) {
        return memberService.getMember(id);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@PathVariable String id, @RequestBody UpdateMemberDto updateMemberDto) {
        return memberService.updateMember(id, updateMemberDto);
    }

}
