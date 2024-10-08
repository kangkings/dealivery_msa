package org.example.auth.global.security.custom.service;

import lombok.RequiredArgsConstructor;
import org.example.auth.global.security.custom.model.dto.CustomCompanyDetails;
import org.example.auth.global.security.custom.model.dto.CustomUserDetails;
import org.example.user.domain.company.model.entity.Company;
import org.example.user.domain.company.repository.CompanyRepository;
import org.example.user.domain.user.model.entity.User;
import org.example.user.domain.user.repository.UserRepository;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String request) throws UsernameNotFoundException {
        throw new InvalidCustomException(BaseResponseStatus.USER_LOGIN_FAIL);
    }

    public UserDetails loadUserByUsername(String email ,String type) throws UsernameNotFoundException {
        if (type.equals("user")){
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException(email)
            );
            if (!user.getEmailStatus()){
                throw new UsernameNotFoundException(email);
            }
            if (!user.getType().equals("inapp")){
                throw new UsernameNotFoundException(email);
            }
            return new CustomUserDetails(user);
        }
        if (type.equals("company")){
            Company company = companyRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException(email)
            );
            if (!company.getEmailStatus()){
                throw new UsernameNotFoundException(email);
            }
            if (!company.getRegStatus()){
                throw new UsernameNotFoundException(email);
            }
            return new CustomCompanyDetails(company);
        }

        throw new UsernameNotFoundException(email);
    }
}
