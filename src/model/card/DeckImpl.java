package model.card;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DeckImpl implements Deck {

	private static Deque<Card> deck;

	private DeckImpl() {
		deck = new LinkedList<Card>();
	}

	@Override
	public Card removeNextCard() throws IllegalStateException {
		if (deck.isEmpty())
			throw new IllegalStateException("Deck is empty. New deck required.");
		return deck.pollFirst();
	}

	@Override
	public int cardsInDeck() {
		return deck.size();
	}

	@Override
	public void shuffleDeck() {
		Collections.shuffle((List<?>) deck);
	}

	public static Deck createSortedDeck() {
		Deck newDeck = new DeckImpl();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new CardImpl(suit, rank);
				deck.addFirst(card);
			}
		}
		return newDeck;
	}

	public static Deck createShuffledDeck() {
		Deck newDeck = new DeckImpl();
		newDeck = createSortedDeck();
		newDeck.shuffleDeck();
		return newDeck;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card: deck) {
			sb.append(card.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
