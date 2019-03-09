package look.imp.dao;

import java.util.List;

import look.imp.models.Friend;
import look.imp.models.User;

public interface FriendDao {
List<User>  getAllSuggestedUsers(String email);
void   addFriend(Friend friend);//toId-> client,fromId-> loggedIn user,status->P
List<Friend> getPendingRequests(String email);//email of the loggedin user
void acceptRequest(Friend pendingRequest);
void deleteRequest(Friend pendingRequest);
List<User> listOfFriends(String email);
}

