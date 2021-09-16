package recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import recipes.presentation.UserModel;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;


    public void register(final UserModel userModel) {
        if (!exists(userModel)) {
            final UserDetails userDetails = userModel.toUserDetails(passwordEncoder);
            userDetailsManager.createUser(userDetails);
        }
    }

    public Optional<UserDetails> findByEmail(final String email) {
        try {
            return Optional.of(userDetailsManager.loadUserByUsername(email));
        } catch (UsernameNotFoundException e) {
            return Optional.empty();
        }
    }

    public boolean exists(final UserModel userModel) {
        return userDetailsManager.userExists(userModel.getEmail());
    }
}
