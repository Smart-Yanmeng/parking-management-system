<template>
	<view class="SharePark">
		<u-navbar title="共享车位" :autoBack="true" rightText="保存" @rightClick="publishMypark"></u-navbar>
		<view class="content">
			<view class="imageUpdate">
				<label class="remark-label">我的小区：</label>
				<u-cell-group>
					<u-cell :title="myComm" :isLink="true" arrow-direction="down" @click="popup"></u-cell>
				</u-cell-group>
				<label class="remark-label">我的车位：</label>
				<u--input placeholder="请输入内容" border="bottom" clearable v-model="myParkaddr"></u--input>
				<label class="remark-label">备注(可选)：</label>
				<u-textarea v-model="remark" placeholder="请输入内容" border="bottom"></u-textarea>
				<label class="remark-label">上传车位图片(可选)：</label>
				<u-upload :fileList="fileList1" @afterRead="afterRead" @delete="deletePic" name="1" multiple
					:maxCount="10"></u-upload>
			</view>
			<view class="priceUpdate">
				<u-cell-group>
					<u-cell title="价格" :isLink="true" :clickable="true" arrow-direction="right" @click="inputout"
						:value="'￥'+rent"></u-cell>
					<u-cell title="发布时间" :isLink="true" :clickable="true" arrow-direction="right" @click="pickTime"
						:value="publishDate"></u-cell>
				</u-cell-group>
			</view>
		</view>
		<u-popup :show="show" @close="close" @open="open" mode="bottom">
			<view class="selectPark">
				<u-search placeholder="请输入小区名称" v-model="keyword" @change="searchChange"></u-search>
				<scroll-view>
					<u-cell-group>
						<u-cell :title="item.commName" v-for="(item,index) in searchData " :key="index"
							@click="selectMyComm(item.commName,item.commId)"></u-cell>
					</u-cell-group>
				</scroll-view>
				<u-empty mode="search" :show="emptyShow">
				</u-empty>
			</view>
		</u-popup>
		<u-popup :show="StartDateShow" @close="pickTimeclose" @open="open" mode="bottom">
			<u-cell-group>
				<u-cell :title="item.name" :isLink="true" arrow-direction="right" :value="item.start+'-'+item.end"
					:clickable="true" v-for="(item,index) in DateTime " :key="index"
					@click="comfirmStartTime(item.id)"></u-cell>
			</u-cell-group>
		</u-popup>
		<u-datetime-picker title="开始时间-结束时间" :show="pickTimeShow" v-model="value1" mode="time" @cancel="timeclose"
			@confirm="comfirmTime" :maxMinute="23"></u-datetime-picker>

		<u-popup :show="inputShow" @close="close" @open="open" mode="bottom">
			<label class="remark-label">价格：</label>
			<u--input id="myButton" placeholder="￥0.00" border="surround" v-model="rent" :focus="true"></u--input>
		</u-popup>
	</view>
</template>

<script>
	import {
		searchComm
	} from '../../apis/park.js'
	import {
		publish
	} from '../../apis/publish.js'
	export default {
		data() {
			return {
				fileList1: [],
				remark: "",
				show: false,
				keyword: '',
				emptyShow: true,
				searchData: [],
				myComm: "选择小区",
				mycommId: 0,
				StartDateShow: false,
				DateTime: [{
						id: 0,
						name: '周一',
						start: "0",
						end: "0",
						Name: "Mon"
					},
					{
						id: 1,
						name: '周二',
						start: "0",
						end: "0",
						Name: "Tues"
					},
					{
						id: 2,
						name: '周三',
						start: "0",
						end: "0",
						Name: "Wed"
					},
					{
						id: 3,
						name: '周四',
						start: "0",
						end: "0",
						Name: "Thurs"
					},
					{
						id: 4,
						name: '周五',
						start: "0",
						end: "0",
						Name: "Fri"
					},
					{
						id: 5,
						name: '周六',
						start: "0",
						end: "0",
						Name: "Sat"
					},
					{
						id: 6,
						name: '周日',
						start: "0",
						end: "0",
						Name: "Sun"
					}
				],
				pickTimeShow: false,
				value1: Number(),
				timeId: Number(),
				publishDate: "",
				daysAdded: [], // 新增一个数组来追踪已添加的天数
				rent: 0.00,
				inputShow: false,
				myParkaddr: "",
			};
		},
		methods: {
			// 删除图片
			deletePic(event) {
				this[`fileList${event.name}`].splice(event.index, 1);
			},
			// 新增图片
			async afterRead(event) {
				// 当设置 multiple 为 true 时, file 为数组格式，否则为对象格式
				let lists = [].concat(event.file);
				let fileListLen = this[`fileList${event.name}`].length;
				lists.map((item) => {
					this[`fileList${event.name}`].push({
						...item,
						status: "uploading",
						message: "上传中",
					});
				});
				for (let i = 0; i < lists.length; i++) {
					const result = await this.uploadFilePromise(lists[i].url);
					let item = this[`fileList${event.name}`][fileListLen];
					this[`fileList${event.name}`].splice(
						fileListLen,
						1,
						Object.assign(item, {
							status: "success",
							message: "",
							url: result,
						})
					);
					fileListLen++;
				}
			},
			uploadFilePromise(url) {
				return new Promise((resolve, reject) => {
					let a = uni.uploadFile({
						url: "http://192.168.2.21:7001/upload", // 仅为示例，非真实的接口地址
						filePath: url,
						name: "file",
						formData: {
							user: "test",
						},
						success: (res) => {
							setTimeout(() => {
								resolve(res.data.data);
							}, 1000);
						},
					});
				});
			},
			popup() {
				this.show = true
			},
			open() {
				// console.log('open');
			},
			close() {
				this.show = false
				this.inputShow = false
				// console.log('close');
			},
			async searchChange() {
				console.log(this.keyword)
				await searchComm(this.keyword).then(res => {
					console.log(res)
					if (res.data != "") {
						this.emptyShow = false
					} else {
						this.searchData = []
						this.emptyShow = true
					}
					this.searchData = res.data

				})

			},
			selectMyComm(CommName, commId) {
				this.myComm = CommName
				this.mycommId = commId
				// console.log(this.mycommId)
				this.show = false
			},
			pickTime() {
				this.StartDateShow = true
			},
			comfirmStartTime(id) {
				this.timeId = id
				this.pickTimeShow = true
			},
			comfirmTime(e) {
				console.log(e.value)
				// 将时间字符串拆分为小时和分钟
				let [hours1, hours2] = e.value.split(':');
				this.DateTime[this.timeId].start = hours1 + ":00"
				this.DateTime[this.timeId].end = hours2 + ":00"
				this.pickTimeShow = false
			},
			timeclose() {
				this.pickTimeShow = false
			},
			pickTimeclose() {
				this.StartDateShow = false
				this.publishDate = ""; // 确保每次关闭时间选择器时都从空字符串开始
				this.daysAdded = []; // 重置已添加的天数数组
				this.DateTime.forEach(item => {
					if (item.start != item.end && !this.daysAdded.includes(item.name)) {
						this.publishDate = this.publishDate + item.name + ' ';
						this.daysAdded.push(item.name); // 将已添加的天数记录下来
					}
				})
			},
			inputout() {
				this.inputShow = true
			},
			publishMypark() {
				let publishData = {
					commId: this.mycommId,
					parkAddr: this.myParkaddr,
					belongBy: 1, //归属业主 userId
					spareTime: JSON.stringify(this.transformDateTime(this.DateTime)),
					status: 0,
					remark: this.remark,
					rent: this.rent
				}
				console.log(publishData)
				publish(publishData).then(res => {
					console.log(res)
					if (res.code == 200) {
						uni.showToast({
							title:"保存成功"
						})
					}


				})
			},
			// 转换数据格式的函数
			transformDateTime(data) {
				let transformedData = {};
				data.forEach(item => {
					if (item.start !== "0" || item.end !== "0") {
						transformedData[item.Name] = {
							start: item.start,
							end: item.end
						};
					}
				});
				return transformedData;
			},



		},
	};
</script>

<style>
	.SharePark {
		background: #f3f4f6;
		height: 100vh;
		display: flex;
		flex-direction: column;
	}

	.content {
		flex: 1;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px;
	}

	.imageUpdate {
		background-color: white;
		border-radius: 15rpx;
		width: 95%;
		max-width: 600px;
		padding: 20px;
		margin-top: 40px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.remark-label {
		display: block;
		margin-top: 10px;
		font-weight: bold;
		font-size: 16px;
		margin-bottom: 15rpx;
	}

	u-textarea {
		margin-top: 10px;
		width: 100%;
	}

	.priceUpdate {
		background-color: white;
		border-radius: 15rpx;
		width: 95%;
		max-width: 600px;
		padding: 20px;
		margin-top: 20px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	u-upload {
		margin-top: 10px;
	}

	.selectPark {
		width: 100%;
		height: 60vh;
		padding-top: 30rpx;
	}
</style>