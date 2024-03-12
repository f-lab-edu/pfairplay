package api.dto.team;

import com.pfairplay.mysql.core.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateTeamDto {

    private String name;

    private Integer birthYear;

    private List<String> activityAreas;

    private List<String> gameDayOfWeeks;

    private Integer joinFee;

    private Integer monthlyFee;

    private String homeStadium;

    private String introduction;

    public Team toEntity() {
        Team team = new Team();
        team.setName(name);
        team.setBirthYear(birthYear);
        team.setActivityAreas(String.join(",", activityAreas));
        team.setGameDayOfWeeks(String.join(",", gameDayOfWeeks));
        team.setJoinFee(joinFee);
        team.setMonthlyFee(monthlyFee);
        team.setHomeStadium(homeStadium);
        team.setIntroduction(introduction);
        return team;
    }

}
