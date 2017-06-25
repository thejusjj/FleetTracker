angular
    .module('inspinia')
    .factory('httpService', ['$http', '$q', function ($http, $q) {
    return {
       
        httpGet:function (urlString, qParams, callbackFunction) {
            $http({
                      method:'GET',
                      url:urlString,
                      params:qParams
                  }).success(
                    function (responseData, status) {
                        callbackFunction(responseData, status);
                    }).error(function (responseData, status) {
                                 callbackFunction(responseData, status);
                             });
        },
        httpPost:function (urlString, qParams, payload, callbackFunction) {
            $http({
                      method:'POST',
                      url:urlString,
                      params:qParams,
                      data:payload,
                      headers:{ 'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'},
                       transformRequest:function (data) {
                       return jQuery.param(data);
                       }
                  }).success(
                    function (responseData, status) {
                        callbackFunction(responseData, status);
                    }).error(function (responseData, status) {
                                 callbackFunction(responseData, status);
                             });
        },
        httpPostDTO:function (urlString, qParams, payload, callbackFunction) {
            $http({
                      method:'POST',
                      url:urlString,
                      params:qParams,
                      data:payload
                  }).success(
                    function (responseData, status) {
                        callbackFunction(responseData, status);

                    }).error(function (responseData, status) {
                         callbackFunction(responseData, status);
                   });
	        }
	    };
	}])
	
	.factory('UserService', ['httpService', '$rootScope', function (httpService, $rootScope) {
	    return {
	        login: function (userName, password, callbackFunction) {
	            var qParams = {
	            		userName: userName,
	            		password: password
	            };
	            httpService.httpGet("rest/trip/login", qParams, callbackFunction);
	        },
	        
	        getUserPosition: function (mobileNumber, callbackFunction) {
	            var qParams = {
	            		mobileNumber: mobileNumber
	            };
	            httpService.httpGet("rest/trip/getUserPosition", qParams, callbackFunction);
	        }
	    };
	}]);