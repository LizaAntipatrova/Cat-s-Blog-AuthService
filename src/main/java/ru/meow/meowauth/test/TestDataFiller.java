package ru.meow.meowauth.test;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.meow.meowauth.data.entity.roles.Role;
import ru.meow.meowauth.data.entity.roles.RoleType;
import ru.meow.meowauth.data.repositories.RoleRepository;

@Component
@RequiredArgsConstructor
@Profile("!test")
// spring_profiles_active=test в edit configuration стратегия игнора: https://www.baeldung.com/spring-profiles
public class TestDataFiller {

    private final RoleRepository repositoryRole;


    /**
     * при запуске приложения заполняет БД тестовыми данными
     */
    @EventListener(ApplicationReadyEvent.class)
    public void fillRoleEntities() {
        Role roleUsr = new Role(RoleType.ROLE_USER);
        Role roleAdm = new Role(RoleType.ROLE_ADMIN);

        repositoryRole.saveAndFlush(roleAdm);
        repositoryRole.saveAndFlush(roleUsr);


    }
}
