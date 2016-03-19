package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Discount;

public interface DiscountDataAccess {
	
	public List<Discount> getAll();
	public void create(Discount discount) throws UssException;
	public void update(Discount discount) throws UssException;
}
