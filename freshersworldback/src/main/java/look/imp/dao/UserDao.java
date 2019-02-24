package look.imp.dao;

import look.imp.models.User;

public interface UserDao {
void userRegistration(User user);//Insert a record in User table

boolean isEmailUnique(String email);
boolean isPhonenumberUnique(String phonenumber);

User login(User user);

void updateUser(User validUser);
User getUser(String email);
}