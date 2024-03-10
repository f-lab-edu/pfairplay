package com.pfairplay.api.repository.member;


import com.pfairplay.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberCustom {
    Optional<Member> findByPhoneNumber(String phoneNumber);
}
