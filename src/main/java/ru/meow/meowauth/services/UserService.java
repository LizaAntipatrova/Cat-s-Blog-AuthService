package ru.meow.meowauth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meow.meowauth.data.entity.roles.RoleType;
import ru.meow.meowauth.data.entity.user.User;
import ru.meow.meowauth.data.repositories.RoleRepository;
import ru.meow.meowauth.data.repositories.UserRepository;
import ru.meow.meowauth.exceptions.data.ExistingUserWithThatUsernameException;
import ru.meow.meowauth.exceptions.data.UserNotFoundException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(String username, String password) {

        if (userRepository.findByUsername(username) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Set.of((roleRepository.findByName(RoleType.ROLE_USER))));

        return save(user);
    }


}
