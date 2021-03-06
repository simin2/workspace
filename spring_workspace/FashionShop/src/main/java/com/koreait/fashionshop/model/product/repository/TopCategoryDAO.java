package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.TopCategory;

public interface TopCategoryDAO {
	//CRUD
	abstract public List selectAll();
	abstract public TopCategory select(int topcategory_id);
	abstract public void insert(TopCategory topcategory);
	abstract public void update(TopCategory topcategory);
	abstract public void delete(int topcategory_id);
}
