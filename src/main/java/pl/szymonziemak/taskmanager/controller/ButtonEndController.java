package pl.szymonziemak.taskmanager.controller;

import pl.szymonziemak.taskmanager.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/end")
public class ButtonEndController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringTaskId = request.getParameter("endButton");
        Long longTaskId = Long.parseLong(stringTaskId);
        TaskService taskService = new TaskService();
        taskService.makeTaskDone(longTaskId);
        response.sendRedirect(request.getContextPath() + "/");
    }

}
