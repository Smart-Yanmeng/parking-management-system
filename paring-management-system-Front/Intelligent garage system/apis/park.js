import {
	apiResquest
} from '@/utils/http.js'

//停车接口
export const park = (query) => {
	console.log(query, 123)
	return apiResquest({
		url: 'park/park',
		method: 'POST',
		header: {
			'content-type': 'application/x-www-form-urlencoded'
		},
		data: {
			x: query.x,
			y: query.y,
			parkTime: query.parkTime
		},

	})

}

//搜索小区
export const searchComm = (query) => {

	return apiResquest({
		url: `park/getComm?key=${encodeURIComponent(query)}`,
		method: 'GET',
		header: {
			'content-type': 'application/x-www-form-urlencoded'
		},
	})

}

//获取小区所有停车位
export const getPark = (query) => {
	return apiResquest({
		url: `park/getPark?commId=${query.commId}&hour=${query.parktime}`,
		method: 'GET',
		header: {
			'content-type': 'application/x-www-form-urlencoded'
		},
	})

}