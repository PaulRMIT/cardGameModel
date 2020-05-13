package model;

import java.util.Collection;

import model.card.Suit;
import view.GameCallback;
import view.GameCallbackCollection;

/**
 * The main interface in <b>Further Programming Assignment</b>.
 * <p>
 * This interface should be implemented as {@link model.GameEngineImpl} which
 * has a no argument constructor. All the methods in the interface need to be
 * carefully implemented as per the Javadoc below.
 * 
 * @author Ross Nye
 * 
 * @see view.GameCallbackCollection
 * @see model.Player
 * @see model.bet.Bet
 * @see model.card.Hand
 * @see model.card.Deck
 */
public interface GameEngine extends GameCallbackCollection
{
	/**
	 * Adds supplied player to the Game Engine. The supplied player must be
	 * valid player with a unique ID; if the ID already exists the add should be
	 * rejected and an exception thrown (see below).
	 * <p>
	 * <b>Hint:</b> The unique ID should be enforced here and not in
	 * {@link model.PlayerImpl} class
	 * 
	 * @param player
	 *            the player to be added to the Game Engine
	 * 			
	 * @throws NullPointerException
	 *             if the player parameter is null
	 * 			
	 * @throws IllegalArgumentException
	 *             if a player with the same ID as the player supplied in the
	 *             parameter already exists in the Game Engine
	 * 			
	 * @see model.Player
	 */
	public void addPlayer(Player player) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Removes the player with the matching supplied ID as a parameter from the
	 * Game Engine. If the supplied ID is not valid an exception is thrown (see
	 * below).
	 * 
	 * @param playerId
	 *            the ID of the player that is to be removed
	 * 			
	 * @throws NullPointerException
	 *             if the supplied player ID is null
	 * 			
	 * @throws IllegalArgumentException
	 *             if there is no player with a matching ID in the Game Engine
	 * 			
	 * @see model.Player
	 */
	public void removePlayer(String playerId) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * @return an unmodifiable collection (shallow copy) of all Players
	 * 
	 * @see model.Player
	 */
	public Collection<Player> getAllPlayers();
	
	/**
	 * Places a score bet of the supplied amount for the player who's id matches
	 * the supplied playerId.
	 * 
	 * <p>
	 * A bet can replace an existing bet if its bet amount is greater than the
	 * existing bet.
	 * 
	 * @param playerId
	 *            the id of the {@link model.Player} who placed the bet
	 * @param amount
	 *            the amount the was bet
	 * @throws NullPointerException
	 *             is thrown if the playerId parameter is null
	 * @throws IllegalArgumentException
	 *             is thrown in the following cases
	 *             <ul>
	 *             <li>There is no current player with the supplied playerId
	 *             <li>The supplied amount is not a positive number
	 *             <li>The supplied player is does not have enough points to
	 *             place the bet
	 *             <li>If, when replacing an existing bet, the bet amount is not
	 *             greater the existing bet
	 *             </ul>
	 * 
	 * @see model.bet.Bet
	 * @see model.bet.ScoreBet
	 */
	public void placeBet(String playerId, int amount) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Places a suit bet of the supplied amount for the player who's id matches
	 * the supplied playerId.
	 * 
	 * <p>
	 * A bet can replace an existing bet if its bet amount is greater than the
	 * existing bet.
	 * 
	 * @param playerId
	 *            the id of the {@link model.Player} who placed the bet
	 * @param amount
	 *            the amount the was bet
	 * @param suit
	 *            the {@link model.card.Suit} that the player is betting on.
	 * 			
	 * @throws NullPointerException
	 *             is thrown if the playerId or the suit parameter is null
	 * 			
	 * @throws IllegalArgumentException
	 *             is thrown in the following cases
	 *             <ul>
	 *             <li>there is no current player with the supplied playerId
	 *             <li>the supplied amount is not a positive number
	 *             <li>the supplied player is does not have enough points to
	 *             place the bet
	 *             <li>if, when replacing an existing bet, the bet amount is not
	 *             greater the existing bet
	 *             </ul>
	 * 
	 * @see model.bet.Bet
	 * @see model.bet.SuitBet
	 */
	public void placeBet(String playerId, int amount, Suit suit) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Deals a hand to the specified player.
	 * 
	 * <p>
	 * Cards are dealt to the specified player in the following manner
	 * <ul>
	 * <li>a card is removed from the deck and dealt to the player
	 * <li>a delay occurs, of the specified time in ms, between the card being
	 * removed from the deck and it being dealt to the player
	 * <ul>
	 * <li>if the card <b>does not</b> bust in player's hand:
	 * <ul>
	 * <li>the {@link view.GameCallback#playerCard(Player, model.card.Card)} is
	 * invoked on <b>all</b> the {@link view.GameCallback} objects that have
	 * been registered, notifying the player and the card dealt (all get the
	 * same information!)
	 * <li>the process is then repeated for the next card until a bust occurs
	 * </ul>
	 * <li>if the card <b>does</b> bust in the player's hand
	 * <ul>
	 * <li>the {@link view.GameCallback#playerBust(Player, model.card.Card)} is
	 * invoked on <b>all</b> the {@link view.GameCallback} objects that have
	 * been registered, notifying the player and the card that was attempted to
	 * be dealt to the player
	 * </ul>
	 * </ul>
	 * </ul>
	 * 
	 * <p>
	 * <b>Note: </b>A bust card is burnt, and not returned to the deck or dealt
	 * to any subsequent player.
	 * 
	 * 
	 * @param playerId
	 *            the id of the {@link model.Player} who placed the bet
	 * @param delay
	 *            the delay in milliseconds prior to each card being dealt
	 * @throws NullPointerException
	 *             if the supplied playerId is {@code null}
	 * 			
	 * @throws IllegalArgumentException
	 *             in the following cases
	 *             <ul>
	 *             <li>there is no current player with the supplied playerId
	 *             <li>the supplied delay is a negative number
	 *             </ul>
	 * 
	 * @throws java.lang.IllegalStateException
	 *             in the following cases
	 *             <ul>
	 *             <li>if the player with the supplied playerId has not placed a
	 *             bet
	 *             <li>if the player with the supplied playerId has already been
	 *             dealt to
	 *             </ul>
	 */
	public void dealPlayer(String playerId, int delay)
			throws NullPointerException, IllegalArgumentException, java.lang.IllegalStateException;
	
	/**
	 * Deals a hand to the House using the same procedure as outlined in
	 * {@link #dealPlayer(String, int)}
	 * 
	 * <p>
	 * The {@link view.GameCallback#houseCard(model.card.Hand, model.card.Card)}
	 * and {@link view.GameCallback#houseBust(model.card.Hand, model.card.Card)}
	 * methods are used in place of the player based callback methods.
	 * 
	 * <p>
	 * <b>Important: </b>Prior to invoking
	 * {@link view.GameCallback#houseBust(model.card.Hand, model.card.Card)} all
	 * the bets must applied so the correct information is available to the
	 * callbacks.
	 * 
	 * 
	 * @param delay
	 *            the delay in milliseconds prior to each card being dealt
	 * @throws IllegalArgumentException
	 *             if the supplied delay is a negative number
	 */
	public void dealHouse(int delay) throws IllegalArgumentException;
	
	/**
	 * Resets all bets for all the players in the Game Engine.
	 * <p>
	 * As each player's bet is reset and all the registered GameCallback objects
	 * should be notified of the updated bet using the
	 * {@link GameCallback#betUpdated(Player)} callback method. After the bets
	 * for each player is reset the player's hand should also be reset in order
	 * be ready for a new hand to be dealt to the player.
	 * <p>
	 * <b>Note:</b> Bets should be reset for all players by the client after a
	 * spin.
	 * 
	 * @see Player#resetBet()
	 */
	public void resetAllBetsAndHands();
	
}
