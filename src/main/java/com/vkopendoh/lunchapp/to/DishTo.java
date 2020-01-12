package com.vkopendoh.lunchapp.to;

import java.math.BigDecimal;
import java.util.Objects;

public class DishTo {
    private String name;
    private BigDecimal price;

    public DishTo() {
    }

    public DishTo(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishTo)) return false;
        DishTo dishTo = (DishTo) o;
        return Objects.equals(name, dishTo.name) &&
                Objects.equals(price.stripTrailingZeros(), dishTo.price.stripTrailingZeros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
