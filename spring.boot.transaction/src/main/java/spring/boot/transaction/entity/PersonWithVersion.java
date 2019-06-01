package spring.boot.transaction.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class PersonWithVersion implements Serializable {
	
	private static final long serialVersionUID = 7309355786388468637L;

	@Id
	@GeneratedValue
	Long id;

	@Version
	@Column
	Long version;

	@Column
	String forename;

	@Column(length = 8)
	String surename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	public Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
