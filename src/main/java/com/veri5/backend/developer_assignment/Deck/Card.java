package com.veri5.backend.developer_assignment.Deck;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card{
    public Card() {
    }

    public enum Value{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public enum Suit {
        SPADES, HEARTS, CLUBS, DIAMONDS;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value.toString();
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getSuit() {
        return suit.toString();
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Id
    private String id;

    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit){
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

}
