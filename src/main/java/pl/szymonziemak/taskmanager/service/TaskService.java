package pl.szymonziemak.taskmanager.service;

import pl.szymonziemak.taskmanager.dao.DaoFactory;
import pl.szymonziemak.taskmanager.dao.TaskDAO;
import pl.szymonziemak.taskmanager.model.Task;
import pl.szymonziemak.taskmanager.model.User;

import java.util.List;

public class TaskService {

    public void addTask(String name, String description, boolean is_finished, User user) {
        Task task = createTaskObject(name, description, is_finished, user);
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.create(task);
    }

    private Task createTaskObject(String name, String description, boolean is_finished, User user) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setIs_finished(is_finished);
        User userCopy = new User(user);
        task.setUser(userCopy);
        return task;
    }

    public Task getTaskById(Long task_id) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        Task task = taskDAO.read(task_id);
        return task;
    }

    public List<Task> getAllTasks() {
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        List<Task> tasks = taskDAO.getAll();
        return tasks;
    }

    public void makeTaskDone(Long task_id) {
        Task taskToUpdate = getTaskById(task_id);
        taskToUpdate.setIs_finished(true);
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.update(taskToUpdate);
    }

    public void makeTaskUndone(Long task_id) {
        Task taskToUpdate = getTaskById(task_id);
        taskToUpdate.setIs_finished(false);
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.update(taskToUpdate);
    }

    public void editTaskById(Long task_id, String name, String description) {
        Task taskToEdit = getTaskById(task_id);
        taskToEdit.setName(name);
        taskToEdit.setDescription(description);
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.update(taskToEdit);
    }

    public void deleteTaskById(Long task_id) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        TaskDAO taskDAO = factory.getTaskDAO();
        taskDAO.delete(task_id);
    }

}
