<script>
	export default {
		globalData: {
			latitude: 0,
			longitude: 0,
		},
		onLaunch: function() {
			console.log('App Launch')
			
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