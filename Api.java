import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class Api{
	private static String model = "qwen:0.5b";
	private static String endpoint = "http://localhost:11434";

	public static void main(String [] args){
		String res = generateResponse("Why is the sky blue?");
		System.out.println(res);
	}

	public static String generateResponse(String prompt){
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

		String jsonBody = "{\"model\": \"" + model + "\", \"prompt\": \"" + prompt + "\", \"stream\": false}";

		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(endpoint + "/api/generate")).timeout(Duration.ofSeconds(10))
		.header("Content-Type", "application/json")
		.POST(BodyPublishers.ofString(jsonBody)).build();

		try{
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return response.body();
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
