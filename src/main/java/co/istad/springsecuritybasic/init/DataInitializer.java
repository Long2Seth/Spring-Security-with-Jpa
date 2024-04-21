package co.istad.springsecuritybasic.init;

import co.istad.springsecuritybasic.model.Authority;
import co.istad.springsecuritybasic.model.Role;
import co.istad.springsecuritybasic.repository.AuthorityRepository;
import co.istad.springsecuritybasic.repository.RoleRepository;
import co.istad.springsecuritybasic.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @PostConstruct
    public void initData(){
        initAuthority();
        initRole();
    }

    private void initAuthority(){
        List<String> authorities = List.of("READ","WRITE","DELETE");
        if(authorityRepository.count() == 0){
            for (String authority : authorities) {
                Authority newAuthority = new Authority();
                newAuthority.setName(authority);
                authorityRepository.save(newAuthority);
            }
        }

    }

    private void initRole(){
        if(roleRepository.count() == 0){
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setDescription("User Role");
            userRole.setAuthorities(Set.of(authorityRepository.findByName("READ").get()));
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescription("Admin Role");
            adminRole.setAuthorities(new HashSet<>(authorityRepository.findAll()));
            roleRepository.save(adminRole);
        }
    }

}
