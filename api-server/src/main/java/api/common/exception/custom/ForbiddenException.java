package api.common.exception.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends CustomException {

    public ForbiddenException() {
        this("요청이 거절 되었습니다.", "올바르지 않은 요청 입니다.");
    }

    public ForbiddenException(String title, String message) {
        super(HttpStatus.CONFLICT, title, message);
    }
}
