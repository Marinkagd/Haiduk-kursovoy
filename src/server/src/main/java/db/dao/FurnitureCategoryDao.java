package db.dao;

import db.entity.FurnitureCategory;
import org.hibernate.Session;

public class FurnitureCategoryDao extends GenericDao<FurnitureCategory> {

    public FurnitureCategoryDao(Class<FurnitureCategory> entityType) {
        super(entityType);
    }

    public FurnitureCategory getFurnitureCategoryByName(String name) {
        FurnitureCategory category = null;
        try (Session session = sessionFactory.openSession();) {
            cBuilder = session.getCriteriaBuilder();
            criteriaQuery = cBuilder.createQuery(FurnitureCategory.class);
            root = criteriaQuery.from(FurnitureCategory.class);

            criteriaQuery.select(root).where(cBuilder.equal(root.get("name"), name));

            category = session.createQuery(criteriaQuery).getSingleResult();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
