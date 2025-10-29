package ch.zli.m223.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DatabaseSeeder {

    @Inject
    EntityManager entityManager;

    List<Tag> tags = new ArrayList<>();
    List<Category> categories = new ArrayList<>();

    @Startup
    @Transactional
    public void seedDatabase() {
        this.createTags();
        this.createCategories();
        this.createEntries();
    }

    private void createTags() {
        var tag1 = new Tag();
        tag1.setTitle("Anforderungsanalyse");
        entityManager.persist(tag1);
        tags.add(tag1);
        
        var tag2 = new Tag();
        tag2.setTitle("Programmieren");
        entityManager.persist(tag2);
        tags.add(tag2);

        var tag3 = new Tag();
        tag3.setTitle("Kundengespräche");
        entityManager.persist(tag3);
        tags.add(tag3);
    }

    private void createCategories() {
        var category1 = new Category();
        category1.setTitle("Berufsschule");
        entityManager.persist(category1);
        categories.add(category1);

        var category2 = new Category();
        category2.setTitle("Überbetrieblicher Kurs");
        entityManager.persist(category2);
        categories.add(category2);

        var category3 = new Category();
        category3.setTitle("Projekt A");
        entityManager.persist(category3);
        categories.add(category3);
    }

    private void createEntries() {
        var entry1 = new Entry();
        entry1.setCheckIn(LocalDateTime.now().minusDays(2).minusHours(2));
        entry1.setCheckOut(LocalDateTime.now().minusDays(2));
        entry1.setCategory(categories.getFirst());
        var entry1tags = new HashSet<Tag>();
        entry1tags.add(tags.get(1));
        entry1.setTags(entry1tags);
        entityManager.persist(entry1);

        var entry2 = new Entry();
        entry2.setCheckIn(LocalDateTime.now().minusDays(1).minusHours(2));
        entry2.setCheckOut(LocalDateTime.now().minusDays(1));
        entry2.setCategory(categories.get(1));
        var entry2tags = new HashSet<Tag>();
        entry2tags.add(tags.get(1));
        entry2.setTags(entry2tags);
        entityManager.persist(entry2);

        var entry3 = new Entry();
        entry3.setCheckIn(LocalDateTime.now().minusHours(2));
        entry3.setCheckOut(LocalDateTime.now());
        entry3.setCategory(categories.get(1));
        var entry3tags = new HashSet<Tag>();
        entry3tags.add(tags.get(1));
        entry3.setTags(entry3tags);
        entityManager.persist(entry3);
    }
}
