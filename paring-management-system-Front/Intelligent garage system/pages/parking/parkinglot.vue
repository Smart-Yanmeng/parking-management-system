<template>
	<view>
		<map id="map" class="map" style="width: 750rpx; height: 700rpx;" :latitude="latitude" :longitude="longitude"
			:markers="covers" :enable-3D="true" :enable-overlooking="true" :enable-rotate="true" :controls="controls"
			@controltap="backHome">
		</map>
		<scroll-view class="park-scroll" :scroll-y="true">
			<u-cell-group>
				<u-cell :title="'车位号：'+item.parkAddr" :label="commName+' '+addr" v-for="(item,index) in parklist "
					:key="index" :value="'价格：'+'￥'+item.rent" :clickable="true"></u-cell>
			</u-cell-group>
		</scroll-view>
	</view>
</template>

<script>
	import {
		getPark
	} from '../../apis/park.js'
	export default {
		data() {
			return {
				commId: Number(),
				commName: String(),
				addr: String(),
				longitude: Number(),
				latitude: Number(),
				covers: [],
				controls: [{
					id: 1,
					position: {
						left: 10,
						top: 20,
						width: 25,
						height: 25
					},
					iconPath: '../../static/back.png',
					clickable: true
				}],
				parklist: [],
				parktime: Number(),
				PlanList: [],
				mylongitude: Number(),
				mylatitude: Number(),
			}
		},
		async onLoad(option) {
			console.log(option)
			this.commId = option.commId
			this.commName = option.commName
			this.addr = option.addr
			this.longitude = option.geoX
			this.latitude = option.geoY
			this.parktime = option.parktime
			console.log(this.longitude, this.latitude)
			this.covers = [{
				id: 0,
				width: 25,
				height: 25,
				longitude: this.longitude,
				latitude: this.latitude,
				iconPath: '../../static/location.png',
				callout: {
					content: this.addr,
					fontSize: 14,
					borderRadius: 10,
					textAlign: "center",
					display: 'ALWAYS'
				}
			}]
			await this.getMyLocation()
			this.getALLPark()
		},
		methods: {
			backHome() {
				uni.navigateBack()
			},
			getALLPark() {
				let parkdata = {
					commId: this.commId,
					parktime: this.parktime
				}
				getPark(parkdata).then(res => {
					this.parklist = res.data
				})
			},
			getLine() {
				let params =
					`origin=${this.mylongitude},${this.mylatitude}&destination=${this.longitude},${this.latitude}&key=0abe4280e4085bada3679b1bb86d4a63`
				console.log(params)
				uni.request({
					url: `https://restapi.amap.com/v3/direction/driving?${params}`,
					success: (res) => {
						let data = res.data.route
						if (data.paths && data.paths[0] && data.paths[0].steps) {
							console.log(data.paths[0].steps)
							washData(data.paths[0].steps)
						}
					}
				});
				let planList = []
				const washData = (steps) => {
					let points = []
					steps.forEach(item => {
						const polen = item.polyline.split(';')
						polen.forEach(vv => {
							let splits = vv.split(',')
							points.push({
								longitude: parseFloat(splits[0]),
								latitude: parseFloat(splits[1])
							})
						})
					})

					planList.value = [{
						points: points,
						width: 6,
						arrowLine: true,
						arrowIconPath: true,
						color: '#4aa1f1',
						dottedLine: true,
						arrowLine: true,
					}]
					this.PlanList = planList.value
					console.log(this.PlanList)
				}
			},
			getMyLocation() {
				uni.getLocation({
					type: 'wgs84', // 坐标类型，默认为wgs84，可选的值为gcj02和bd09ll
					isHighAccuracy: true,
					highAccuracyExpireTime: 5000,
					accuracy: "best",
					success: (res) => {
						this.mylatitude = res.latitude
						this.mylongitude = res.longitude
						let cover = {
							id: 2,
							width: 25,
							height: 25,
							longitude: this.mylongitude,
							latitude: this.mylatitude,
							iconPath: '../../static/location2.png',
						}
						this.covers.push(cover)
						this.getLine()
					}
				})
			},

		}
	}
</script>

<style>
	.park-scroll {
		height: 900rpx;
	}
</style>