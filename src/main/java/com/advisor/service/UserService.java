package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.NewUserRequest;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.response.UserProfileResponse;


public interface UserService {
	User findUserByEmail(String email);
	void saveUser(NewUserRequest newUserRequest);
	User findUserById(Long userId);

	UserProfile findUserProfileByUser(User user);
	UserProfileResponse createUserProfileResponseByUser(User user);

	void updateUserProfile(UserProfileRequest userProfileRequest, Long userProfileId);

	UserProfileResponse createUserResponseByUser(User user);
	//UserProfile findUserProfileById(Long id);
}
