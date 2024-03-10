package com.pfairplay.api.service;


import com.pfairplay.api.common.exception.CustomErrorEnum;
import com.pfairplay.api.common.exception.CustomException;
import com.pfairplay.api.dto.team.CreateTeamDto;
import com.pfairplay.api.dto.team.TeamDto;
import com.pfairplay.api.entity.Team;
import com.pfairplay.api.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamDto createTeam(CreateTeamDto createTeamDto) {
        Team savedTeam = teamRepository.save(createTeamDto.toEntity());
        teamRepository.flush();
        Optional<Team> foundTeam = teamRepository.findById(savedTeam.getId());
        System.out.println(foundTeam.get().getJoinFee());
        return TeamDto.fromEntity(savedTeam);
    }

    public TeamDto findTeamById(Long id) {
        Team foundTeam = teamRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorEnum.SOURCE_NOT_FOUND));

        return TeamDto.fromEntity(foundTeam);

    }
}

