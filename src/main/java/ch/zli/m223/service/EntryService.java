package ch.zli.m223.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(findByEntry(id));
    }

    public Entry updateEntry(Long id, Entry entry) {
        if (id != entry.getId()) {
            throw new NotFoundException();
        }
        return entityManager.merge(entry);
    }

    public Entry findByEntry(Long id) {
        return entityManager.find(Entry.class, id);
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }
}
