package org.example.board.domain.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.user.model.entity.User;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSignupComplete {
        private Long idx;
        private String name;
        private String email;

        public User toEntity() {
            return User.builder()
                    .idx(this.idx)
                    .name(this.name)
                    .email(this.email)
                    .build();
        }
    }
}
