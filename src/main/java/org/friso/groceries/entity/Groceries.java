package org.friso.groceries.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "groceries")
@SequenceGenerator(name = "pk_generator", sequenceName = "pk_seq", initialValue = 10, allocationSize = 10)
public class Groceries extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_generator")
    private Long id;

    @ManyToMany
    @JoinTable(name = "groceries_grocery_item",
            joinColumns = @JoinColumn(name = "groceries_id"),
            inverseJoinColumns = @JoinColumn(name = "grocery_item_id")
    )
    private Set<GroceryItem> groceryItems;

    @Column(name = "date")
    private LocalDate date;

    public Groceries() {
        super();
    }

    public Groceries(final LocalDate date) {
        super();
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(Set<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}