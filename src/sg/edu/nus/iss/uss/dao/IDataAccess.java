package sg.edu.nus.iss.uss.dao;

import java.util.List;

public interface IDataAccess<E> {
	
	public List<E> getAll();
	public void create(E e);
	public void update(E e);

}
