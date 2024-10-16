package org.example.auth.global.adaptor.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.auth.domain.company.model.dto.CompanyDto;
import org.example.auth.domain.company.model.entity.Company;
import org.example.auth.domain.company.repository.CompanyRepository;
import org.example.auth.domain.user.model.dto.UserDto;
import org.example.auth.domain.user.model.entity.User;
import org.example.auth.domain.user.repository.UserRepository;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AuthKafkaConsumer {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @KafkaListener(topics = "user_signup_complete", groupId = "auth_group")
    public void user_signup_complete(UserDto.UserSignupComplete userSignupComplete) {
        User newUser = userSignupComplete.toEntity();
        userRepository.save(newUser);
    }

    @KafkaListener(topics = "company_signup_complete", groupId = "auth_group")
    public void company_signup_complete(CompanyDto.CompanySignupComplete companySignupComplete) {
        Company newCompany = companySignupComplete.toEntity();
        companyRepository.save(newCompany);
    }
}