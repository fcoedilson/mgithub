package org.gozhe.android.cgt.entity;

/**
 * @author xiaox 
 * 案件核查指令表
 */
public class Check {
	private int id;
	private String checkid;
	private String describe;
	private String address;
	private float longitude;
	private float latitude;
	private String photos;
	private String scencephotos;
	private int ifchecked;
	private int ifcompleted;

	public Check(int id, String checkid, String describe, String address,
			float longitude, float latitude, String photos,
			String scencephotos, int ifchecked, int ifcompleted) {
		this.id = id;
		this.checkid = checkid;
		this.describe = describe;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.photos = photos;
		this.scencephotos = scencephotos;
		this.ifchecked = ifchecked;
		this.ifcompleted = ifcompleted;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckId() {
		return this.checkid;
	}

	public void setCheckId(String checkid) {
		this.checkid = checkid;
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

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getPhotos() {
		return this.photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getScencephotos() {
		return this.scencephotos;
	}

	public void setScencephotos(String scencephotos) {
		this.scencephotos = scencephotos;
	}

	public int getIfChecked() {
		return this.ifchecked;
	}

	public void setIfChecked(int ifchecked) {
		this.ifchecked = ifchecked;
	}

	public int getIfCompleted() {
		return this.ifcompleted;
	}

	public void setIfCompleted(int ifcompleted) {
		this.ifcompleted = ifcompleted;
	}
}
