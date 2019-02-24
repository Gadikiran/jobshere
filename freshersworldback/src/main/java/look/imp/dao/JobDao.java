package look.imp.dao;

import java.util.List;

import look.imp.models.Job;

public interface JobDao {
void addJob(Job job);

List<Job> getAllJobs();
}
