package co.istad.springsecuritybasic.model.dto;

import co.istad.springsecuritybasic.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserRequest(

        String email,
        String password,
        Set<String> roles

) {
}
