package com.simplehibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplehibernate.model.UserInfo;
import com.simplehibernate.repository.UserInfoRepository;

@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo login(String domain, String username, String password){
        UserInfo userInfo = this.userInfoRepository.get(domain, username, password);
        if (userInfo == null) {
            throw new RuntimeException("Wrong domain/username/password combinationnvalid.username");
        }

        return userInfo;
    }
} 