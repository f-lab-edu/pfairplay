package api.dto.member;

import api.common.util.DateStringConverter;
import com.pfairplay.mysql.core.entity.Gender;
import com.pfairplay.mysql.core.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MemberDto {

    private String id;
    private String name;
    private String nickname;
    private String birthDate;
    private String phoneNumber;
    private Gender gender;
    private List<String> preferredPositions;

    public static MemberDto fromEntity(Member m) {
        MemberDto memberDto = new MemberDto();
        memberDto.id = m.getId();
        memberDto.name = m.getName();
        memberDto.nickname = m.getNickname();
        memberDto.birthDate = DateStringConverter.toLocalDateFormatString(m.getBirthDate());
        memberDto.phoneNumber = m.getPhoneNumber();
        memberDto.gender = m.getGender();
        memberDto.preferredPositions = Arrays.stream(m.getPreferredPositions().split(",")).toList();
        return memberDto;
    }
}
