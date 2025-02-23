import java.io.Serializable;

public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String client;
	private String datetime;
	private String message;
	
	public ChatMessage(String client, String datetime, String message) {
		this.client = client;
		this.datetime = datetime;
		this.message = message;
	}
	
	public String getClient() {
		return client;
	}
	
	public String getDatetime() {
		return datetime;
	}
	
	public String getMessage() {
		return message;
	}
	
}
