package org.gozhe.android.cgt.entity;

public class QuestionType {
	private String code;
	private String name;

	@Override
	public String toString() {
		// //为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
		// TODO Auto-generated method stub
		//return super.toString();
		return name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNamae() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
