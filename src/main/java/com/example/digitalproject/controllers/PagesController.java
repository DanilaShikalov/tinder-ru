package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.chat.Chat;
import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.dto.pair.PairPostDTO;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.AuthenticationRequest;
import com.example.digitalproject.models.security.RegisterRequest;
import com.example.digitalproject.models.security.Role;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.UserRepository;
import com.example.digitalproject.services.AuthenticationService;
import com.example.digitalproject.services.MessageService;
import com.example.digitalproject.services.PairService;
import com.example.digitalproject.utils.GPTUtils;
import com.example.digitalproject.utils.ModelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("api/tinder-ru")
@AllArgsConstructor
@Tag(name = "Pages", description = "Страницы")
public class PagesController {
    private final AuthenticationService authenticationService;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PairService pairService;
    private final MessageService messageService;

    @GetMapping("/login")
    @Operation(description = "Стартовая страница")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/auth")
    public String authRequest(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        AuthenticationRequest request = AuthenticationRequest
                .builder()
                .email(email)
                .password(password)
                .build();
        try {
            var auth = authenticationService.authenticate(request);
            Person person = personRepository.getPersonByToken(auth.getToken()).get(0);
            if (person.getUser().isBanned()) {
                return "exception";
            }
            session.setAttribute("token", auth.getToken());
            User randomPairUser = pairService.getRandomPairUser(auth.getToken());
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", auth.getToken());
                return "not_found";
            }
            session.setAttribute("token", auth.getToken());
            if (personRepository.getPersonByToken(auth.getToken()).get(0).getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        List<Person> personByToken = personRepository.getPersonByToken(token);
        if (!personByToken.isEmpty()) {
            User randomPairUser = pairService.getRandomPairUser(token);
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", token);
                return "not_found";
            }
            session.setAttribute("token", token);
            if (personByToken.get(0).getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } else {
            return "exception";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerRequest(@RequestParam("email") String email, @RequestParam("password") String password,
                                  @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                  HttpSession session, Model model) {
        RegisterRequest request = RegisterRequest.builder()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .password(password)
                .photo("/images/default_photo.png")
                .build();
        try {
            var auth = authenticationService.register(request, Role.USER);
            User randomPairUser = pairService.getRandomPairUser(auth.getToken());
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", auth.getToken());
                return "not_found";
            }
            session.setAttribute("token", auth.getToken());
            if (personRepository.getPersonByToken(auth.getToken()).get(0).getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        try {
            Person person = personRepository.getPersonByToken(token).get(0);
            model.addAttribute("firstName", person.getUser().getFirstname());
            model.addAttribute("lastName", person.getUser().getLastname());
            model.addAttribute("description", person.getUser().getDescription());
            model.addAttribute("photoUrl", person.getUser().getPhoto());
            return "profile";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/settings")
    public String settingPage() {
        return "settings";
    }

    @PostMapping("/save-profile")
    public String saveProfile(HttpSession session, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                              @RequestParam("description") String description, Model model) {
        String token = (String) session.getAttribute("token");
        try {
            Person person = personRepository.getPersonByToken(token).get(0);
            User user = person.getUser();
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setDescription(description);
            userRepository.save(user);
            User randomPairUser = pairService.getRandomPairUser(token);
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", token);
                return "not_found";
            }
            session.setAttribute("token", token);
            if (person.getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } catch (Exception e) {
            return "exception";
        }
    }

    @PostMapping("/like")
    public String likeProfile(HttpSession session, @RequestParam("name") String name, @RequestParam("email") String email,
                              Model model) {
        String token = (String) session.getAttribute("token");
        try {
            Person person = personRepository.getPersonByToken(token).get(0);
            pairService.postPair(PairPostDTO.builder()
                    .emailFirst(person.getUser().getEmail())
                    .emailSecond(email)
                    .status(true)
                    .build());
            User randomPairUser = pairService.getRandomPairUser(token);
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", token);
                return "not_found";
            }
            session.setAttribute("token", token);
            if (person.getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } catch (Exception e) {
            return "exception";
        }
    }

    @PostMapping("/dislike")
    public String dislikeProfile(HttpSession session, @RequestParam("name") String name, @RequestParam("email") String email,
                                 Model model) {
        String token = (String) session.getAttribute("token");
        try {
            Person person = personRepository.getPersonByToken(token).get(0);
            pairService.postPair(PairPostDTO.builder()
                    .emailFirst(person.getUser().getEmail())
                    .emailSecond(email)
                    .status(false)
                    .build());
            User randomPairUser = pairService.getRandomPairUser(token);
            if (randomPairUser != null) {
                ModelUtils.fillModel(randomPairUser, model);
            } else {
                model.addAttribute("photo", "/images/not_found.jpg");
                session.setAttribute("token", token);
                return "not_found";
            }
            session.setAttribute("token", token);
            if (person.getUser().getRole().equals(Role.ADMIN)) {
                return "admin_main";
            }
            return "main";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/chats")
    public String chatsPage(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        try {
            Person person = personRepository.getPersonByToken(token).get(0);
            List<Chat> allChats1 = new java.util.ArrayList<>(pairService.getAllPairs(token).stream().distinct().map(x ->
                    Chat.builder().name(x.getFirst().getFirstname()).photo(x.getFirst().getPhoto()).email(x.getFirst().getEmail()).build()).toList());
            List<Chat> allChats2 = pairService.getAllPairs(token).stream().distinct().map(x ->
                    Chat.builder().name(x.getSecond().getFirstname()).photo(x.getSecond().getPhoto()).email(x.getSecond().getEmail()).build()).toList();

            allChats1.addAll(allChats2);

            List<Chat> chats = allChats1.stream().distinct().filter(x ->
                    !x.getName().equals(person.getUser().getFirstname())).toList();

            if (chats.isEmpty()) {
                return "without_pair";
            }
            model.addAttribute("chats", chats);
            session.setAttribute("token", token);
            return "chats";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/chat")
    public String chatPage(HttpSession session, Model model, @RequestParam("email") String email) {
        String token = (String) session.getAttribute("token");
        try {
            List<MessageGetDTO> messages = messageService.getAllMessages(token, email);
            model.addAttribute("messages", messages.stream().map(x ->
                    String.format("%s --- %s", x.getMessage(), x.getUserFrom().getFirstname())
            ));
            model.addAttribute("email", email);
            return "chat";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/ban-list")
    public String banPage(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        try {
            var users = authenticationService.getAllUsers();
            model.addAttribute("users", users);
            return "admin";
        } catch (Exception e) {
            return "exception";
        }
    }

    @GetMapping("/baning")
    public String banPage(HttpSession session, Model model, @RequestParam("email") String email) {
        String token = (String) session.getAttribute("token");
        try {
            authenticationService.banUser(email);
            var users = authenticationService.getAllUsers();
            model.addAttribute("users", users);
            return "admin";
        } catch (Exception e) {
            return "exception";
        }
    }

    @PostMapping("/send-message")
    public String messageRequest(HttpSession session, Model model, @RequestParam("email") String email, @RequestParam("message") String message) {
        String token = (String) session.getAttribute("token");
        Person person = personRepository.getPersonByToken(token).get(0);
        try {
            messageService.postMessage(MessagePostDTO.builder()
                    .message(message)
                    .color("red")
                    .date(LocalDateTime.now())
                    .emailFrom(person.getUser().getEmail())
                    .emailTo(email)
                    .build());

//            String messageToGPT = GPTUtils.sendMessageToGPT(message, messageService.getAllMessages(token, email));
//            messageService.postMessage(MessagePostDTO.builder()
//                    .message(messageToGPT)
//                    .color("red")
//                    .date(LocalDateTime.now())
//                    .emailFrom(email)
//                    .emailTo(person.getUser().getEmail())
//                    .build());

            List<MessageGetDTO> messages = messageService.getAllMessages(token, email);
            model.addAttribute("messages", messages.stream().map(x ->
                    String.format("%s --- %s", x.getMessage(), x.getUserFrom().getFirstname())
            ));
            model.addAttribute("email", email);

            return "chat";
        } catch (Exception e) {
            return "exception";
        }
    }
}
