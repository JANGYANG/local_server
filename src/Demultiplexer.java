import java.io.InputStream;
import java.net.Socket;

public class Demultiplexer implements Runnable {

	private final int HEADER_SIZE = 6;

	private Socket socket;

	private HandleMap handleMap;

	public Demultiplexer(Socket socket, HandleMap handleMap) {
		this.socket = socket;
		this.handleMap = handleMap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			InputStream inputStream = socket.getInputStream();
			byte[] buffer = new byte[HEADER_SIZE];
			inputStream.read(buffer);
			String header = new String(buffer);

			handleMap.get(header).handleEvent(inputStream);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
