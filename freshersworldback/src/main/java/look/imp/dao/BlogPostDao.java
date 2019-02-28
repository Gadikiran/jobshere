package look.imp.dao;

import java.util.List;

import look.imp.models.BlogPost;
public interface BlogPostDao {
void addBlogPost(BlogPost blogPost);
List<BlogPost> getBlogsApproved();
List<BlogPost> getBlogsWaitingForApproval();
}
