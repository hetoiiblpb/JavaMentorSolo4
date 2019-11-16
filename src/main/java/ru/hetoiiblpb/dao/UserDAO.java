package ru.hetoiiblpb.dao;

import ru.hetoiiblpb.exception.DBException;
import ru.hetoiiblpb.model.User;
import ru.hetoiiblpb.util.DBHelper;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User admin = new User(DBHelper.getProperties().getProperty("adminName", "defAdmin"),
            DBHelper.getProperties().getProperty("adminPass", "defPass"),
            DBHelper.getProperties().getProperty("adminEmail", "defMail@mail.ru"),
            Long.parseLong(DBHelper.getProperties().getProperty("adminAge", "300")),
            "admin");

    <T> List<T> getAllUsers() throws SQLException;

    boolean checkUserByEmail(String email) throws SQLException;

    User verifyUserPassword(String name, String password) throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException, DBException;

    User getUserByName(String name) throws SQLException, DBException;

}
