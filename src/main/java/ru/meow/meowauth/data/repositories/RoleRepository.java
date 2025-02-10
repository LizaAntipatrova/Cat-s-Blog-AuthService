package ru.meow.meowauth.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.meow.meowauth.data.entity.roles.Role;
import ru.meow.meowauth.data.entity.roles.RoleType;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleType nameRole);
}
