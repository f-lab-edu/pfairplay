package com.pfairplay.mysql.core.repository.member;


import com.pfairplay.mysql.core.config.Querydsl5RepositorySupport;
import com.pfairplay.mysql.core.entity.Member;
import com.pfairplay.mysql.core.entity.QMember;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class MemberRepositoryImpl extends Querydsl5RepositorySupport implements MemberCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public void softDeleteById(String id) {
        getQueryFactory()
                .update(QMember.member)
                .set(QMember.member.deletedAt, LocalDateTime.now())
                .where(idEq(id))
                .execute();
    }

    private Predicate idEq(String id) {
        return id == null ? null : QMember.member.id.eq(id);
    }

}
