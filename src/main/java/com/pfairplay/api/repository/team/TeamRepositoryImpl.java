package com.pfairplay.api.repository.team;


import com.pfairplay.api.config.Querydsl5RepositorySupport;
import com.pfairplay.api.entity.Team;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.pfairplay.api.entity.QTeam.team;

@Repository
public class TeamRepositoryImpl extends Querydsl5RepositorySupport implements TeamCustom {
    public TeamRepositoryImpl() {
        super(Team.class);
    }

    @Override
    public void softDeleteById(long id) {
        getQueryFactory()
                .update(team)
                .set(team.deletedAt, LocalDateTime.now())
                .where(idEq(id))
                .execute();
    }

    private Predicate idEq(long id) {
        return team.id.eq(id);
    }

}
