/**
 * UserService
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.userRegistration=function(user){
	return	$http.post("http://localhost:8081/p2middleware/registration",user)
	}
	
	userService.login=function(user){// {'email':'...','password':'...'}
     return $http.post("http://localhost:8081/p2middleware/login",user)
	}
	
	userService.logout=function(){
		return $http.put("http://localhost:8081/p2middleware/logout")
	}
	
	userService.getUser=function(){
		return $http.get("http://localhost:8081/p2middleware/getuser")
	}
	
	userService.updateUserProfile=function(user){
		//user is updated user details from controller
		return $http.put("http://localhost:8081/p2middleware/updateuserdetails",user)
	}
	
	
	return userService;//returned to the UserCtrl,in userService object has 2 functions userRegistration,login
})
//5,7,11,15,19
