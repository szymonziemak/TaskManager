package pl.szymonziemak.taskmanager.dao;

import pl.szymonziemak.taskmanager.exception.NoSuchDbTypeException;

public abstract class DaoFactory {

    public static final int MYSQL_DAO_FACTORY = 1;

    public abstract UserDAO getUserDAO();

    public abstract TaskDAO getTaskDAO();

    public static DaoFactory getDaoFactory() {
        DaoFactory factory = null;
        try {
            factory = getDaoFactory(MYSQL_DAO_FACTORY);
        } catch (NoSuchDbTypeException e) {
            e.printStackTrace();
        }
        return factory;
    }

    private static DaoFactory getDaoFactory(int type) throws NoSuchDbTypeException {
        switch (type) {
            case MYSQL_DAO_FACTORY:
                return new MysqlDaoFactory();
            default:
                throw new NoSuchDbTypeException();
        }
    }

}
