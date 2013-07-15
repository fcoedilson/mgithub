package org.gozhe.android.core.db;

import java.io.Serializable;
import java.util.List;


/**
 * @author xiaox
 * 基本DAO类
 * EntityDao是一个泛型接口，定义了CRUD的方法
 * @param <T>
 * @param <PK>
 */
public interface EntityDao<T,PK extends Serializable>{
	
	
	/**
	 * 添加实体
	 * @param entity
	 */
	void save(final T entity);
	
	
	/**
	 * 移除实体对象
	 * @param ids
	 */
	void remove(final PK... ids);
	
	/**
	 * 更新
	 * @param entity
	 */
	void upDate(final T entity);
	
	/**
	 * 按照ID查询对象
	 * @param id
	 * @return
	 */
	T find(final PK id);
	
	
	/**
	 * 分页查询
	 * @param startResult
	 * @param maxResult
	 * @return
	 */
	List<T> getPageData(Integer startResult, Integer maxResult);
	
	
	/**
	 * 返回记录数
	 * @return
	 */
	public long getCount();
}

