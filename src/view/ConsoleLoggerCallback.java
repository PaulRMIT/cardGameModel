package view;

import java.util.Collection;
import java.util.Formatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngine;
import model.Player;
import model.bet.Bet;
import model.card.Card;
import model.card.Deck;
import model.card.Hand;

/**
 * An implementation of GameCallback which uses a Logger to log game events to
 * the console.
 * 
 * <p>
 * <b>Important!</b> DO NOT EDIT THE STATIC BLOCK THAT SETS UP THE LOGGER OR
 * IT'S DECLARATION!
 * 
 * <p>
 * <b>Note:</b> Logging message format should be consistent with the output
 * trace.
 * 
 * @author Ross Nye
 * 
 * @see view.GameCallback
 * @see view.GameCallbackCollection
 *
 */
public class ConsoleLoggerCallback implements GameCallback {

	private GameEngine engine;
	StringBuilder sb;

	public ConsoleLoggerCallback(GameEngine engine) {
		this.engine = engine;
		this.sb = new StringBuilder();
	}

	/**
	 * A static {@link java.util.logging.Logger} object used for logging information
	 * (in this case to the console)
	 * 
	 * DO NOT EDIT!
	 */
	public static final Logger LOGGER;

	static {
		// DO NOT EDIT THIS STATIC BLOCK!!

		// Creating consoleHandler, add it and set the log levels.
		LOGGER = Logger.getLogger(ConsoleLoggerCallback.class.getName());
		LOGGER.setLevel(Level.FINER);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINER);
		LOGGER.addHandler(handler);
		LOGGER.setUseParentHandlers(false);
	}

	@Override
	public void addPlayer(Player player) {
		LOGGER.info("Added Player: " + player.toString());

	}

	@Override
	public void removePlayer(Player player) {
		LOGGER.info("Removed Player: " + player.toString());

	}

	@Override
	public void betUpdated(Player player) {
		this.sb.delete(0, sb.capacity());
		this.sb.append("Bet Updated for ");
		this.sb.append(player.getId());
		this.sb.append(" to ");
		this.sb.append(player.getBet().toString());
		LOGGER.info(this.sb.toString());

	}

	@Override
	public void newDeck(Deck deck) {
		LOGGER.info("A new deck of cards was created with " + deck.cardsInDeck() + " cards");

	}

	@Override
	public void playerCard(Player player, Card card) {
		this.sb.delete(0, sb.capacity());
		this.sb.append("Player ");
		this.sb.append(player.getId());
		this.sb.append(" dealt ");
		this.sb.append(card.getRank().toString());
		this.sb.append(" of ");
		this.sb.append(card.getSuit().toString());
		LOGGER.fine(this.sb.toString());

	}

	@Override
	public void playerBust(Player player, Card card) {
		this.sb.delete(0, sb.capacity());
		this.sb.append("Player ");
		this.sb.append(player.getId());
		this.sb.append(" bust on ");
		this.sb.append(card.getRank().toString());
		this.sb.append(" of ");
		this.sb.append(card.getSuit().toString());
		LOGGER.fine(this.sb.toString());

		this.sb.delete(0, sb.capacity());
		this.sb.append("Player score is ");
		this.sb.append(player.getHand().getScore());
		LOGGER.info(this.sb.toString());
	}

	@Override
	public void houseCard(Hand houseHand, Card card) {
		this.sb.delete(0, sb.capacity());
		this.sb.append("House dealt ");
		this.sb.append(card.getRank().toString());
		this.sb.append(" of ");
		this.sb.append(card.getSuit().toString());
		LOGGER.fine(sb.toString());
	}

	@Override
	public void houseBust(Hand houseHand, Card card) {
		Collection<Player> players = engine.getAllPlayers();
		this.sb.delete(0, sb.capacity());

		this.sb.append("House bust on ");
		this.sb.append(card.getRank().toString());
		this.sb.append(" of ");
		this.sb.append(card.getSuit().toString());
		LOGGER.fine(this.sb.toString());
		this.sb.delete(0, sb.capacity());

		this.sb.append("House Hand: Hand of ");
		this.sb.append(houseHand.getNumberOfCards());
		this.sb.append(" cards ");
		this.sb.append(houseHand.toString());
		LOGGER.info(this.sb.toString());
		this.sb.delete(0, sb.capacity());

		Formatter fmt = new Formatter(sb);
		for (Player player : players) {
			this.sb.append(player.toString());
			this.sb.append("\n");
			fmt.format("Player: %-10s", player.getId());
			fmt.format("%-20s", player.getName());
			if (player.getBet() == Bet.NO_BET) {
				fmt.format("%-20s", player.getBet().toString());
			} else {
				fmt.format("%-20s", player.getBet().getResult().toString());
				fmt.format("%-20s", player.getBet().getOutcome());
			}
			sb.append("\n");
		}
		fmt.close();
		LOGGER.info(sb.toString());
	}

}
