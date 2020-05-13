package model.card;

/**
 * This interface is used to represent a deck of card and should be implemented
 * by the concrete class {@link model.card.DeckImpl}
 * 
 * <p>
 * <b>Note: </b>{@link model.card.DeckImpl} includes some static methods not
 * defined by this interface.
 * 
 * @author Ross Nye
 * 
 * @see model.card.DeckImpl
 * @see model.card.Card
 * @see model.card.Suit
 * @see model.card.Rank
 *
 */
/**
 * @author Ross
 *
 */
public interface Deck
{
	public final static int TOTAL_NUM_CARDS = Rank.values().length * Suit.values().length;
	
	/**
	 * Removes a card from the deck and returns the card to caller
	 * 
	 * @return the card removed from the deck
	 * 
	 * @throws IllegalStateException is thrown if the deck is empty (contains no cards)
	 * 
	 * @see model.card.Card
	 */
	public Card removeNextCard() throws IllegalStateException;
	
	
	/**
	 * @return the number of cards remaining in the deck
	 */
	public int cardsInDeck();
	
	
	/**
	 * Shuffles (randomises the order) of the cards in the deck
	 */
	public void shuffleDeck();
	
}
