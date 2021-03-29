package kim.REST313.service;//package com.security.bootspring.crudboot.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
