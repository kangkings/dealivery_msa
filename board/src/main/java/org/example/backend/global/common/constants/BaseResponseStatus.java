package org.example.backend.global.common.constants;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 모든 요청 성공 1000
    SUCCESS(true, 1000, "요청이 성공하였습니다."),

    // 상품게시글 기능 4000
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_TITLE(false, 4000, "상품게시글 등록에 실패했습니다. 제목을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_TITLE(false, 4001, "상품게시글 등록에 실패했습니다. 유효한 제목을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_IMAGE(false, 4002, "상품게시글 등록에 실패했습니다. 상품 이미지를 1장이상 등록해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_UPLOAD_IMAGE(false, 4003, "상품 이미지 파일 업로드를 실패했습니다"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_START_TIME(false, 4010, "상품게시글 등록에 실패했습니다. 상품 판매 시작 시간을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_END_TIME(false, 4011, "상품게시글 등록에 실패했습니다. 상품 판매 종료 시간을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_START_TIME(false, 4012, "상품게시글 등록에 실패했습니다. 유효한 시작 시간이 아닙니다."),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_END_TIME(false, 4013, "상품게시글 등록에 실패했습니다. 유효한 종료 시간이 아닙니다."),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_MINUTE(false, 4014, "상품게시글 등록에 실패했습니다. 유효한 시간(minute)이 아닙니다."),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_PRODUCT_NAME(false, 4020, "상품게시글 등록에 실패했습니다. 상품 이름을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_STOCK(false, 4021, "상품게시글 등록에 실패했습니다. 상품 재고를 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_PRICE(false, 4022, "상품게시글 등록에 실패했습니다. 상품 가격을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_PRODUCT_NAME(false, 4023, "상품게시글 등록에 실패했습니다. 유효한 상품 이름을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_PRODUCT_STOCK(false, 4024, "상품게시글 등록에 실패했습니다. 유효한 상품 재고값을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_PRODUCT_PRICE(false, 4025, "상품게시글 등록에 실패했습니다. 유효한 상품 가격을 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_EMPTY_CATEGORY(false, 4030, "상품게시글 등록에 실패했습니다. 카테고리를 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_INVALID_CATEGORY(false, 4031, "상품게시글 등록에 실패했습니다. 유효한 카테고리가 아닙니다. 다시 입력해주세요"),
    PRODUCT_BOARD_REGISTER_FAIL_START_TIME_PASSED(false, 4040, "상품게시글 등록에 실패했습니다. 시작 시간이 이미 지나 게시글을 수정할 수 없습니다."),
    PRODUCT_BOARD_REMOVE_FAIL_START_TIME_PASSED(false, 4050, "상품게시글 삭제에 실패했습니다. 시작 시간이 이미 지나 게시글을 삭제할 수 없습니다."),
    PRODUCT_BOARD_LIST_FAIL(false, 4100, "상품게시글 조회에 실패했습니다."),
    PRODUCT_BOARD_DETAIL_FAIL(false, 4200, "상품 상세 조회에 실패했습니다."),

    // 모든 요청 실패 9000
    FAIL(false, 9000, "요청에 실패했습니다. 관리자에게 문의해주세요."),
    FAIL_UNAUTHORIZED(false, 9001, "요청이 실패했습니다. 권한이 없습니다.");

    private boolean isSuccess;
    private Integer code;
    private String message;

    BaseResponseStatus(Boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
     }
    }
