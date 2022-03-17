package cosc310Bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyEventListener extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		
		Message msg = event.getMessage();
		String s = msg.getContentRaw();
		MessageChannel channel = event.getChannel();
		String prefix = "!";
		
		if(s.startsWith(prefix)) {
			int out = 0;
			s = s.toLowerCase();
			if (s.contains("who") && s.contains("are") && s.contains("?")) // "Who are you?"
				out = 1;
			else if (s.contains("how") && s.contains("you") && s.contains("?")) // "How are you?"
				out = 2;
			else if (s.contains("foods") || s.contains("food") || s.contains("snacks") && s.contains("?")) { // "Favourite food?"
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
			else if ((s.contains("hello") || s.contains("hi ")) && (s.contains(".") || s.contains("!"))) // "Hello, I'm John!"
				out = 11;
			else if(s.contains("book")||s.contains("books") && s.contains("?")) { //"Favourite book"?
				out = 12;
				if(s.contains("least") || s.contains("hate"))//"Least favourite book?"
					out = 13;
			}
			else if(s.contains("where") && s.contains("?")) //"Where do you live?"
				out = 14;
			else if(s.contains("weather") && s.contains("?"))//"How's the weather where you are?"
				out = 15;
			else if(s.contains("talk") || s.contains("about") && s.contains("?"))//"What would you like to talk about?"
				out = 16;
			else if(s.contains("video") || s.contains("games") && s.contains("?"))//"What sort of video games do you play?"
				out = 17;
			else if(s.contains("good") || s.contains("great") && (s.contains(".") || s.contains("!"))) { //"The weather over here is great!"
				out = 18;
				if(s.contains("not")||s.contains("bad")) //"It's not good right now."
					out = 19;
			}
			else if(s.contains("i like") || s.contains("i enjoy") || s.contains("i love") || s.contains("my favourite") && (s.contains(".") || s.contains("!")))//"I love skiing!"
                out = 20;
			else if(s.contains("live") || s.contains("in ") && (s.contains(".") || s.contains("!")))//"I live in Canada."
				out = 21;
			else if(s.contains("thanks") || s.contains("thank you") && (s.contains(".") || s.contains("!")))//"Thank you friend bot."
                out = 22;
			else if(s.contains("too") || s.contains("as well") && (s.contains(".") || s.contains("!")))//"My favourite book is the Hobbit as well!"
				out = 23;
			else if(s.contains("to work") || s.contains("i work") || s.contains("work for") || s.contains("my job") || s.contains("my boss") && (s.contains(".") || s.contains("!"))) {//"I work at save on foods."
                out = 24;
                if(s.contains("hate") || s.contains("dislike") || s.contains("can't stand") || s.contains("don't want"))//"I hate my boss."
                    out = 25;
            }
			else if(s.contains("bye") || s.contains("see you later") || s.contains("goodbye") || s.contains("got to go") || s.contains("cya") && (s.contains(".") || s.contains("!"))) {//"See you later."
                out = 26;
                if(s.contains("sorry") || s.contains("unfortunately"))//"Sorry, I have got to go now."
                    out = 27;
            }
			else if(s.contains("coolest") && (s.contains(".") || s.contains("!")))
                out = 28;
			else if (s.contains("?")) // "What are your thoughts on the economic status of Norway?"
				out = 29;
			else	// "Blah blah blah."
				out = 30;

			// output
			switch (out) {
			case 1:
				channel.sendMessage("I am Friend-Bot.").queue();
				break;
			case 2:
				String[] greetings = { "I'm doing well. Thanks for asking.", "I'm doing great!", "I'm kind of bored." };
                String greeting = greetings[(int) Math.round(Math.random() * 2)];
                channel.sendMessage(greeting+" How are you?").queue();
				break;
			case 3:
				String[] foods = { "pizza.", "sushi.", "chicken noodle soup.", "ice cream.", "lasagna." };
                String food = foods[(int) Math.round(Math.random()*4)];
                channel.sendMessage("I love to eat " + food).queue();
				break;
			case 4:
				channel.sendMessage("Well, I hate brussels sprouts.").queue();
				break;
			case 5:
				String[] hobbies = {"playing minesweeper.", "cycling.", "rock climbing.", "playing guitar."};
				String hobby = hobbies[(int)Math.round(Math.random()*3)];
				channel.sendMessage("I really enjoy " + hobby+" What hobbies do you have?").queue();
				break;
			case 6:
				String[] sports = { "cricket", "water polo", "hockey", "swimming", "skiing" };
                String sport = sports[(int) Math.round(Math.random()*4)];
                channel.sendMessage("My favourite sport is " + sport+"!").queue();
				break;
			case 7:
				channel.sendMessage("I'm not a fan of golf. It's a bit boring.").queue();
				break;
			case 8:
				channel.sendMessage("I was developed by group 6.").queue();
				break;
			case 9:
				String[] subjects = { "computer science.", "physical education.", "astronomy.", "molecular biology."};
                String subject = subjects[(int) Math.round(Math.random() * 3)];
                channel.sendMessage("My favourite subject is " + subject+" How about you?").queue();
				break;
			case 10:
				channel.sendMessage("I'm not a fan of chemistry.").queue();
				break;
			case 11:
				channel.sendMessage("Nice to meet you!").queue();
				break;
			case 12:
				String[] favBook = {"Lord of the Rings!", "the Bible.", "the Life of Pi.", "my math textbook."};
				String book = favBook[(int)Math.round(Math.random()*3)];
				channel.sendMessage("I really enjoy " + book).queue();
				break;
			case 13:
				channel.sendMessage("I don't prefer the Chronicles of Narnia").queue();
				break;
			case 14:
				channel.sendMessage("I'm currently in the Metaverse. How about you?").queue();
				break;
			case 15:
				String[] weathers = {"a bit chilly", "quite sunny", "pouring rain"};
				String weather = weathers[(int)Math.round(Math.random()*2)];
				channel.sendMessage("It's " +weather+" in the cloud right now.").queue();
				break;
			case 16:
				String[] topics = {"sports", "food", "hobbies","school subjects","books","video games"};
				String topic = topics[(int)Math.round(Math.random()*5)];
				channel.sendMessage("We could talk about " +topic+".").queue();
				break;
			case 17:
				String[] games = {"Skyrim", "Mario Kart", "CS:GO","Legend of Zelda","Pokemon","Call of Duty"};
				String game = games[(int)Math.round(Math.random()*5)];
				channel.sendMessage("I love playing " +game+"!").queue();
				break;
			case 18:
				channel.sendMessage("Glad to hear!").queue();
				break;
			case 19:
				channel.sendMessage("That is quite unfortunate!").queue();
				break;
			case 20:
				String[] responses = { "That's pretty cool.", "Wow! I do too.", "That's nice to hear.", "You have good taste!"};
                String response = responses[(int)Math.round(Math.random()*3)];
                channel.sendMessage(response).queue();
				break;
			case 21:
				String[] places = { "I heard it's nice there.", "Wow! Lucky!", "Very interesting!"};
                String place = places[(int)Math.round(Math.random()*2)];
                channel.sendMessage(place+" How's the weather?").queue();
				break;
			case 22:
                channel.sendMessage("No problem.").queue();
                break;
			case 23:
				channel.sendMessage("No way! Awesome!").queue();
				break;
			case 24:
                channel.sendMessage("That sounds like a nice job.").queue();
                break;
            case 25:
                channel.sendMessage("Sorry to hear that. I hope it gets better.").queue();
                break;
            case 26:
                String[] departs = {"Thanks for talking!", "It was nice talking to you.", "Sounds good.", "Hope you have a great day!"};
                String depart = departs[(int)Math.round(Math.random()*3)];
                channel.sendMessage(depart+" Goodbye.").queue();
                break;
            case 27:
                channel.sendMessage("No worries! See you next time.").queue();
                break;
            case 28:
            	channel.sendMessage("Agreed.").queue();
            	break;
			case 29:
				channel.sendMessage("I dont know...").queue();
				break;
			case 30:
				channel.sendMessage("I dont understand.").queue();
				break;
			}
		}
	}
}
