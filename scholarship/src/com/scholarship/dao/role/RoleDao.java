package com.scholarship.dao.role;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

public interface RoleDao {
	public int count(Map map);
	/***
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryAll();
	/***
	 * 查询角色可分配角色列表
	 * @param role
	 * @return
	 */
	public List<Role> queryRoleList(Map<?,?> map);
	/***
	 * 查询角色ById
	 * @param id
	 * @return
	 */
	public Role queryById(int id);
	/***
	 * 查询角色ByName
	 * @param name
	 * @return
	 */
	public List<Role> queryByName(String name);
	/***
	 * 添加角色
	 * @param role
	 * @return
	 */
	public int  insert(Role role);
	/***
	 * 删除角色
	 * @param role
	 */
	public void delete(Role role);
	/***
	 * 删除角色
	 * @param role
	 */
	public void deleteById(int id);
	/***
	 * 更新角色
	 * @param role
	 */
	public void update(Role role);
	/***
	 * 获取角色关联学院
	 * @param role
	 * @return
	 */
	public List<College> queryColleges(Role role);
	/***
	 * 获取角色关联班级
	 * @param role
	 * @return
	 */
	public List<Grade> queryGrade(Role role);
	/***
	 * 添加角色关联关系
	 * @return
	 */
	public int insertRelation(Map<?, ?> map);
	/***
	 * 删除角色关联关系
	 * @param role
	 */
	public void deleteRelation(Role role);
	/***
	 * 模糊查询
	 * @return
	 */
	public List<Role> query(Map<?,?> map,int startRow, int pageSize);
	/***
	 * 查询以创建排序
	 * @return
	 */
	public List<Role> queryOrderbyCreate();
	/***
	 * 查询以更新排序
	 * @return
	 */
	public List<Role> queryOrderbyUpdate();
}
