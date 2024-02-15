package notebook;

import notebook.controller.UserController;
//import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

/**
 * Реализуйте удаление пользователей.
 * Подумать, где должен находиться метод createUser из UserView и если получится,
 * вынести его в нужный слой. Вынести логику dao в слой репозитория, а от слоя dao
 * избавится физически(перенести нужный код в класс репозитория, а пакет dao удалить).
 */
public class Main {
    public static void main(String[] args) {

        createDB(); //todo hw
        GBRepository repository = new UserRepository("db.txt"); //здесь поменяли на file на db.txt
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }
}