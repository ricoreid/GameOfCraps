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

public class CrapsTest
{

	public static void main(String[] args)
	{
		Craps game = new Craps();
		
		long time_array[] = new long[1000];
		
		
		for(int play = 0; play < 1000; play++)
		{
			long start_time = System.nanoTime();
			game.gamePlay();
			long end_time = System.nanoTime();
			time_array[play] = end_time - start_time;
		}
		
		game.GameStats();
		
		long average_time = 0L;
		for(long times : time_array)
			average_time += times;
		
		long times_results = average_time / 1000L;
		
		System.out.print("Average time is " + times_results + " Nano-seconds" );
	}

}
