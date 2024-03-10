package com.pfairplay.api.controller;

import com.pfairplay.api.dto.team.CreateTeamDto;
import com.pfairplay.api.dto.team.TeamDto;
import com.pfairplay.api.service.TeamService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("")
    public TeamDto createTeam(@RequestBody CreateTeamDto createTeamDto) {
        return teamService.createTeam(createTeamDto);
    }

    @GetMapping("/{id}")
    public TeamDto findTeamById(@PathVariable String id) {
        return teamService.findTeamById(Long.valueOf(id));
    }


}
