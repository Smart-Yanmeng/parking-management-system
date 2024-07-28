<template>
	<view class="page-background">
		<u-navbar title="车位详情" :autoBack="true"></u-navbar>
		<view class="details-container">
			<u--text :text="'车位号:'+ this.parkDetail.parkAddr" class="detail-item" </u--text>
				<u--text :text="'车位位置:'+ this.addr" class="detail-item"></u--text>
				<u--text :text="'车位所在小区:'+ this.commName" class="detail-item"></u--text>
				<u--text :text="'车位开放时间:'+ this.commName" class="detail-item"></u--text>
				<view class="detail-item">
					<u--text text="空闲时段:" class="inline-text"></u--text>
					<view v-for="(time, day) in formattedSpareTime" :key="day">
						<u--text :text="`${day}: ${time.start} - ${time.end}`" class="spare-time"></u--text>
					</view>
				</view>
		</view>
		<view class="details-container">
			<view class="detail-item horizontal">
				<u--text text="停车时长:" class="inline-text"></u--text>
				<u-number-box v-model="parktime" @change="valChange" class="number-box"></u-number-box>
			</view>

		</view>
	</view>
</template>

<script>
	import {
		getParkDetail
	} from '../../apis/park.js'
	export default {
		data() {
			return {
				parkId: Number(),
				parktime: Number(),
				commName: String(),
				addr: String(),
				parkDetail: Object(),
				formattedSpareTime: []
			}
		},
		onLoad(option) {
			console.log(option)
			this.parkId = option.parkId
			this.parktime = option.parktime
			this.commName = option.commName
			this.addr = option.addr
			this.getDetial()
		},
		methods: {
			getDetial() {
				getParkDetail(this.parkId).then(res => {
					this.parkDetail = res.data
					const spareTime  = JSON.parse(this.parkDetail.spareTime)
					this.formatSpareTime(spareTime)
				})
			},
			valChange(e) {
				this.parktime = e.value
			},
			formatSpareTime(spareTime) {
				const dayMap = {
					"Mon": "周一",
					"Tues": "周二",
					"Wed": "周三",
					"Thurs": "周四",
					"Fri": "周五",
					"Sat": "周六",
					"Sun": "周日"
				};
				const formattedSpareTime = {};

				for (const day in spareTime) {
					if (spareTime.hasOwnProperty(day)) {
						formattedSpareTime[dayMap[day]] = spareTime[day];
					}
				}
				this.formattedSpareTime = formattedSpareTime;
				console.log(this.formattedSpareTime)
			}
		}
	}
</script>

<style scoped>
	.page-background {
		background-color: #f8f8f8;
		min-height: 100vh;
	}

	.details-container {
		padding: 20px;
		background-color: #fff;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		max-width: 600rpx;
		margin: 70px auto;
	}

	.detail-item {
		margin-bottom: 10px;
		font-size: 16px;
		color: #333;
	}

	.horizontal {
		display: flex;
		align-items: center;
	}

	.inline-text {
		margin-right: 10px;
	}

	.number-box {
		flex-shrink: 0;
	}

	.spare-time {
		margin-top: 5px;
		font-size: 14px;
		color: #555;
	}
</style>