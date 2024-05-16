package api.async;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AsyncAllDto {

    private List<AsyncMemberDto> members = new ArrayList<>();

    private List<AsyncProductDto> products = new ArrayList<>();

    private List<AsyncAdDto> ad = new ArrayList<>();

}
