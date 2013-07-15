package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 任务表指令表
 */
public class Task {

	private String tag;
	private String type;

	private int id;
	private String taskid;
	private String address;
	private String remarks;
	private String taskdate;
	private String imagepath;

	private double longitude;
	private double latitude;

	private int iscomplete;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskId() {
		return this.taskid;
	}

	public void setTaskId(String taskid) {
		this.taskid = taskid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaskDate() {
		return this.taskdate;
	}

	public void setTaskDate(String taskdate) {
		this.taskdate = taskdate;
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

	public int getIsComplete() {
		return this.iscomplete;
	}

	public void setIsComplete(int iscomplete) {
		this.iscomplete = iscomplete;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// ---------------------------------------------------------------//

	public static final String T_NAME = "T_TASK";
	public static final String ID = "_ID";
	public static final String TASKID = "TASKID";
	public static final String TYPE = "TYPE";
	public static final String TAG = "TAG";
	public static final String ADDRESS = "ADDRESS";
	public static final String REMARKS = "REMARKS";
	public static final String TASKDATE = "TASKDATE";
	public static final String IMAGEPATH = "IMAGEPATH";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String LATITUDE = "LATITUDE";
	public static final String ISCOMPLETE = "ISCOMPLETE";

	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ T_NAME + "(" + ID
			+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " + TASKID
			+ "VARCHAR(50) NOT NULL , " + TYPE + " VARCHAR(50) NOT NULL, "
			+ TAG + " VARCHAR(50) NOT NULL, " + ADDRESS + " VARCHAR(200) , "
			+ REMARKS + " VARCHAR(400) , " + TASKDATE + " VARCHAR(100) , "
			+ IMAGEPATH + " VARCHAR(200) , " + LONGITUDE + " FLOAT NOT NULL, "
			+ LATITUDE + "FLOAT NOT NULL, " + ISCOMPLETE + " INTEGER NOT NULL)";
}