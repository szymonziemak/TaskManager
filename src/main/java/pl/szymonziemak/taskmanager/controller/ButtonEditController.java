package pl.szymonziemak.taskmanager.controller;

import pl.szymonziemak.taskmanager.model.Task;
import pl.szymonziemak.taskmanager.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class ButtonEditController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringTaskId = request.getParameter("editButton");
        Long longTaskId = Long.parseLong(stringTaskId);

        TaskService taskService = new TaskService();
        Task taskToEdit = taskService.getTaskById(longTaskId);

        request.setAttribute("taskToEdit", taskToEdit);
        request.getRequestDispatcher("WEB-INF/edited.jsp").forward(request, response);
    }

}
