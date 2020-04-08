package org.friso.groceries.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.friso.groceries.entity.Groceries;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class GroceriesRepository implements PanacheRepository<Groceries> {

    public List<Groceries> finalAllByDate(final LocalDate date) {
        return list("date", date, Sort.by("date").descending());
    }

    public Groceries findLatest() {
        return findAll(Sort.by("date").descending()).firstResult();
    }

}
