public class Main{
	public static void main(String[] args){
		Interface ui = new Interface();
		
		ui.menu();
		Utils.clear();
		while(ui.loop());
	}
}
