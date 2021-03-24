package br.com.fef.campingclubapi.enums;

public enum EUserMail {

    GMAIL(1, "campingclubcontato@gmail.com", "23iKiuiNdJC9Qp5u8i70", EMail.GMAIL);

    private int id;
    private String username;
    private String password;
    private EMail eMail;

    EUserMail(int id, String username, String password, EMail eMail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EMail geteMail() {
        return eMail;
    }

    public void seteMail(EMail eMail) {
        this.eMail = eMail;
    }
}
