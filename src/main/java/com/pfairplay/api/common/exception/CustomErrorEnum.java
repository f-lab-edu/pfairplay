package com.pfairplay.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CustomErrorEnum {
    SOURCE_NOT_FOUND("리소스를 찾을 수 없습니다.", "정보를 등록한 후 다시 이용해주세요."),
    BAD_REQUEST_ERROR("올바르지 않은 형식의 요청입니다.", "요청을 확인해 주세요."),
    INTERNAL_SERVER_ERROR("알 수 없는 오류입니다.", "관리자에게 문의하세요."),
    DUPLICATE_ENTRY("중복된 리소스 입니다.", "이미 등록된 정보가 있습니다.");


    private final String title;
    private final String message;

}
