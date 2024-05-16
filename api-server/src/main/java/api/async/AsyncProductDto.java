package api.async;

import com.pfairplay.mysql.core.entity.AsyncProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AsyncProductDto {

    private Long id;

    private String name;

    private Long memberId;

    public static AsyncProductDto fromEntity(AsyncProduct asyncProduct) {
        AsyncProductDto asyncProductDto = new AsyncProductDto();
        asyncProductDto.id = asyncProduct.getId();
        asyncProductDto.name = asyncProduct.getName();
        asyncProductDto.memberId = asyncProduct.getMemberId();
        return asyncProductDto;
    }
}
