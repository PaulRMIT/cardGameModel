package model.bet;

import model.card.Suit;

/**
 * This interface extends {@link model.bet.Bet} and should be implemented as
 * {@link model.bet.SuitBetImpl}.
 * 
 * <p>
 * Suit bets compare the total number cards of a specific suit in the player's
 * hand with that of the house. The player wins if they have <b>more</b> cards
 * of the specified then the house has of the same specified suit. Anything else
 * should be considered a loss. There is <b>no draw</b> for suit bets.
 * 
 * <p>
 * If a player wins a suit bet they win the bet amount multiplied by 4.
 * 
 * @author Ross Nye
 * 
 * @see model.bet.Bet
 * @see model.bet.SuitBetImpl
 *
 */
public interface SuitBet extends Bet
{
	/**
	 * Simple getter to return the suit the player placed a bet on
	 * 
	 * @return the {@link model.card.Suit} the player placed a bet on
	 */
	Suit getSuit();
}
