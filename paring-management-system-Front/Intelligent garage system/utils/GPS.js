/*
 * 打开[ios/安卓]GPS定位权限
 */
export function openGps() {
	let system = uni.getSystemInfoSync(); // 获取系统信息
	if (system.platform === 'android') { // 判断平台
		var context = plus.android.importClass("android.content.Context");
		var locationManager = plus.android.importClass("android.location.LocationManager");
		var main = plus.android.runtimeMainActivity();
		var mainSvr = main.getSystemService(context.LOCATION_SERVICE);
		if (!mainSvr.isProviderEnabled(locationManager.GPS_PROVIDER)) {
			uni.showModal({
				title: '提示',
				content: '请打开定位服务功能',
				showCancel: false, // 不显示取消按钮
				success() {
					var Intent = plus.android.importClass('android.content.Intent');
					var Settings = plus.android.importClass('android.provider.Settings');
					var intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					main.startActivity(intent); // 打开系统设置GPS服务页面

				}
			});
		}
	} else if (system.platform === 'ios') {
		var cllocationManger = plus.ios.import("CLLocationManager");
		var enable = cllocationManger.locationServicesEnabled();
		var status = cllocationManger.authorizationStatus();
		plus.ios.deleteObject(cllocationManger);
		console.log("手机系统的定位没有打开");
		uni.showModal({
			title: '提示',
			content: '请打开定位服务功能',
			showCancel: false, // 不显示取消按钮
			success() {
				var UIApplication = plus.ios.import("UIApplication");
				var application2 = UIApplication.sharedApplication();
				var NSURL2 = plus.ios.import("NSURL");
				var setting2 = NSURL2.URLWithString("App-Prefs:root=Privacy&path=LOCATION");
				application2.openURL(setting2);
				plus.ios.deleteObject(setting2);
				plus.ios.deleteObject(NSURL2);
				plus.ios.deleteObject(application2);
			}
		});
	}
}
