public class Api{
	private String model;
	private String endpoint;

	public Api(String model, String endpoint){
		this.model = model;
		this.endpoint = endpoint;
	}

	public String generateResponse(String prompt){
		String jsonBody = "{\"model\": \"" + model + "\", \"prompt\": \"" + prompt + "\", \"stream\": false}";

		try{
			String response = Utils.HttpRequest(endpoint + "/api/generate", jsonBody);
			String responsePrompt = Utils.jsonParser(response, "response");

			return responsePrompt;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
