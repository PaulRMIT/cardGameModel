package client;


import validate.Validator;

import model.GameEngine;
import model.GameEngineImpl;
import model.PlayerImpl;
import model.card.Suit;
import view.ConsoleLoggerCallback;

/**
 * A basic client that tests your model implementation.
 * <p>
 * Tests performed by this client are not exhaustive. As such you are encouraged to write your own
 * test client to test your model.
 * <p>
 * This client was used to produce the supplied output trace. You should compare the output of this
 * class using your model with the output trace. As such it's advisable to write your own test
 * client rather modifying this one.
 * <p>
 * This class also uses the Validator (from lib/validator.jar) to check adherence
 * to the specifications.
 * <p>
 * 
 * @author Ross Nye
 * 
 * @see model.GameEngine
 * @see validate.Validator
 *
 */
public class BasicTestClient
{
	private static int TEST_DELAY = 100;
	
	public static void main(String[] args)
	{
		// calls validator to check your code meets specifications
		// set parameter to true for more verbose output
		if(Validator.validate(true) == false)
			return;
	
		// Creates a game engine
		GameEngine engine = new GameEngineImpl();
		
		// add ConsoleLoggerCallback
		engine.registerCallback(new ConsoleLoggerCallback(engine));
		
		// you can / should be able to add a second logger (and double the logger output)
		//engine.registerCallback(new ConsoleLoggerCallback(engine));

		engine.addPlayer(new PlayerImpl("P1", "Player One", 1000));
		engine.addPlayer(new PlayerImpl("P2", "Player Two", 2000));
		engine.addPlayer(new PlayerImpl("P3", "Player Three", 3000));
		engine.addPlayer(new PlayerImpl("X4", "Player Four", 4000));
		
		// removes player
		engine.removePlayer("X4");
		
		engine.placeBet("P1", 100);
		
		engine.placeBet("P2", 100, Suit.CLUBS);
		
		engine.dealPlayer("P2", TEST_DELAY);
		engine.dealPlayer("P1", TEST_DELAY);
		
		engine.dealHouse(TEST_DELAY);
		
		engine.resetAllBetsAndHands();
		
	}
	
}
