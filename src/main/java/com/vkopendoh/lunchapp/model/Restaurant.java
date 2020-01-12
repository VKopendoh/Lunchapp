package com.vkopendoh.lunchapp.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<User> voters;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    List<History> histories;

    public Set<User> getVoters() {
        return voters;
    }

    public void setVoters(Set<User> voters) {
        this.voters = voters;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "voters=" + voters +
                ", menu=" + menu +
                '}';
    }
}

