package com.simplehibernate.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "t_user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 3302763173315884925L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_user_id", unique = true, nullable = true)
	private Long userID;

	@Column(name = "login_name", length = 45)
	private String loginName;

	@Column(name = "email", length = 45)
	private String email;

	@Column(name = "password", length = 45)
	private String password;

	@Column(name = "ordercode")
	private Integer orderCode;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "pk_user_id") }, inverseJoinColumns = { @JoinColumn(name = "pk_role_id") })
	private Set<Role> roles = new HashSet<Role>();

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@OneToMany(cascade = {  CascadeType.MERGE, CascadeType.REMOVE ,CascadeType.PERSIST })
	@JoinTable(name = "USER_INDUSTRY_MAP", joinColumns = { @JoinColumn(name = "CUIM_USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "CUIM_INDUSTRY_ID") })
	private List<Industry> industries = new ArrayList<Industry>();

	public List<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ConversationLabel_Conversations",
	           joinColumns = @JoinColumn(name = "ConversationLabelID"), inverseJoinColumns = @JoinColumn(name = "ConversationID"))
	private List<Conversation> conversations;

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}
}
