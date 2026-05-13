import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Config {//This class stores the data in config.txt file
	
	//These values are static because loadConfig() and get() methods are static 
	private static HashMap<String , Integer> config = new HashMap<>();
	private static File file = new File("config.txt");
	
	//This method reads and stores the values in config.txt
	//Before using the data , this method must be invoke 
	public static void loadConfig() {
		
		//This try-catch block checks the file exist and closes it automatically
		try(Scanner input = new Scanner(file)){
			
			while(input.hasNext()) {
				
				String s = input.nextLine();
				if(!s.isEmpty()) {
					String[] list = s.split(":");
					String key = list[0].trim();
					int value = Integer.parseInt(list[1].trim());
					config.put(key , value);
				}
			}
		}
		
		catch(FileNotFoundException e) {
			System.out.println("Config.txt File was not founded !");
		}
	}
	
	//This method allow us the get the value of the given string
	public static Integer get(String s) {
		return config.get(s);
	}
}
