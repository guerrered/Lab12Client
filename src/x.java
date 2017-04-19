import java.util.Scanner;

public class x {
	public static void main(String [] args){
		String command = "";
		//String bib = "";
		//String time = "";
		Client client = new Client();
		Scanner stdIn = new Scanner(System.in);
		while(!command.equalsIgnoreCase("exit")){
			System.out.println("<command> <bib> <time>:");
			String [] input = stdIn.nextLine().split(" ");
			command = input[0];
			
			if(input[0].equalsIgnoreCase("add")){
				client.add(input[1], input[2]);
			}
		}
	}
}
