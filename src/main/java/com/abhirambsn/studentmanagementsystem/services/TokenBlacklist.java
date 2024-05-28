package com.abhirambsn.studentmanagementsystem.services;


import org.springframework.stereotype.Service;

public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}

