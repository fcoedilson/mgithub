package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 任务交接表
 */
public class Handover {

	private int id;
	private String userid;
	private String cnname;
	private String logintime;
	private String logouttime;
	private int signout;
	private String remarks;

	public Handover(){
		
	}
	
	public Handover(int id, String userid, String cnname, String logintime,
			String logouttime, int signout, String remarks) {
		this.id = id;
		this.userid = userid;
		this.cnname = cnname;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.signout = signout;
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

	public String getCnName() {
		return this.cnname;
	}

	public void setCnName(String cnname) {
		this.cnname = cnname;
	}

	public String getLoginTime() {
		return this.logintime;
	}

	public void setLoginTime(String logintime) {
		this.logintime = logintime;
	}

	public String getLogoutTime() {
		return this.logouttime;
	}

	public void setLogoutTime(String logouttime) {
		this.logouttime = logouttime;
	}

	public int getSignout() {
		return this.signout;
	}

	public void setSignout(int signout) {
		this.signout = signout;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// ----------------------------------------------------------------------------//

	public static final String T_NAME = "T_TASKHANDOVER";
	public static final String ID = "_ID";
	public static final String NAME = "NAME";
	public static final String HANDOVERTIME = "HANDOVERTIME";
	public static final String REMARKS = "REMARKS";

	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ T_NAME + " (" + ID
			+ " integer not null primary key autoincrement, " + NAME
			+ " VARCHAR(50) NOT NULL , " + HANDOVERTIME + "VARCHAR(100) , "
			+ REMARKS + " VARCHAR(400))";
}
