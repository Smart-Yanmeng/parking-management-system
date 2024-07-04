<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			uni.onTabBarMidButtonTap(() => {
						uni.navigateTo({
							url: "/pages/publish/publish",
							animationType: "slide-in-bottom",
							animationDuration: 400
						})
					})		
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
			// uni.stopLocationUpdate();

		},
		methods: {
		
		}
	}
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
	/*每个页面公共css */
</style>