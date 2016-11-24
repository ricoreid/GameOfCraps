/*
 * (Game of Craps) Write an application that runs 1000 games of craps (Fig. 6.9) and answers
 * the following questions:
 * a) How many games are won on the first roll, second roll, ..., twentieth roll and after the
 * twentieth roll?
 * b) How many games are lost on the first roll, second roll, ..., twentieth roll and after the
 * twentieth roll?
 * c) What are the chances of winning at craps? [Note: You should discover that craps is one
 * of the fairest casino games. What do you suppose this means?]
 * d) What is the average length of a game of craps?
 * e) Do the chances of winning improve with the length of the game?
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package practice.exercise;

import java.util.Random;

public class Craps
{
	Random randomNumber = new Random();
	
	private int throws_won[] = new int[50];
	private int throws_lost[] = new int[50];
	
	private final int SNAKE_EYES = 2;
	private final int TREY = 3;
	private final int SEVEN = 7;
	private final int YO_LEVEN = 11;
	private final int BOX_CARS = 12;
	
	private static enum Status { WON, LOST, CONTINUE };
	
	private int point;
	
	int rollDice()
	{
		int value = 1 + randomNumber.nextInt(6);
		
		return value;
	} // end method rollDice
	
	void gamePlay()
	{
		Status game_status;
		int dice_throw = 0;
		
		int die1 = rollDice();
		int die2 = rollDice();
		
		int sum_of_dice = die1 + die2;
		
		switch (sum_of_dice)
		{
			case SEVEN:
			case YO_LEVEN:
				game_status = Status.WON;
				ThrowsWon(dice_throw);
				break;
			
			case SNAKE_EYES:
			case TREY:
			case BOX_CARS:
				game_status = Status.LOST;
				ThrowsLost(dice_throw);
				break;
				
				default:
					game_status = Status.CONTINUE;
					point = sum_of_dice;	
		} // end switch/case
		
		while( game_status == Status.CONTINUE )
		{
			dice_throw++;
			die1 = rollDice();
			die2 = rollDice();
			sum_of_dice = die1 + die2;
			
			if(sum_of_dice == point)
			{
				game_status = Status.WON;
				ThrowsWon(dice_throw);
			}
			else if(sum_of_dice == 7)
			{
				game_status = Status.LOST;
				ThrowsLost(dice_throw);
			}
			
		} // end while loop
		
	} // end method gamePlay
	
	void ThrowsWon(int throw_num )
	{
		++throws_won[throw_num];
	} // end method Throws
	
	void ThrowsLost(int throw_num)
	{
		++throws_lost[throw_num];
	} // end method ThrowsLost
	
	void GameStats()
	{
		int won20s = 0;
		int lost20s = 0;
		
		for(int i = 0; i < 20; i++)
		{
			System.out.println(throws_won[i] + " won and, " + throws_lost[i] + " lost on throw #" + (i+1) );
		}
		
		for(int i = 21; i < 50; i++)
		{
			won20s += throws_won[i];
			lost20s += throws_lost[i];
		}
		
		System.out.println(won20s + " won and, " + lost20s + " lost on throws over #20" );
		
		double total = 0.0;
		for(int chance : throws_won)
			total += chance;
		
		double num = 0.0;
		
		num = (total / 1000) * 100;
		
		System.out.println("\nChance of winning is " + num + "%" );
		
	} // end method WonStats
} // end class Craps
