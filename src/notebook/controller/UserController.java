package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) { //(2)пришла команда со Вида и Контроллер направляет ее в слой модели

        repository.create(user); // контороллер вызввает метод репозитория Create.
        //репозиторий-это класс кот-й взаимодействует с базой данных
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public List<User> readAll() { //todo task
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public boolean deleteUser(Long id) { //todo hw метод для удаления юзера в контроллер,
        return repository.delete(id); //todo (2) контороллер вызввает метод репозитория delete.
    }
}

