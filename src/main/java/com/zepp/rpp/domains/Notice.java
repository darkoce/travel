package com.zepp.rpp.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Notice implements Serializable{

	private static final long serialVersionUID = -8624502420688088706L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int noticeId;
	private String noticeTitle;
	@Lob
	private String noticeNote;
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@OneToOne
	@JoinColumn(name = "id")
	@JsonIgnore
	private User noticeUser;
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeNote() {
		return noticeNote;
	}
	public void setNoticeNote(String noticeNote) {
		this.noticeNote = noticeNote;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getNoticeUser() {
		return noticeUser;
	}
	public void setNoticeUser(User noticeUser) {
		this.noticeUser = noticeUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noticeId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		if (noticeId != other.noticeId)
			return false;
		return true;
	}
	
}
