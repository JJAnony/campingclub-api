package br.com.fef.campingclubapi.enums;

public enum EMail {
    GMAIL(1,"true", "true", "smtp.gmail.com","465","465","javax.net.ssl.SSLSocketFactory");

    private int id;
    private String auth;
    private String starttls;
    private String host;
    private String port;
    private String socketPort;
    private String socketClass;

    EMail(int id, String auth, String starttls, String host, String port, String socketPort, String socketClass) {
        this.id = id;
        this.auth = auth;
        this.starttls = starttls;
        this.host = host;
        this.port = port;
        this.socketPort = socketPort;
        this.socketClass = socketClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(String socketPort) {
        this.socketPort = socketPort;
    }

    public String getSocketClass() {
        return socketClass;
    }

    public void setSocketClass(String socketClass) {
        this.socketClass = socketClass;
    }

}
