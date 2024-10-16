package org.example.orders.global.adaptor.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.bouncycastle.internal.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.example.orders.domain.board.repository.ProductBoardRepository;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.company.repository.CompanyRepository;
import org.example.orders.domain.orders.model.event.OrdersEvent;
import org.example.orders.domain.product.model.entity.Product;
import org.example.orders.domain.product.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.example.orders.domain.user.model.dto.UserDto;
import org.example.orders.domain.user.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrdersKafkaConsumer {

    private final ProductBoardRepository productBoardRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @KafkaListener(topics = "board-register", groupId = "orders_group")
    public void consumeBoardRegisterEvent(OrdersEvent.BoardRegisterEvent event) {
        System.out.println("메시지 받음");
        Company company = companyRepository.findById(event.getCompanyIdx()).orElseThrow();
        List<Product> products = event.getProductIdxList().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow()).collect(Collectors.toList());
        productBoardRepository.save(event.toEntity(company,products));
    }
  
    @KafkaListener(topics = "user_signup_complete", groupId = "orders_group")
    public void user_signup_complete(UserDto.UserSignupComplete userSignupComplete) {
        User newUser = userSignupComplete.toEntity();
        userRepository.save(newUser);
    }
}
