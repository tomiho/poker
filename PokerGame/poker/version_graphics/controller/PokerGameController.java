package poker.version_graphics.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerAmount;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	public PokerGameView view;
	private PlayerAmount amount;
	
	public PokerGameController(PokerGameModel model, PokerGameView view, PlayerAmount amount) {
		this.model = model;
		this.view = view;
	
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getPlrSetButton().setOnAction(e-> openPlayerAmount());
		
		amount.getBtnOk().setOnAction(e -> setPlayerAmount());
		
	}
	


    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        	}
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
    
    /*private void dealMorePlayers() {
    	int cardsRequired = PokerGame.morePlayers * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
    	for (int i = 0; i < PokerGame.morePlayers; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		for (int j = 0; j < Player.HAND_SIZE; j++) {
    			Card card = deck.dealCard();
    			p.addCard(card);
    		}
    		p.evaluateHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    		}
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }*/
    
    public PlayerAmount openPlayerAmount() {
    	amount = new PlayerAmount(view);
    
    	return amount;
    }
    
   private void setPlayerAmount() {
	   if(Integer.parseInt(amount.getPlrText()) <= 2 && Integer.parseInt(amount.getPlrText()) >= 6) {
		   PokerGame.morePlayers = Integer.parseInt(amount.getPlrText());
		   view.morePlayerView();
	   }
    	
    }
}
