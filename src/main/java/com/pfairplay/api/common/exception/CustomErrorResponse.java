package com.pfairplay.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomErrorResponse {

    private final Integer statusCode;

    private final CustomError error;

}
