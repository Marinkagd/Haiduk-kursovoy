package com.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.hibernate.HibernateException;
import org.junit.Test;

import db.connection.DBConnection;

/**
 * Unit test for simple App.
 */
public class DBConnectionTest 
{
  
    @Test
    public void shouldSuccessfullConnectToDB()
    {
            try {
                var connection = DBConnection.GetSessionFactory();
                assertNotNull(connection);
            } catch (HibernateException e) {
                e.getStackTrace();
                fail("Hibernate exception in db connection");
            }
    }
}
