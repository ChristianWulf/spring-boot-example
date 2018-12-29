package chw.tutorial.springboot.data.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "suchdienst_leistung")
@EntityListeners(AuditingEntityListener.class)
public class SuchdienstLeistungEntity {

	// TODO configure default table name and column name

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "lbid")
	private String lbId;

	@Column(name = "created_date", nullable = false)
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "modified_date")//, insertable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP")
	@LastModifiedDate
	// @Temporal(TemporalType.TIMESTAMP)
	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Timestamp modifiedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLbId() {
		return lbId;
	}

	public void setLbId(String lbId) {
		this.lbId = lbId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
