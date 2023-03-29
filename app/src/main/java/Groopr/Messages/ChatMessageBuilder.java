package Groopr.Messages;

import org.joda.time.DateTime;

public class ChatMessageBuilder {
    @SuppressWarnings("FieldMayBeFinal")
    private ChatMessage message;

    public ChatMessageBuilder() {
        this.message = new ChatMessage();
    }

    public void setMessage(String msg) {
        message.setMessage(msg);
    }

    public void setSender(String id) {
        message.setSender(id);
    }

    public void setRecipient(String id) {
        message.setRecipient(id);
    }

    public void setType(ChatMessage.Type type) {
        message.setType(type);
    }

    public void setMessageId(String id) {
        message.setMessageId(id);
    }

    public void setFileName(String fileName) {
        message.setFileName(fileName);
    }

    public void setDateData(DateTime dateData) {
        message.setDateData(dateData);
    }

    public ChatMessage getMessage() {
        return this.message;
    }
}
