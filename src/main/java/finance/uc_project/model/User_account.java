package finance.uc_project.model;

import finance.uc_project.enums.AccountState;
import finance.uc_project.enums.AccountStatus;
import finance.uc_project.enums.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class User_account {

    @Id
    private String matricule;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String verificationCode;
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;

    // Constructors, getters, and setters

    public User_account() {
    }

    public User_account(String matricule, String password, AccountStatus accountStatus, AccountType accountType, String verificationCode, String email, AccountState accountState) {
        this.matricule = matricule;
        this.password = password;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.verificationCode = verificationCode;
        this.email = email;
        this.accountState = accountState;
    }

    // Getters and setters

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

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }
}
