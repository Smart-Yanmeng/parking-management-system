<template>
  <view class="login-content">
    <view class="login-part">
      <view class="login-header">
        <text class="login-title">欢迎登录智能车库系统</text>
      </view>
      <u-form :model="form" ref="uForm">
        <u-form-item label="手机" prop="phone">
          <u-input v-model="form.phone" placeholder="请输入手机号" />
        </u-form-item>
        <u-form-item label="密码" prop="password">
          <u-input v-model="form.password" placeholder="请输入密码" type="password" :password-icon="true" />
        </u-form-item>
      </u-form>
      <u-button type="primary" @click="submit" class="login-button">登录</u-button>
    </view>
    <view class="register-part">
      <text @click="ToRegister" class="register-text">立即注册</text>
    </view>
  </view>
</template>

<script>
import { login } from '../../apis/login';
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
  onLoad() {},
  onReady() {
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    submit() {
      const loginData = {
        username: this.form.phone,
        password: this.form.password
      };
      login(loginData).then(res => {
        const token = res.data;
        uni.setStorage({
          key: 'token',
          data: token,
          success: function() {
            console.log('Token 存储成功');
          },
          fail: function(err) {
            console.error('Token 存储失败', err);
          }
        });
        uni.reLaunch({
          url: "/pages/user/user"
        });
      })
      .catch(error => {
        console.error('登录失败', error);
      });
    },
    ToRegister() {
      uni.navigateTo({
        url: '/pages/user/register'
      });
    }
  }
};
</script>

<style>
.login-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f3f4f6;
  padding: 20rpx;
}

.login-header {
  margin-bottom: 20rpx;
}

.login-title {
  font-size: 36rpx;
  font-weight: bold;
  color: #007BFF;
  text-align: center;
}

.login-part {
  background-color: #fff;
  border-radius: 20rpx;
  padding: 30rpx;
  width: 80%;
  box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
}

.login-button {
  width: 100%;
  margin-top: 20rpx;
}

.register-part {
  margin-top: 20rpx;
}

.register-text {
  color: #007BFF;
  text-align: center;
  font-size: 28rpx;
}
</style>