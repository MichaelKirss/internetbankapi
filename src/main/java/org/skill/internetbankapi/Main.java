package org.skill.internetbankapi;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//        System.out.println("Hello world!");
//
//        Configuration configuration = new Configuration().configure().addAnnotatedClass(UserAccount.class);
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tx= session.beginTransaction();
//
//        UserAccount userAccount = (UserAccount) session.get(UserAccount.class, new Long(2L));
//        System.out.println("_______________________________________");
//        System.out.println(userAccount.getBalance());
//        System.out.println("_______________________________________");
//
//        tx.commit();
//        sessionFactory.close();



}
