package com.wchallenge.forum.domain.service;

import com.wchallenge.forum.domain.exception.DataNotFoundException;
import com.wchallenge.forum.domain.exception.ForumNotificationCode;
import com.wchallenge.forum.domain.model.user.User;
import com.wchallenge.forum.domain.port.UserPort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserPort userPort;

	public UserService(UserPort userPort) {
		this.userPort = userPort;
	}

	public List<User> findAll() {
		return userPort.findAll();
	}

	public User findById(int userId) {

		User user = userPort.findById(userId);

		if (user == null) {
			throw new DataNotFoundException(ForumNotificationCode.DATA_NOT_FOUND);
		}

		return user;
	}

}
