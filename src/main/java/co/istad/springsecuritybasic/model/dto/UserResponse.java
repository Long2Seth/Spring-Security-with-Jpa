package co.istad.springsecuritybasic.model.dto;


import co.istad.springsecuritybasic.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(
        String id,
        String email,
        Set<String> roles

) {
}
