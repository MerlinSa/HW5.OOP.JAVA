package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.Scanner;

public class UserView {
    private final UserController userController; //поле юзерконт типа юзерконт.

    public UserView(UserController userController) { // конструктор,где

        this.userController = userController;//инициализируются контроллеры
    }

    public void run(){ //метод ран отвечает за вывод инфо для пользователя
        Commands com; // это константы enums, вводя с консоли эти команды мы получаем инфо
                        // ссылка com типа Commands
        while (true) {
            String command = prompt("Введите команду: "); //вводим команду с консоли,
            com = Commands.valueOf(command);  //вводим команду с консоли,
            if (com == Commands.EXIT) return; //проверяем является ли команда EXIT: if yes-exit from app.
            switch (com) {
                case CREATE: //if command is CREATE,
                    User u = createUser(); //we create a user
                    userController.saveUser(u); //вызываем метод контоллера-схранить юзера, call method saveUser to save the user. (1)View дал команду контроллеру saveUser
                    break;
                case READ: //if com is READ, we read it by id.
                    String id = prompt("Идентификатор пользователя: "); //text to user to monitor
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case LIST:
                    System.out.println(userController.readAll());
                    break;


                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    String userIdy = prompt("Enter user id: ");
                    userController.deleteUser(Long.parseLong(userIdy));//todo вызываем метод контоллера deleteUser для удаления юзера,
                    break;  // todo (1)View дал команду контроллеру deleteUser
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() { //todo вот это куда? возможно в репозиторий.
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");

        UserValidator validator = new UserValidator(); //TODO???

        return validator.validate(new User(firstName, lastName, phone));
    }
}

