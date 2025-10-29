package ch.zli.m223.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public void deleteCategory(Long id) {
        entityManager.remove(findByCategory(id));
    }

    public Category updateCategory(Long id, Category category) {
        if (id != category.getId()) {
            throw new NotFoundException();
        }
        return entityManager.merge(category);
    }

    public Category findByCategory(Long id) {
        return entityManager.find(Category.class, id);
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }
}
