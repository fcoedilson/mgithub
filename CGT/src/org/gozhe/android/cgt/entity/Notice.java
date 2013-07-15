package org.gozhe.android.cgt.entity;



/**
 * @author xiaox
 * ΩÒ»’Ã·–—
 */
public class Notice {

	private int id;
	private String noticedate;
	private String content;

	public Notice(int id, String noticedate, String content) {
		this.id = id;
		this.noticedate = noticedate;
		this.content = content;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoticeDate() {
		return this.noticedate;
	}

	public void setNoticeDate(String noticedate) {
		this.noticedate = noticedate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
