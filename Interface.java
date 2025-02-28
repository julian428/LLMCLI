import java.util.Scanner;

public class Interface{
	public static void main(String[] args){
		System.out.println("Welcome to dumGPT");
			
		clear();

		Scanner input = new Scanner(System.in);
		while(loop(input));
	}

	private static boolean loop(Scanner input){
		System.out.print("prompt: ");
		String prompt = input.nextLine();

		if(prompt.equals("!q")) return false;

		Api api = new Api("qwen:0.5b", "http://localhost:11434");
		String response = api.generateResponse(prompt);
		System.out.println("response: " + response);
		return true;
	}

	private static void clear(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
