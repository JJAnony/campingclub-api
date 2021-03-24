package br.com.fef.campingclubapi.exception;

import br.com.fef.campingclubapi.dto.MessageDTO;
import br.com.fef.campingclubapi.enums.EMessage;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class StandardExeption extends RuntimeException {

    private int code;
    private HttpStatus status;
    private String icon;
    private String message_title;
    private String[] messages;


    public StandardExeption(EMessage exception) {
        super(exception.getMessageTitle());
        this.code = exception.getStatusCode();
        this.status = exception.getStatus();
        this.icon = exception.getIcon();
        this.message_title = exception.getMessageTitle();
        this.messages = exception.getMessages();
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public String getMessage_title() {
        return message_title;
    }

    public String[] getMessages() {
        return messages;
    }

    public MessageDTO toDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setStatusCode(this.getCode());
        messageDTO.setIcon(this.getIcon());
        messageDTO.setMessageTitle(this.getMessage_title());
        messageDTO.setMessages(Arrays.asList(this.getMessages()));
        return messageDTO;
    }
}
