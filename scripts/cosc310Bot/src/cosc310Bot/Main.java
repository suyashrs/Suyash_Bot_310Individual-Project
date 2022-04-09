package cosc310Bot;


import net.dv8tion.jda.api.JDABuilder;


public class Main {
	public static JDABuilder jda;
	
	public static void main(String [] args) throws Exception{
		try {
			JDABuilder jda = JDABuilder.createDefault("OTYxNzk4Mzc4NDU3NTU5MDYx.Yk-Oig.TsFbFzqyZe-EV7W00AVnhB9nt4k"); //TO-DELETE:
			jda.addEventListeners(new MyEventListener());
			jda.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
