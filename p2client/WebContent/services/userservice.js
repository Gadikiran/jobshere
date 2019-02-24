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
	
	return userService;//returned to the UserCtrl,in userService object has 2 functions userRegistration,login
})
//5,7,11,15
