package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User User1 = new User("User1", "Lastname1", "user1@mail.ru");
      User User2 = new User("User2", "Lastname2", "user2@mail.ru");
      User User3 = new User("User3", "Lastname3", "user3@mail.ru");
      User User4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car haval = new Car("Haval", 2022);
      Car cherry = new Car("cherry", 2023);
      Car geely = new Car("geely", 2024);
      Car lifan = new Car("lifan", 2020);

      userService.add(User1.setCar(haval).setUser(User1));
      userService.add(User2.setCar(cherry).setUser(User2));
      userService.add(User3.setCar(geely).setUser(User3));
      userService.add(User4.setCar(lifan).setUser(User4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("geely", 2024));

      try {
         User notFoundUser = userService.getUserByCar("geely", 2020);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }



      context.close();
   }
}
