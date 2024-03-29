<template>
	<view>
		<!-- 转让或求购 -->
		<view class="classify">
			<button class="classify-item" :class="{'isSelectT':isTransfer}" @click="changeSelect('transfer')">转让</button>
			<button class="classify-item" :class="{'isSelectP':!isTransfer}" @click="changeSelect('purchase')">求购</button>
		</view>
		<!-- 公司列表 -->
		<view class="scroll-firms" v-if="firmsData != '' && firmsData != undefined ">
			<view v-for="(item,index) in firmsData" class="firms" :key="item.id" @click="firmClick(item.id)">
				<!-- 头部公司名称 -->
				<view v-if="isTransfer" class="firms-item">
					<view class="firms-item-top-left">公司转让</view>
					<view class="firms-item-top-right">{{ item.firmName }}</view>
				</view>
				<view v-else class="firms-item">
					<view class="firms-item-top-left" style="background-color: #0088ff;">公司求购</view>
				</view>
				
				<!-- 中部，公司大致信息 -->
				<view class="firms-item">
					<view class="firms-item-mid">
						<view class="firms-item-mid-item">
							所在地
							<view class="firms-item-mid-item-child">{{item.firmLocation}}</view>
						</view>
						<view class="firms-item-mid-item">
							成立时间
							<view class="firms-item-mid-item-child">{{ item.firmEstablishDate }}</view>
						</view>
						<view class="firms-item-mid-item">
							行业类型
							<view class="firms-item-mid-item-child">{{ item.firmSectorType }}</view>
						</view>
						<view class="firms-item-mid-item">
							纳税性质
							<view class="firms-item-mid-item-child">{{ item.firmTaxableType }}</view>
						</view>
					</view>
				</view>
				
				<!-- 底部售价，发布等信息 -->
				<view class="firms-item firms-item-bottom">
					<view v-if="isTransfer ">
						售价：{{ item.firmPriceTransfer ?  item.firmPriceTransfer + ' 元' : '面议' }}
					</view>
					<view v-else>
						求购：{{ item.firmPriceTransfer ?  item.firmPriceTransfer + ' 元' : '面议' }}
					</view>
					
					<view>
						发布时间：{{ showTime[index] }} 前
					</view>
				</view>
			</view>
			
		</view>
		<view v-else class="scroll-firms-none">
			未发布
		</view>
	</view>
</template>

<script>
	import config from "@/config/config.js";
	export default {
		data() {
			return {
				firmsData: [],
				isTransfer: 1,
				showTime: [],
			}
		},
		onLoad() {
			this.init();
		},
		methods: {
			init() {
				let that = this;
				let tempkey = '';
				let tempurl = '';
				if(that.isTransfer == 1){
					tempkey = 'localdata-transfer';
					tempurl = 'myFirmInfoList';
				}else{
					tempkey = 'localdata-purchase';
					tempurl = 'myFirmPurchaseList';
				}
				
				
				uni.getStorage({
					key: tempkey,
					success(res) {
						var localdata = JSON.parse(res.data);
						var jsondata = JSON.stringify(localdata)
						uni.request({
							url: config.domain + tempurl,
							method: 'POST',
							dataType:'application/json',
							data: jsondata,
							success(res) {
								console.log("success:", res);
								var temp = JSON.parse(res.data);
								console.log("temp:", temp);
								that.firmsData = temp.data;
								that.getTime(that.firmsData);
								
								console.log("that.time = ",that.showTime);
								console.log("that.firmsData:", that.firmsData);
							}
						})
					}
				})

				
			},
			// 点击切换转让，求购页面
			changeSelect:function(str){
				if(str == 'transfer'){
					this.isTransfer = 1;
				}else if(str == 'purchase'){
					this.isTransfer = 0;
				}
				// 先清空数据
				this.firmsData = [];
				this.init();
			},
			
			// 点击详情 
			firmClick(id){
				let that = this;
				uni.navigateTo({
					url:'/pages/publish/modify?id='+ id +'&isTransfer=' +that.isTransfer,
				})
			},
			
			
			/**
			  * @desc 重新计算时间，
			  * @调用 在查询和首次加载页面时
			  * */
			getTime:function(data){
			
				let that = this;
				that.showTime = [];
				const options = { hour12: false, hour: 'numeric', minute: 'numeric', second: 'numeric' };
				const nowTime = new Date() ;
				for(var i = 0; i < data.length; i++){
					
					var temp = nowTime - new Date(data[i].createTime);
					temp =Math.round( temp / 3600000);
					var hour = ~~(temp / 24);
					var publish = '';
					if(hour == 0){
						publish = temp + "小时";
					}else{
						temp = temp % 24;
						publish = hour + "天" + temp + "小时";
					}
					that.showTime.push(publish);
				}
					
			},
		}
	}
</script>

<style>

	/* 转让或求购 */
	.classify{
		position: sticky;
		top: 0;
		z-index: 100;
		/* height: 10%; */
		width: 100%;
		margin-top: 20rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		background-color: white;
		
	}
	.classify-item{
		height: 70rpx;
		width: 30%;
		margin: 10rpx 0;
		color: black;
		text-align: center;
		line-height: 70rpx;
		/* border: 2rpx solid black; */
		border-radius: 70rpx;
		background-color: whitesmoke;
	}
	.isSelectT{
		background-color: #ff9300;
		color: white;
	}
	.isSelectP{
		background-color: #0088ff;
		color: white;
	}

	/* 公司列表 */
	.scroll-firms{
		margin-top: 10rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		background: whitesmoke;
	}
	.firms{
		background: whitesmoke;
		width: 100%;
		margin: 5px 20px;
		display: flex;
		flex-direction: column;
		align-items: center;
		font-size: 24rpx;
		background-color: white;
	}
	.firms-item{
		display: flex;
		flex-direction: row;
		
		width: 95%;
	}
	.firms-item-top-left{
		margin-top: 7px;
		padding: 2px 5px;
		/* border: 2px solid red; */
		border-radius: 10px 0 10px 0;
		/* font-size: 11px; */
		background-color: #ff9300; 
		color: white;
	}
	
	.firms-item-top-right{
		margin-top: 7px;
		margin-left: 10px;
		padding: 2px;
		/* border: 2px solid red;
		border-radius: 10px 0 10px 0; */
		/* font-size: 11px; */
		background-color: white;
		max-width: 50%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.firms-item-mid{
		width: 100%;
		margin-top: 7px;/* 
		margin-left: 10px; */
		display: flex;
		/* flex-direction: row; */
		/* border: 2px solid red; */
		border-radius: 10px ;
		justify-content: space-evenly;
		text-align: center;
		background-color: #e2e2e2;
	}
	.firms-item-mid-item{
		margin: 10px 0;
		display: flex;
		flex-direction: column;
		width: 22%;
		/* padding: 5px 0; */
		/* font-size: 12px; */
		/* background-color: beige; */
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.firms-item-mid-item-child{
		margin-top: 10px;
	}
	
	.firms-item-bottom{
		 display: flex;
		 flex-direction: row;
		 justify-content: space-between;
		 padding: 5px 0;
	}	
	
	.scroll-firms-none{
		align-items: center;
		margin-top: 10px;
		padding: 10px 0;
		text-align: center;
		color: darkgray;
	}
</style>
