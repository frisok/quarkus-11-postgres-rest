package org.friso.groceries.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.friso.groceries.entity.GroceryItem;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class GroceryItemRepository implements PanacheRepository<GroceryItem> {

    public List<GroceryItem> findAllByName(final String name) {
        return list("name", name);
    }

}
