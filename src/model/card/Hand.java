package model.card;

import java.util.Collection;

public interface Hand
{
	/**
	 * A constant used to determine the maximum score of a hand
	 */
	public static int BUST_SCORE = 21;
	
	/**
	 * The supplied {@link model.card.Card} is dealt to hand.
	 * 
	 * <p>
	 * If, when added to the hand, the total score for all cards does not exceed
	 * {@link Hand#BUST_SCORE} then the card is added to the hand and the method
	 * returns {@code true}. Otherwise the method returns {@code false}.
	 * 
	 * @param card
	 *            to be added (or rejected) to the hand
	 * @return {@code true} if the card was added; otherwise {@code false}.
	 */
	public boolean dealCard(Card card);
	
	/**
	 * Returns {@code true} if the hand contains no cards; i.e. if it is empty
	 * 
	 * @return {@code true} if the hand contains no cards; otherwise
	 *         {@code false}
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of cards currently held by the hand. Returns 0 if the
	 * hand is empty.
	 * 
	 * @return the number of cards currently held by the hand
	 */
	public int getNumberOfCards();
	
	/**
	 * Returns the total score based on the {@link model.card.Rank} of every
	 * card currently held in the hand. Returns 0 if the hand is empty.
	 * 
	 * @return the total score of the hand
	 * 
	 * @see model.card.Rank
	 * 
	 */
	public int getScore();
	
	/**
	 * Returns the total number of cards in the hand of the specified
	 * {@link model.card.Suit}
	 * 
	 * <p>
	 * If there are no cards in the hand whose suit matches that of the supplied
	 * parameter the method shall return 0. It should also return 0 if the
	 * supplied parameter is {@code null}.
	 * 
	 * @param suit
	 *            the suit for which to count the number of cards in the hand
	 * 			
	 * @return the total number of cards in the hand of the specified
	 *         {@link model.card.Suit}
	 */
	public int getSuitCount(Suit suit);
	
	/**
	 * Returns an <b>unmodifiable</b> collection (shallow copy) of all cards
	 * currently in the hand
	 * 
	 * @return an unmodifiable collection of all the cards currently in the hand
	 */
	public Collection<Card> getCards();
	
	/**
	 * Resets the hand, removing all cards and any associated score
	 */
	public void reset();
	
	/**
	 * Override {@link java.lang.Object#toString()}.
	 * 
	 * Generates a string representing the hand as seen in the output trace. The
	 * following are examples of such strings.
	 * <ul>
	 * <li>Empty Hand
	 * <li>Hand of 1 cards [2 of Clubs] Score: 2
	 * <li>Hand of 2 cards [2 of Clubs, 7 of Diamonds] Score: 9
	 * <li>Hand of 4 cards [Ace of Clubs, 4 of Diamonds, Queen of Clubs, Ace of Diamonds] Score: 16
	 * </ul>
	 * 
	 * @return a string representing the hand as seen in the output trace
	 */
	@Override
	public String toString();
	
}
