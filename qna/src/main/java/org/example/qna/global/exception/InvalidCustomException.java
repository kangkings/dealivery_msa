package org.example.qna.global.exception;

import lombok.Getter;
import org.example.qna.global.common.constants.BaseResponseStatus;


@Getter
public class InvalidCustomException extends RuntimeException {
    private final BaseResponseStatus status;

    public InvalidCustomException(BaseResponseStatus status) {
        this.status = status;
    }
}
