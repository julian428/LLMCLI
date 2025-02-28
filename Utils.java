import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class Utils{
	public static void clear(){
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	public static String jsonParser(String jsonBody, String key){
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

	public static String HttpRequest(String endpoint, String jsonBody){
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(endpoint)).timeout(Duration.ofSeconds(10))
		.header("Content-Type", "application/json")
		.POST(BodyPublishers.ofString(jsonBody)).build();

		try{
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		}
		catch(Exception e){
		}
		return "";
	}
}
