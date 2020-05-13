package model;

import model.bet.Bet;
import model.bet.BetResult;
import model.card.Hand;
import model.card.HandImpl;

public class PlayerImpl implements Player {

	private String id;
	private String name;
	private int points;
	private int totalPoints;
	private Bet currentBet;
	private Hand currentHand;

	public PlayerImpl(String id, String name, int points) throws NullPointerException, IllegalArgumentException {

		if ((id == "") || (name == "") || (points < 1))
			throw new IllegalArgumentException("Could not create player.");
		this.id = id;
		this.name = name;
		this.points = points;
		this.totalPoints = points;
		this.currentBet = Bet.NO_BET;
		this.currentHand = new HandImpl();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public int getTotalPoints() {
		return this.totalPoints;
	}

	@Override
	public void assignBet(Bet bet) {
		this.points -= bet.getAmount();
		this.currentBet = bet;
	}

	@Override
	public Bet getBet() {
		return this.currentBet;
	}

	@Override
	public Hand getHand() {
		return this.currentHand;
	}

	@Override
	public void applyBetResult(Hand houseHand) {
		if (houseHand == null) {
			System.out.println("No house hand value was provided");
			return;
		} else {
			BetResult result = this.currentBet.finaliseBet(houseHand);
			if (this.currentBet.getOutcome(result) >= 0)
				this.points += this.currentBet.getOutcome(result);
		}
	}

	@Override
	public void resetBet() {
		if (this.currentBet.getResult() == BetResult.PLAYER_LOSS)
			this.totalPoints -= this.currentBet.getAmount();
		else
			this.totalPoints = this.points + this.currentBet.getAmount();
		this.points = this.totalPoints;
		this.currentBet = Bet.NO_BET;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player ID=");
		sb.append(this.id);
		sb.append(", name=");
		sb.append(this.name);
		sb.append(", points=");
		sb.append(this.points);
		sb.append(", ");
		sb.append(this.currentBet.toString());
		sb.append(", ");
		sb.append(this.currentHand.toString());
		return sb.toString();
	}

}
