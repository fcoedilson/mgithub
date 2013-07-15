package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 信息核实指令表
 */
public class Verify {

	private int id;
	private String verifyid;
	private String describe;
	private String address;
	private String userid;
	private int ifverify;
	private int ifproblem;
	
	public Verify(){
		
	}

	public Verify(int id, String verifyid, String describe, String address,
			String userid, int ifverify, int ifproblem) {
		this.id = id;
		this.verifyid = verifyid;
		this.describe = describe;
		this.address = address;
		this.userid = userid;
		this.ifverify = ifverify;
		this.ifproblem = ifproblem;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVerifyId() {
		return this.verifyid;
	}

	public void setVerifyId(String verifyid) {
		this.verifyid = verifyid;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserId() {
		return this.userid;
	}

	public void setUserId(String userid) {
		this.userid = userid;
	}

	public int getIfVerify() {
		return this.ifverify;
	}

	public void setIfVerify(int ifverify) {
		this.ifverify = ifverify;
	}

	public int getIfProblem() {
		return this.ifproblem;
	}

	public void setIfProblem(int ifproblem) {
		this.ifproblem = ifproblem;
	}

}
