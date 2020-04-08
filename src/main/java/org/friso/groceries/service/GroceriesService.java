package org.friso.groceries.service;

import org.friso.groceries.entity.Groceries;
import org.friso.groceries.entity.GroceryItem;
import org.friso.groceries.repository.GroceriesRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class GroceriesService {

    @Inject
    private GroceriesRepository groceriesRepository;

    @Inject
    private GroceryItemService groceryItemService;

    public List<Groceries> findAll() {
        return groceriesRepository.findAll().list();
    }

    public Groceries findById(final Long id) {
        return groceriesRepository.findById(id);
    }

    public List<Groceries> findAllByDate(final LocalDate date) {
        return groceriesRepository.finalAllByDate(date);
    }

    public Groceries findLatest() {
        return groceriesRepository.findLatest();
    }

    public boolean createOrUpdate(final Long id, final LocalDate date) {
        Groceries groceries = id == null ? null : groceriesRepository.findById(id);

        if (groceries != null) {
            groceries.setDate(date);

        } else {
            groceries = new Groceries(date);
        }
        groceriesRepository.persist(groceries);

        return true;
    }

    public boolean addItem(final Long groceriesId, final Long itemId) {
        boolean result = false;

        final GroceryItem groceryItem = itemId == null ? null : groceryItemService.findById(itemId);
        if (groceryItem != null) {
            final Groceries groceries = groceriesId == null ? null : groceriesRepository.findById(groceriesId);
            if (groceries != null) {
                groceries.getGroceryItems().add(groceryItem);
                groceriesRepository.persist(groceries);
                result = true;
            }
        }

        return result;
    }

    public boolean delete(final Long id) {
        boolean result = false;

        final Optional<Groceries> groceryItem = groceriesRepository.findByIdOptional(id);
        if (groceryItem.isPresent()) {
            groceriesRepository.delete(groceryItem.get());
            result = true;
        }

        return result;
    }

}
