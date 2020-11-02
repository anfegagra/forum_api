package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.user.User;
import java.util.List;

public interface UserPort {

	/**
	 * Method that obtains a list of users registered in JSONPlaceholder.
	 *
	 * @return The list of users with their personal info.
	 */
	List<User> findAll();

	/**
	 * Method that obtains a user by id.
	 *
	 * @param userId Id of the user to be used in search.
	 * @return User with personal info.
	 */
	User findById(int userId);
}
