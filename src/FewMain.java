
public class FewMain {

	public static void main(String[] args) {
		ClientAFrame clientA = new ClientAFrame();
		ClientBFrame clientB = new ClientBFrame();

        ClientListener listener = new ClientListener(clientA, clientB);
        
        // Attach the listener to both frames’ send buttons
        clientA.getSendButton().addActionListener(listener);
        clientB.getSendButton().addActionListener(listener);
		
        clientA.start();
        clientB.start();
	}
	
}
