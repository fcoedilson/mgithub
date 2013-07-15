package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 用户管理
 */
public class User {

	// 字段
	
	private int id;
	private String userid;
	private String username;
	private String password;
	private String cnname;
	private String empid;
	private String tel;
	private String dutygrid;
	private String remarks;
	
	public User(int id, String userid, String username, String password,
			String cnname, String empid, String tel, String dutygrid,
			String remarks) {
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.cnname = cnname;
		this.empid = empid;
		this.tel = tel;
		this.dutygrid = dutygrid;
		this.remarks = remarks;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userid;
	}

	public void setUserId(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnName() {
		return this.cnname;
	}

	public void setCnName(String cnname) {
		this.cnname = cnname;
	}

	public String getEmpId() {
		return this.empid;
	}

	public void setEmpId(String empid) {
		this.empid = empid;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDutyGrid() {
		return this.dutygrid;
	}

	public void setDutyGrid(String dutygrid) {
		this.dutygrid = dutygrid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// -----------------------------------------------------------------------------//
}
