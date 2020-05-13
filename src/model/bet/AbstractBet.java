package model.bet;

import model.Player;
import model.card.Hand;

public abstract class AbstractBet implements Bet {

	private static int MULTIPLIER = 2;
	
	private Player player;
	private int amount;
	private BetResult result = BetResult.UNDETERMINED;
	
	public AbstractBet(Player player, int amount) throws NullPointerException, IllegalArgumentException {
		
		if (player == null)
			throw new NullPointerException();
		else if (amount < 1)
			throw new IllegalArgumentException();
		else if (player.getPoints() < amount)
			throw new IllegalArgumentException();
		else {
			this.player = player;
			this.amount = amount;
		}
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public int getAmount() {
		return this.amount;
	}

	@Override
	public int getMultiplier() {
		return MULTIPLIER;
	}
	
	@Override
	public BetResult finaliseBet(Hand houseHand) {
		this.result = BetResult.UNDETERMINED;
		if (houseHand.getScore() > player.getHand().getScore())
			this.result = BetResult.PLAYER_LOSS;
		else if (houseHand.getScore() < player.getHand().getScore())
			this.result = BetResult.PLAYER_WIN;
		else
			this.result = BetResult.DRAW;
		return this.result;
	}
	
	@Override
	public BetResult getResult() {
		return this.result;
	}

	@Override
	public int getOutcome() {
		return calculateWinnings(this.getResult(), this.amount);
	}

	@Override
	public int getOutcome(BetResult result) {
		int sum = calculateWinnings(result, this.amount);
		return sum;
	}

	@Override
	public int compareTo(Bet bet) {
		int delta = this.amount - bet.getAmount();
		return delta;
	}

	@Override
	public String toString() {
		String betString = "Score Bet for " + this.getAmount();
		return betString;
	}

	// helper method to calculate winnings
	private int calculateWinnings(BetResult result, int amount) {
		int winnings = 0;
		if (result == BetResult.PLAYER_WIN)
			winnings = this.amount * this.getMultiplier();
		else if (result == BetResult.PLAYER_LOSS)
			winnings = this.amount * -1;
		else
			winnings = this.amount;
		return winnings;
	}
}
