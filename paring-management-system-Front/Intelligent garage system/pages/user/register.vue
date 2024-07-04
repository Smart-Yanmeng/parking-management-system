<template>
	<view class="register-content">
		<view class="register-part">
			<view class="register-header">
				<text class="register-title">欢迎注册智能车库系统</text>
			</view>
			<u-form :model="form" ref="uForm">
				<u-form-item label="手机" prop="phone">
					<u-input v-model="form.phone" placeholder="请输入手机号" />
				</u-form-item>
				<u-form-item label="密码" prop="password">
					<u-input v-model="form.password" placeholder="请输入密码" type="password" :password-icon="true" />
				</u-form-item>
			</u-form>
			<u-button type="primary" @click="submit" class="register-button">注册</u-button>
		</view>
		<view class="to-login">
			<text @click="ToLogin" class="login-text">立即登录</text>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import {
		register
	} from '../../apis/register';
	export default {
		data() {
			return {
				form: {
					phone: '',
					password: ''
				},
				rules: {
					phone: [{
						required: true,
						message: '请输入手机号',
						trigger: ['change', 'blur'],
					}, {
						validator: (rule, value, callback) => {
							return this.$u.test.mobile(value);
						},
						message: '手机号码不正确',
						trigger: ['change', 'blur'],
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: ['change', 'blur'],
					}]
				}
			};
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			submit() {
				var lastFourDigits = this.form.phone.slice(-4);
				const registerData = {
					username: this.form.phone,
					password: this.form.password,
					lastFourDigits: lastFourDigits
				};
				console.log(registerData.lastFourDigits);
				register(registerData).then(res => {
					console.log(res);
					console.log(res.data.msg);
				});
			},
			ToLogin() {
				uni.reLaunch({
					url: '/pages/user/login'
				});
			}
		}
	};
</script>

<style>
	.register-content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		height: 100vh;
		background-color: #f3f4f6;
		padding: 20rpx;
	}

	.register-header {
		margin-bottom: 20rpx;
	}

	.register-title {
		font-size: 36rpx;
		font-weight: bold;
		color: #007BFF;
		text-align: center;
	}

	.register-part {
		background-color: #fff;
		border-radius: 20rpx;
		padding: 30rpx;
		width: 80%;
		box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
	}

	.register-button {
		width: 100%;
		margin-top: 20rpx;
	}

	.to-login {
		margin-top: 20rpx;
	}

	.login-text {
		color: #007BFF;
		text-align: center;
		font-size: 28rpx;
	}
</style>