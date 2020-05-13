package model.bet;

import model.Player;
import model.card.Hand;
import model.card.Suit;

public class SuitBetImpl extends AbstractBet implements SuitBet {

	private static int MULTIPLIER = 4;

	private Suit suit;
	private BetResult result = BetResult.UNDETERMINED;

	public SuitBetImpl(Player player, int amount, Suit suit) throws NullPointerException, IllegalArgumentException {
		super(player, amount);
		this.suit = suit;
	}

	@Override
	public int getMultiplier() {
		return SuitBetImpl.MULTIPLIER;
	}

	@Override
	public BetResult finaliseBet(Hand houseHand) {
		if (super.getPlayer().getHand().getSuitCount(this.suit) <= houseHand.getSuitCount(this.suit))
			this.result = BetResult.PLAYER_LOSS;
		else if (super.getPlayer().getHand().getSuitCount(this.suit) > houseHand.getSuitCount(this.suit))
			this.result = BetResult.PLAYER_WIN;
		return this.result;
	}

	@Override
	public BetResult getResult() {
		return this.result;
	}

	@Override
	public Suit getSuit() {
		return this.suit;
	}

	@Override
	public String toString() {
		String betString = "Suit Bet for " + this.getAmount() + " on " + this.suit.toString();
		return betString;
	}
}
