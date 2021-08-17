package data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root
public class HandlerData {

	@Attribute
	private String header;

	@Text
	private String text;

	public String getHeader() {
		return header;
	}

	public String getHandler() {
		return text;
	}
}
