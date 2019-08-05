package pl.szymonziemak.taskmanager.controller;

import pl.szymonziemak.taskmanager.model.Task;
import pl.szymonziemak.taskmanager.model.User;
import pl.szymonziemak.taskmanager.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("")
public class UndoneListViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveUndoneTasksInRequest(request);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    private void saveUndoneTasksInRequest(HttpServletRequest request) {
        User loggedInUser = (User) request.getSession(false).getAttribute("user");
        TaskService taskService = new TaskService();
        List<Task> undoneTasks = taskService.getAllTasks().stream()
                .filter(x -> x.getUser().getUser_id() == loggedInUser.getUser_id())
                .filter(x -> x.getIs_finished() == false).collect(Collectors.toList());

        request.setAttribute("undoneTasks", undoneTasks);
    }

}
