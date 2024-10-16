package org.example.board.global.adaptor.in;

import lombok.AllArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.repository.ProductBoardRepository;
import org.example.board.domain.company.model.dto.CompanyDto;
import org.example.board.domain.company.model.entity.Company;
import org.example.board.domain.company.repository.CompanyRepository;
import org.example.board.domain.user.model.dto.UserDto;
import org.example.board.domain.user.model.entity.User;
import org.example.board.domain.user.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardKafkaConsumer {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @KafkaListener(topics = "board-register", groupId = "board_group")
    public void consumeBoardRegisterEvent(BoardRegisterEvent event) {
    }

    @KafkaListener(topics = "user_signup_complete", groupId = "board_group")
    public void user_signup_complete(UserDto.UserSignupComplete userSignupComplete) {
        User newUser = userSignupComplete.toEntity();
        userRepository.save(newUser);
    }

    @KafkaListener(topics = "company_signup_complete", groupId = "board_group")
    public void company_signup_complete(CompanyDto.CompanySignupComplete companySignupComplete) {
        Company newCompany = companySignupComplete.toEntity();
        companyRepository.save(newCompany);
    }
}

