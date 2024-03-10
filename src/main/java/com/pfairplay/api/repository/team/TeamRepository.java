package com.pfairplay.api.repository.team;


import com.pfairplay.api.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustom {
}
