package db.dao;

import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;

public class AccountDao<T> extends GenericDao<T> {

    public AccountDao(Class<T> entityType) {
        super(entityType);
    }

    public T getByLoginAndPassword(String login, byte[] password) {
        T account = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(entityType);
            root = criteriaQuery.from(entityType);
            Predicate[] conditions = new Predicate[2];
            conditions[0] = cBuilder.equal(root.get("id"), login);
            conditions[1] = cBuilder.equal(root.get("password"), password);
            criteriaQuery.select(root).where(conditions);
            account = session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            e.getStackTrace();
            return account;
        }
        return account;
    }

    public T getByLogin(String login) {
        T account = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(entityType);
            root = criteriaQuery.from(entityType);
            criteriaQuery.select(root).where(cBuilder.equal(root.get("id"), login));
            account = session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            e.getStackTrace();
            return account;
        }
        return account;
    }

}
