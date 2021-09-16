package recipes.presentation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserModel {
    @Email(regexp = ".+@.+\\..+")
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 8)
    private String password;


    public UserDetails toUserDetails(PasswordEncoder passwordEncoder) {
        return User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username(email)
                .password(password)
                .roles("USER")
                .build();
    }
}
