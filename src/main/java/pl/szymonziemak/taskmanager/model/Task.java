package pl.szymonziemak.taskmanager.model;

import java.util.Objects;

public class Task {
    private long task_id;
    private User user;
    private String name;
    private String description;
    private boolean is_finished;

    public Task() {}

    public Task(Task task) {
        this.task_id = task.task_id;
        this.user = new User(task.user);
        this.name = task.name;
        this. description = task.description;
        this.is_finished = task.is_finished;
    }

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(boolean is_finished) {
        this.is_finished = is_finished;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_finished=" + is_finished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return task_id == task.task_id &&
                is_finished == task.is_finished &&
                user.equals(task.user) &&
                name.equals(task.name) &&
                description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task_id, user, name, description, is_finished);
    }
}
