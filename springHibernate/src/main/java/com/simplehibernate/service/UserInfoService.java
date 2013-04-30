package com.simplehibernate.service;

import com.simplehibernate.model.UserInfo;

public interface UserInfoService {

	public abstract UserInfo login(String domain, String username, String password);

}
