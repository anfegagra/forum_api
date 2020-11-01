package com.wchallenge.forum.domain.port;

import com.wchallenge.forum.domain.model.post.Comment;
import com.wchallenge.forum.domain.model.post.Post;
import java.util.List;

public interface PostPort {

	/**
	 * Method that obtains a list of comments registered in JSONPlaceholder.
	 *
	 * @return The list of comments with their details.
	 */
	List<Comment> findComments();

	/**
	 * Method that obtains a list of comments registered in JSONPlaceholder filtered by name.
	 *
	 * @param name Name of the comment to be used in the filer.
	 * @return The list of comments filtered by the given name.
	 */
	List<Comment> findCommentsByName(String name);

	/**
	 * Method that obtains a list of posts registered in JSONPlaceholder filtered by user id.
	 *
	 * @param userId Id of the user to be used in the filter.
	 * @return The list of posts filtered by the given user id.
	 */
	List<Post> findPostsByUserId(int userId);
}
