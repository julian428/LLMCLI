import java.util.Scanner;

public class Interface{
	private String model;

	public void menu(){
		Scanner input = new Scanner(System.in);
		Utils.clear();
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

		Api api = new Api(this.model, "http://localhost:11434");
		System.out.print("response: ");
		String response = api.generateResponse(prompt);
		System.out.println(response);
		return true;
	}
}
