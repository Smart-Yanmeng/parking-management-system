import {
	apiResquest
} from '@/utils/http.js'

//获取详细信息数据接口
export const publish = (query) => {
	console.log(query)
	return apiResquest({
		url: 'park/ParkShare',
		method: 'POST',
		header: {
			'content-type': 'application/x-www-form-urlencoded'
		},
		data: {
			commId: query.commId,
			parkAddr: query.parkAddr,
			belongBy: query.belongBy,
			spareTime: query.spareTime,
			status: 0,
			remark: query.remark,
			rent: query.rent
		}
	})

}