package app.studentacademy.repository;

import java.util.List;

public interface Repository <E, ID>{
    public E save(E entity);
    public E findById(ID id);
    public List<E> getAll();
    public E update(E entity);
    public E delete(ID id);
}
