package api.controller;

import api.dto.member.LoginDto;
import api.dto.member.MemberDto;
import api.dto.member.UpdateMemberDto;
import api.dto.member.SignInDto;
import api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-in")
    public MemberDto signIn(@Valid @RequestBody SignInDto signInDto) {
        return memberService.signIn(signInDto);
    }

    @PostMapping("/{id}/sign-out")
    public void signOut(@PathVariable String id) {
        memberService.signOut(id);
    }

    @PostMapping("/login")
    public MemberDto login(@RequestBody LoginDto loginDto) {
        return memberService.login(loginDto);
    }

    @PostMapping("/{id}/logout")
    public void logout(@PathVariable String id) {
        memberService.logout(id);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable String id) {
        return memberService.getMember(id);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@PathVariable String id, @RequestBody UpdateMemberDto updateMemberDto) {
        return memberService.updateMember(id, updateMemberDto);
    }

}
