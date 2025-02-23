import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ClientAFrame {
	
	private JFrame frame;
	private TextArea consoleTextArea;
	private TextField commandTextField;
	private JButton sendButton;

    public ClientAFrame() {
        frame = new JFrame("Chatbox (Fake Websocket) - Client A");
        frame.setLayout(new BorderLayout());

        consoleTextArea = new TextArea();
        consoleTextArea.setEditable(false);
        frame.add(consoleTextArea, BorderLayout.CENTER);

        JPanel textInput = new JPanel();
        textInput.setLayout(new BorderLayout());

        commandTextField = new TextField();
        textInput.add(commandTextField, BorderLayout.CENTER);
        
        sendButton = new JButton("Send");
        textInput.add(sendButton, BorderLayout.EAST);

        frame.add(textInput, BorderLayout.SOUTH);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}	
	
	public TextField getCommandTextField() {
		return commandTextField;
	}
	
	public TextArea getConsoleTextArea() {
		return consoleTextArea;
	}
	
	public JButton getSendButton() {
		return sendButton;
	}
	
}
