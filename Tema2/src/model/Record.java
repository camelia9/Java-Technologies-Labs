package model;

public class Record extends Login {
    private String email;

    public Record() {
    }

    public Record(String username, String email, String password) {
        super(username, password);
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
