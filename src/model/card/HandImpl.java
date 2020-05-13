package model.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HandImpl implements Hand {

	private List<Card> cards;

	public HandImpl() {
		this.cards = new ArrayList<Card>();
	}

	@Override
	public boolean dealCard(Card card) {
		boolean check = false;
		if (card.getValue() + this.getScore() <= BUST_SCORE) {
			cards.add(card);
			check = true;
		} else
			check = false;
		return check;
	}

	@Override
	public boolean isEmpty() {
		boolean check = false;
		if (cards.isEmpty())
			check = true;
		else
			check = false;
		return check;
	}

	@Override
	public int getNumberOfCards() {
		return cards.size();
	}

	@Override
	public int getScore() {
		int score = 0;
		for (int i = 0; i < cards.size(); i++)
			score += cards.get(i).getValue();
		return score;
	}

	@Override
	public int getSuitCount(Suit suit) {
		int count = 0;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().compareTo(suit) == 0)
				count++;
		}
		return count;
	}

	@Override
	public Collection<Card> getCards() {
		List<Card> cardCopy = Collections.unmodifiableList(cards);
		return cardCopy;
	}

	@Override
	public void reset() {
		cards.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isEmpty() == true)
			sb.append("Empty Hand");
		else {
			sb.append("Hand of ");
			sb.append(this.getNumberOfCards());
			sb.append(" cards ");
			sb.append(this.getCards());
			sb.append(" Score: ");
			sb.append(this.getScore());
		}
		return sb.toString();
	}

}
