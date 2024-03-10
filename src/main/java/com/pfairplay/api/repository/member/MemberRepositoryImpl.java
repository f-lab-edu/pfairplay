package com.pfairplay.api.repository.member;


import com.pfairplay.api.config.Querydsl5RepositorySupport;
import com.pfairplay.api.entity.Member;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.pfairplay.api.entity.QMember.member;

@Repository
public class MemberRepositoryImpl extends Querydsl5RepositorySupport implements MemberCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public void softDeleteById(String id) {
        getQueryFactory()
                .update(member)
                .set(member.deletedAt, LocalDateTime.now())
                .where(idEq(id))
                .execute();
    }

    private Predicate idEq(String id) {
        return id == null ? null : member.id.eq(id);
    }

}
