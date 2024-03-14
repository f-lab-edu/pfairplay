package api.dto.team;

import com.pfairplay.mysql.core.entity.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class TeamDto {

    private long id;
    private String name;

    private Integer birthYear;

    private List<String> activityAreas;

    private List<String> gameDayOfWeeks;

    private Integer joinFee;

    private Integer monthlyFee;

    private String homeStadium;

    private String introduction;

    public static TeamDto fromEntity(Team t) {
        TeamDto teamDto = new TeamDto();
        teamDto.id = t.getId();
        teamDto.name = t.getName();
        teamDto.birthYear = t.getBirthYear();
        teamDto.activityAreas = Arrays.stream(t.getActivityAreas().split(",")).toList();
        teamDto.gameDayOfWeeks = Arrays.stream(t.getGameDayOfWeeks().split(",")).toList();
        teamDto.joinFee = t.getJoinFee();
        teamDto.monthlyFee = t.getMonthlyFee();
        teamDto.homeStadium = t.getHomeStadium();
        teamDto.introduction = t.getIntroduction();
        return teamDto;
    }
}
