package org.friso.groceries.service;

import org.friso.groceries.entity.GroceryItem;
import org.friso.groceries.repository.GroceryItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class GroceryItemService {

    @Inject
    GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> findAll() {
        return groceryItemRepository.findAll().list();
    }

    public GroceryItem findById(final Long id) {
        final GroceryItem result = groceryItemRepository.findById(id);
        return result;
    }

    public boolean createOrUpdate(final Long id, final String name, final String description, final String barcode) {
        GroceryItem groceryItem = id == null ? null : groceryItemRepository.findById(id);

        if (groceryItem != null) {
            groceryItem.setName(name);
            groceryItem.setDescription(description);
            groceryItem.setBarcode(barcode);
        } else {
            groceryItem = new GroceryItem(name, description, barcode);
        }
        groceryItemRepository.persist(groceryItem);

        return true;
    }

    public boolean delete(final Long id) {

        boolean result = false;

        final Optional<GroceryItem> groceryItem = groceryItemRepository.findByIdOptional(id);
        if (groceryItem.isPresent()) {
            groceryItemRepository.delete(groceryItem.get());
            result = true;
        }

        return result;
    }

}
