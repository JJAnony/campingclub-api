package br.com.fef.campingclubapi.enums;

import br.com.fef.campingclubapi.util.IconConstants;
import br.com.fef.campingclubapi.util.MessageConstants;
import org.springframework.http.HttpStatus;

public enum EMessage {
    USER_SUCESS_SAVE(0, HttpStatus.CREATED, IconConstants.SUCESS, MessageConstants.SUCESS_SAVE, new String[]{"Verifique seu email para concluir o cadastro!"}),
    USER_SUCESS_UPDATE(1, HttpStatus.OK, IconConstants.SUCESS, MessageConstants.SUCESS_UPDATE, new String[]{"Alteração efetuada com sucesso!"}),
    USER_ERROR_EXISTS(2, HttpStatus.BAD_REQUEST, IconConstants.ERROR, MessageConstants.ERROR_USER_EXISTS, new String[]{"Usuário com esse nome já existe"}),
    USER_SUCESS_ACTIVE(3, HttpStatus.OK, IconConstants.SUCESS, MessageConstants.SUCESS_ACTIVE, new String[]{"Usuario Ativado com Sucesso!"}),
    CAMPING_SUCESS_SAVE(4, HttpStatus.CREATED, IconConstants.SUCESS, MessageConstants.SUCESS_SAVE, new String[]{"Camping cadastrado com sucesso!"});

    private int statusCode;
    private HttpStatus status;
    private String icon;
    private String messageTitle;
    private String[] messages;

    EMessage(int statusCode, HttpStatus status, String icon, String messageTitle, String[] messages) {
        this.statusCode = statusCode;
        this.status = status;
        this.icon = icon;
        this.messageTitle = messageTitle;
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String[] getMessages() {
        return messages;
    }
}
