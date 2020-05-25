package pe.isil.DAO;

import java.util.List;

public interface BaseDAO<T, X> {
    public void create(T t);
    public void update(T t);
    public void delete(T t);

    public List<T> getAll();
    public T getById(X x);

}
