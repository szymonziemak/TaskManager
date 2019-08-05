package pl.szymonziemak.taskmanager.dao;

import pl.szymonziemak.taskmanager.model.Task;

import java.util.List;

public interface TaskDAO extends GenericDAO<Task, Long>{

    List<Task> getAll();

}
