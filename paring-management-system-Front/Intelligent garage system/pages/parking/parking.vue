<template>
	<view>
		<map id="map" class="map" style="width: 750rpx; height: 700rpx;" :latitude="latitude" :longitude="longitude"
			:markers="covers" :enable-3D="true" :enable-overlooking="true" :enable-rotate="true" :controls="controls"
			@controltap="backHome">
		</map>
		<u-cell-group>
			<u-cell :title="item.commName" :label="item.addr" v-for="(item,index) in commVoList " :key="index"
				:value="'剩余车位：'+item.count"
				@click="ToParingPage(item.commId,item.commName,item.addr,item.geoX,item.geoY)"
				:clickable="true"></u-cell>
		</u-cell-group>
		<u-button class="point-button" type="primary" @click="showPoint">{{pointButton}}</u-button>
	</view>
</template>

<script>
	import {
		park
	} from '../../apis/park.js'
	export default {
		data() {
			return {
				includePoints: [],
				covers: [],
				commVoList: [],
				latitude: 0,
				longitude: 0,
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
				pointButton: "显示目标位置",
				parkingtime: Number()
			}
		},
		async onLoad(option) {
			console.log(option, 'parkingtime')
			this.parkingtime = option.parkingTime
			await this.getMyLocation()
		},
		methods: {
			getMyLocation() {
				uni.getLocation({
					type: 'wgs84', // 坐标类型，默认为wgs84，可选的值为gcj02和bd09ll
					isHighAccuracy: true,
					highAccuracyExpireTime: 5000,
					accuracy: "best",
					success: (res) => {
						this.latitude = res.latitude
						this.longitude = res.longitude
						this.Parking()
					}
				})
			},
			async Parking() {
				let ParkData = {
					x: this.longitude,
					y: this.latitude,
					parkTime: this.parkingtime
				}
				console.log(ParkData, '345')
				await park(ParkData).then(res => {
					console.log(res.data.commVoList, '123')
					this.commVoList = res.data.commVoList
					this.covers = [{
						id: 0,
						width: 25,
						height: 25,
						longitude: this.longitude,
						latitude: this.latitude,
						iconPath: '../../static/location2.png',
					}]
					let includePoint1 = {
						longitude: this.latitude,
						latitude: this.longitude,
					}
					this.includePoints.push(includePoint1)
				})
				// uni.navigateTo({
				// 	url: '/pages/parking/parking'
				// })
			},
			backHome(e) {
				console.log(e, '456')
				uni.reLaunch({
					url: '/pages/Home/Home'
				})
			},
			showPoint() {
				if (this.pointButton = "显示目标位置") {
					this.commVoList.forEach(item => {
						let cover = {
							id: item.commId,
							width: 25,
							height: 25,
							longitude: item.geoX,
							latitude: item.geoY,
							iconPath: '../../static/location.png',
							callout: {
								content: item.addr,
								fontSize: 14,
								borderRadius: 10,
								textAlign: "center",
								display: 'ALWAYS'
							}
						}
						let includePoint = {
							longitude: item.geoX,
							latitude: item.geoY,
						}
						this.includePoints.push(includePoint)
						this.covers.push(cover)
						// console.log(this.includePoints, this.covers)
					})
					this.pointButton = "关闭目标位置"
				} else {
					this.pointButton = "显示目标位置"
					this.includePoints.length = 0
					this.covers.length = 0
				}

			},
			ToParingPage(commId, commName, addr, geoX, geoY) {
				uni.navigateTo({
					url: `/pages/parking/parkinglot?commId=${commId}&commName=${commName}&addr=${addr}&geoX=${geoX}&geoY=${geoY}&parktime=${this.parkingtime}`
				})
			}
		}
	}
</script>

<style>
	.point-button {
		background-color: #007BFF;
		color: #fff;
		border-radius: 20rpx;
		padding: 10rpx 30rpx;
		font-size: 32rpx;
		margin-top: 50rpx;
		width: 80%;
	}
</style>