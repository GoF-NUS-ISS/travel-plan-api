package com.gof.springcloud.entities;

import java.io.Serializable;

/**
 * @author leomax
 *entity --orm--- db_table
 */
public class Dept implements Serializable
{
	private Long deptno;
	private String dname;
	private String db_source; // which database come from

	public String getDb_source() {
		return db_source;
	}

	public void setDb_source(String db_source) {
		this.db_source = db_source;
	}

	public Long getDeptno() {
		return deptno;
	}

	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

}
