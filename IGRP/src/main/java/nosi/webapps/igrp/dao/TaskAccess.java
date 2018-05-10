package nosi.webapps.igrp.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * Emanuel
 * 9 May 2018
 */

@Entity
@Table(name="tbl_task_access")
public class TaskAccess extends BaseActiveRecord<TaskAccess> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "org_fk",foreignKey = @ForeignKey(name="TASK_ACCESS_ORGANIZATION_FK"),nullable=false)
	private Organization organization;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "prof_fk",foreignKey = @ForeignKey(name="TASK_ACCESS_PROFILE_TYPE_FK"))
	private ProfileType profileType;
	@Column(length=100)
	private String taskName;
	@Column(length=150)
	private String processName;
	
	public TaskAccess() {
		
	}
	
	public TaskAccess(Organization organization, ProfileType profileType, String taskName,
			String processName) {
		super();
		this.organization = organization;
		this.profileType = profileType;
		this.taskName = taskName;
		this.processName = processName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public ProfileType getProfileType() {
		return profileType;
	}
	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Override
	public String toString() {
		return "TaskAccess [id=" + id + ", organization=" + organization + ", profileType=" + profileType
				+ ", taskName=" + taskName + ", processName=" + processName + "]";
	}
}
