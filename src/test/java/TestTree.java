
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out responses to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		
		check(Strings.IS_IT_ALIVE);
        say("Y");
        //now what? Think of all the input and outputs here...
		check(Strings.IS_IT_A+Strings.DUCK+"?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Cat");
		check(Strings.NEW_QUESTION+Strings.DUCK+" and a Cat");
		say("Is it a pet?");
		check("Answering yes to Is it a pet? means Cat?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Is it a pet?");
        say("Y");
        check("Is it a Cat?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
		check(Strings.IS_IT_ALIVE);
        say("N");
		check(Strings.IS_IT_A+Strings.ROCK+"?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Pencil");
		check(Strings.NEW_QUESTION+Strings.ROCK+" and a Pencil");
		say("Is it an office supply?");
		check("Answering yes to Is it an office supply? means Pencil?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		check(Strings.IS_IT_ALIVE);
        say("N");
		check("Is it an office supply?");
		say("N");
		check(Strings.IS_IT_A+Strings.ROCK+"?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Phone");
		check(Strings.NEW_QUESTION+Strings.ROCK+" and a Phone");
		say("Is it part of nature?");
		check("Answering yes to Is it part of nature? means Phone?");
		say("N");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		check(Strings.IS_IT_ALIVE);
        say("N");
		check("Is it an office supply?");
		say("N");
		check("Is it part of nature?");
		say("N");
		check(Strings.IS_IT_A+"Phone"+"?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("N");
	//	assertThrows(IOException.class, ()->{say("k")});
        //close the streams at the end to ensure good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}
