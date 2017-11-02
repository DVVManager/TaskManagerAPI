package models;

/**
 * Created by Administrator on 10/29/2017.
 */
public class User implements Model {

 private int id;
 private String accountType;
 private String login;
 private String firstName;
 private String lastName;
 private String emailConfirmed;

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public User setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setEmailConfirmed(String emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailConfirmed() {
        return emailConfirmed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailConfirmed='" + emailConfirmed + '\'' +
                '}';
    }
}
