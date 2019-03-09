package look.imp.dao;

import java.util.List;

import look.imp.models.Notification;

public interface NotificationDao {
	//insert into notification values (...)
	void addNotification(Notification notification);
	
	//select * from notification where userToBeNotified.email=? and viewed=0
	List<Notification> getAllNotificationsNotViewed(String email);//glyphicon globe
	
	//select * from notification where notificationId=?
	Notification getNotification(int notificationId);
	
	//update notification set viewed=1 where notificationId=?
	void updateNotificactionViewedStatus(int notificationId);
}
