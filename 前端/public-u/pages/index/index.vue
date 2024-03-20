<template>
	<view class="content">
		
		<!-- <view class="weui-search-bar">
			<view class="uni-form-item uni-column input-up">
				<input class="uni-input" confirm-type="search" placeholder="键盘右下角按钮" selection-start="5" selection-end="20" />
			</view>
		</view> -->
		<!-- 搜索框固定 -->
		<!-- <view class="top-contain">
			<image class="top-image" src="../../static/image/location.png" mode="aspectFit"></image>
			<view >廊坊</view>
			<uni-section class="uni-section" type="line">
					<uni-search-bar @confirm="search" :focus="true" v-model="searchValue" @blur="blur" @focus="focus" @input="input" 
						@clear="clear" cancelButton="none">
					</uni-search-bar>
			</uni-section>
			
			<image class="top-image" src="../../static/image/筛选.png"></image>
		</view> -->
	
		<scroll-view class="scroll-contain" scroll-y="true">
			<!-- 搜索框固定==占位，否则搜索框是浮动，脱离文档流的，会重叠 -->
			<!-- <view class="scroll-line"></view> -->
		
			<!-- 顶部图片轮播 -->
			<view class="swiper-contain">
				<swiper class="swiper" indicator-dots="true" autoplay="true" interval="3000">
					<swiper-item v-for="item in imageList" :key="index"  >
						<uni-link :href="item.web">
							<image :src="item.src" mode="scaleToFill"></image>
						</uni-link>
					</swiper-item>
				</swiper>
			</view>
			
			<!-- 选择区域 -->
			<view class="select">
				
				<!-- 成立年份 -->
				<!-- <view class="select-time">
				</view> -->
				<view class="select-picker" style="width: 80%;margin-bottom: 5px;margin-top: 5px;padding: 0 10px; background-color: whitesmoke;">
					<view class="select-picker-text">
						成立年份：
					</view>
					<view class="select-picker-db" style="margin-right: 5px;">
						<picker mode="date" start="2000-1-1" end="2025-1-1" @change="pickerClick_date"  >
							<view v-if="date != ''" class="select-picker-input">{{date}}</view>
							<view v-else class="select-picker-input">全部</view>
						</picker>
					</view>
					<image style="width: 20px;height: 20px;" @click="clearBtn" src="../../static/image/叉.png">
					</image>
				</view>
				
				
				
				<view class="select-picker select-mid-contain" >
					<!-- 地域 -->
					<!-- <view class="select-shore">
						<image class="select-image" src="../../static/image/location.png" mode="aspectFit"></image>
						<view >廊坊</view>
					</view> -->
					<view class="select-mid" >
						<view class="select-picker-text" >
							区域：
						</view>
						<view class="select-picker-db" >
							<picker @change="pickerClick_area" :value="areaData_index" :range="areaData">
								<view class="select-picker-input">{{areaData[areaData_index]}}</view>
							</picker>
						</view>
					</view>
					
					
					<!-- 行业 -->
					<!-- <view class="select-picker">
						行业:
					</view> -->
					<view class="select-mid">
						<view class="select-picker-text" >
							行业：
						</view>
						<view class="select-picker-db" >
							<picker @change="pickerClick_sector" :value="sectorsData_index" :range="sectorsData">
								<view class="select-picker-input">{{sectorsData[sectorsData_index]}}</view>
							</picker>
						</view>
					</view>
						
				</view>
				
				
				<!-- 纳税性质 -->
				<!-- <view class="select-taxable">
					
				</view> -->
				<view class="select-picker select-bottom-contain">
					<view class="select-bottom" >
						<view class="select-picker-text">
							纳税性质：
						</view>
						<view class="select-picker-db" style="margin-right: 20px;">
							<picker @change="pickerClick_taxable" :value="taxableData_index" :range="taxableData">
								<view class="select-picker-input">{{taxableData[taxableData_index]}}</view>
							</picker>
						</view>
						
						
					</view>
						<button class="select-search">查询</button>
				</view>
				
			</view>
			
			<!-- 公司列表 -->
			<view class="scroll-firms" v-if="firmsData != '' && firmsData != undefined ">
				<view v-for="(item, index) in firmsData" class="firms" :key="item.id" @click="firmClick(item.id)">
					<!-- 头部公司名称 -->
					<view class="firms-item">
						<view class="firms-item-top-left">公司转让</view>
						<view class="firms-item-top-right">{{ item.firmName }}</view>
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
						<view>
							售价：{{ item.firmPriceTransfer }} 元
						</view>
						
						<view>
							发布时间：{{ showTime[index] }} 前
						</view>
					</view>
				</view>
			</view>
			<view v-else class="scroll-firms-none">
				无数据！
			</view>
			
		</scroll-view>
	</view>
</template>

<script>
	import config from '@/config/config.js';
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
				
				
				// 区域 TODO：首页区域选择需要到县
				area_display:'',
				provinces: [],
				cities: [],
				districts: [],
				multiArrary:[[], [], []],
				multiIndex: [0,0,0],
				
				// 发布时间
				showTime:[],
				// 公司数据
				firmsData:[]
			}
		},
		
		
		onReady(){
			this.init();
			uni.setNavigationBarTitle({
				title: '惠企转'
			});
			
		},
		onLoad() {
			
		},
		methods: {
			init:function(){
				this.opentionInit();
				this.firmsInit();
				this.areaInit();
			},
			
			
			// 行业、纳税性质选项初始化
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
						// console.log("imageList:",that.imageList);
						
					}
				})
			},
			
			// 区域初始化
			areaInit:function(){
				
				this.areaData = ['全部','廊坊','北京','天津'];
				
			},
			/*
			 * @desc 从服务器获取公司数据，并处理数据。
			 */
			firmsInit:function(){
				 let that = this;
				uni.request({
					url:config.domain + 'firmInfoList',
					header: {  
						'Content-Type': 'application/x-www-form-urlencoded'  
					}, 
					data:{
						page: 1,
						size: 8
					},
					method:'POST',
					success(res) {
						
						const options = { hour12: false, hour: 'numeric', minute: 'numeric', second: 'numeric' };
						const nowTime = new Date() ;
						console.log("now "+nowTime);
						for(var i = 0; i < res.data.length; i++){
							that.firmsData.push(res.data[i]);
							
							console.log("res = ",new Date(res.data[i].createTime));
							var temp = nowTime - new Date(res.data[i].createTime);
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
						console.log("showtime: ",that.showTime);
					}
				})
			},
			
			 bindPickerChange: function(e) {
				console.log("picker:",e);
				console.log('picker发送选择改变，携带值为', e.detail.value)
				this.index = e.detail.value
			},
			
			// 点击进入详情
			firmClick:function(index){
				uni.navigateTo({
					url:'/pages/index/detail?id='+index
				})
				
			},
			clearBtn:function(){
				this.date = '';
			},
			
			// 选择器点击事件
			pickerClick_date:function(e){
				console.log("选择：",e.detail.value);
				this.date = e.detail.value;
			},
			pickerClick_area:function(e){
				console.log("选择：",this.areaData[e.detail.value]);
				this.areaData_index = e.detail.value;
			},
			pickerClick_sector:function(e){
				console.log("选择：",this.sectorsData[e.detail.value]);
				this.sectorsData_index = e.detail.value;
			},
			pickerClick_taxable:function(e){
				console.log("选择：",this.taxableData[e.detail.value]);
				this.taxableData_index = e.detail.value;
			},
			
			
			
			// 时间格式化函数
			formatTime:function(value) {
				
				const date = new Date(value);
				const year = date.getFullYear();
				const month = String(date.getMonth() + 1).padStart(2, '0');
				const day = String(date.getDate()).padStart(2, '0');
				const hours = String(date.getHours()).padStart(2, '0');
				const minutes = String(date.getMinutes()).padStart(2, '0');
				const seconds = String(date.getSeconds()).padStart(2, '0');
				return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
			}
		}
	}
</script>

<script>
	
	
</script>
<style>
	
	.content{
		display: flex;
		flex-direction: column;
		/* justify-content: center; */
		/* align-items: center;  */
		height: calc(100vh-50px);
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
		height: 28%;
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
	
	.select{
		height: auto;
		width: 100%;
		margin-top: 8px;
		/* padding-top: 8px;
		padding-bottom: 8px; */
		display: flex;
		flex-wrap: wrap; 
		justify-content: center;
		background-color: white;
		font-size: 28rpx;
	}
	.select-shore{
		/* align-items: center; */
		display: flex;
		/* flex-direction: row; */
		width: 20%;
		overflow: scroll;
	}
	.select-image{
		height: 20px;
		width: 20px;
	}
	.select-picker {
		position: relative;
		display: flex;
		flex-direction: row;
		/* justify-content: flex-start; */
		align-items: center;
	}
	.select-mid-contain{
		display: flex;
		width: 95%;
		
		align-items: center;
		justify-content: space-around;
	}
	.select-mid{
		padding: 0 2.5%;
		/* margin: 0 ; */
		width: 40%;
		display: flex;
		flex-direction: row;
		position: relative;
		align-items: center;
		background-color: whitesmoke;
	}
	.select-bottom-contain{
		width: 95%;
		display: flex;
		align-items: center;
		justify-content: space-around;
	}
	.select-bottom{
		padding: 0 2.5%;
		/* margin: 0 ; */
		width: 70%;
		margin-right: 0%;
		display: flex;
		flex-direction: row;
		position: relative;
		align-items: center;
		background-color: whitesmoke;
	}
	.select-picker-text{
		/* font-size: 35rpx; */
		
	}
	.select-picker-db{
		flex: 1 ;
		/* background-color: white; */
		overflow: scroll;
	}
	.select-picker-input{
		height: 50rpx;
		padding: 8rpx 25rpx;
		line-height:50rpx;
		/* font-size:35rpx; */
	}
	.select-search{
		/* flex: 1; */
		
		margin: 5px 0px 5px 5px;
		/* margin: 5px 0px 0px 0px; */
		padding-top: 0;
		padding-bottom: 0;
		font-size: 28rpx;
			/* width: 20%; */
			
		/* margin: 8rpx 0; */
	}
	
	
	.scroll-firms{
		margin-top: 5px;
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
		
	}
	.firms-item{
		display: flex;
		flex-direction: row;
		background-color: white;
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
