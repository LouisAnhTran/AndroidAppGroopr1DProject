package Groopr.Messages;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class ChatMessage {

    private String message;
    private String sender;
    private String recipient;
    private Type type;
    private String messageId;
    private String fileName;
    private Instant instant;
    private LocalDate date;
    private LocalTime time;
    private DateTime dateData;

    public ChatMessage() {}

    public ChatMessage(String message, String sender, String recipient, Type type, String messageId, String fileName, DateTime dateData) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.messageId = messageId;
        this.fileName = fileName;
        this.dateData = dateData;
        this.instant = dateData.toInstant();
        this.time = dateData.toLocalTime();
        this.date = dateData.toLocalDate();
    }

    enum Type {
        MESSAGE, IMAGE, VIDEO, FILE, LINK
    }

    public JsonObject getMessageJson() {
        JsonObject json = new JsonObject();

        json.addProperty("message", this.message);
        json.addProperty("sender", this.sender);
        json.addProperty("recipient", this.recipient);
        json.addProperty("type", this.type.toString());
        json.addProperty("message", this.messageId);
        json.addProperty("filename", this.fileName);
        json.addProperty("instant", this.instant.toString());
        json.addProperty("date", this.date.toString());
        json.addProperty("time", this.time.toString());

        return json;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDateData(DateTime dateData) {
        this.dateData = dateData;
    }
}
