package ch.zli.m223.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Tag createTag(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Transactional
    public void deleteTag(Long id) {
        entityManager.remove(findByTag(id));
    }

    public Tag updateTag(Long id, Tag tag) {
        if (id != tag.getId()) {
            throw new NotFoundException();
        }
        return entityManager.merge(tag);
    }

    public Tag findByTag(Long id) {
        return entityManager.find(Tag.class, id);
    }

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }
}
