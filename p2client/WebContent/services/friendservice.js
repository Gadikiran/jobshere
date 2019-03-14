/**
 * FriendService
 */
app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:8082/p2middleware"
		friendService.getAllSuggestedUsers=function()
		{
		return $http.get(BASE_URL+"/suggestedusers")
		}
	friendService.addFriend=function(toId){
		return $http.post(BASE_URL+"/friendrequest",toId)
		}
	friendService.getAllPendingRequests=function(){
		return $http.get(BASE_URL+"/pendingrequests")
		}
		friendService.acceptFriendRequest=function(request){
			alert("Friend Request Accepted")
		return $http.put(BASE_URL+"/acceptfriendrequest",request)
		}
		friendService.deleteFriendRequest=function(request){
			alert("Friend Request Deleted")
		return $http.put(BASE_URL+"/deletefriendrequest",request)
		}
		
	    friendService.getAllFriends=function(){
	    	return $http.get(BASE_URL + "/listoffriends")
	    }
	return friendService;
})