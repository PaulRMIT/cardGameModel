package model;

import model.bet.Bet;
import model.card.Hand;

/**
 * Represents a single player in the GameEngine in <b>Further Programming Assignment</b>.
 * <p>
 * Is to be implemented as {@link model.PlayerImpl}.
 * <p>
 * Each player has a unique id (the unique id is enforced elsewhere) and a name, both of which
 * cannot be changed once assigned. The player has a <em>points</em> balance which is used in
 * placing bets within the game. Bet and Hand objects also represent information relevant to
 * the game.
 * 
 * @author Ross Nye
 * 
 * @see model.GameEngine
 * @see model.bet.Bet
 * @see model.card.Hand
 */
public interface Player
{
	
	/**
	 * Simple getter for player's id
	 * 
	 * @return the player's id
	 */
	public String getId();
	
	
	
	/**
	 * Simple getter for player's name
	 * 
	 * @return the player's name
	 */
	public String getName();
	
	
	
	/**
	 * Simple getter for player's current points (excluding any amount currently bet)
	 * 
	 * @return the player's current points
	 */
	public int getPoints();
	
	
	
	/**
	 * Returns the current total points for a player, which includes any amount currently bet.
	 * <p>
	 * <b>Hint:</b> If the player doesn't have a current bet this method should return the current
	 * points, otherwise add the bet's value to it.
	 * 
	 * @return the player's current total points.
	 */
	public int getTotalPoints();
	
	
	
	/**
	 * Assigns the supplied bet placed for the player via the GameEngine.
	 * <p>
	 * The players points should be adjusted accordingly and the supplied bet parameter assigned to
	 * an appropriate instance variable.
	 * <p>
	 * Take care when replacing a bet as the original bet amount has already been deducted from the
	 * current points.
	 * <p>
	 * <b>Hint:</b> Try to ensure you don't duplicate code between the Game Engine, Bet and Player
	 * when dealing with bets.
	 * 
	 * 
	 * @param bet
	 *            a valid {@link Bet} object
	 * 
	 */
	public void assignBet(Bet bet);
	
	
	
	/**
	 * Simple getter method for the currently assigned {@link Bet}
	 * 
	 * @return the {@link Bet} object
	 */
	public Bet getBet();
	

	/**
	 * Simple getter method to return the player's {@link Hand}
	 * @return the player's {@link Hand} 
	 */
	public Hand getHand();
	

	
	/**
	 * Applies the result the player's bet based on the supplied house hand.
	 * 
	 * <p>
	 * This methods should finalise the bet and apply the win, loss or draw to
	 * the player's points balance.
	 *
	 * <p>
	 * If the supplied {@code houseHand} is {@code null} this method should do
	 * nothing (and exit gracefully).
	 * 
	 * <p>
	 * <b>Note: </b>When applying the result ensure you take into account the
	 * points that were quarantined in the bet amount.
	 * 
	 * @param houseHand
	 *            the {@link model.card.Hand} that was dealt to the House
	 */
	public void applyBetResult(Hand houseHand);
	
	
	
	/**
	 * Sets the appropriate instance variable to {@link Bet#NO_BET}
	 */
	public void resetBet();
	
	
	
	/**
	 * Override {@link java.lang.Object#toString()}.
	 * 
	 * Generates a string representing the player as seen in the output trace. The following are
	 * examples of such strings.
	 * <ul>
	 * <li>Player id=P1, name=Player One, points=800, No Bet, Empty Hand
	 * <li>Player id=P1, name=Player One, points=800, Score Bet for 100, Hand of 3 cards [6 of Spades, 7 of Clubs, 5 of Clubs] Score: 18
	 * <li>Player id=P2, name=Player Two, points=1800, Suit Bet for 100 on Clubs, Hand of 2 cards [Jack of Hearts, 7 of Clubs] Score: 17
	 * </ul>
	 * <b>Note:</b> This method uses toString() methods from other classes to produce part of these results.
	 * 
	 * @return a string representing the player as seen in the output trace
	 */
	@Override
	public String toString();



}
