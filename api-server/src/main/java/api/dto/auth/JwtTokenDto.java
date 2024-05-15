package api.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class JwtTokenDto {
    private final String accessToken;

    private final String refreshToken;
}
