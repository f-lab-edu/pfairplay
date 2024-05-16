package api.async;

import com.pfairplay.mysql.core.entity.AsyncAd;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AsyncAdDto {

    private Long id;

    private String name;

    private Long memberId;

    public static AsyncAdDto fromEntity(AsyncAd asyncAd) {
        AsyncAdDto asyncAdDto = new AsyncAdDto();
        asyncAdDto.id = asyncAd.getId();
        asyncAdDto.name = asyncAd.getName();
        asyncAdDto.memberId = asyncAd.getMemberId();
        return asyncAdDto;
    }
}
