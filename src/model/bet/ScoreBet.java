package model.bet;

/**
 * This interface extends {@link model.bet.Bet} and should be implemented
 * {@link model.bet.ScoreBetImpl}.
 * 
 * <p>
 * Score bets compare the total score of the player's cards with that of the
 * house. The score is calculated by adding the rank of each card. Aces
 * <b>only</b> have a rank of 1, and not 1 or 11 as often seen in many card
 * games. Whoever has the <b>highest score wins</b>. A draw occurs when both
 * scores are equal.
 * 
 *<p>
 * If a player wins a score bet they win the bet amount multiplied by 2.
 *  
 * @author Ross Nye
 * 
 * @see model.bet.Bet
 * @see model.bet.ScoreBetImpl
 *
 */
public interface ScoreBet extends Bet
{

}
