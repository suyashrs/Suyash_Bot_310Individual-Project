package cosc310Bot;


import net.dv8tion.jda.api.JDABuilder;


public class Main {
	public static JDABuilder jda;
	
	public static void main(String [] args) throws Exception{
		try {
			JDABuilder jda = JDABuilder.createDefault("OTQ2NTU3NjM5ODg4ODc1NTIx.Yhgcfw.uamsDJ_eW_3IKfGBmQH2avPz0dg");
			jda.addEventListeners(new MyEventListener());
			jda.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
