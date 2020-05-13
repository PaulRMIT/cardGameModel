package model.card;

/**
 * This interface is used to represent a single card and should be implemented
 * by the concrete class {@link model.card.CardImpl}
 * 
 * @author Ross Nye
 * 
 * @see model.card.CardImpl
 * @see model.card.Suit
 * @see model.card.Rank
 *
 */
public interface Card extends Comparable<Card>
{
	/**
	 * A simple getter to return the suit of card.
	 * 
	 * @return the card's suit
	 * 
	 * @see model.card.Suit
	 */
	public Suit getSuit();
	
	/**
	 * A simple getter to return the rank of card.
	 * 
	 * @return the card's rank.
	 * 
	 * @see model.card.Rank
	 */
	public Rank getRank();
	
	/**
	 * Returns the value of the card's rank.
	 * 
	 * <p>
	 * Number cards return their face value. Ace returns 1. Court Cards (J,Q,K)
	 * return 10.
	 * 
	 * @return the value of the card's rank.
	 * 
	 * @see model.card.Rank
	 */
	public int getValue();
	
	/**
	 * Overrides the method {@link java.lang.Object#equals(Object)} to provide a
	 * method to testing equality for {@link model.card.Card} objects.
	 * 
	 * <p>
	 * The same rules of reflexivity, symmetry, transitivity, and consistency
	 * apply. However, the two object should be tested and treated as
	 * {@link model.card.Card} objects and the two fields compared. If both the
	 * {@link model.card.Suit} and the {@link model.card.Rank} are equal, the
	 * {@link model.card.Card} objects are equal.
	 * 
	 * 
	 * @param obj
	 *            the reference object with which to compare.
	 * 			
	 * @return {@code true} if this {@link model.card.Card} object is the same
	 *         as the obj argument; {@code false} otherwise
	 * 		
	 * @see java.lang.Object#equals(Object)
	 * @see model.card.Suit
	 * @see model.card.Rank
	 */
	@Override
	public boolean equals(Object obj);
	
	/**
	 * Overrides the method {@link java.lang.Object#hashCode()}.
	 * 
	 * <p>
	 * See {@link java.lang.Object#hashCode()} for relevant information for
	 * implementing this method.
	 * 
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode();
	
	/**
	 * Override {@link java.lang.Comparable#compareTo(Object)} Compares this
	 * Card with the supplied Card for order. Returns a negative integer, zero,
	 * or a positive integer if this Card is less than, equal to, or greater
	 * than the supplied Card.
	 * 
	 * <p>
	 * If the two cards are of the same suit the card ranks used to make the
	 * comparison, using the natural order or rank (Ace, 2, ... 10, Jack, Queen,
	 * King). However if the cards are of different suit, then the suit order is
	 * used which determined by ascending alphabetical order: clubs (lowest),
	 * followed by diamonds, hearts, and spades (highest)
	 * 
	 * @return a negative integer, zero, or a positive integer representing the
	 *         order of the two cards as above
	 * 		
	 */
	@Override
	public int compareTo(Card card);
	
	/**
	 * Override {@link java.lang.Object#toString()}.
	 * 
	 * Generates a string representing the card as seen in the output trace. The
	 * following are examples of such strings.
	 * <ul>
	 * <li>2 of Clubs
	 * <li>7 of Diamonds
	 * <li>Queen of Hearts
	 * <li>Ace of Spades
	 * </ul>
	 * 
	 * @return a string representing the card as seen in the output trace
	 */
	@Override
	public String toString();
}
