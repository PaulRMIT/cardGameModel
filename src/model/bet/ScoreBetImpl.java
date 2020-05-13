package model.bet;

import model.Player;

public class ScoreBetImpl extends AbstractBet implements ScoreBet {


	public ScoreBetImpl(Player player, int amount) throws NullPointerException, IllegalArgumentException {
		super(player, amount);
	}
}
