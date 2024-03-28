<template>
    <view class="content">
        <view class="top-contain">
			<image class="top-image" mode="aspectFit" src="../../static/logo.png"></image>
			<button @click="goFunctionClick">暂无</button>
		</view>
        
        <cc-wxMenuBtn leftTitle="我的报名" icon="../../static/my_enroll.png" @click="goFunctionClick(0)"></cc-wxMenuBtn>

    </view>
</template>

<script>

    export default {

        data() {
            return {
                title: 'Hello'
            }
        },

        methods: {
            goFunctionClick(flag){
				var url = 'http://10.177.13.153:8080/pages/my/my';
				var encodedUrl = encodeURIComponent(url);
				console.log(encodedUrl);
				window.location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa079e1c44eb5786b&'+
				'redirect_uri='+encodedUrl+'&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect';
				
            }
        }
    }
</script>

<style>
    .content {
        display: flex;
        flex-direction: column;
		background-color: whitesmoke;
    }
	.top-contain{
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		align-items: center;
		height: 400rpx;
		background-color: bisque;
		margin-bottom: 30rpx;
	}
	.top-image{
		width: 200rpx;
		height: 200rpx;
		border-radius: 50%;
	}
</style>