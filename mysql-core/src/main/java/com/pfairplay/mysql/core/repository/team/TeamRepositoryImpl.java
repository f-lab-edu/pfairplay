package com.pfairplay.mysql.core.repository.team;


import com.pfairplay.mysql.core.config.Querydsl5RepositorySupport;
import com.pfairplay.mysql.core.entity.QTeam;
import com.pfairplay.mysql.core.entity.Team;
import com.querydsl.core.types.Predicate;

import java.time.LocalDateTime;


public class TeamRepositoryImpl extends Querydsl5RepositorySupport implements TeamCustom {
    public TeamRepositoryImpl() {
        super(Team.class);
    }

    @Override
    public void softDeleteById(long id) {
        getQueryFactory()
                .update(QTeam.team)
                .set(QTeam.team.deletedAt, LocalDateTime.now())
                .where(idEq(id))
                .execute();
    }

    private Predicate idEq(long id) {
        return QTeam.team.id.eq(id);
    }

}
