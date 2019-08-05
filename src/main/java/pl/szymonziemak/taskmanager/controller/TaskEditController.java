package pl.szymonziemak.taskmanager.controller;

import pl.szymonziemak.taskmanager.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edited")
public class TaskEditController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String editedName = request.getParameter("inputName");
        String editedDescription = request.getParameter("inputDescription");

        String stringTaskId = request.getParameter("task_id");
        Long longTaskId = Long.parseLong(stringTaskId);

        TaskService taskService = new TaskService();
        taskService.editTaskById(longTaskId, editedName, editedDescription);
        response.sendRedirect(request.getContextPath() + "/");
    }

}
