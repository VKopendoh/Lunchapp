package com.vkopendoh.lunchapp.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Dish extends AbstractNamedEntity {

    @NotNull
    @Range(min = 0)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Dish() {
    }

    public Dish(String name, @NotNull @Range(min = 0) BigDecimal price) {
        super(null, name);
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", menu=" + menu +
                '}';
    }
}
