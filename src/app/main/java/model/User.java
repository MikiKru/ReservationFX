package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private static int id;      // pole statyczne do inkrementacji id użytkownika
    private int userId;
    private String login;
    private String password;
    private String emial;

    public User(String login, String password, String emial) {
        id++;
        this.userId = id;
        this.login = login;
        this.password = password;
        this.emial = emial;
    }
}
