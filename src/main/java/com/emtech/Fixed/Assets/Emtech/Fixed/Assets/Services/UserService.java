package com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Services;

import com.emtech.Fixed.Assets.Emtech.Fixed.Assets.Entity.User;

public interface UserService {
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void saveUser(User user);
    User authenticateUser(String username, String password);
}
