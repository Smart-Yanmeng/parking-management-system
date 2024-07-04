<template>
  <view class="background">
    <view class="user-background">
      <u-avatar class="user-avatar" :src="src" size="100"></u-avatar>  
    </view>
    <view class="order-list">
      <u-grid :col="4">
        <u-grid-item @click="ToOrder">
          <u-icon name="photo" :size="46"></u-icon>
          <view class="grid-text">我的订单</view>
        </u-grid-item>
        <u-grid-item>
          <u-icon name="lock" :size="46"></u-icon>
          <view class="grid-text">车位管理</view>
        </u-grid-item>
        <u-grid-item>
          <u-icon name="hourglass" :size="46"></u-icon>
          <view class="grid-text">进行中</view>
        </u-grid-item>
        <u-grid-item>
          <u-icon name="hourglass" :size="46"></u-icon>
          <view class="grid-text">待评价</view>
        </u-grid-item>
      </u-grid>
    </view>
    <view class="button-container">
      <u-button @click="ToLogin" class="login-button">返回登录</u-button>
    </view>
  </view>
</template>

<script>
  export default {
    data() {
      return {
        src: 'path-to-your-avatar-image' // 替换为实际头像图片路径
      }
    },
    methods: {
      ToLogin() {
        uni.clearStorage();
        uni.navigateTo({
          url: '/pages/user/login'
        });
      },
      ToOrder() {
        uni.navigateTo({
          url: '/pages/user/order'
        });
      }
    }
  }
</script>

<style>
.user-background {
  background-image: url('../../static/background.jpeg');
  background-size: cover;
  background-repeat: no-repeat;
  height: 30vh;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.user-avatar {
  margin-top: 20rpx;
  border: 4rpx solid #fff;
  border-radius: 50%;
  box-shadow: 0 4rpx 8rpx rgba(0, 0, 0, 0.3);
}

.background {
  background-color: #f3f4f6;
  min-height: 100vh;
  padding: 20rpx;
}

.order-list {
  background-color: #fff;
  border-radius: 20rpx;
  margin-top: -50rpx;
  padding: 20rpx;
  box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
}

.grid-text {
  margin-top: 8rpx;
  font-size: 28rpx;
  color: #333;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20rpx;
}

.login-button {
  background-color: #007BFF;
  color: #fff;
  border-radius: 20rpx;
  padding: 10rpx 30rpx;
  font-size: 32rpx;
}
</style>
