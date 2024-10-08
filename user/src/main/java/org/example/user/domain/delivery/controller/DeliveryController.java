package org.example.user.domain.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.user.domain.delivery.model.dto.DeliveryDto;
import org.example.user.domain.delivery.service.DeliveryService;
import org.example.user.global.common.constants.BaseResponse;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;
import org.example.user.global.security.custom.model.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public BaseResponse getList(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        List<DeliveryDto.DeliveryResponse> deliveryResponses = deliveryService.getList(userDetails.getIdx());
        return new BaseResponse(deliveryResponses);
    }

    @PostMapping("")
    public BaseResponse createDelivery(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody DeliveryDto.CreateDeliveryRequest request
    ){
        deliveryService.createDelivery(userDetails.getIdx(),request);
        return new BaseResponse();
    }

    @DeleteMapping("/{idx}")
    public BaseResponse deleteDelivery(
            @PathVariable Long idx
    ){
        if (idx == null || idx <= 0){
            throw new InvalidCustomException(BaseResponseStatus.FAIL);
        }
        if(!deliveryService.isExist(idx)){
        throw new InvalidCustomException(BaseResponseStatus.USER_DELIVERY_REMOVE_FAIL_NO_MATCH_DELIVERY);
        }
        deliveryService.deleteDelivery(idx);
        return new BaseResponse();
    }

    @PatchMapping("")
    public BaseResponse setDefault(
            @Valid @RequestBody DeliveryDto.SetDefaultRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        if (request.getIdx() == null || request.getIdx() <= 0){
            throw new InvalidCustomException(BaseResponseStatus.FAIL);
        }
        deliveryService.setDefault(request.getIdx(), userDetails.getIdx());

        return new BaseResponse();
    }

    @PutMapping("")
    public BaseResponse editDelivery(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody DeliveryDto.EditDeliveryRequest request
    ){
        deliveryService.editDelivery(userDetails.getIdx(),request);
        return new BaseResponse();
    }

}
