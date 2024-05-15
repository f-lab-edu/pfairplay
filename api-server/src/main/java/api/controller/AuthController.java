package api.controller;

import api.dto.auth.SignInRequestDto;
import api.dto.auth.SignInResponseDto;
import api.dto.member.LoginDto;
import api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        return authService.signIn(signInRequestDto);
    }

    @PostMapping("/sign-out")
    // TODO : 회원탈퇴 메소드 리팩토링 필요
    public void signOut(Principal p) {
        authService.signOut(p.getName());
    }

    @PostMapping("/login")
    public SignInResponseDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    // TODO : 로그아웃 메소드 리팩토링 필요
    @PostMapping("/logout")
    public void logout(@PathVariable String id) {
        authService.logout(id);
    }
}
