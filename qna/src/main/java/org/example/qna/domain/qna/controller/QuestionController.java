package org.example.qna.domain.qna.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.example.qna.domain.qna.model.dto.QuestionDto;
import org.example.qna.domain.qna.service.QuestionService;
import org.example.qna.global.common.constants.BaseResponse;
import org.example.qna.global.common.constants.BaseResponseStatus;
import org.example.qna.global.common.constants.SwaggerDescription;
import org.example.qna.global.common.constants.SwaggerExamples;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "문의 등록 API", description = SwaggerDescription.QNA_QUESTION_REQUEST,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = SwaggerExamples.QNA_QUESTION_REQUEST)
                            }
                    )
            ))
    @PostMapping("/create")
    public BaseResponse create(@RequestBody QuestionDto.QuestionCreateRequest request,
                               @RequestHeader("X-User-Email") String email) {
        Long productBoardIdx = request.getProductBoardIdx();  // Request Body로 전달받은 productBoardIdx 사용

        QuestionDto.QuestionCreateResponse response = questionService.createQuestion(request, email, productBoardIdx);
        return new BaseResponse<>(response);
    }

    @Operation(summary = "문의 목록 조회 API", description = "DB에 저장된 문의 목록을 반환합니다.")
    @GetMapping("/list")
    public BaseResponse<List<QuestionDto.QuestionListResponse>> getQuestions() {
        List<QuestionDto.QuestionListResponse> questionList = questionService.getQuestions();
        return new BaseResponse<>(questionList);
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse deleteQuestion(@PathVariable Long id,
                                       @RequestHeader("X-User-Email") String email) {
        questionService.deleteQuestion(id, email);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "문의 목록 조회 API", description = "로그인된 기업 회원이 자신의 게시글에 달린 문의만 조회합니다.")
    @GetMapping("/list/company")
    public BaseResponse<List<QuestionDto.QuestionListResponse>> getCompanyQuestions(
            @RequestHeader("X-User-Email") String companyEmail
    ) {
        List<QuestionDto.QuestionListResponse> questionList = questionService.getQuestionsByCompanyEmail(companyEmail);
        return new BaseResponse<>(questionList);
    }

    @Operation(summary = "로그인된 사용자의 문의 목록 조회 API", description = "로그인된 사용자가 작성한 문의 목록만 조회합니다.")
    @GetMapping("/list/my")
    public BaseResponse<List<QuestionDto.QuestionListResponse>> getMyQuestions(
            @RequestHeader("X-User-Email") String email) {
        List<QuestionDto.QuestionListResponse> questionList = questionService.getQuestionsByUserEmail(email);
        return new BaseResponse<>(questionList);
    }

    @Operation(summary = "문의 수정 API", description = "문의 제목과 내용을 수정합니다.")
    @PutMapping("/update/{id}")
    public BaseResponse updateQuestion(@PathVariable Long id,
                                       @RequestBody QuestionDto.QuestionUpdateRequest request,
                                       @RequestHeader("X-User-Email") String email) {
        questionService.updateQuestion(id, request, email);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
