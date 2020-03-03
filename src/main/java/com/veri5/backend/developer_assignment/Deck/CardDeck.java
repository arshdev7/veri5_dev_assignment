package com.veri5.backend.developer_assignment.Deck;

import com.veri5.backend.developer_assignment.Deck.DeckExceptions.NumberOfCardsNotMentionedException;
import com.veri5.backend.developer_assignment.Deck.Utility.Constants;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Random;

public class CardDeck{

    private static final List<Card> demoDeck = new ArrayList<>();

    //Initialize the demo deck with all the card value and suits
    static {
        for (Card.Suit eachSuit : Card.Suit.values()){
            for (Card.Value eachValue : Card.Value.values()){
                demoDeck.add(new Card(eachValue, eachSuit));
            }
        }
    }

    public static List<Card> newDeck(){
        return new ArrayList<>(demoDeck);
    }

    //Shuffle all the cards
    public static void shuffle(List<Card> myDeck){
        if(myDeck != null && myDeck.size() == Constants.CARDS_IN_DECK) {
            Collections.shuffle(myDeck);
        }else{
            myDeck = CardDeck.newDeck();
            Collections.shuffle(myDeck);
        }
    }

    //Method to print all the cards
    public static void print(List<Card> myDeck){
        if(myDeck != null && myDeck.size() > 0 && myDeck.size() <= Constants.CARDS_IN_DECK) {
            for (int i=0; i<myDeck.size(); i++){
                System.out.println(i+". "+myDeck.get(i).toString());
            }
        }
    }

    public static Card getOneCard(List<Card> myDeck) {
        Card card = null;
        try {
            card = CardDeck.getCards(Constants.ONE, myDeck).get(Constants.ZERO);

        } catch (NumberOfCardsNotMentionedException e) {
            System.out.println(e.toString());
        }

        return card;
    }

    //Gives specified cards at random and removes the picked cards from the Deck
    public static List<Card> getCards(int numberOfCards, List<Card> myDeck) throws NumberOfCardsNotMentionedException {
        CardDeck.shuffle(myDeck);
        List<Card> pickedCards = new ArrayList<>();
        if(numberOfCards == Constants.ONE){
            Card pickedCard = myDeck.get(Constants.ZERO);
            pickedCards.add(pickedCard);
            myDeck.remove(pickedCard);
            return pickedCards;
        }

        if(numberOfCards > Constants.ZERO && numberOfCards < Constants.CARDS_IN_DECK) {
            Random rand = new Random();
            for (int i = Constants.ZERO; i < numberOfCards; i++) {
                Card selectedCard = myDeck.get(rand.nextInt(Constants.CARDS_IN_DECK - i));
                pickedCards.add(selectedCard);
                myDeck.remove(selectedCard);
            }
        }else {
            throw new NumberOfCardsNotMentionedException("Invalid number of cards provided");
        }
        return pickedCards;
    }

    //Method to add a card to the deck
    public static boolean addOneCard(Card card, List<Card> myDeck){
        List<Card> toAddCards = new ArrayList<>();
        if(card != null) {
            toAddCards.add(card);
        }
        return addCardsToDeck(toAddCards, myDeck);
    }

    //Method to add multiple cards to deck
    public static boolean addCardsToDeck(List<Card> toAddCards, List<Card> myDeck){
        if(toAddCards != null && toAddCards.size()>0 && (toAddCards.size() + myDeck.size() <= Constants.CARDS_IN_DECK)){
            for(Card eachCard : toAddCards){
                if(!myDeck.contains(eachCard)){
                    myDeck.add(eachCard);
                }
            }
        }else{
            return false;
        }
        return true;
    }


}
