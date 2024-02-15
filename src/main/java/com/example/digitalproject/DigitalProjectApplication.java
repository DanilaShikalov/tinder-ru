package com.example.digitalproject;

import com.example.digitalproject.models.dto.persons.PersonPostDTO;
import com.example.digitalproject.models.security.RegisterRequest;
import com.example.digitalproject.models.security.Role;
import com.example.digitalproject.services.AuthenticationService;
import com.example.digitalproject.services.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Digital API", version = "1337.228", description = "Educational platform"))
@SecurityScheme(name = "digital-project", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class DigitalProjectApplication implements CommandLineRunner {
    @Autowired
    private PersonService personService;
    @Autowired
    private AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        authenticationService.register(new RegisterRequest("Аня", "Иванова", "anya@gmail.com", "123",
                "/images/photo1.jpg", "Молодая, энергичная и ищу надежного партнера для приключений и счастливых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Настя", "Смирнова", "nastya@gmail.com", "123",
                "/images/photo2.jpg", "Я люблю путешествовать и открывать новые места. Ищу партнера, с которым смогу делиться приключениями и впечатлениями.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Таня", "Кузнецова", "tanya@gmail.com", "123",
                "/images/photo3.jpg", "Моя страсть - готовить вкусные блюда. Ищу мужчину, который оценит мои кулинарные таланты и поделится со мной любовью к еде.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Алена", "Попова", "alena@gmail.com", "123",
                "/images/photo4.jpg", "Я спортивная и энергичная девушка, ищу партнера, который разделит мою страсть к физическим активностям и здоровому образу жизни.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Юля", "Васильева", "julia@gmail.com", "123",
                "/images/photo5.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Галя", "Петрова", "galya@gmail.com", "123",
                "/images/photo6.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Альмедрида", "Соколова", "almedrida@gmail.com", "123",
                "/images/photo7.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Августина", "Михайлова", "avgustina@gmail.com", "123",
                "/images/photo8.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Василиса", "Новикова", "vasilisa@gmail.com", "123",
                "/images/photo9.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Трап", "Федорова", "trap@gmail.com", "123",
                "/images/photo10.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Анджелика", "Морозова", "angjelika@gmail.com", "123",
                "/images/photo11.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Катя", "Волкова", "katya@gmail.com", "123",
                "/images/photo12.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Лиза", "Алексеева", "liza@gmail.com", "123",
                "/images/photo13.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Надежда", "Лебедева", "nadejda@gmail.com", "123",
                "/images/photo14.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Уля", "Семенова", "ulya@gmail.com", "123",
                "/images/photo15.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Наташа", "Егорова", "natasha@gmail.com", "123",
                "/images/photo16.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Люба", "Павлова", "luba@gmail.com", "123",
                "/images/photo17.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Даша", "Козлова", "dasha@gmail.com", "123",
                "/images/photo18.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Кристина", "Степанова", "kristina@gmail.com", "123",
                "/images/photo19.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Герда", "Николаева", "gerda@gmail.com", "123",
                "/images/photo20.jpg", "Активная и жизнерадостная девушка ищет партнера для веселых приключений и незабываемых моментов вместе.", false), Role.USER);
        authenticationService.register(new RegisterRequest("Гига", "Чад", "gigachad@gmail.com", "123",
                "/images/gigachad.png", "Я идеален.", false), Role.ADMIN);

        personService.postEntity(new PersonPostDTO("", "", "", ""), 1L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 2L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 3L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 4L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 5L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 6L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 7L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 8L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 9L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 10L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 11L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 12L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 13L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 14L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 15L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 16L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 17L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 18L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 19L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 20L);
        personService.postEntity(new PersonPostDTO("", "", "", ""), 21L);

    }
}
