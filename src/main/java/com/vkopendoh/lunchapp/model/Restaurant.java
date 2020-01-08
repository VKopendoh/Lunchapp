package com.vkopendoh.lunchapp.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<User> voters;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

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

    public void addVote(User user) {
        if (voters == null) {
            voters = new HashSet<>();
        }
        voters.add(user);
    }
}

