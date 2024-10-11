package org.example.auth.global.adaptor.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthKafkaConsumer {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @KafkaListener(topics = "user_signup_complete", groupId = "auth_group")
    public void user_signup_complete(ConsumerRecord<String, String> record) throws IOException {
        // ConsumerRecord에서 value 추출
        String messageValue = record.value();
        System.out.println("Received message value: " + messageValue);

        // JSON 데이터를 Map으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> userData = objectMapper.readValue(messageValue, Map.class);

        System.out.println("Received Map data: " + userData);

        // 이후 userData에서 데이터를 처리하는 로직
        UserDto.UserSignupComplete userSignupComplete = UserDto.UserSignupComplete.builder()
                .idx(Long.parseLong(userData.get("idx").toString()))
                .email(userData.get("email").toString())
                .password(userData.get("password").toString())
                .role(userData.get("role").toString())
                .type(userData.get("type") != null ? userData.get("type").toString() : null)
                .build();

        // DTO를 User 엔티티로 변환하여 DB에 저장
        User newUser = User.builder()
                .idx(userSignupComplete.getIdx())
                .email(userSignupComplete.getEmail())
                .password(passwordEncoder.encode(userSignupComplete.getPassword()))  // 비밀번호 암호화
                .role(userSignupComplete.getRole())
                .type(userSignupComplete.getType())
                .build();

        userRepository.save(newUser);
    }

    @KafkaListener(topics = "company_signup_complete", groupId = "auth_group")
    public void company_signup_complete(ConsumerRecord<String, String> record) throws IOException {
        // ConsumerRecord에서 value 추출
        String messageValue = record.value();
        System.out.println("Received message value: " + messageValue);

        // JSON 데이터를 Map으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> companyData = objectMapper.readValue(messageValue, Map.class);

        System.out.println("Received Map data: " + companyData);

        // 이후 CompanyData에서 데이터를 처리하는 로직
        UserDto.UserSignupComplete companySignupComplete = UserDto.UserSignupComplete.builder()
                .idx(Long.parseLong(companyData.get("idx").toString()))
                .email(companyData.get("email").toString())
                .password(companyData.get("password").toString())
                .role(companyData.get("role").toString())
                .build();

        // DTO를 User 엔티티로 변환하여 DB에 저장
        Company newCompany = Company.builder()
                .idx(companySignupComplete.getIdx())
                .email(companySignupComplete.getEmail())
                .password(passwordEncoder.encode(companySignupComplete.getPassword()))  // 비밀번호 암호화
                .role(companySignupComplete.getRole())
                .build();

        companyRepository.save(newCompany);
    }
}