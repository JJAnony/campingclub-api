package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.enums.EMessage;

import java.util.Arrays;
import java.util.List;

public class MessageDTO {

    private int statusCode;
    private String icon;
    private String messageTitle;
    private List<String> messages;

    public MessageDTO() {
    }

    public MessageDTO(EMessage eMessage) {
        this.statusCode = eMessage.getStatusCode();
        this.icon = eMessage.getIcon();
        this.messageTitle = eMessage.getMessageTitle();
        this.messages = Arrays.asList(eMessage.getMessages());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
