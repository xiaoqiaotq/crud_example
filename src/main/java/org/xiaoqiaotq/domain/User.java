/**
 * 
 */
package org.xiaoqiaotq.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author xiaoqiaotq@gmail.com
 * @date 2014年11月27日
 */
@Entity
@Table(name="t_user")
public class User extends BaseEntity{
	@NotNull
	private String username;
	
	@Min(0)
	private Integer age;
	
	@NotNull
	private String pass;

	private String photo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date registerDate;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_role", 
			joinColumns = { @JoinColumn(name = "user_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "role_id")})
	private Set<Role> roles;
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}



	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", username=" + username + ", age=" + age
				+ ", date=" + registerDate + "]";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public enum Habit {
		Basketball, Football, Swimming;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
