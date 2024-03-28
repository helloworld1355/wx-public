<template>
	<view>
		<view v-if="firmList.length">
			<view v-for="item in firmList" :key="item.id">
				<view>{{item.firmName}}</view>
				<view>{{item.firmContacts}}</view>
			</view>
		</view>
		<view v-else>未发布数据！</view>
	</view>
</template>

<script>
	import config from "@/config/config.js";
	export default {
		data() {
			return {
				localData: '',
				firmList: []
			}
		},
		onLoad() {
			this.init();
		},
		methods: {
			init() {
				let that = this;
				uni.getStorageInfo({
					success(res) {
						console.log("getInfo__",res);
					}
				})
				uni.getStorage({
					key: 'localData',
					success(res) {
						that.localData = res.data;
						console.log("res.data = ",res);
						console.log("this.localIdlist::", that.localData);
						// uni.request({
						// 	url: config.domain + 'myFirmInfoList',
						// 	method: 'POST',
						// 	success(res) {
						// 		console.log("success:", res);
						// 		that.firmList = res.data.data;
						// 	}
						// })
					}
				})
				console.log("that = ",that.firmList);
				
			}
		}
	}
</script>

<style>

</style>
