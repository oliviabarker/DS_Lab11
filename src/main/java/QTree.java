import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
	
	
	Scanner in;
	PrintStream out;
	Node start;

    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		Node duck = new Node(Strings.DUCK, null, null);
		Node rock = new Node(Strings.ROCK, null, null);
		this.start = new Node(Strings.IS_IT_ALIVE, duck, rock);		//Please initialize your data here
	}
	

	
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		out.println(this.start.val);
		String ans = in.next();
		Node curr = this.start;
		Node last = curr;
		if (ans.equals("Y"))
		{
			curr = this.start.y;
		}
		else
		{
			curr = this.start.n;
		}
		while (curr.y!=null || curr.n!=null)
		{
			last = curr;
			out.println(curr.val);
			ans = in.next();
			if (ans.equals("Y"))
			{
				curr = curr.y;

			}
			else
			{
				curr = curr.n;

			}
		}
		out.println(Strings.IS_IT_A+curr.val+"?");
		ans = in.next();
		if (ans.equals("Y"))
		{
			out.println(Strings.I_WIN);
		}
		else
		{
			out.println(Strings.WHAT_IS_THE_ANSWER);
			String newItem = in.next();
			out.println(Strings.NEW_QUESTION + curr.val + " and a " + newItem);
			String newQuestion = in.nextLine();
			newQuestion = in.nextLine();
			out.println("Answering yes to " + newQuestion + " means " + newItem + "?");
			String yOrN = in.next();
			Node itemNode = new Node(newItem, null, null);
			Node newQ;
			if (yOrN.equals("Y"))
			{
				newQ = new Node(newQuestion,itemNode, curr);
			}
			else
			{
				newQ = new Node(newQuestion,curr, itemNode);
			}
			if (last.y==curr)
			{
				last.y = newQ;
			}
			else 
			{
				last.n=newQ;

			}
			out.println(Strings.THANKS);	
		}

		out.println(Strings.PLAY_AGAIN);
		ans = in.next();
		if (ans.equals("Y"))
		{
			this.playGame();
		}
        
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}
