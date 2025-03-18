//package ru.meow.meowauth.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import ru.meow.meowauth.data.entity.user.User;
//import ru.meow.meowauth.data.repositories.RoleRepository;
//import ru.meow.meowauth.data.repositories.UserRepository;
//import ru.meow.meowauth.data.entity.roles.RoleType;
//import ru.meow.meowauth.exceptions.UserNotFoundException;
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthUserService {
//    private final UserRepository repository;
//    private final RoleRepository roleRepository;
//
//    /**
//     * Сохранение пользователя
//     *
//     * @return сохраненный пользователь
//     */
//    public User save(User user) {
//        return repository.save(user);
//    }
//
//
//    /**
//     * Создание пользователя
//     *
//     * @return созданный пользователь
//     */
//    public User create(User user) {
//
//        if (repository.findByUsername(user.getUsername()) != null) {
//            // Заменить на свои исключения
//            throw new RuntimeException("Пользователь с таким именем уже существует");
//        }
//
//        return save(user);
//    }
//
//    /**
//     * Получение пользователя по имени пользователя
//     *
//     * @return пользователь
//     */
//    public User getByUsername(String username) {
//        User foundUser = repository.findByUsername(username);
//        if (foundUser == null) {
//            throw new UserNotFoundException();
//        }
//        return foundUser;
//
//    }
//
//    /**
//     * Получение пользователя по имени пользователя
//     * <p>
//     * Нужен для Spring Security
//     *
//     * @return пользователь
//     */
//    public UserDetailsService userDetailsService() {
//        return this::getByUsername;
//    }
//
//    /**
//     * Получение текущего пользователя
//     *
//     * @return текущий пользователь
//     */
//    public User getCurrentUser() {
//        // Получение имени пользователя из контекста Spring Security
//        var username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return getByUsername(username);
//    }
//
//
//    /**
//     * Выдача прав администратора текущему пользователю
//     * <p>
//     * Нужен для демонстрации
//     */
//    @Deprecated
//    public void getAdmin() {
//        var user = getCurrentUser();
//        var role = roleRepository.findByName(RoleType.ROLE_ADMIN);
//        user.setUserRole(role);
//        save(user);
//    }
//}