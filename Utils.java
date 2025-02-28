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
}
