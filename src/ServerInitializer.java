import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import data.HandlerData;
import data.HandlerListData;
import data.ServerListData;

public class ServerInitializer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 5000;
		System.out.println("Server On:" + port);

		Reactor reactor = new Reactor(port);

		try {
			Serializer serializer = new Persister();
			File source = new File("HandlerList.xml");
			ServerListData serverListData = serializer.read(ServerListData.class, source);

			for (HandlerListData handlerListData : serverListData.getServer()) {
				if (handlerListData.getName().equals("server1")) {
					for (HandlerData handlerData : handlerListData.getHandler()) {
						try {
							reactor.registerHandler(handlerData.getHeader(),
									(EventHandler) Class.forName(handlerData.getHandler()).newInstance());
						} catch (InstantiationException e) {
							// TODO: handle exception
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO: handle exception
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

//		reactor.registerHandler(new StreamSayHelloEventHandler());
//		reactor.registerHandler(new StreamUpdateProfileEventHandler());

		reactor.startServer();

//		try {
//			ServerSocket serverSocet = new ServerSocket(port);
////			Socket connection;
//
//			Dispatcher dispatcher = new Dispatcher();
//
//			while (true) {
//
//				dispatcher.dispatch(serverSocet);
//				
//
////				connection = serverSocet.accept();
////				InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
////				BufferedReader bufferReader = new BufferedReader(inputStreamReader);
////				String line = bufferReader.readLine();
////
////				System.out.println("Read:" + line);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
