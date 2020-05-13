package model.bet;

import model.Player;
import model.card.Hand;

/**
 * Represents a bet in the Game Engine in <b>Further Programming Assignment</b>.
 * 
 * This interface is extended by {@link model.bet.ScoreBet} and {@link model.bet.SuitBet}. You're
 * encouraged to think about the common functionality that exists between them.
 * <p>
 * This interface extends {@link java.lang.Comparable} to allow Bets (specifically their amounts) to
 * be compared.
 * 
 * @author Ross Nye
 * 
 * @see model.bet.BetResult
 * @see model.bet.ScoreBet
 * @see model.bet.SuitBet
 * @see java.lang.Comparable
 */
public interface Bet extends Comparable<Bet>
{
	/**
	 * A simple getter
	 * 
	 * @return the Player who placed the bet
	 */
	public Player getPlayer();
	
	
	/**
	 * A simple getter
	 * 
	 * @return the bet amount
	 */
	public int getAmount();
	
	
	/**
	 * Returns the bet multiplier used to calculate how much the player wins
	 * for a winning bet
	 * 
	 * @return the bet multiplier
	 */
	public int getMultiplier();
	
	/**
	 * Finalises a bet based on the supplied house hand and returns a {@link model.bet.BetResult}
	 * to indicate the result of the bet. This result should also be stored in the bet for later use
	 * 
	 * @param houseHand the hand that was dealt to the house used to finalise the bet
	 * 
	 * @return the result of the bet
	 * 
	 * @see model.bet.BetResult
	 * @see model.bet.Bet#getResult()
	 * @see model.bet.Bet#getOutcome()
	 */
	public BetResult finaliseBet(Hand houseHand);
	
	/**
	 * Getter that returns the result of a bet. Before the bet has been finalised this method should
	 * return {@link model.bet.BetResult#UNDETERMINED}
	 * 
	 * @return the result of the Bet, or {@link model.bet.BetResult#UNDETERMINED} if the bet has not been
	 * finalised
	 * 
	 * @see model.bet.BetResult
	 */
	public BetResult getResult();

	/**
	 * <p>
	 * Returns the outcome of the bet; i.e. the amount won or lost as a result of the bet.</p>
	 * 
	 * If this method is called prior to the bet being finalised then it should return 0,
	 * otherwise is should return a negative value for a loss and a positive value for a win,
	 * both of which should be bases on the bet amount and the bet multiplier. A draw should
	 * return 0.
	 * 
	 * @return 0 or a negative or positive value indicating the amount won or lost by the
	 * finalised bet. 
	 */
	public int getOutcome();
	
	
	/**
	 * <p>
	 * Returns the outcome of the bet; i.e. the amount won or lost as a result of the bet.</p>
	 * 
	 * This overloaded version of the method can be supplied a {@link model.bet.BetResult} parameter
	 * meaning it can be called prior to the bet being finalised to show the amount which <em>may</em>
	 * be won or lost by the bet. The returned amount shall the same as defined by 
	 * {@link model.bet.Bet#getOutcome()}
	 * 
	 * @param result the {@link BetResult} to base the outcome.
	 * 
	 * @return 0 or a negative or positive value indicating the amount won or lost by the
	 * based on the supplied {@link BetResult}
	 */
	public int getOutcome(BetResult result);
	
	
	/**
	 * Override {@link java.lang.Comparable#compareTo(Object)}
	 * Compares this Bet with the supplied Bet for order. 
	 * Returns a negative integer, zero, or a positive integer as this Bet value is less than, 
	 * equal to, or greater than the supplied Bet.
	 * <p>
	 * <b>Hint:</b> It's just some very simple maths to get the return value.</p> 
	 * 
	 * @return a negative integer, zero, or a positive integer as this Bet value is less than, 
	 * equal to, or greater than the Bet value.
	 * 
	 */
	@Override
	public int compareTo(Bet bet);	

	
	/**
	 * Override {@link java.lang.Object#toString()}.
	 * 
	 * Generates a string representing the bet as seen in the output trace. The following are
	 * examples of such strings (with the first being supplied by @link {@link model.bet.Bet#NO_BET}
	 * <ul>
	 * <li>No Bet
	 * <li>Score Bet for 100
	 * <li>Suit Bet for 200 on Clubs
	 * </ul>
	 * 
	 * @return a string representing the bet as seen in the output trace
	 */
	@Override
	public String toString();

	
	
	/**
	 * A static Bet object used for indication of no bet.
	 */
	static final Bet NO_BET = new Bet()
	{
		@Override
		public Player getPlayer()
		{
			return null;
		}
		
		@Override
		public int getAmount()
		{
			return 0;
		}
		
		@Override
		public int getMultiplier()
		{
			return 0;
		}
		
		@Override
		public BetResult finaliseBet(Hand houseHand)
		{
			return BetResult.UNDETERMINED;
		}
		
		@Override
		public int getOutcome(BetResult result)
		{
			return 0;
		}
		@Override
		public int getOutcome()
		{
			return 0;
		}
		
		@Override
		public BetResult getResult()
		{
			return BetResult.UNDETERMINED;
		}
		
		@Override
		public int compareTo(Bet bet)
		{
			return bet == this ? 0 : -bet.getAmount();
		}
		
		@Override
		public String toString()
		{
			return "No Bet";
		}
	};


}
