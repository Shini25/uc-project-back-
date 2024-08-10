package finance.uc_project.model;

public class AuthenticationRequest {
    private String matricule;
    private String password;

    // Default constructor for JSON Parsing
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String matricule, String password) {
        this.matricule = matricule;
        this.password = password;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}