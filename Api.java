import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class Api{
	private String model;
	private String endpoint;

	public Api(String model, String endpoint){
		this.model = model;
		this.endpoint = endpoint;
	}

	public String generateResponse(String prompt){
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

		String jsonBody = "{\"model\": \"" + model + "\", \"prompt\": \"" + prompt + "\", \"stream\": false}";

		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(endpoint + "/api/generate")).timeout(Duration.ofSeconds(10))
		.header("Content-Type", "application/json")
		.POST(BodyPublishers.ofString(jsonBody)).build();

		try{
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String responsePrompt = jsonParser(response.body(), "response");

			return responsePrompt;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	public String jsonParser(String jsonBody, String key){
		jsonBody = jsonBody.replaceAll("[{}]", "");
		String [] keyValuePairs = jsonBody.split(",\"");

		for(String pair : keyValuePairs){
			String [] rawEntry = pair.split("\":");
			String currentKey = rawEntry[0].trim();
			String currentValue = rawEntry[1].trim().replaceAll("[\"]", "");

			if(key.equals(currentKey)) return currentValue;
		}

		return "";
	}
}
