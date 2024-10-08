package org.example.company.domain.company.service;


import lombok.RequiredArgsConstructor;
import org.example.company.domain.company.model.dto.CompanyDto;
import org.example.company.domain.company.model.entity.Company;
import org.example.company.domain.company.repository.CompanyRepository;
import org.example.company.global.constants.BaseResponseStatus;
import org.example.company.global.exception.InvalidCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Boolean isExist(String email){
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isPresent()){
            throw new InvalidCustomException(BaseResponseStatus.USER_SIGNUP_FAIL_ALREADY_EXIST);
        }

        return true;
    }
    public void signup(CompanyDto.CompanySignupRequest request) {
        Company newCompany = companyRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));
    }
}