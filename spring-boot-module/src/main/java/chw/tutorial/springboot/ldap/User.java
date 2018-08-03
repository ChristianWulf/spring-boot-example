package chw.tutorial.springboot.ldap;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

//@Entry(base = "ou=people,dc=springframework,dc=org", objectClasses = { "top", "person", "organizationalPerson", "inetOrgPerson", })
@Entry(base = "ou=" + User.GROUP, objectClasses = { "top", "person", "organizationalPerson", "inetOrgPerson", })
// @Entry(objectClasses = { "top", "person", "organizationalPerson",
// "inetOrgPerson", })
public class User {

	public static final String GROUP = "people";

	@Id
	private Name dn;
	@Attribute(name = "cn")
	private String username;
	@Attribute(name = "userPassword")
	private String hashedPassword;

	@Attribute(name = "uid")
	@DnAttribute(value = "uid", index = 1)
	private String uid;

	private String group = GROUP;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGroup() {
		return group;
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}