package spring.boot.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
// @Cacheable // not necessary
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) // does not work
// @SelectBeforeUpdate(false) // does not work
public class LastEventId {

	@Id
	@GeneratedValue
	Long id;

	@Version
	@Column
	Long version;

	@Column
	String lastEventId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastEventId() {
		return lastEventId;
	}

	public void setLastEventId(String lastEventId) {
		this.lastEventId = lastEventId;
	}

	public Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "LastEventId [id=" + id + ", version=" + version + ", lastEventId=" + lastEventId + "]";
	}

}
