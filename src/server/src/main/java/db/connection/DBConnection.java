package db.connection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import db.entity.*;

public class DBConnection {
    private static SessionFactory sessionFactory;

    public static SessionFactory GetSessionFactory() throws HibernateException
    {
        if(sessionFactory != null)
            return sessionFactory;
        
        Configuration hbConfiguration = new Configuration();
        hbConfiguration.configure("hibernatecfg/hibernate.cfg.xml");
        AddAnnotatedClasses(hbConfiguration);
        sessionFactory = hbConfiguration.buildSessionFactory();

        return sessionFactory;
    }

    private static void AddAnnotatedClasses(Configuration hbConfiguration)
    {
        hbConfiguration.addAnnotatedClass(FurnitureCategory.class);
        hbConfiguration.addAnnotatedClass(Furniture.class);

        hbConfiguration.addAnnotatedClass(User.class);
        hbConfiguration.addAnnotatedClass(Assistant.class);
        hbConfiguration.addAnnotatedClass(Admin.class);

        hbConfiguration.addAnnotatedClass(CartElement.class);

        hbConfiguration.addAnnotatedClass(UserOrder.class);
        hbConfiguration.addAnnotatedClass(OrderElement.class);
        hbConfiguration.addAnnotatedClass(ServicedOrder.class);
        hbConfiguration.addAnnotatedClass(ServicedOrderElement.class);
    }
}
