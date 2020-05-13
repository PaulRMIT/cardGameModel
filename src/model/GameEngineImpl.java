package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bet.Bet;
import model.bet.ScoreBetImpl;
import model.bet.SuitBetImpl;
import model.card.Card;
import model.card.Deck;
import model.card.DeckImpl;
import model.card.Hand;
import model.card.HandImpl;
import model.card.Suit;
import view.GameCallback;

public class GameEngineImpl implements GameEngine {

	private Map<String, Player> playerMap = new HashMap<String, Player>();
	private List<GameCallback> callbacks = new ArrayList<GameCallback>();
	private Deck deck = null;

	public GameEngineImpl() {
	}

	@Override
	public void registerCallback(GameCallback callback) {
		callbacks.add(callback);

	}

	@Override
	public void removeCallback(GameCallback callback) {
		callbacks.remove(callback);

	}

	@Override
	public void addPlayer(Player player) throws NullPointerException, IllegalArgumentException {
		checkPlayer(player);
		 Player result = playerMap.putIfAbsent(player.getId(), player);
		 if (result != null) {
			 throw new IllegalArgumentException("That Player ID already exists.");
		 }
		 
		
		for (GameCallback callback : callbacks) {
			callback.addPlayer(player);
		}
	}

	@Override
	public void removePlayer(String playerId) throws NullPointerException, IllegalArgumentException {
		Player player = playerMap.get(playerId);
		if (player == null)
			throw new IllegalArgumentException("No player found with that ID");
		this.playerMap.remove(playerId);
		for (GameCallback callback : callbacks) {
			callback.removePlayer(player);
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> playersCopy = Collections.unmodifiableCollection(playerMap.values());
		return playersCopy;
	}

	@Override
	public void placeBet(String playerId, int amount) throws NullPointerException, IllegalArgumentException {
		Bet bet = null;
		Player player = playerMap.get(playerId);
		checkPositive(amount);
		checkBetAmount(amount, player);
		bet = new ScoreBetImpl(player, amount);
		player.assignBet(bet);
		for (GameCallback callback : callbacks) {
			callback.betUpdated(player);
		}
	}

	@Override
	public void placeBet(String playerId, int amount, Suit suit) throws NullPointerException, IllegalArgumentException {

		Bet bet = null;
		checkPlayerIdExists(playerId);
		Player player = playerMap.get(playerId);
		checkSuit(suit);
		checkPositive(amount);
		checkBetAmount(amount, player);
		bet = new SuitBetImpl(player, amount, suit);
		player.assignBet(bet);
		for (GameCallback callback : callbacks) {
			callback.betUpdated(player);
		}
	}

	@Override
	public void dealPlayer(String playerId, int delay)
			// TODO: write error handling
			throws NullPointerException, IllegalArgumentException, IllegalStateException {
		checkPlayerIdNull(playerId);
		Player player = playerMap.get(playerId);
		checkPositive(delay);
		checkNoBet(player);
		checkHand(player);
		checkDeckEmpty();
		Card card = null;
		boolean cardAdded = true;
		while (cardAdded == true) {
			card = deck.removeNextCard();
			cardAdded = player.getHand().dealCard(card);
			delay(delay);
			if (cardAdded == true) {
				for (GameCallback callback : callbacks)
					callback.playerCard(player, card);
			}
		}
		for (GameCallback callback : callbacks)
			callback.playerBust(player, card);
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		checkPositive(delay);
		Card card = null;
		Hand houseHand = new HandImpl();
		checkDeckEmpty();
		boolean cardAdded = true;
		while (cardAdded == true) {
			card = deck.removeNextCard();
			cardAdded = houseHand.dealCard(card);
			delay(delay);
			if (cardAdded == true) {
				for (int i = 0; i < callbacks.size(); i++)
					callbacks.get(i).houseCard(houseHand, card);
			}
		}
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			entry.getValue().applyBetResult(houseHand);
		}
		for (GameCallback callback : callbacks)
			callback.houseBust(houseHand, card);
	}

	@Override
	public void resetAllBetsAndHands() {
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			entry.getValue().resetBet();
			for (GameCallback callback : callbacks)
				callback.betUpdated(entry.getValue());
			entry.getValue().getHand().reset();
		}

	}
	
	private void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			System.out.println("delay time failed");
		}
	}

	/**
	 * Checks if a provided player object is null
	 * 
	 * @param player object
	 * @throws NullPointerException if the provided Player is null
	 */
	private void checkPlayer(Player player) throws NullPointerException {
		if (player == null)
			throw new NullPointerException("No player details provided.");
	}

	/**
	 * Overload of the previous method, performing the same check for a Player ID
	 * string
	 * 
	 * @param playerId
	 * @throws NullPointerException if the provided player ID is null
	 */
	private void checkPlayerIdNull(String playerId) throws NullPointerException {
		if (playerId == null)
			throw new NullPointerException("No player details provided.");
	}

	/**
	 * Checks if a player ID is found in the Players collection. Differs from
	 * getPlayerById as it is used when a player should exist - eg. when placing a
	 * bet.
	 * 
	 * @param playerId
	 * @throws IllegalArgumentException if player Id not found
	 */
	private void checkPlayerIdExists(String playerId) throws IllegalArgumentException {
		Player player = playerMap.get(playerId);
		if (player == null)
			throw new IllegalArgumentException("No player found with that ID");
	}

	/**
	 * Helper method to validate suit object is not null
	 * 
	 * @param suit
	 * @throws NullPointerException if suit object is null
	 */
	private void checkSuit(Suit suit) {
		if (suit == null)
			throw new NullPointerException("No suit details provided.");
	}

	/**
	 * Helper method to check an integer is a positive number. Used when placing a
	 * bet.
	 * 
	 * @param number
	 * @throws IllegalArgumentException if integer is below 1
	 */
	private void checkPositive(int number) {
		if (number < 0)
			throw new IllegalArgumentException("input must be a positive number");
	}

	/**
	 * Helper method to check a player has placed a bet
	 * 
	 * @param player
	 * @throws IllegalStateException if a player has not placed a bet
	 */
	private void checkNoBet(Player player) {
		if (player.getBet() == Bet.NO_BET)
			throw new IllegalStateException("The supplied player has not yet placed a bet");
	}

	/**
	 * Helper method to check if a player's hand already contains cards
	 * 
	 * @param player
	 * @throws IllegalStateException if the player's hand contains 1 or more cards
	 */
	private void checkHand(Player player) {
		if (player.getHand().getNumberOfCards() > 0)
			throw new IllegalStateException("This player already has a hand");
	}

	/**
	 * Helper method to check the requested bet amount is a) less than the player's
	 * remaining points balance, and b) larger than the player's current bet
	 * 
	 * @param amount
	 * @param player
	 * @throws IllegalArgumentException is thrown in the following cases
	 *                                  <ul>
	 *                                  <li>the supplied amount is less than the
	 *                                  players points (excluding any amount already
	 *                                  bet)
	 *                                  <li>the supplied amount is less than the
	 *                                  amount currently bet by the player
	 *                                  </ul>
	 */
	private void checkBetAmount(int amount, Player player) {
		if (amount > player.getPoints())
			throw new IllegalArgumentException("Bet amount is larger than player balance");
		else if (amount < player.getBet().getAmount())
			throw new IllegalArgumentException("Bet amount is smaller than current bet");
	}

	/**
	 * Helper method to check if deck is either empty or not created yet. If empty
	 * or not created, the method will create a new deck and trigger a callback
	 * logger
	 */
	private void checkDeckEmpty() {
		if (deck == null || deck.cardsInDeck() == 0) {
			deck = DeckImpl.createShuffledDeck();
			for (GameCallback callback : callbacks)
				callback.newDeck(deck);
		}
	}

}
