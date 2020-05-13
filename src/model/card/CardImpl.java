package model.card;

public class CardImpl implements Card {

	private Suit suit;
	private Rank rank;

	public CardImpl(Suit suit, Rank rank) {

		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public int compareTo(Card o) {
		if (this.suit.compareTo(o.getSuit()) == 0) {
			if (this.rank.getRankValue() < o.getRank().getRankValue())
				return -1;
			else if (this.rank.getRankValue() == o.getRank().getRankValue())
				return 0;
			else
				return 1;
		} else
			return this.suit.compareTo(o.getSuit());
	}

	@Override
	public boolean equals(Object obj) {
		Card checkCard = null;
		if (obj.equals(this))
			checkCard = (Card) obj;
		else
			return false;
		if (this.rank.getRankValue() == checkCard.getRank().getRankValue() 
				&& (this.suit.equals(checkCard.getSuit())))
			return true;
		else
			return false;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return rank.getRankValue();
	}

	@Override
	public int hashCode() {
		int hashcode = this.getValue();
		if (this.rank == Rank.JACK)
			hashcode += 1;
		if (this.rank == Rank.QUEEN)
			hashcode += 2;
		if (this.rank == Rank.KING)
			hashcode += 3;

		if (this.suit == Suit.DIAMONDS)
			hashcode += 100;
		else if (this.suit == Suit.HEARTS)
			hashcode += 200;
		else if (this.suit == Suit.SPADES)
			hashcode += 300;
		return hashcode;
	}

	@Override
	public String toString() {
		String cardString = this.rank.toString() + " of " + this.suit.toString();
		return cardString;
	}

}
