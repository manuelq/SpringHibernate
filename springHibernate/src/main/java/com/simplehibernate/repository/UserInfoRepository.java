package com.simplehibernate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehibernate.dao.UniversalDoa;
import com.simplehibernate.model.UserInfo;

@Repository("accountRepository")
public class UserInfoRepository {
	
	@Autowired
	UniversalDoa userInfoDao;

    public UserInfo get(String domain, String username, String password) {
        return (UserInfo) userInfoDao.getAll(UserInfo.class).get(0);
    }
}