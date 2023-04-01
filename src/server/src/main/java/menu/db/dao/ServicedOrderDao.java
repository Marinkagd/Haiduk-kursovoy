package menu.db.dao;

import java.sql.Date;
import java.util.List;

import menu.db.entity.ServicedOrder;
import org.hibernate.Session;

import jakarta.persistence.criteria.Predicate;

public class ServicedOrderDao extends GenericDao<ServicedOrder>{

    public ServicedOrderDao(Class<ServicedOrder> entityType) {
        super(entityType);
    }

    public List<ServicedOrder> getProfitServicedOrderList()
    {
        List<ServicedOrder> profitOrderList = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(ServicedOrder.class);
            root = criteriaQuery.from(ServicedOrder.class);

            criteriaQuery.select(root).where(cBuilder.equal(root.get("isaccepted"), 1));

            profitOrderList = session.createQuery(criteriaQuery).getResultList();
            return profitOrderList;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return profitOrderList;
    }
    
    public List<ServicedOrder> getProfitServicedOrderListPerPeriod(Date lowBorder, Date highBorder)
    {
        List<ServicedOrder> profitOrderList = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(ServicedOrder.class);
            root = criteriaQuery.from(ServicedOrder.class);
            Predicate[] conditions = new Predicate[2];
            conditions[0] = cBuilder.equal(root.get("isaccepted"), 1);
            conditions[1] = cBuilder.between(root.get("answerdate"), lowBorder, highBorder);
            criteriaQuery.select(root).where(conditions);

            profitOrderList = session.createQuery(criteriaQuery).getResultList();
            return profitOrderList;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return profitOrderList;
    }
}
