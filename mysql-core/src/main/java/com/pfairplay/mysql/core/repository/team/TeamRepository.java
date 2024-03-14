package com.pfairplay.mysql.core.repository.team;


import com.pfairplay.mysql.core.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustom {
}
