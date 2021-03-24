package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.enums.EMail;
import br.com.fef.campingclubapi.enums.EUserMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private MessageService messageService;

    /**
     * Envia um email
     *
     * @param subjectMatter
     * @param message
     * @param destiny
     */
    public void send(String mail, String password, EMail eMail, String subjectMatter, String message, String destiny) throws Exception {

        Properties properties = getProperties(eMail);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        Address[] addresses = InternetAddress.parse(destiny);

        Message messageMail = new MimeMessage(session);
        messageMail.addFrom(InternetAddress.parse(mail));
        messageMail.addRecipients(Message.RecipientType.TO, addresses);
        messageMail.setSubject(subjectMatter);
        messageMail.setText(message);

        Transport.send(messageMail);
    }

    /**
     * Facilita o envio de email
     *
     * @param eUserMail
     * @param subjectMatter
     * @param message
     * @param destiny
     * @throws Exception
     */
    public void send(EUserMail eUserMail, String subjectMatter, String message, String destiny) throws Exception {
        this.send(eUserMail.getUsername(), eUserMail.getPassword(), eUserMail.geteMail(), subjectMatter, message, destiny);
    }

    /**
     * Envia uma menssagem de Ativação no email
     *
     * @param eUserMail
     * @param destiny
     */
    public void sendActivationMessage(EUserMail eUserMail, String destiny) throws Exception {
        this.send(eUserMail, MessageService.HEADER_ACTiVATION_MAIL, messageService.messageActivationMail(destiny), destiny);
    }


    /**
     * Retorna as propriedades de email
     *
     * @param eMail
     * @return
     */
    private Properties getProperties(EMail eMail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", eMail.getAuth()); // Atenticação
        properties.put("mail.stmp.starttls", eMail.getStarttls()); // Autenticação
        properties.put("mail.smtp.host", eMail.getHost()); // Servidor
        properties.put("mail.smtp.port", eMail.getPort()); // Porta do Servidor
        properties.put("mail.smtp.socketFactory.port", eMail.getSocketPort()); // Expecifica a porta de socket
        properties.put("mail.smtp.socketFactory.class", eMail.getSocketClass()); // Classe de conexão socket
        return properties;
    }
}
