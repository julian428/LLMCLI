import java.util.Scanner;

public class Interface{
	private String model;

	public void menu(){
		Scanner input = new Scanner(System.in);
		clear();
		System.out.println("Console LLM");

		System.out.print("Choose a model: ");
		String model = input.nextLine();

		this.model = model;
	}

	public boolean loop(){
		Scanner input = new Scanner(System.in);
		System.out.print("prompt: ");
		String prompt = input.nextLine();

		if(prompt.equals("!q")) return false;

		Api api = new Api("qwen:0.5b", "http://localhost:11434");
		System.out.print("response: ");
		String response = api.generateResponse(prompt);
		System.out.println(response);
		return true;
	}

	public void clear(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
