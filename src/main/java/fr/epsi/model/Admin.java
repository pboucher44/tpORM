package fr.epsi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "admin")
public class Admin extends User {

    private String nickname;
    private String carteBancaire;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

	public String getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(String carteBancaire) {
		this.carteBancaire = carteBancaire;
	}
}
