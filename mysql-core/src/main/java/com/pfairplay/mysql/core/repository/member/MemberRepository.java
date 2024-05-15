package com.pfairplay.mysql.core.repository.member;


import com.pfairplay.mysql.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member, String>, MemberCustom {
    Optional<Member> findByEmail(String email);
}
