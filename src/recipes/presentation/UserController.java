package recipes.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    @RequestMapping("register")
    public ResponseEntity<?> register(final @RequestBody @Valid UserModel userModel) {
        if (userService.exists(userModel)) {
            return ResponseEntity.badRequest()
                    .build();
        }
        userService.register(userModel);
        return ResponseEntity.ok().build();
    }
}
