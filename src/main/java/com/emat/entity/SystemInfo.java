package com.emat.entity;

/**
 * <B>系统基本信息</B><br>
 * 
 * @author 
 * @since
 * 
 */
public class SystemInfo {

	/**
	 * couchbase状态
	 */
	private boolean thesaurusStatus;
	/**
	 * 上下词couchbase服务器
	 */
	private Couchbase thesaurusCouchbase;

	/**
	 * couchbase服务器
	 */
	private Couchbase couchbase;
	

	public boolean isThesaurusStatus() {
		return thesaurusStatus;
	}

	public void setThesaurusStatus(boolean thesaurusStatus) {
		this.thesaurusStatus = thesaurusStatus;
	}

	public Couchbase getThesaurusCouchbase() {
		return thesaurusCouchbase;
	}

	public void setThesaurusCouchbase(Couchbase thesaurusCouchbase) {
		this.thesaurusCouchbase = thesaurusCouchbase;
	}

	public Couchbase getCouchbase() {
		return couchbase;
	}

	public void setCouchbase(Couchbase couchbase) {
		this.couchbase = couchbase;
	}

}
