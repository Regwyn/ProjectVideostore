package com.entities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class EntityAccountsManager {

    private static Connection conn;

    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static EntityAccounts entityAccounts;

    public EntityAccountsManager() {
        createDatabase();
        setupDbConnection();
        createTable();
    }

    public static void createDatabase() {
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void setupDbConnection() {
        try {
            String uri = "jdbc:mysql://localhost:3306/videostore_accounts?user=root";
            Properties props = setLoginForDB();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(uri, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties setLoginForDB() {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        return props;
    }

    public static void createTable() {
        entityAccounts = new EntityAccounts();
    }

    public static void addNewUser(String email, String username, String password) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            entityAccounts.setEmail(email);
            entityAccounts.setUsername(username);
            entityAccounts.setPassword(password);
            session.save(entityAccounts);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void addVideotape(String videotape) {
//ne raboti mai - da se proveri
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();


            session.save(entityAccounts);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static boolean checkIfUserAlreadyExists(String username, String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean doesUserExist = false;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();

            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (username.equalsIgnoreCase(employee.getUsername()) || email.equalsIgnoreCase(employee.getEmail())) {
                    doesUserExist = true;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doesUserExist;
    }

    public static boolean checkIfUsernameAlreadyExists(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean doesUserExist = false;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();

            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (username.equalsIgnoreCase(employee.getUsername())) {
                    doesUserExist = true;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doesUserExist;
    }

    public static boolean checkIfPasswordMatches(String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean doesPasswordMatch = false;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();

            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (password.equalsIgnoreCase(employee.getPassword())) {
                    doesPasswordMatch = true;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doesPasswordMatch;
    }

    public static void delete(long id) {
        //ne raboti ?? - da se testva predi prerabotka
        Session session;
        EntityAccounts entityAccounts;

        session = sessionFactory.getCurrentSession();
        entityAccounts = (EntityAccounts) session.load(EntityAccounts.class, id);
        session.delete(entityAccounts);

        //This makes the pending delete to be done
        session.flush();
        session.close();
    }

    public static int checkIfUsernameExistsReturnsId(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        long userId = -1;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (employee.getUsername().equalsIgnoreCase(username)) {
                    userId = employee.getId();
                }
            }
            tx.commit();
            return (int) userId;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (int) userId;
    }

    public static String checkIfUsernameExistsReturnsUsername(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String nameOfUser = "No match";
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (employee.getUsername().equalsIgnoreCase(username)) {
                    nameOfUser = username;
                }
            }
            tx.commit();
            return nameOfUser;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nameOfUser;
    }

    public static int checkIfEmailExistsReturnsId(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        long userId = -1;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (employee.getEmail().equalsIgnoreCase(email)) {
                    userId = employee.getId();
                }
            }
            tx.commit();
            return (int) userId;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (int) userId;
    }

    public static String checkIfEmailExistsReturnsEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String userEmail = "No match";
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (employee.getEmail().equalsIgnoreCase(email)) {
                    userEmail = email;
                }
            }
            tx.commit();
            return userEmail;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userEmail;
    }

    public static String findUserByIdReturnUsername(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String nameOfUser = "";
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                EntityAccounts employee = (EntityAccounts) users.get(i);
                if (employee.getId() == id) {
                    nameOfUser = employee.getUsername();
                }
            }
            tx.commit();
            return nameOfUser;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nameOfUser;
    }

    public static void addMoreVideotapesToUsers(long id, String movieName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                entityAccounts = (EntityAccounts) users.get(i);
                if (entityAccounts.getId() == id) {
                    entityAccounts.getNamesOfTakenVideotapes().add(movieName);
                    entityAccounts.getVideotapeBookDate().add(getBookingDate());
                    entityAccounts.getVideotapeReturnDate().add(getReturnDate());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void getAllVideotapesBorrowedFromAnAccount(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM EntityAccounts").list();


            for (int i = 0; i < users.size(); i++) {
                entityAccounts = (EntityAccounts) users.get(i);
                if (entityAccounts.getId() == id) {
                    for (int j = 0; j < entityAccounts.getNamesOfTakenVideotapes().size(); j++) {
                        System.out.println(entityAccounts.getNamesOfTakenVideotapes().get(j));
                    }
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static String getBookingDate() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        String newFormat = currentDateTime.format(format);

        return newFormat;
    }

    public static String getReturnDate() {
        String returnDate;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");

        returnDate = currentDateTime.plusDays(7).format(format);

        return returnDate;
    }
}
