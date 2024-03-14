package api.controller;

import api.service.TeamService;
import api.dto.team.CreateTeamDto;
import api.dto.team.TeamDto;
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
