package data;

import java.util.List;

import org.simpleframework.xml.Element;

public class ServerListData {

	@Element
	private List<HandlerListData> server;

	public List<HandlerListData> getServer() {
		return server;
	}

}
