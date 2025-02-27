public class Interface{
	public static void main(String[] args){
		System.out.println("Welcome to dumGPT");
		Api api = new Api("qwen:0.5b", "http://localhost:11434");

		String response = api.generateResponse("What is your name?");
		System.out.println(response);
	}
}
