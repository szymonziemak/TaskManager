package pl.szymonziemak.taskmanager.dao;

public class MysqlDaoFactory extends DaoFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDaoImpl();
    }

    @Override
    public TaskDAO getTaskDAO() {
        return new TaskDaoImpl();
    }

}
