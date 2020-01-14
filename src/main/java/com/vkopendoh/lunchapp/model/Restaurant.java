package com.vkopendoh.lunchapp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant extends AbstractNamedEntity implements Serializable {

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<User> voters;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    List<History> histories;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Set<User> voters, Menu menu, List<History> histories) {
        super(id, name);
        this.voters = voters;
        this.menu = menu;
        this.histories = histories;
    }

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

    public void addVoter(User user) {
        if (voters == null) {
            voters = new HashSet<>();
        }
        if (user != null) {
            voters.add(user);
            user.setRestaurant(this);
        }
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "voters=" + voters +
                ", menu=" + menu +
                '}';
    }
}

