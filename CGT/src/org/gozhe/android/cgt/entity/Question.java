package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 
 */
public class Question {

	private int id;
	private String issueid;
	private String type;
	private String dl;
	private String xl;
	private String dealtime;

	private String address;
	private String remarks;

	private String imagepath;
	private String recordpath;

	private double longitude;
	private double latitude;

	private int isupload;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueId() {
		return this.issueid;
	}

	public void setIssueId(String issueid) {
		this.issueid = issueid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDL() {
		return this.dl;
	}

	public void setDL(String dl) {
		this.dl = dl;
	}

	public String getXL() {
		return this.xl;
	}

	public void setXL(String xl) {
		this.xl = xl;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDealTime() {
		return this.dealtime;
	}

	public void setDealTime(String dealtime) {
		this.dealtime = dealtime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImagePath() {
		return this.imagepath;
	}

	public void setImagePath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getRecordPath() {
		return this.recordpath;
	}

	public void setRecordPath(String recordpath) {
		this.recordpath = recordpath;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getIsUpload() {
		return this.isupload;
	}

	public void setIsUpload(int isupload) {
		this.isupload = isupload;
	}

	// ---------------------------------------------------------------//

	public static final String T_NAME = "T_ISSUE";
	public static final String ID = "_ID";
	public static final String ISSUEID = "ISSUEID";
	public static final String TYPE = "TYPE";
	public static final String DL = "DL";
	public static final String XL = "XL";
	public static final String DEALTIME = "DELLTIME";
	public static final String ADDRESS = "ADDRESS";
	public static final String REMARKS = "REMARKS";
	public static final String IMAGEPATH = "IMAGEPATH";
	public static final String RECORDPATH = "RECORDPATH";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String LATITUDE = "LATITUDE";
	public static final String ISUPLOAD = "ISUPLOAD";

	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ T_NAME + "(" + ID + "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ ISSUEID + "VARCHAR(50) NOT NULL , " + TYPE
			+ "VARCHAR(50) NOT NULL, " + DL + " VARCHAR(50) NOT NULL, " + XL
			+ "VARCHAR(50) NOT NULL, " + DEALTIME + " VARCHAR(100) , "
			+ ADDRESS + "VARCHAR(200) , " + REMARKS + " VARCHAR(400) , "
			+ IMAGEPATH + "VARCHAR(200) , " + RECORDPATH + " VARCHAR(200) , "
			+ LONGITUDE + "DOUBLE NOT NULL, " + LATITUDE + " DOUBLE NOT NULL, "
			+ "ISUPLOAD INTEGER NOT NULL)";
}
