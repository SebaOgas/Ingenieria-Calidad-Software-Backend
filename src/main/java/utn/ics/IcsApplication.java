package utn.ics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IcsApplication {

  public static void main(String[] args) {
    SpringApplication.run(IcsApplication.class, args);
    System.out.println("La aplicación se ha iniciado correctamente");
  }
}
