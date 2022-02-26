import java.util.Scanner;

public class friendBot {
	public static void main(String[] args) {
		// loop conversation prompt
		while (true) {
			
			// get user input
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			//System.out.println("You entered string " + s);

			// associate user input with output via keywords
			int out = 0;
			s = s.toLowerCase();
			if (s.contains("who") && s.contains("are") && s.contains("?")) // "Who are you?"
				out = 1;
			else if (s.contains("how") && s.contains("you") && s.contains("?")) // "How are you?"
				out = 2;
			else if (s.contains("foods") || s.contains("food") && s.contains("?")) { // "Favourite food?"
				out = 3;
				if (s.contains("least") || s.contains("hate")) // "Least favourite food?"
					out = 4;} 
			else if ((s.contains("hobbies") || s.contains("hobby")) && s.contains("?")) // "Have any hobbies?"
				out = 5;
			else if (s.contains("sports") || s.contains("sport") && s.contains("?")) { // "Favourite sport?"
				out = 6;
				if (s.contains("least") || s.contains("hate")) // "Least favourite sport?"
					out = 7;}
			else if (s.contains("developed") || s.contains("created") || s.contains("made") && s.contains("?")) // "Who made you?"
				out = 8;
			else if (s.contains("class") || s.contains("subject") && s.contains("?")) { // "Favourite subject?"
				out = 9;
				if (s.contains("least") || s.contains("hate")) // "Least favourite subject?"
					out = 10;}
			else if ((s.contains("hello") || s.contains("hi")) && (s.contains(".") || s.contains("!"))) // "Hello, I'm John!"
				out = 11;
			else if (s.contains("?")) // "What are your thoughts on the economic status of Norway?"
				out = 12;
			else	// "Blah blah blah."
				out = 13;

			// output
			switch (out) {
			case 1:
				System.out.println("I am Friend-Bot.");
				break;
			case 2:
				System.out.println("I’m doing well. Thanks for asking.");
				break;
			case 3:
				System.out.println("I love pizza.");
				break;
			case 4:
				System.out.println("I hate brussels sprouts.");
				break;
			case 5:
				String[] hobbies = {"playing minesweeper.", "cycling.", "rock climbing.", "playing guitar."};
				String hobby = hobbies[(int)Math.round(Math.random()*4)];
				System.out.println("I really enjoy " + hobby);
				break;
			case 6:
				System.out.println("I really like hockey.");
				break;
			case 7:
				System.out.println("I’m not a fan of golf. It’s a bit boring.");
				break;
			case 8:
				System.out.println("I was developed by group 6.");
				break;
			case 9:
				System.out.println("My favourite subject is computer science.");
				break;
			case 10:
				System.out.println("I’m not a fan of chemistry.");
				break;
			case 11:
				System.out.println("Nice to meet you!");
				break;
			case 12:
				System.out.println("I don’t know...");
				break;
			case 13:
				System.out.println("I don’t understand.");
				break;
			}
		}
	}
}
