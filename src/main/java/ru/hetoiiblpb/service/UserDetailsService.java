package ru.hetoiiblpb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hetoiiblpb.dao.UserDAO;
import ru.hetoiiblpb.exception.DBException;
import ru.hetoiiblpb.model.Role;
import ru.hetoiiblpb.model.User;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            User user = userDAO.getUserByName(name);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role: user.getRoles() ) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
