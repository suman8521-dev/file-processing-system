package com.file.mapper;
import com.file.entity.User;
import com.file.entity.resp.UserResponse;
import com.file.repo.UserRepo;
import lombok.Data;

@Data
public class UserMapper {

    public static UserResponse toDto(User user){
        UserResponse response=new UserResponse();
        response.setId(user.getId());
        response.setEmil(user.getEmail());
        response.setPhone(user.getPhone());
        response.setFullName(user.getFullName());
        response.setLastLogin(user.getLastLogin());
        response.setProfileImage(user.getProfileImage());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        return  response;
    }
}
