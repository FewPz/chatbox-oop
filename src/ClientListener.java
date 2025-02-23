import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientListener implements ActionListener {

    private ClientAFrame clientAFrame;
    private ClientBFrame clientBFrame;
    private static final String FILE_NAME = "chat_history.dat";

    public ClientListener(ClientAFrame clientAFrame, ClientBFrame clientBFrame) {
        this.clientAFrame = clientAFrame;
        this.clientBFrame = clientBFrame;
        loadMessagesFromFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String dateTime = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;

        if (source == clientAFrame.getSendButton()) {
            String message = clientAFrame.getCommandTextField().getText();
            if (message.isEmpty()) return;

            clientAFrame.getConsoleTextArea().append("[" + dateTime + "] A (You): " + message + "\n");
            clientBFrame.getConsoleTextArea().append("[" + dateTime + "] A: " + message + "\n");

            appendMessageToFile(new ChatMessage("A", dateTime, message));

            clientAFrame.getCommandTextField().setText("");
        } 
        else if (source == clientBFrame.getSendButton()) {
            String message = clientBFrame.getCommandTextField().getText();
            if (message.isEmpty()) return;

            clientAFrame.getConsoleTextArea().append("[" + dateTime + "] B: " + message + "\n");
            clientBFrame.getConsoleTextArea().append("[" + dateTime + "] B (You): " + message + "\n");

            appendMessageToFile(new ChatMessage("B", dateTime, message));

            clientBFrame.getCommandTextField().setText("");
        }
    }

    private void appendMessageToFile(ChatMessage newMessage) {
        try {
            List<ChatMessage> messages = readMessagesFromFile();
            messages.add(newMessage);
            writeMessagesToFile(messages);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	private List<ChatMessage> readMessagesFromFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<ChatMessage>) ois.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        }
    }

    private void writeMessagesToFile(List<ChatMessage> messages) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(messages);
        }
    }

    private void loadMessagesFromFile() {
        try {
            List<ChatMessage> messages = readMessagesFromFile();
            for (ChatMessage message : messages) {
                String formattedMessage = "[" + message.getDatetime() + "] " + message.getClient() + ": " + message.getMessage() + "\n";
                clientAFrame.getConsoleTextArea().append(formattedMessage);
                clientBFrame.getConsoleTextArea().append(formattedMessage);
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
