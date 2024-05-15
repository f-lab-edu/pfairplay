package api.controller;

import api.dto.team.CreateTeamDto;
import api.dto.team.TeamDto;
import api.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // TODO : 전체 메소드 JWT기반 Principal을 받아서 처리하도록 필요
    @PostMapping("")
    public TeamDto createTeam(@RequestBody CreateTeamDto createTeamDto) {
        return teamService.createTeam(createTeamDto);
    }

    @GetMapping("/{id}")
    public TeamDto findTeamById(@PathVariable String id) {
        return teamService.findTeamById(Long.valueOf(id));
    }


}
