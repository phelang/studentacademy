package app.studentacademy.service;

import java.util.List;

public interface Service <E, ID>{
    public E save(E entity);
    public E find(ID id);
    public List<E> getAll();
    public E update(E entity);
    public E delete(ID id);
}
