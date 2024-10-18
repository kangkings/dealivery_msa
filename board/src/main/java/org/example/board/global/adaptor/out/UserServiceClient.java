package org.example.board.global.adaptor.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8000/api")
public interface UserServiceClient {

    @GetMapping("/company/company-idx")
    Long getCompanyIdx();  // companyIdx를 가져오는 API 호출
}
