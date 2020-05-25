package pe.isil;

import pe.isil.DAO.UserDAO;
import pe.isil.model.User;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        User user = new User();

        user.setLogin("vlucero");
        user.setPassword("vlucero");
        user.setFullName("Victor Lucero");
        user.setIsActive(Boolean.TRUE);
        user.setExpirationDate(LocalDate.of(2020, 05, 26));
        userDAO.create(user);

        user.setLogin("vvite");
        user.setPassword("vvite");


    }
}
