package com.sonata.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.User;
import com.sonata.Repository.UserRepository;
// import com.sonata.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // Create user
    public User saveUser(User user) {
        return userRepo.save(user);
    }
    
    //Create multiple users
    public List<User> saveMultipleUsers(List<User> users){
    	return userRepo.saveAll(users);
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
    
    // Get all users
    public List<User> getAllUsers(){
    	return userRepo.findAll();
    }

    // Get user by username
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // Get user by email and password(Login)
    public User getUserByEmailPassword(String email, String password) {
        User exisitingUser = userRepo.findByEmail(email);
        if(exisitingUser != null){
            if(password.equals(exisitingUser.getPassword())){
                return exisitingUser;
            }
        }
        return null;
    }

    // Update user details
    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {

            if(updatedUser.getUsername() != null){
                existingUser.setUsername(updatedUser.getUsername());
            }
            if(updatedUser.getPassword() != null){
                existingUser.setPassword(updatedUser.getPassword());   
            }
            if(updatedUser.getEmail() != null){
                existingUser.setEmail(updatedUser.getEmail());
            }
            if(updatedUser.getPhone() != null){
                existingUser.setPhone(updatedUser.getPhone());
            }
            if(updatedUser.getAddress() != null){
                existingUser.setAddress(updatedUser.getAddress());
            }
            return userRepo.save(existingUser);
        }
        return null;
    }

    // Delete user 
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
