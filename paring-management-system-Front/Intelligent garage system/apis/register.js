import { apiResquest } from '@/utils/http.js'

//登录接口
export const register = (query) => {
	return apiResquest({
		url: 'user/register',
		method: 'POST',
		header:{'content-type':'application/x-www-form-urlencoded'},
		data:{
			commId:1,
			username:query.username,
			password:query.password,
			userType:1,
			nickName:"车主"+query.lastFourDigits,
			sex:0
		},
		
	})
	
}