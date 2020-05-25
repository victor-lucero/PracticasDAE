package pe.isil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    public int id;
    public String login;
    public String password;
    public String fullName;
    public Boolean isActive;
    public LocalDate expirationDate;
}
