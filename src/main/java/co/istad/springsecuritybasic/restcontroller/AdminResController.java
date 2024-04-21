package co.istad.springsecuritybasic.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminResController {

    @GetMapping
    public String getAllUser() {
        return "Get all users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Delete user with id " + id;
    }

}
