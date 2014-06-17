package tinf13b4.forum.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.CategoryBuilder;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.PostBuilder;
import tinf13b4.forum.model.User;
import tinf13b4.forum.model.UserBuilder;


public class ResultSetUtil {
	public static User buildUser(ResultSet resultSet, PostController postController) {
		UserBuilder userBuilder = new UserBuilder();
		try {
			int userId = resultSet.getInt("User_ID");
			int posts = postController.getPostsCountForUser(userId);
			userBuilder.setId(userId);
			userBuilder.setName(resultSet.getString("Name"));
			userBuilder.setPicture(resultSet.getString("Picture"));
			userBuilder.setEmail(resultSet.getString("Email"));
			userBuilder.setJoinedOn(new Date(resultSet.getTimestamp("JoinedOn").getTime()));
			userBuilder.setConfirmed(resultSet.getBoolean("Confirmed"));
			userBuilder.setPostsCount(posts);
		} catch(SQLException e) {
			throw new IllegalStateException("SQL error while building User: " + e);
		}
		return userBuilder.build();
	}

	public static Post buildPostForUserId(ResultSet resultSet, PostController postController, int userId) {
		PostBuilder postBuilder = new PostBuilder();
		try {
			postBuilder.setPostId(resultSet.getInt("Post_ID"));
			postBuilder.setUser(buildUser(resultSet, postController));
			postBuilder.setThreadId(resultSet.getInt("Thread_ID"));
			postBuilder.setContent(resultSet.getString("Content"));
			postBuilder.setDate(new Date(resultSet.getTimestamp("Date").getTime()));
		} catch (SQLException e) {
			throw new IllegalStateException("SQL error while building Post: " + e);
		}
		return postBuilder.build();
	}

	public static Post buildPostForThreadId(ResultSet resultSet, PostController postController, int threadId) {
		PostBuilder postBuilder = new PostBuilder();
		try {
		postBuilder.setPostId(resultSet.getInt("Post_ID"));
		postBuilder.setUser(buildUser(resultSet, postController));
		postBuilder.setThreadId(threadId);
		postBuilder.setContent(resultSet.getString("Content"));
		postBuilder.setDate(new Date(resultSet.getTimestamp("Date").getTime()));
		} catch (SQLException e) {
			throw new IllegalStateException("SQL error while building Post: " + e);
		}
		return postBuilder.build();
	}

	public static Category buildCategory(ResultSet resultSet) {
		CategoryBuilder categoryBuilder = new CategoryBuilder();
		try {
		categoryBuilder.setId(resultSet.getInt("Category_ID"));
		categoryBuilder.setTitle(resultSet.getString("Title"));
		categoryBuilder.setSubtitle(resultSet.getString("Subtitle"));
		categoryBuilder.setOrderNumber(resultSet.getInt("OrderNumber"));
		} catch (SQLException e) {
			throw new IllegalStateException("SQL error while building Category: " + e);
		}
		return categoryBuilder.build();
	}

	public static int getPostsCount(ResultSet resultSet) {
		int count = 0;
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL error while getting posts count: " + e);
		}
		return count;
	}
}
