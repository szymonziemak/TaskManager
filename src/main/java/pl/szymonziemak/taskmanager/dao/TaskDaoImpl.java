package pl.szymonziemak.taskmanager.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.szymonziemak.taskmanager.model.Task;
import pl.szymonziemak.taskmanager.model.User;
import pl.szymonziemak.taskmanager.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoImpl implements TaskDAO {

    private static final String CREATE_TASK =
            "INSERT INTO task(user_id, name, description, is_finished) "
                    + "VALUES(:user_id, :name, :description, :is_finished);";
    private static final String READ_ALL_TASKS =
            "SELECT user.user_id, username, password, task_id, name, description, is_finished "
                    + "FROM task LEFT JOIN user ON task.user_id=user.user_id;";
    private static final String READ_TASK =
            "SELECT user.user_id, username, password, task_id, name, description, is_finished "
                    + "FROM task LEFT JOIN user ON task.user_id=user.user_id WHERE task_id=:task_id;";
    private static final String UPDATE_TASK =
            "UPDATE task SET name=:name, description=:description, is_finished=:is_finished "
                    + "WHERE task_id=:task_id;";
    private static final String DELETE_TASK =
            "DELETE FROM task WHERE task_id=:task_id;";

    private NamedParameterJdbcTemplate template;

    public TaskDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Task create(Task task) {
        Task resultTask = new Task(task);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", task.getUser().getUser_id());
        paramMap.put("name", task.getName());
        paramMap.put("description", task.getDescription());
        paramMap.put("is_finished", task.getIs_finished());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_TASK, paramSource, holder);
        if (update > 0) {
            resultTask.setTask_id(holder.getKey().longValue());
        }
        return resultTask;
    }

    @Override
    public Task read(Long task_id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("task_id", task_id);
        Task task = template.queryForObject(READ_TASK, paramSource, new TaskRowMapper());
        return task;
    }

    @Override
    public boolean update(Task task) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", task.getName());
        paramMap.put("description", task.getDescription());
        paramMap.put("is_finished", task.getIs_finished());
        paramMap.put("task_id", task.getTask_id());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_TASK, paramSource);
        if (update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long task_id) {
        boolean result = false;
        SqlParameterSource paramSource = new MapSqlParameterSource("task_id", task_id);
        int update = template.update(DELETE_TASK, paramSource);
        if (update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = template.query(READ_ALL_TASKS, new TaskRowMapper());
        return tasks;
    }


    private class TaskRowMapper implements RowMapper<Task> {
        @Override
        public Task mapRow(ResultSet resultSet, int row) throws SQLException {
            Task task = new Task();
            task.setTask_id(resultSet.getLong("task_id"));
            task.setName(resultSet.getString("name"));
            task.setDescription(resultSet.getString("description"));
            task.setIs_finished(resultSet.getBoolean("is_finished"));
            User user = new User();
            user.setUser_id(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            task.setUser(user);
            return task;
        }
    }
}
