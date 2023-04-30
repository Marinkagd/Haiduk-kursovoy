package db.dao;

import db.entity.User;
import jakarta.persistence.criteria.CriteriaDelete;
import org.hibernate.Session;

import java.util.List;

public class UserRefersDao<T> extends GenericDao<T> {

    public UserRefersDao(Class<T> entityType) {
        super(entityType);
    }

    public List<T> getElementsRefersToUser(User user) {
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(entityType);
            root = criteriaQuery.from(entityType);

            criteriaQuery.select(root).where(cBuilder.equal(root.get("user"), user.getId()));
            var cartElementList = session.createQuery(criteriaQuery).getResultList();
            return cartElementList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeElementsRefersToUser(User user) {
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            CriteriaDelete<T> criteriaDelete = cBuilder.createCriteriaDelete(entityType);
            root = criteriaDelete.from(entityType);
            criteriaDelete.where(cBuilder.equal(root.get("user"), user.getId()));

            session.beginTransaction();
            session.createMutationQuery(criteriaDelete).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
