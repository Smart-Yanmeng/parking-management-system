<template>
	<view>
		<u-button @click="ToLogin">返回登录</u-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			ToLogin()
			{
				uni.navigateTo({
					url:'/pages/login/login'
				})
			}
		}
	}
</script>

<style>

</style>
