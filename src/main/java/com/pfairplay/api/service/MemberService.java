package com.pfairplay.api.service;


import com.pfairplay.api.common.exception.CustomErrorEnum;
import com.pfairplay.api.common.exception.CustomException;
import com.pfairplay.api.dto.member.LoginDto;
import com.pfairplay.api.dto.member.MemberDto;
import com.pfairplay.api.dto.member.SignInDto;
import com.pfairplay.api.dto.member.UpdateMemberDto;
import com.pfairplay.api.entity.Member;
import com.pfairplay.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberDto signIn(SignInDto signInDto) {
        Optional<Member> duplicatedMember = memberRepository.findByPhoneNumber(signInDto.getPhoneNumber());

        if (duplicatedMember.isPresent()) {
            throw new CustomException(HttpStatus.CONFLICT, CustomErrorEnum.DUPLICATE_ENTRY);
        }

        Member savedMember = memberRepository.save(signInDto.toEntity());
        return MemberDto.fromEntity(savedMember);
    }

    public MemberDto login(LoginDto loginDto) {
        Member member = memberRepository.findByPhoneNumber(loginDto.getPhoneNumber())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        return MemberDto.fromEntity(member);
    }

    public void signOut(String id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        memberRepository.softDeleteById(id);
        member.softDelete();
    }

    @Transactional(readOnly = true)
    public MemberDto getMember(String id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        return MemberDto.fromEntity(member);

    }

    public MemberDto updateMember(String id, UpdateMemberDto updateMemberDto) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        member.updateMemberInformation(updateMemberDto);
        return MemberDto.fromEntity(member);
    }

    public void logout(String id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        member.logout();
    }

}

