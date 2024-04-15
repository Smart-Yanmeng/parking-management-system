<template>
	<view>
		<view class="loginPart">
			<u-form :model="form" ref="uForm">
				<u-form-item label="手机" prop="phone">
					<u-input v-model="form.phone" placeholder="请输入手机号" />
				</u-form-item>
				<u-form-item label="密码" placeholder="请输入密码">
					<u-input v-model="form.password" placeholder="请输入密码" type="password" :password-icon="true" />
				</u-form-item>
			</u-form>
			<u-button type="primary" @click="submit">注册</u-button>
		</view>
	</view>
</template>

<script>
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
					},, {
						// 自定义验证函数，见上说明
						validator: (rule, value, callback) => {
							// 上面有说，返回true表示校验通过，返回false表示不通过
							// this.$u.test.mobile()就是返回true或者false的
							return this.$u.test.mobile(value);
						},
						message: '手机号码不正确',
						// 触发器可以同时用blur和change
						trigger: ['change', 'blur'],
					}]
				}
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			submit() {

			}
		}
	}
</script>

<style>

</style>