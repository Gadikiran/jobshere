package look.imp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import look.imp.dao.UserDao;
import look.imp.models.ErrorClazz;
import look.imp.models.User;
@RestController //@ResponseBody + @Controller
public class UserController {
	@Autowired
private UserDao userDao;

	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public  ResponseEntity<?>    userRegistration(@RequestBody User user ){
		
		//read the data from the request body
		//check if email is unique
		if(!userDao.isEmailUnique(user.getEmail())){//if email is not unique
			ErrorClazz errorClazz=new ErrorClazz(2,"Email already exists.. pls choose different email id");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(!userDao.isPhonenumberUnique(user.getPhonenumber())){
			ErrorClazz errorClazz=new ErrorClazz(1,"Phone number already exists.. pls enter another phonenumber");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(user.getRole()=="" || user.getRole()==null){
			ErrorClazz errorClazz=new ErrorClazz(4,"Role cannot be null..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try{
		userDao.userRegistration(user);
		}catch(Exception e){
			ErrorClazz errorClazz=new ErrorClazz(3,"Unable to register user datails.."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	 @RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){//user in json fmt,{'email':'...','password':'...'}
		User validUser= userDao.login(user);
		if(validUser==null){//invalid credentials
			ErrorClazz errorClazz=new ErrorClazz(5,"Email/password is incorrect..Please enter valid credentials...");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		else{//valid credentials
			System.out.println("Session Id is " +session.getId());
			System.out.println("Session createdTime " + session.getCreationTime());
			validUser.setOnline(true);
			//Update the data in the table.
			userDao.updateUser(validUser);
			session.setAttribute("loginId",user.getEmail());//Adding an attribute in HttpSession only in login method
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	 @RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		 //get email of the user who has logged in
		//update online status to false
		//remove session attribute loginId
		//invalidate the http session object
		//return success 
		 String email=(String)session.getAttribute("loginId");
		 if(email==null){
			 ErrorClazz errorClazz=new ErrorClazz(5,"Please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//401 - login.html
		 }
		 //for updating online status, get user object
		User user= userDao.getUser(email);
		 user.setOnline(false);
		 userDao.updateUser(user);
		 
		 //remove the session attribute loginId
		 session.removeAttribute("loginId");
		 session.invalidate();
		 return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
