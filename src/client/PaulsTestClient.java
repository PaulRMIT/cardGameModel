package client;

import model.GameEngine;
import model.GameEngineImpl;
import model.PlayerImpl;
import model.card.Card;
import model.card.Deck;
import model.card.DeckImpl;
import model.card.Suit;
import view.ConsoleLoggerCallback;

public class PaulsTestClient {
	
	public static void main(String[] args) {
		
		GameEngine engine = new GameEngineImpl();
		Deck deck = DeckImpl.createSortedDeck();
		
		engine.registerCallback(new ConsoleLoggerCallback(engine));
		
		
		deck.shuffleDeck();
		System.out.println(deck.toString());
		/*
		Card card = null;
		for (int i = 0; i < (Deck.TOTAL_NUM_CARDS + 1); i++) {
			try {
				card = deck.removeNextCard();
			} catch (IllegalStateException s) {
				System.out.println(s.getMessage());
			}
			
			System.out.println(card.toString());
			System.out.println(deck.cardsInDeck());
		}
		*/
		
		/*
		
		
		
		//test addPlayer
		try {
			engine.addPlayer(new PlayerImpl("P1", "Player One", 1000));
			//engine.addPlayer(new PlayerImpl("", "Player One", 1000));
			//engine.addPlayer(null);
			engine.addPlayer(new PlayerImpl("P2", "Player Two", 1000));
			engine.addPlayer(new PlayerImpl("P3", "Player Three", 1000));
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} finally {
			System.out.println(engine.getAllPlayers().toString());
		}
	
		/*
		//test removePlayer
		try {
			//engine.removePlayer("P2");
			engine.removePlayer(null);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} finally {
			System.out.println(engine.getAllPlayers().toString());
		}
		
		
		
		try {
			//engine.placeBet(null, 100);
			//engine.placeBet("P2", 100);
			
			engine.placeBet("P1", 100);
			engine.placeBet("P2", 100, Suit.CLUBS);
			engine.placeBet("P3", 500);
			//engine.placeBet("P1", 50);
			//engine.placeBet("P1", 150, null);
		} catch (NullPointerException np2) {
			System.out.println(np2.getMessage());
		} catch (IllegalArgumentException ia1) {
			System.out.println(ia1.getMessage());
		} finally {
			System.out.println(engine.getAllPlayers().toString());
		}
		
		try {
			engine.dealPlayer("P1", 100);
			System.out.println("Test");
			engine.dealPlayer("P2", 100);
			//engine.dealPlayer("P3", 100);
			//engine.dealPlayer(null, 100);
			//engine.dealPlayer("P2", 100);
			//engine.dealPlayer("P1", -100);
			//engine.dealPlayer("P1", 100);
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (IllegalStateException is) {
			System.out.println(is.getMessage());
		} finally {
			System.out.println(engine.getAllPlayers().toString());
		}
		
		try {
			//engine.dealHouse(-100);
			engine.dealHouse(100);
		} catch (IllegalArgumentException is) {
			System.out.println(is.getMessage());
		} finally {
			System.out.println(engine.getAllPlayers().toString());
		}
		
		engine.resetAllBetsAndHands();
		System.out.println(engine.getAllPlayers().toString());
		*/
	}
	
}
