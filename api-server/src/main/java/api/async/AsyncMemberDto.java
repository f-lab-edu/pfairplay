package api.async;

import com.pfairplay.mysql.core.entity.AsyncMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AsyncMemberDto {

    private Long id;

    public static AsyncMemberDto fromEntity(AsyncMember asyncMember) {
        AsyncMemberDto asyncMemberDto = new AsyncMemberDto();
        asyncMemberDto.id = asyncMember.getId();
        return asyncMemberDto;
    }
}
