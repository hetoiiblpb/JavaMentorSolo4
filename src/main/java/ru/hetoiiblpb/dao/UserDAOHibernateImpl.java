package ru.hetoiiblpb.dao;

import org.springframework.stereotype.Repository;
import ru.hetoiiblpb.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOHibernateImpl implements UserDAO {
    private static UserDAOHibernateImpl instance;
    private static SessionFactory sessionFactory;

    private UserDAOHibernateImpl(SessionFactory sessionFactory) throws SQLException {
        this.sessionFactory = sessionFactory;
        if (checkUserByEmail(admin.getEmail())) {
            addUser(admin);
        }
    }

    public static UserDAOHibernateImpl getInstance(SessionFactory sessionFactory) throws SQLException {
        if (instance == null) {
            instance = new UserDAOHibernateImpl(sessionFactory);
        }
        return instance;
    }

    @Override
    public <T> List<T> getAllUsers() throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User", User.class);
        List<T> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        try {
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
        return false;
    }

    @Override
    public boolean deleteUser(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getUserById(id));
        try {
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
        return false;
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id =:param");
        query.setParameter("param", id);
        User user = (User) query.getSingleResult();
        try {
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name =:param");
        query.setParameter("param", name);
        User user = (User) query.getSingleResult();
        try {
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
        session.update(user);
            System.out.println(user.toString());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
        return false;
    }

    @Override
    public boolean checkUserByEmail(String email) throws SQLException {     //Проверка на отсутствие user с таким email
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where mail=:param");
        query.setParameter("param", email);
        boolean exist = query.getResultList().isEmpty();
        try {
            transaction.commit();
            session.close();
            return exist;
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
        return true;
    }

    @Override
    public User verifyUserPassword(String name, String password) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name=:name and password=:password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        User user = null;
        if (!query.getResultList().isEmpty()) {
            user = (User) query.getSingleResult();
        }
        try {
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
        return null;
    }

}
