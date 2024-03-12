package api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomError {
    private final String title;

    private final String message;

    private Map<String,String> errors = new HashMap<>();

    public static CustomError fromCustomErrorEnum(CustomErrorEnum customErrorEnum) {
        return new CustomError(customErrorEnum.getTitle(), customErrorEnum.getMessage());
    }

}
