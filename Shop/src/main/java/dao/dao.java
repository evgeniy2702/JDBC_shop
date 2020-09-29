package dao;

import java.sql.SQLException;

public interface dao<T> {

    public void saveEntity(T entity) throws SQLException, ClassNotFoundException;

    public T getEntityById (Integer idEntity) throws SQLException, ClassNotFoundException;

    public void print () throws SQLException, ClassNotFoundException;
}
