package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

import poker.version_graphics.model.Card.Rank;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
    
        // TODO
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	ArrayList<Card> pairCards = new ArrayList<Card>();
    	
        boolean threeFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !threeFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !threeFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                	pairCards.add(clonedCards.get(i));
                	pairCards.add(clonedCards.get(j));
                	clonedCards.remove(j);  
                    clonedCards.remove(i); 
                    
                    if(pairCards.size() < 4) {
                    for(int a = 0; a < clonedCards.size(); a++) {
                    	if(clonedCards.get(a).getRank() == pairCards.get(0).getRank()) {
                    		threeFound = true;
                    	}
                    }
                  }
                   
                }
            }
        }
        return threeFound;
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {//anschauen
        // TODO  
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	boolean straight = false;
    	int counter = 0;
    		Collections.sort(clonedCards);
    		if (clonedCards.get(4).getRank() == Rank.Ace && clonedCards.get(3).getRank() == Rank.Five) {
    			int count = 0;
    			for(int i = 0; i <= 3; i++) {
    				if(clonedCards.get(i+1).getRank().ordinal() - clonedCards.get(i).getRank().ordinal() == 1 && !straight ) {
    					count++;
    					if (count >= 3) {
    						straight = true;
    					}
    				}
    			}
    		}else {
    			for(int i = 0; i < clonedCards.size()-1; i++) {
    				if(clonedCards.get(i+1).getRank().ordinal() - clonedCards.get(i).getRank().ordinal() == 1) {
    					counter++;
    					if(counter >= 4) {
    						straight = true;
    					}
    				}
    			}
    				
    		}
    	
    	return straight;
    }
    
    public static boolean isFlush(ArrayList<Card> cards) {
        // TODO
    	int count = 0;
    	boolean flush = false;
    	for(int i = 0; i < cards.size()-1 && !flush; i++) {
    			if(cards.get(i).getSuit() == cards.get(4).getSuit()) {
    				count++;
    				if(count == 4){
    					flush = true;
    				}
    		}
    	}
        return flush;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
        // TODO
    	boolean fullHouseFound = false;
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	Collections.sort(clonedCards);
    	
    	if(clonedCards.get(0).getRank() == clonedCards.get(1).getRank() && clonedCards.get(1).getRank() == clonedCards.get(2).getRank()
    		&& clonedCards.get(3).getRank() == clonedCards.get(4).getRank()) {
    		fullHouseFound = true;
    	}else {
    		if(clonedCards.get(0).getRank() == clonedCards.get(1).getRank() && clonedCards.get(2).getRank() == clonedCards.get(3).getRank()
    	    		&& clonedCards.get(3).getRank() == clonedCards.get(4).getRank()) {
    			fullHouseFound = true;
    		}
    	}
      return fullHouseFound; 
    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
        // TODO 
    	boolean found = false;
    	int count = 0;
    	
    	for(int i = 0; i < cards.size()-1 ; i++) {
    		for(int j = i+1; j < cards.size() ; j++ ) {
    			if(cards.get(i).getRank() == cards.get(j).getRank() && (count < 4)) {
    				count++;
    			}	
    		}
    		if(count == 4) {
				found = true;
			}

    	}
        return found;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        // TODO
    	boolean straightFlush = false;
    	if(isStraight(cards)&& isFlush(cards)) {
    		straightFlush = true;
    	}
    	
        return straightFlush;
    }
    
    
    
}
