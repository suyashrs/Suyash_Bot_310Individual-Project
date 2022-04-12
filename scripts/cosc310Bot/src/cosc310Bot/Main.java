package cosc310Bot;


import net.dv8tion.jda.api.JDABuilder;


public class Main {
	public static JDABuilder jda;
	public static String prefix = "!";
	public static void main(String [] args) throws Exception{
		try {
			JDABuilder jda = JDABuilder.createDefault("OTYxNzk4Mzc4NDU3NTU5MDYx.Yk-Oig.L6ek2GjpUko6NMsVu4zkTyYo44E"); //TO-DELETE: OTYxNzk4Mzc4NDU3NTU5MDYx.Yk-Oig.L6ek2GjpUko6NMsVu4zkTyYo44E
			jda.addEventListeners(new MyEventListener());
			jda.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
