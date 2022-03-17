package cosc310Bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;


public class MyEventListener extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		
		Message msg = event.getMessage();
		String s = msg.getContentRaw();
		MessageChannel channel = event.getChannel();
		String prefix = "!";
		
		
		
		// NEW STUFF STARTS HERE
				// break s into array of words
		try {
				String[] split = s.split("((?=!)|(?<=!)|(?=,)|(?=\\.)|(\\s+))");
				// run each word/punctuation through spellchecker
				for(int i = 0; i < split.length; i++) {
					//~~~TODO fix these 4 lines below~~~
					//Spellchecker.text = split[i]; 
						
						System.out.println(split[i]);
						Spellchecker.check(split[i]); // set text in spellchecker
						
					
					if(Spellchecker.result != null) // if Spellchecker returns new word
						split[i] = Spellchecker.result; // replace array word with with corrected one
					
					
					Thread.sleep(334);
				}
				// put array back together into a string
				String combine = "";
				for(int i = 0; i < split.length; i++) {
					if(i == split.length-1) // if its the last word
						combine = combine + split[i];	//append and dont add a space
					else
						combine = combine + split[i] + " "; // append and add a space
				}
				s = combine;
				System.out.println(s);
				
				
				//KARLEN + CAM STUFF
				
				//Create an array of strings, derived from input sentence and remove prefix
				String [] strArray = null;
				strArray = s.split(" ");
				strArray[0] = strArray[0].replace("!", "");
				if(strArray[strArray.length-1].contains(".")) {
					strArray[strArray.length-1] = strArray[strArray.length-1].replace(".", "");
				}else if(strArray[strArray.length-1].contains("?")) {
					strArray[strArray.length-1] = strArray[strArray.length-1].replace("?", "");
				}
				//Create InputStreams and models from local files, do this for POSTagging and NameFinder
				//POSTagging
				InputStream modelIn = null;
				POSModel model = null;
				//NameFinder
				InputStream inputStreamNameFinder = null;
				TokenNameFinderModel nameModel = null;
				try {
					//get models from local system
					modelIn = new FileInputStream("/Users/zachprenovost/Downloads/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin");
					inputStreamNameFinder = new FileInputStream("/Users/zachprenovost/Downloads/en-ner-person.bin");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					//initialize models for each technique
					model = new POSModel(modelIn);
					nameModel = new TokenNameFinderModel(inputStreamNameFinder);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//Named Entity Identifier
				//Create NameFinder, create span to store name positions
				NameFinderME nameFinder = new NameFinderME(nameModel);
				Span nameSpans [] = nameFinder.find(strArray);
				
				//convert name positions into string
				String sent = "";
				for(Span span : nameSpans) {
					sent = span.toString();
				}
				//get name positions as integers
				int num1 = 11;
				int num2 = 0;
				for(int i =0;i<10;i++) {
					if(sent.contains(i+"")) {
						if(num1==11) {
							num1 = i;
						}else {
							num2 = i;
						}
					}
				}
				String [] names = new String[5];
				//Store names in new array, according to name position integers
				for(int l = 0;l<num2-num1;l++) {
					names[l] = strArray[num1+l];
				}
				
				//Part-of-Speech Tagging	
				POSTaggerME tagger = new POSTaggerME(model);
				
				//Create a new array that stores the tags of each word from the input sentence
				String tags[] = tagger.tag(strArray);
				String [] nouns = new String[8];
				int k = 0;
				 
				//If a word is tagged as a noun, store that word into the nouns array
				for(int j=0;j<tags.length;j++) {
					if(tags[j].contentEquals("NOUN")) {
						if(!strArray[j].contentEquals("foods")) {
							if(!strArray[j].contentEquals("food")) {
								if(!strArray[j].contentEquals("sports")) {
									if(!strArray[j].contentEquals("sport")) {
										if(!strArray[j].contentEquals("subject")) {
											if(!strArray[j].contentEquals("subjects")) {
												nouns[k] = strArray[j];
												k++;
											}
										}
									}
								}
							}
						}
					}
				}
		
				for(var b=0; b <nouns.length; b++) {
					System.out.println(""+nouns[b]);
				}
				//Selects a random noun from the nouns array
				int nounNum = (int) (Math.random()*k);
				
				//END
		
				if(s.startsWith(prefix)) {
					int out = 0;
					s = s.toLowerCase();
					if (s.contains("who") && s.contains("are") && s.contains("?")) // "Who are you?"
						out = 1;
					else if (s.contains("how") && s.contains("you") && s.contains("?")) // "How are you?"
						out = 2;
					else if (s.contains("i like") || s.contains("i enjoy") || s.contains("i love") || s.contains("my favorite") && (s.contains(".") || s.contains("!"))) { //"I love skiing!"
						out = 20;
						if (s.contains("least") || s.contains("hate")) // "Least favorite food?"
						out = 4;} 
					else if ((s.contains("hobbies") || s.contains("hobby")) && s.contains("?")) // "Have any hobbies?"
						out = 5;
					else if (s.contains("sports") || s.contains("sport") && (s.contains("?") || s.contains("."))) { // "Favorite sport?"
						out = 6;
						if (s.contains("least") || s.contains("hate")) // "Least favorite sport?"
						out = 7;}
					else if (s.contains("developed") || s.contains("created") || s.contains("made") && s.contains("?")) // "Who made you?"
						out = 8;
					else if (s.contains("class") || s.contains("subject") && (s.contains("?")|| s.contains("."))) { // "Favorite subject?"
						out = 9;
						if (s.contains("least") || s.contains("hate")) // "Least favorite subject?"
						out = 10;}
					else if ((s.contains("hello") || s.contains("hi ")) && (s.contains(".") || s.contains("!"))) // "Hello, I'm John!"
						out = 11;
					else if(s.contains("book")||s.contains("books") && s.contains("?")) { //"Favorite book"?
						out = 12;
						if(s.contains("least") || s.contains("hate"))//"Least favorite book?"
							out = 13;
					}
					else if(s.contains("where") && s.contains("?")) //"Where do you live?"
						out = 14;
					else if(s.contains("weather") && s.contains("?"))//"How's the weather where you are?"
						out = 15;
					else if(s.contains("talk") || s.contains("about") && s.contains("?"))//"What would you like to talk about?"
						out = 16;
					else if(s.contains("video") || s.contains("games") && (s.contains("?") || s.contains(".")))//"What sort of video games do you play?"
						out = 17;
					else if(s.contains("good") || s.contains("great") && (s.contains(".") || s.contains("!"))) { //"The weather over here is great!"
						out = 18;
						if(s.contains("not")||s.contains("bad")) //"It's not good right now."
							out = 19;
					}
					else if(s.contains("foods") || s.contains("food") || s.contains("snacks") && (s.contains("?") || s.contains(".")))// "Favorite food?"
		                out = 3;
					else if(s.contains("live") || s.contains("in ") && (s.contains(".") || s.contains("!")))//"I live in Canada."
						out = 21;
					else if(s.contains("thanks") || s.contains("thank you") && (s.contains(".") || s.contains("!")))//"Thank you friend bot."
		                out = 22;
					else if(s.contains("too") || s.contains("as well") && (s.contains(".") || s.contains("!")))//"My favorite book is the Hobbit as well!"
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
					
					/* TESTING CODE
					 	for(int i=0;i<nouns.length;i++) {
						if(nouns[i]!=null) {	
							channel.sendMessage(nouns[i]).queue();
						}
					 }
					 
					channel.sendMessage("Here are the names: ").queue();
					for(int i=0;i<names.length;i++) {
						if(names[i]!=null) {	
							channel.sendMessage(names[i]).queue();
						}
					 }
					channel.sendMessage("The numbers are "+num1+", "+num2).queue();
					*/
					
					//output
					switch (out) {
					case 1:
						channel.sendMessage("I am Friend-Bot.").queue();
						break;
					case 2:
						String[] greetings = { "I'm doing well. Thanks for asking.", "I'm doing great!", "I'm kind of bored." };
		                String greeting = greetings[(int) Math.round(Math.random() * 2)];
		                if(names[0]!=null) {
		                	channel.sendMessage(greeting+" How are you "+names[0]+"?").queue();
		                }else {
		                	channel.sendMessage(greeting+" How are you?").queue();
		                }
						break;
					case 3:
						String[] foods = { "pizza.", "sushi.", "chicken noodle soup.", "ice cream.", "lasagna." };
		                String food = foods[(int) Math.round(Math.random()*4)];
		                channel.sendMessage("I love to eat " + food+" How about you?").queue();
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
		                channel.sendMessage("My favourite sport is " + sport+"! What is yours?").queue();
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
						if(names[0]!=null) {
							channel.sendMessage("Nice to meet you, "+names[0]+"!").queue();
		                }else {
		                	channel.sendMessage("Nice to meet you!").queue();
		                }
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
						//If a keyword is specified within the input sentence of what the user likes, attempt to customize response based on keyword, if not then use default answer
						String[] responses = { "That's pretty cool.", "Wow! I do too.", "That's nice to hear.", "You have good taste!"};
		                String response = responses[(int)Math.round(Math.random()*3)];
		                if(s.contains("foods") || s.contains("food") || s.contains("snacks")) {
		                	if(nouns[nounNum]!=null) {
		                    	if(!nouns[nounNum].equalsIgnoreCase("food")) {
		                    		if(!nouns[nounNum].equalsIgnoreCase("foods")) {
		                    			channel.sendMessage(nouns[nounNum].substring(0,1).toUpperCase()+nouns[nounNum].substring(1)+" sounds quite tasty!").queue();
		                    			break;
		                    		}
		                    	}
		                    }
		                }else if(s.contains("sports") || s.contains("sport")) {
		                	if(nouns[nounNum]!=null) {
		                    	if(!nouns[nounNum].equalsIgnoreCase("sport")) {
		                    		if(!nouns[nounNum].equalsIgnoreCase("sports")) {
		                    			channel.sendMessage(nouns[nounNum].substring(0,1).toUpperCase()+nouns[nounNum].substring(1)+" is a great sport!").queue();
		                    			break;
		                    		}
		                    	}
		                    }
		                }else if(s.contains("class") || s.contains("subject")) {
		                	if(nouns[nounNum]!=null) {
		                    	if(!nouns[nounNum].equalsIgnoreCase("class")) {
		                    		if(!nouns[nounNum].equalsIgnoreCase("subject")) {
		                    			channel.sendMessage(nouns[nounNum].substring(0,1).toUpperCase()+nouns[nounNum].substring(1)+" sounds like a tough class!").queue();
		                    			break;
		                    		}
		                    	}
		                    }
		                }
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
						if(names[0]!=null) {
			                channel.sendMessage(names[0]+" seems like a cool boss.").queue();
			            }else {
			            	channel.sendMessage("That sounds like a nice job.").queue();
			            }
			            break;
			        case 25:
			            if(names[0]!=null) {
			                channel.sendMessage(names[0]+" seems like a tough boss.").queue();
			            } else {
			                channel.sendMessage("Sorry to hear that. I hope it gets better.").queue();
			            }
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
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
