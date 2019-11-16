package ru.hetoiiblpb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hetoiiblpb.model.Role;

public interface RoleDAO extends JpaRepository<Role,Long> {
}
