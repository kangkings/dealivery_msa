package org.example.user.domain.company.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.user.domain.company.model.dto.CompanyDto;
import org.example.user.domain.company.model.entity.Company;
import org.example.user.domain.company.repository.CompanyRepository;
import org.example.user.global.adaptor.out.CompanyKafkaProducer;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CompanyKafkaProducer companyKafkaProducer;

    public Boolean isExist(String email){
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isPresent()){
            throw new InvalidCustomException(BaseResponseStatus.USER_SIGNUP_FAIL_ALREADY_EXIST);
        }

        return true;
    }

    @Transactional
    public boolean signup(CompanyDto.CompanySignupRequest request) {
        Company newCompany = companyRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));

        companyKafkaProducer.sendSignupMessage(newCompany.toCompanySignupComplete());
        return true;
    }
}