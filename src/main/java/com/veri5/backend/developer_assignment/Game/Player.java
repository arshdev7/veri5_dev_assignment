package com.veri5.backend.developer_assignment.Game;


import com.veri5.backend.developer_assignment.Deck.Card;

import javax.persistence.*;


@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    @OneToOne
    Card card;

    static int number = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Player(String name){
        this.name = name;
        card = new Card();
    }

    public Player() {
        name = "Player " + number++;
    }

    public Player(Card card) {
        name = "Player " + number++;
        this.card = card;
    }
}
