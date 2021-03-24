package br.com.fef.campingclubapi.service.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private AESService aesService;

    private static final String BASE_CLI_URL = "http://localhost:4200/";

    public static final String HEADER_ACTiVATION_MAIL = "Ativação da Conta";

    public String messageActivationMail(String email) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Para completar a ativação click no Link a seguir:");
        sb.append(BASE_CLI_URL).append("active/").append(aesService.encrypted(email));

        return sb.toString();
    }
}
