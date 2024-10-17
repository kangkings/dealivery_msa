package org.example.board.global.adaptor.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "company-service", url = "http://localhost:8080")
public interface UserServiceClient {

    @GetMapping("/company/companyIdx")
    Long getCompanyIdx();  // companyIdx를 가져오는 API 호출
}
