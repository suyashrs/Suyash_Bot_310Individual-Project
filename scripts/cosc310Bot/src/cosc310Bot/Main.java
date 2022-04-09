package cosc310Bot;


import net.dv8tion.jda.api.JDABuilder;


public class Main {
	public static JDABuilder jda;
	
	public static void main(String [] args) throws Exception{
		try {
			JDABuilder jda = JDABuilder.createDefault("DISCORD_BOT_TOKEN"); //TO-DELETE:
			jda.addEventListeners(new MyEventListener());
			jda.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
