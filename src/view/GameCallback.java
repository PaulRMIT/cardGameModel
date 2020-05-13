package view;

import model.Player;
import model.card.Card;
import model.card.Deck;
import model.card.Hand;

/**
 * The callback interface in <b>Further Programming Assignment</b>
 * <p>
 * The methods is this interface are called upon as specified in the GameEngine
 * interface
 * 
 * @author Ross Nye
 * 
 * @see model.GameEngine
 */
public interface GameCallback
{
	/**
	 * Updates the view about a {@link model.Player} added to the game engine.
	 * This information should be logged using Logger info level.
	 * 
	 * @param player
	 *            the {@link model.Player} that was added
	 * 			
	 * @see model.Player
	 */
	public void addPlayer(Player player);
	
	/**
	 * Updates the view about a {@link model.Player} removed from the game
	 * engine. This information should be logged using Logger info level.
	 * 
	 * @param player
	 *            the {@link model.Player} that was added
	 * 			
	 * @see model.Player
	 */
	public void removePlayer(Player player);
	
	/**
	 * Updates view about changes to bet and points balance of a
	 * {@link model.Player}. This information should be logged using Logger info
	 * level.
	 * 
	 * @param player
	 *            the {@link model.Player} who's bet and points have been
	 *            updated
	 * 
	 * @see model.Player
	 * @see model.bet.Bet
	 */
	public void betUpdated(Player player);
	
	/**
	 * Notifies the view when a new {@link model.card.Deck} is created
	 * 
	 * @param deck
	 *            the {@link model.card.Deck} that has been created
	 * 			
	 * @see model.card.Deck
	 */
	public void newDeck(Deck deck);
	
	/**
	 * Updates the view when a {@link model.card.Card} successfully dealt to a
	 * {@link model.Player}
	 * 
	 * @param player
	 *            the {@link model.Player} who was dealt a card
	 * @param card
	 *            the {@link model.card.Card} that was dealt
	 * 			
	 * @see model.Player
	 * @see model.card.Card
	 */
	public void playerCard(Player player, Card card);
	
	/**
	 * Updates the view when a {@link model.card.Card} was unsuccessfully dealt
	 * to a {@link model.Player} and the player bust.
	 * 
	 * @param player
	 *            the {@link model.Player} who was unsuccessfully dealt the card
	 * @param card
	 *            the {@link model.card.Card} that was attempted to be dealt to
	 *            the player
	 * 
	 * @see model.Player
	 * @see model.card.Card
	 */
	public void playerBust(Player player, Card card);
	
	/**
	 * Updates the view when a {@link model.card.Card} successfully dealt to the
	 * House
	 * 
	 * @param houseHand
	 *            the House's {@link model.card.Hand}
	 * @param card
	 *            the {@link model.card.Card} that was dealt to the House
	 * 			
	 * @see model.card.Hand
	 * @see model.card.Card
	 */
	public void houseCard(Hand houseHand, Card card);
	
	/**
	 * Updates the view when a {@link model.card.Card} was unsuccessfully dealt
	 * to the House
	 * 
	 * @param houseHand
	 *            the House's {@link model.card.Hand}
	 * @param card
	 *            the {@link model.card.Card} that was attempted to be dealt to
	 *            the House
	 * 
	 * @see model.card.Hand
	 * @see model.card.Card
	 */
	public void houseBust(Hand houseHand, Card card);
}
