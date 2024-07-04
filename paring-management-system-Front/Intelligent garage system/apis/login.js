import { apiResquest } from '@/utils/http.js'

//登录接口
export const login = (query) => {
	return apiResquest({
		url: 'user/login',
		method: 'POST',
		header:{'content-type':'application/x-www-form-urlencoded'},
		data:{
			username:query.username,
			password:query.password
		},
		
	})
	
}