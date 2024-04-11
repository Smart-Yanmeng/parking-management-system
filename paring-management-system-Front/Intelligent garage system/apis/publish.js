import { apiResquest } from '@/utils/http.js'

//获取详细信息数据接口
export const detail = (query) => {
	return apiResquest({
		url: 'dynamic/detail/' + query,
		method: 'POST',
		header:{'content-type':'application/x-www-form-urlencoded'},
	})
	
}
