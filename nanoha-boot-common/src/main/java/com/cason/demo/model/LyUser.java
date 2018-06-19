package com.cason.demo.model;

import java.util.Date;

public class LyUser {
	private Integer id;

	private String username;

	private String accountname;

	private String password;

	private String credentialssalt;

	private String description;

	private String locked;

	private Date createtime;

	private Integer deletestatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname == null ? null : accountname.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getCredentialssalt() {
		return credentialssalt;
	}

	public void setCredentialssalt(String credentialssalt) {
		this.credentialssalt = credentialssalt == null ? null : credentialssalt.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked == null ? null : locked.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(Integer deletestatus) {
		this.deletestatus = deletestatus;
	}
}