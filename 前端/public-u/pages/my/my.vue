<template>
    <view class="content">
        <view class="top-contain">
			<image class="top-image" mode="aspectFit" src="../../static/logo.png"></image>
			<view >暂无</view>
		</view>
		
        <cc-wxMenuBtn leftTitle="我的发布" icon="../../static/my_enroll.png" @click="toMyPublishClick"></cc-wxMenuBtn>
		<cc-wxMenuBtn leftTitle="注意事项" icon="../../static/my_enroll.png" @click="helpClick"></cc-wxMenuBtn>

    </view>
</template>

<script>

    export default {

        data() {
            return {
                
            }
        },

        methods: {
			
			// 跳转到“我的发布”
            toMyPublishClick(){
				uni.navigateTo({
					url:'/pages/my/mypublish'
				})
				
            },
			// 跳转到注意事项
			helpClick(){
				uni.showModal({
					title:"提示",
					content:"发布后暂时不要清除缓存，否则会丢失已发布的数据"
				})
			},
			
			/**
			 * @title 微信快捷登录，暂不使用 
			 * @desc 通过后端发送请求。在同意获取信息后跳转到url中
			 * 
			 */
			wxLogin(){
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
		width: 170rpx;
		height: 170rpx;
		border-radius: 50%;
	}
	
	.fun-contain{
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
		
	}
	
	
</style>