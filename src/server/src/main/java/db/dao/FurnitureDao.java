package db.dao;

import db.entity.Furniture;
import db.entity.FurnitureCategory;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import request.tdo.FurnitureFilterTDO;

import java.util.ArrayList;
import java.util.List;

public class FurnitureDao extends GenericDao<Furniture> {

    public FurnitureDao(Class<Furniture> entityType) {
        super(entityType);
    }

    public List<Furniture> getFurnitureListByFiltes(FurnitureFilterTDO filters) {
        List<Furniture> furnitureList = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(Furniture.class);
            root = criteriaQuery.from(Furniture.class);

            List<Predicate> conditionlist = new ArrayList<>();
            Predicate[] conditions = null;
            if (filters == null) {
                conditionlist = null;
                criteriaQuery.select(root);
            } else {
                if (filters.isPrice())
                    conditionlist.add(cBuilder.between(root.get("price"), filters.getPriceFrom(), filters.getPriceTo()));
                if (filters.isCategory())
                    conditionlist.add(cBuilder.equal(root.get("furniturecategory"), session.get(FurnitureCategory.class, filters.getCategory())));
                if (filters.isWarranty())
                    conditionlist.add(cBuilder.between(root.get("warranty"), filters.getWarrantyFrom(), filters.getWarrantyTo()));
                if (filters.isName())
                    conditionlist.add(cBuilder.like(root.get("name"), "%" + filters.getName() + "%"));
                conditions = new Predicate[conditionlist.size()];
                conditionlist.toArray(conditions);
                criteriaQuery.select(root).where(conditions);
            }


            furnitureList = session.createQuery(criteriaQuery).getResultList();
            return furnitureList;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return furnitureList;
    }

}
