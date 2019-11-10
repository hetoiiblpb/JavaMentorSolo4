package ru.hetoiiblpb.service;

import ru.hetoiiblpb.exception.DBException;
import ru.hetoiiblpb.model.User;

import java.util.List;

public interface UserService {
     List<User> getAllUsers() throws DBException ;

     boolean addUser(User user) throws DBException;

     boolean deleteUser(Long id) throws DBException;

     boolean updateUser(User user) throws DBException;

     User getUserById(Long id) throws DBException;

     User getUserByName(String name) throws DBException;


     boolean checkUserByEmail(String mail) throws DBException;

     User verifyUserPassword(String name, String password) throws DBException;
}
