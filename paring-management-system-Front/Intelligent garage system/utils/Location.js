var location ={
	getLocation()
	{
		uni.getLocation({
			type: 'wgs84', // 坐标类型，默认为wgs84，可选的值为gcj02和bd09ll
			isHighAccuracy: true,
			highAccuracyExpireTime: 5000,
			accuracy: "best",
			success: res => {
				// 获取成功，经度和纬度在res.longitude和res.latitude中
				getApp().globalData.latitude = res.latitude
				getApp().globalData.longitude = res.longitude
				console.log('精度', res.latitude,res.longitude)
			
		// 		let params =
		// 			`origin=${res.longitude},${res.latitude}&destination=118.656892,24.872813&key=0abe4280e4085bada3679b1bb86d4a63`
		// 		console.log(params)
		
		// 		uni.request({
		// 			url: `https://restapi.amap.com/v3/direction/driving?l${params}`,
		// 			success: (res) => {
		// 				let data = res.data.route
		// 				if (data.paths && data.paths[0] && data.paths[0]
		// 					.steps) {
		// 					console.log(data.paths[0].steps)
		// 					washData(data.paths[0].steps)
		// 				}
		// 			}
		// 		});
		// 		let planList = []
		// 		const washData = (steps) => {
		// 			let points = []
		// 			steps.forEach(item => {
		// 				const polen = item.polyline.split(';')
		// 				polen.forEach(vv => {
		// 					let splits = vv.split(',')
		// 					points.push({
		// 						longitude: parseFloat(splits[
		// 							0]),
		// 						latitude: parseFloat(splits[1])
		// 					})
		// 				})
		// 			})
		
		// 			planList.value = [{
		// 				points: points,
		// 				width: 6,
		// 				arrowLine: true,
		// 				arrowIconPath: true,
		// 				color: '#4aa1f1',
		// 				dottedLine: true,
		// 				arrowLine: true,
		// 			}]
		// 			this.PlanList = planList.value
		// 			console.log(this.PlanList)
			// 	}
			},
			fail: err => {
				// 获取失败，err为错误信息
				console.log('getLocation err:', err);
			}
		});
	}
	
}

export default location;