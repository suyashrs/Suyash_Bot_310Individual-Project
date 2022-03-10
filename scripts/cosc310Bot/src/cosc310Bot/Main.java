package cosc310Bot;


import net.dv8tion.jda.api.JDABuilder;


public class Main {
	public static JDABuilder jda;
	
	public static void main(String [] args) throws Exception{
		try {
			JDABuilder jda = JDABuilder.createDefault("OAUTH_KEY");
			jda.addEventListeners(new MyEventListener());
			jda.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
