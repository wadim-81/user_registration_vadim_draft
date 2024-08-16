
import java.util.Scanner;

    public class RegistrationController {
        private final UserService userService;
        private final Scanner scanner;

        public RegistrationController(UserService userService, Scanner scanner) {
            this.userService = userService;
            this.scanner = scanner;
        }

        public void start() {
            try {
                System.out.println("1. Регистрация\n2. Авторизация");
                String choice = scanner.nextLine();
                UserCommand userCommand = UserCommand.getCommandByValue(choice);

                switch (userCommand) {
                    case SIGNUP:
                        signUp();
                        break;
                    case SIGNIN:
                        signIn();
                        break;
                    default:
                        System.out.println("Некорректный выбор");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }

        private void signUp() {
            System.out.print("Введите имя пользователя: ");
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            if (userService.isUserExists(login)) {
                System.out.println("Пользователь с таким именем уже существует");
                return;
            }

            if (!userService.isValidPassword(password)) {
                System.out.println("Некорректный пароль. Пароль должен содержать хотя бы одну цифру, один специальный символ и одну заглавную букву.");
                return;
            }

            String encryptedPassword = userService.encryptPassword(password);
            User user = new User(login, encryptedPassword);
            userService.saveUser(user);
            System.out.println("Пользователь успешно зарегистрирован");
        }

        private void signIn() {
            System.out.print("Введите имя пользователя: ");
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            if (!userService.isUserExists(login)) {
                System.out.println("Пользователь не существует");
                return;
            }

            User user = userService.getUserByUsername(login);
            String encryptedPassword = userService.encryptPassword(password);
            if (!user.getPassword().equals(encryptedPassword)) {
                System.out.println("Некорректный пароль");
                return;
            }

            System.out.println("Пользователь успешно авторизован");


        }}
