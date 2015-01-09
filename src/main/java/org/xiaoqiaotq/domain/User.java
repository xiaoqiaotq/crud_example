/**
 * 
 */
package org.xiaoqiaotq.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author xiaoqiaotq@gmail.com
 * @date 2014年11月27日
 */
// @XmlRootElement(name="xmdls")
@Entity
@Table(name="t_user")
public class User {
	@GeneratedValue
	@Id
	private int id;

	private String username;
	@Max(10)
	private int age;


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.DATE)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age
				+ ", date=" + date + "]";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public enum Habit {
		Basketball, Football, Swimming;
	}
}
