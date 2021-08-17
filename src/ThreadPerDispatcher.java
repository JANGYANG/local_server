import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerDispatcher {

	private final int HEADER_SIZE = 6;

	public void dispatch(ServerSocket serverSocket, HandleMap handleMap) {
		try {
			Socket socket = serverSocket.accept();
			demultiplex(socket, handleMap);

			Runnable demultiplexer = new Demultiplexer(socket, handleMap);
			Thread thread = new Thread(demultiplexer);
			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void demultiplex(Socket socket, HandleMap handleMap) {
		try {
			InputStream inputStream = socket.getInputStream();

			byte[] buffer = new byte[HEADER_SIZE];
			inputStream.read(buffer);
			String header = new String(buffer);

			handleMap.get(header).handleEvent(inputStream);

			socket.close();
//
//			switch (header) {
//			case "0x5001": {
//				StreamSayHelloEventHandler sayHelloProtocol = new StreamSayHelloEventHandler();
//				sayHelloProtocol.handleEvent(inputStream);
//				break;
//			}
//			case "0x6001":
//				StreamUpdateProfileEventHandler streamUpdateProfileProtocol = new StreamUpdateProfileEventHandler();
//				streamUpdateProfileProtocol.handleEvent(inputStream);
//				break;
//			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
