package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.user.User;
import java.util.List;

public interface UserPort {

	/**
	 * Method that obtains a list of users registered in JSONPlaceholder
	 *
	 * @return The list of users with their personal info
	 */
	List<User> findAll();
}
