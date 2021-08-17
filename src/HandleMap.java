import java.util.HashMap;

public class HandleMap extends HashMap<String, EventHandler> {
	
	private static HandleMap handleMap;
	
	
	public HandleMap getInstance() {
		return handleMap;
	}
	
	public void registerHandler(EventHandler handler) {
		handleMap.put(handler.getHandler(), handler);
	}

	public void registerHandler(String header, EventHandler handler) {
		handleMap.put(header, handler);
	}

	public void removeHandler(EventHandler handler) {
		handleMap.remove(handler.getHandler());
	}
	

}
