<template>
	<view class="content">
	
		<scroll-view class="scroll-contain" scroll-y="true">
		
			<!-- 顶部图片轮播 -->
			<view class="swiper-contain">
				<swiper class="swiper" indicator-dots="true" autoplay="true" interval="3000">
					<swiper-item v-for="item in imageList" :key="item.id"  >
						<uni-link :href="item.web">
							<image :src="item.src" mode="scaleToFill"></image>
						</uni-link>
					</swiper-item>
				</swiper>
			</view>
			
			
			
			<!-- 公司详情 -->
			<view class="row" >
				<view v-if="firmData && firmData.hasOwnProperty('firmName')" class="title">
					<view class="scripe"></view>
					公司基本信息
				</view>
				<view v-else class="title">
					<view class="scripe"></view>
					意向公司信息
				</view>
				
				<!-- 公司名称 -->
				<view v-if="firmData && firmData.hasOwnProperty('firmName')"  class="line" style="display: flex;">
					<view class="line-text" >
						公司名称：
					</view>
					<text class="uni-input" >{{ firmData.firmName }}</text>
				</view>
				
			<!-- 行业选择 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmSectorType')"  class="line">
					<view class="line-text">行业：
					</view>
					
					<view class="uni-input" >{{ firmData.firmSectorType }}</view>
				
				</view>
				
				
				<!-- 地区选择器 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmLocation')"  class="line">
					<view class="line-text">
						注册地址：
					</view>
					<view class="uni-input" >{{ firmData.firmLocation }}</view>
					
				</view>	
				
				<!-- 详细地址选择 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmLocationDetail')"  class="line">
					<view class="line-text">
						详细地址：
					</view>
					<view class="uni-input" >{{ firmData.firmLocationDetail }}</view>
					
				</view>
				
				
				<!-- 注册资本 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmRegistCapital')"  class="line" style="display: flex;">
					<view class="line-text">
						注册资本：
					</view>
					<view class="uni-input" >{{ firmData.firmRegistCapital }} (万元)</view>
					
				</view>
				
				<!-- 营业范围 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmBusinessScope')"  class="line" >
					<view class="line-text">
						营业范围：
					</view>
					<view class="uni-input" >{{ firmData.firmBusinessScope }}</view>
			
				</view>
				
				
				<!-- 纳税性质 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmTaxableType')"  class="line">
					<view class="line-text">
						纳税性质：
					</view>
					<view class="uni-input" >{{ firmData.firmTaxableType }}</view>
				</view>
				
				<!-- 注册时间 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmEstablishDate')"  class="line" >
					<view class="line-text"> 
						注册时间：
					</view>
					<view class="uni-input" >{{ firmData.firmEstablishDate }}</view>
					
				</view>
				
				<!-- 归属税局 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmTaxBelong')"  class="line">
					<view class="line-text">
						归属税局：
					</view>
					<view class="uni-input" >{{ firmData.firmTaxBelong }}</view>
				</view>
				<view  style="margin-bottom: 10px;"></view>
			</view>
			
			<view class="row" style="margin-top: 10px;">
				<view class="title">
					<view class="scripe"></view>
					联系人信息
				</view>
				<!-- 联系人 -->
				<view v-if="firmData && firmData.hasOwnProperty('firmContacts')"  class="line" >		<!--  -->
					<view class="line-text">
						联系人：
					</view>
					<view class="uni-input" >{{ firmData.firmContacts }}</view>
				
				</view>	
					
				<!-- 联系人电话 -->
				<view  v-if="firmData && firmData.hasOwnProperty('firmContactsPhone')"  class="line" >
					<view class="line-text">
						电话：
					</view>
					<a class="uni-input" :href="'tel:' + firmData.firmContactsPhone">{{ firmData.firmContactsPhone }}</a>
								
				</view>
				
				<view  v-if="firmData && firmData.hasOwnProperty('firmPriceTransfer')"  class="line" >
					<view v-if="firmData && firmData.hasOwnProperty('firmName')" class="line-text">
						售价：
					</view>
					<view v-else class="line-text">
						求购价：
					</view>
					<view v-if="firmData && firmData.firmPriceTransfer" class="uni-input" >{{ firmData.firmPriceTransfer }} 元</view>	
					<view v-else class="uni-input" >面议</view>
				</view>
				
				<!-- 空白占位 -->
				<view style="height: 100px;" >
				</view>
				
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import config from '@/config/config.js'
	export default {
		data() {
			return {
				title: 'Hello',
				sectorsData : [],
				taxableData: [],
				areaData: [],
				date: '',
				sectorsData_index: 0,
				taxableData_index: 0,
				areaData_index: 0,
				firms: [],
				imageList:[],
				firmData:''
			}
		},
		onReady(){
			this.init();
			
			uni.setNavigationBarTitle({
				title: '公司详情'
			});
			
		},
		onLoad(option) {
			console.log("option:",option);
			this.firmsInit(option.id,option.type);
			
		
		},
		methods: {
			init:function(){
				this.opentionInit();
				
			},
			
			/*
			 * @desc 从服务器获取公司数据，并处理数据。
			 */
			firmsInit:function(id,type){
				console.log("id:",id);
				let that = this;
				let typeUrl = '';
				// 如果type为1，则是转让，为0则是求购
				if(type == 1){
					typeUrl = 'firmInfoItem';
				}else{
					typeUrl = 'firmPurchaseItem';
				}
				uni.request({
					url:config.domain + typeUrl,
					dataType:'application/json', 
					data:id,
					method:'POST',
					success(res) {
						let tempdata = JSON.parse(res.data);
						that.firmData = tempdata.data;
					}
				})
			},
			
			/**
			 * @desc 成立年份、行业、纳税性质、广告图片、地区选项初始化;
			 * 		 并保存到缓存，key：optionConfig
			 * */
			opentionInit:function(){
				let that = this;
				// 测试用
				
				// 正式用
				uni.request({
					url:config.domain + 'configList',
					method:'POST',
					header:{
						'Content-Type': 'application/x-www-form-urlencoded'  
					}, 
					success(res) {
						console.log("success: " ,res.data.data);
						console.log("分类",res.data.data.sectors.split(','));
						const sectorList = res.data.data.sectors.split(',');
						const taxableList = res.data.data.taxables.split(',');
						
						var imageTemp = res.data.data.imgSrc.split(';;');
						// 存入行业
						for(var i=0; i<sectorList.length; i++){
							that.sectorsData.push(sectorList[i]);
						}
						// 存入纳税性质
						for(var i=0; i<taxableList.length; i++){
							that.taxableData.push(taxableList[i]);
						}
						var imagelist =[];
						// 存入首页图片
						for(var i=0; i<imageTemp.length - 1; i++){
							var temp = imageTemp[i].split(',,');
							let src = temp[0];
							let web = temp[1];
							var item = {
								src : src,
								web : web
							}
							if(item == '')
							 break;
							that.imageList.push(item);
						}
						console.log("imageList:",that.imageList);
						
					}
				})
			},
			
			
			
		}
	}
</script>


<style>
	
	.content{
		display: flex;
		flex-direction: column;
		/* justify-content: center; */
		/* align-items: center;  */
		height: 100vh;  
		position: relative;
		background: whitesmoke;
	}
	/* 搜索框要固定顶部时使用 */
	/* .top-contain{
		position: fixed;
		z-index: 999;
		margin: 0px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.top-image{
		height: 30px;
		width: 30px;
	}
	.scroll-line{
		margin-bottom: 10px;
		height: 60px;
		width: 100%;
		background-color: white;
	} */
	
	.scroll-contain{
		height: 100%;
		width: 100%;
		/* margin-top: 60px; */
	}
	.swiper-contain{
		/* margin-top: 60px; */
		width: 100%;
		height: 350rpx;
		/* padding-top: 10px; */
		background-color: white;
	}
	.swiper{
		width: 100%;
		height: 100%;
	}
	.swiper-contain image{
		width: 100%;
		height: 100%;
		border-radius: 10px;
	}
	
	
	.scroll-firms{
		margin-top: 5px;
		display: flex;
		flex-direction: column;
		align-items: center;
		background: whitesmoke;
	}
	.firms{
		background: white;
		width: 100%;
		margin: 5px 20px;
	}
	.firms-item{
		margin: 5px 10%;
		padding: 5px 10%;
		background-color: slategray;
		width: 60%;
		border-radius: 5px;
	}
	.scroll-firms-none{
		align-items: center;
		margin-top: 10px;
		padding: 10px 0;
		text-align: center;
		color: darkgray;
	}
	
	
	.row{
		display: flex;
		flex-direction: column;
		background-color: white;
		font-size: 26rpx;
		margin-top: 8px;
		padding-top: 5px;
	}
	.title{
		font-size: 12px;
		padding: 5px 10px;
		width: 100%;
		height: 20px;
		background-color: #ff9300;
		color: white;
		display: flex;
		align-items: center;
		flex-direction: row;
	}
	.scripe{
		background-color: #0092ff;
		width: 5px;
		height: 80%;
		border-radius: 2px;
		margin-right: 10px;
	}
	.line{
		/* margin: 5px 0; */
		/* background-color: white; */
		display: flex;
		align-items: center;
		justify-content: flex-start;
		margin-top: 10px;
		/* 不换行，自动以。。。代替 */
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		
		
	}
	.line-text{
		width: 30%;
		margin-left: 20px;
		display: flex;
	}
	.line-text view{
		color: red;
	}
	.uni-input{
		height: auto;
		margin-right: 20px;
		flex-grow: 1;
		border-radius: 5px;
		padding-left: 10px;
		white-space: nowrap;
		overflow: scroll;
		/* 可复制 */
		user-select: text;
	}
	
	.bottom{
		padding: 20px 0;
	}
	.bottom-btn{
		/* padding: 0 50px; */
		margin: 0 50px;
		background-color: #5d73d9;
	}
	
	
	
	
	
</style>
