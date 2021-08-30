package com.example.linkpreview.service;

import com.example.linkpreview.Entity.Userd;

public interface UserdService {
    void addUser(Userd userd);
    Userd getUserd(String userName);
}
