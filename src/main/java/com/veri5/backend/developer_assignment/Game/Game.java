package com.veri5.backend.developer_assignment.Game;

import com.veri5.backend.developer_assignment.Deck.Card;
import com.veri5.backend.developer_assignment.Deck.CardDeck;
import com.veri5.backend.developer_assignment.Deck.DeckExceptions.NumberOfCardsNotMentionedException;
import com.veri5.backend.developer_assignment.Deck.DeckExceptions.PlayersNotFoundException;
import com.veri5.backend.developer_assignment.Deck.Utility.Constants;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Game {

    private static List<Player> players;
    private static List<Card> gameDeck;
    private static Map<String, Integer> rankMap;
    private static Map<String, Integer> suitMap;

    public Game() {
        players = new ArrayList<>();
        gameDeck = CardDeck.newDeck();
        initMap();
    }

    private void initMap() {
        rankMap = new HashMap<>();
        rankMap.put("TWO", 2);
        rankMap.put("THREE", 3);
        rankMap.put("FOUR", 4);
        rankMap.put("FIVE", 5);
        rankMap.put("SIX", 6);
        rankMap.put("SEVEN", 7);
        rankMap.put("EIGHT", 8);
        rankMap.put("NINE", 9);
        rankMap.put("TEN", 10);
        rankMap.put("JACK", 11);
        rankMap.put("QUEEN", 12);
        rankMap.put("KING", 13);
        rankMap.put("ACE", 14);

        suitMap = new HashMap<>();
        suitMap.put("DIAMONDS", 1);
        suitMap.put("CLUBS", 2);
        suitMap.put("HEARTS", 3);
        suitMap.put("SPADES", 4);

    }

    //Game starts with two players each having a random card in hand
    public void startTwoPersonGame() throws NumberOfCardsNotMentionedException {
        CardDeck.shuffle(gameDeck);
        players.add(new Player(CardDeck.getOneCard(gameDeck)));
        players.add(new Player(CardDeck.getOneCard(gameDeck)));
    }

    //Player can be added in the player list
    private boolean addPlayer(Player player) throws NumberOfCardsNotMentionedException {
        if (player == null) {
            player = new Player();
        }
        CardDeck.shuffle(gameDeck);
        if (null == player.getCard()) {
            player.setCard(CardDeck.getOneCard(gameDeck));
        }
        players.add(player);
        return true;
    }

    //Player gets removed from the player list
    private boolean removePlayer(Player player) throws PlayersNotFoundException {
        if (player == null) {
            throw new PlayersNotFoundException();
        }

        if (players.contains(player)) {
            players.remove(player);
            return true;
        } else {
            return false;
        }

    }

    //shows all the players with their card in hand
    public void showCardsOfAllPlayers() {
        if (players != null && players.size() > 0) {
            int i=0;
            for (Player eachPlayer : players) {
                System.out.println(i++ + ". " + eachPlayer.getName() + " : " + eachPlayer.getCard().toString());
            }
        }
    }

    public Player decideWinnerOfMatch() throws PlayersNotFoundException {
        if (players == null) {
            throw new PlayersNotFoundException();
        }
        if (players.size() == Constants.ZERO) {
            System.out.println("No players in the match..");
        }
        if (players.size() == 1) {
            return players.get(Constants.ZERO);
        }

        Player winner = players.get(Constants.ZERO);
        for (int i = 1; i < players.size(); i++) {
            int opponentRank = rankMap.get(players.get(i).getCard().getValue());
            int winnerRank = rankMap.get(winner.getCard().getValue());
            if (opponentRank > winnerRank) {
                winner = players.get(i);
            } else if (opponentRank == winnerRank) {
                int opponentSuit = suitMap.get(players.get(i).getCard().getSuit());
                int winnerSuit = suitMap.get(players.get(i).getCard().getSuit());
                winner = opponentSuit > winnerSuit ? players.get(i) : winner;
            }
        }

        return winner;
    }

    public void showAllPlayers() {
        System.out.println("\nPlayers List: ");
        int i = 1;
        for (Player eachPlayer : players) {
            System.out.println(i++ + ". " + eachPlayer.getName());
        }
    }

    public boolean removeSelectedPlayer(int playerNumber) {
        if(playerNumber > 0 && playerNumber < players.size()) {
            try {
                removePlayer(players.get(playerNumber));
            }catch (PlayersNotFoundException ex){
                System.out.println("Player number to remove is invalid!");
            }
        }else{
            return false;
        }
        return true;
    }

    public boolean addPlayer() throws NumberOfCardsNotMentionedException {
        return addPlayer(null);
    }

    public void printEntireDeck(){
        CardDeck.print(gameDeck);
    }

    public void pickRandomCardsForAllPlayers() {
        CardDeck.shuffle(gameDeck);
        for(Player eachPlayer : players){
            eachPlayer.setCard(CardDeck.getOneCard(gameDeck));
        }
    }
}
