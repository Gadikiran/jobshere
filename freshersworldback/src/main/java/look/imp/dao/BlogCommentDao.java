package look.imp.dao;

import java.util.List;

import look.imp.models.BlogComment;

public interface BlogCommentDao {
void addBlogComment(BlogComment blogComment);
List<BlogComment> getAllBlogComments(int blogPostId);
}

