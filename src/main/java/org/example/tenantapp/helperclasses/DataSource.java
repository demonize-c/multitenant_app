package org.example.tenantapp.helperclasses;

public class DataSource {
    private String db_name;
    private String db_pass;
    private String db_user;
    private String db_host;
    private String db_port;

    public DataSource(String db_host, String db_port, String db_name, String db_user, String db_pass) {
        this.db_host = db_host;
        this.db_port = db_port;
        this.db_name = db_name;
        this.db_pass = db_pass;
        this.db_user = db_user;
    }
    // Getters
    public String getDb_name() {
        return db_name;
    }

    public String getDb_pass() {
        return db_pass;
    }

    public String getDb_user() {
        return db_user;
    }

    public String getDb_host() {
        return db_host;
    }

    public String getDb_port() {
        return db_port;
    }

    // Setters
    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public void setDb_pass(String db_pass) {
        this.db_pass = db_pass;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public void setDb_host(String db_host) {
        this.db_host = db_host;
    }

    public void setDb_port(String db_port) {
        this.db_port = db_port;
    }

    // equals method using String.equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataSource)) return false;
        DataSource that = (DataSource) o;

        return (db_name != null && db_name.equals(that.db_name)) &&
                (db_pass != null && db_pass.equals(that.db_pass)) &&
                (db_user != null && db_user.equals(that.db_user)) &&
                (db_host != null && db_host.equals(that.db_host)) &&
                (db_port != null && db_port.equals(that.db_port));
    }

    // toString method
    @Override
    public String toString() {
        return "DataSource{" +
                "db_name='" + db_name + '\'' +
                ", db_pass='" + db_pass + '\'' +
                ", db_user='" + db_user + '\'' +
                ", db_host='" + db_host + '\'' +
                ", db_port='" + db_port + '\'' +
                '}';
    }
}

