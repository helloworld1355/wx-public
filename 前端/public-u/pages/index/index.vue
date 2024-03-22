<template>
	<view class="content">
	
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
			
				<view class="select-picker " style="margin-top: 20rpx;" >
					<view class="select-mid" >
						<view class="select-picker-input" >
							区域：
						</view>
						<view class="select-picker-db" >
							<picker  mode="multiSelector"  @columnchange="pickerColumnChange" @change="pickerClick($event, 'multiArrary')" :value="multiIndex" :range="multiArrary">
								<view class="select-picker-input ispicker" >{{ showLocation }}</view>
							</picker>
						</view>
					</view>
					
				</view>
				
			
				<view class="select-picker ">
					<view class="select-mid select-mid-left" style="flex: 2;">
						<view class="select-picker-input">
							成立年份：
						</view>
						<view class="select-picker-db">
							<picker  @change="pickerClick($event, 'yearData')" :value="yearData_index" :range="yearData" >
								<view v-if="yearData != ''" class="select-picker-input ispicker">{{yearData[yearData_index]}}</view>
								<view v-else class="select-picker-input ispicker">全部</view>
							</picker>
						</view>
					</view>
					
					<!-- 行业 -->
					<view class="select-mid">
						<view class="select-picker-input" > 
							行业：
						</view>
						<view class="select-picker-db" >
							<picker @change="pickerClick($event, 'sectorsData')" :value="sectorsData_index" :range="sectorsData">
								<view class="select-picker-input ispicker">{{sectorsData[sectorsData_index]}}</view>
							</picker>
						</view>
					</view>
					
				</view>
				
				<view class="select-picker" style="margin-bottom: 20rpx;">
					<!-- 纳税性质 -->
					<view class="select-mid select-mid-left"  style="flex: 2;" >
						<view class="select-picker-input">
							纳税性质：
						</view>
						<view class="select-picker-db" >
							<picker @change="pickerClick($event, 'taxableData')" :value="taxableData_index" :range="taxableData">
								<view class="select-picker-input ispicker">{{taxableData[taxableData_index]}}</view>
							</picker>
						</view>
					</view>
					
					<button class="select-search" @click="searchFirm" >查询</button>
				</view>
				
			</view>
			
			<!-- 公司列表 -->
			<view class="scroll-firms" v-if="showFirms != '' && showFirms != undefined ">
				<view v-for="(item,index) in showFirms" class="firms" :key="item.id" @click="firmClick(item.id)">
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
				// 查询选项配置
				sectorsData : [],
				taxableData: [],
				yearData:[],
				// areaData: [],
				sectorsData_index: 0,
				taxableData_index: 0,
				yearData_index: 0,
				// areaData_index: 0,
				
				firms: [],
				
				imageList:[],
				
				// 区域选择器：首页区域选择需要到县
				showLocation:'全部',
				provinces: [],
				cities: [],
				districts: [],
				multiArrary:[[], [], []],
				multiIndex: [0,0,0],
				
				// 发布时间
				showTime:[],
				// 公司数据,展示的公司数组
				firmsData:[],
				showFirms:[]
			}
		},
		
		
		onReady(){
			
			uni.setNavigationBarTitle({
				title: '惠企转'
			});
			
		},
		onLoad() {
			this.init();
			
		},
		methods: {
			
			/**
			 * @desc 数据初始化，从缓存中获取配置信息。从后端获取公司详情
			 */
			init:function(){
				let that = this;
				
				// 获取选项配置缓存
				uni.getStorage({
					key: 'optionConfig',
					success: function (res) {
						that.sectorsData = res.data.sectorsData;
						that.taxableData = res.data.taxableData;
						that.yearData = res.data.yearData;
						that.imageList = res.data.imageList;
						that.yearData.unshift('全部');
						that.sectorsData.unshift('全部');
						that.taxableData.unshift('全部');
						console.log("获取缓存！",res.data.sectorsData);
					},
					fail:function(res) {
						console.log("获取缓存失败！",res);
						that.opentionInit();
					}
				});
				
				// 获取地域缓存
				uni.getStorage({
					key: 'areaData',
					success:function(res){
						console.log("获取地域缓存成功！！");
						that.provinces = res.data.provinces;
						that.cities = res.data.cities;
						that.districts = res.data.districts;
						
						that.multiArrary[0] = that.provinces;
						that.multiArrary[1] = that.cities[0];
						that.multiArrary[2] = that.districts[0][0];
						that.uploadData.firmLocation = that.provinces[0] + "-" + that.cities[0][0] + "-" + that.districts[0][0][0] ;
					},
					fail:function(res){
						console.log("获取缓存失败，重新拉取数据!",res);
						that.uploadAreaData();
					}
				})
				
				// 获取公司数据
				that.uploadFirmsInfo();
			},
			
			// 点击进入详情
			firmClick:function(index){
				uni.navigateTo({
					url:'/pages/index/detail?id='+index
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
						that.uploadAreaData();
						const sectorList = res.data.data.sectors.split(',');
						const taxableList = res.data.data.taxables.split(',');
						const yearList = res.data.data.years.split(',');
						console.log("years:",yearList);
						var imageTemp = res.data.data.imgSrc.split(';;');
						// 存入成立年份
						for(var i=0; i<yearList.length - 1; i++){
							that.yearData.push(yearList[i]);
						}
						// 存入行业
						for(var i=0; i<sectorList.length - 1; i++){
							that.sectorsData.push(sectorList[i]);
						}
						// 存入纳税性质
						for(var i=0; i<taxableList.length - 1; i++){
							that.taxableData.push(taxableList[i]);
						}
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
						uni.setStorage({
							key:'optionConfig',
							data:{
								yearData: that.yearData,
								sectorsData: that.sectorsData,
								taxableData: that.taxableData,
								imageList: that.imageList
							},
							success:function(res){
								
							}
						})
						that.yearData.unshift('全部');
						that.sectorsData.unshift('全部');
						that.taxableData.unshift('全部');
					}
				})
			},
			
			/**
			 * @desc 从服务器获取公司数据，并处理数据。
			 */
			uploadFirmsInfo:function(){
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
						
						that.firmsData = res.data;
						that.getTime(that.firmsData);
						that.showFirms = that.firmsData;
					}
				})
			},
			
			/**
			  * @desc  查询按钮函数
			  * @TODO 区域查询
			  */
			searchFirm:function(){
				let that = this;
				that.showFirms = that.firmsData;
				const selSector = that.sectorsData[that.sectorsData_index];
				const selTaxable = that.taxableData[that.taxableData_index];
				const selYear = that.yearData[that.yearData_index];
				const selLocation = that.showLocation;
				
				// sectorsData taxableData yearData
				if( selSector != '全部'){
					console.log("selSector:",selSector);
					that.showFirms = that.showFirms.filter(item => item.firmSectorType == selSector);
				}
				if( selTaxable != '全部'){
					console.log("selTaxable:",selTaxable);
					that.showFirms = that.showFirms.filter(item => item.firmTaxableType == selTaxable);
				}
				if( selYear != '全部'){
					console.log("selYear:",selYear);
					that.showFirms = that.showFirms.filter(item => item.firmEstablishDate == selYear);
				}
				if( selLocation != '全部' ){
					console.log("selLocation:",selLocation);
					that.showFirms = that.showFirms.filter(item => item.firmLocation == selLocation);
				}
				that.getTime(that.showFirms);
			},
			
			/**
			 * 	@desc 选择器点击事件
			 *	选择器点击事件;传递两个参数，第一个参数是默认参数，
			 * 	第二个参数是传递进来的字符串。用于识别点击的是哪个picker
			 */ 
			pickerClick:function(e,str){
				let that = this;
				if(str === 'multiArrary'){
					const value = e.detail.value;
					if(value[0]=='0'){
						that.showLocation = '全部';
						return;
					}
					that.multiIndex = value;
					that.multiArrary[1] = that.cities[that.multiIndex[0]];
					that.multiArrary[2] = that.districts[that.multiIndex[0]][that.multiIndex[1]];
					that.showLocation = that.multiArrary[0][value[0]] + "-" + that.multiArrary[1][value[1]] + "-" + that.multiArrary[2][value[2]] ;
					console.log("选择：",str);
					console.log("值：:",value);
					
				}
				if(str === 'yearData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.yearData_index = e.detail.value;
					
				}
				if(str === 'sectorsData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.sectorsData_index = e.detail.value;
					
				}
				if(str === 'taxableData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.taxableData_index = e.detail.value;
				}
			},
			
			
			/**
			  * @desc 时间格式化函数，格式化到秒
			  * */
			formatTime:function(value) {
				
				const date = new Date(value);
				const year = date.getFullYear();
				const month = String(date.getMonth() + 1).padStart(2, '0');
				const day = String(date.getDate()).padStart(2, '0');
				const hours = String(date.getHours()).padStart(2, '0');
				const minutes = String(date.getMinutes()).padStart(2, '0');
				const seconds = String(date.getSeconds()).padStart(2, '0');
				return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
			},
			
			/**
			  * @desc 从文件中读取地区数据。
			  * */
			uploadAreaData:function(){
				let that = this;
				fetch('/static/data/pca.json')
					.then(response => {
						if (!response.ok) {
						  throw new Error('请求失败');
						}
						return response.json();
					})
					.then(data => {
						// 处理数据
						// 将地区数据转换成数组格式
						// that.provinces = ['全部'];
						that.provinces=(Object.keys(data));
						
						that.provinces.forEach(province => {
							const cityNames = Object.keys(data[province]);
							const cityArray = [];
							const districtArray = [];
				
							cityNames.forEach(city => {
							cityArray.push(city);
							districtArray.push(data[province][city]);
							});
				
							that.cities.push(cityArray);
							that.districts.push(districtArray);
						});
						that.provinces.unshift('全部');
						that.cities.unshift('');
						that.districts.unshift('');
						
				
						
						that.multiArrary[0] = that.provinces;
						that.multiArrary[1] = that.cities[0];
						that.multiArrary[2] = that.districts[0][0];
						
						that.showLocation = that.provinces[0] + "-" + that.cities[0][0] + "-" + that.districts[0][0][0] ;

						uni.setStorage({
							key:'areaData',
							data:{
								provinces: that.provinces,
								cities: that.cities,
								districts: that.districts,
							},
							success:function(res){
								console.log("地域数据保存成功：",res);
							}
						})

					})
					.catch(error => {
						console.error('请求地区数据失败：', error.message);
					});
			},
			
			/**
			  * @desc 选择器改变触发 
			  * */
			pickerColumnChange:function(e){
				let that = this;
				const column = e.detail.column;
				const value = e.detail.value;
				that.multiIndex[column] = value;
				console.log('修改的列为：' + e.detail.column + '，值为：' + e.detail.value)
				switch(column){
					case 0:{
						that.multiIndex[1] = 0;
						that.multiIndex[2] = 0;
						that.multiArrary[1] = that.cities[that.multiIndex[0]];
						that.multiArrary[2] = that.districts[that.multiIndex[0]][that.multiIndex[1]];
						break;
					}
					case 1:{
						that.multiIndex[2] = 0;
						that.multiArrary[2] = that.districts[that.multiIndex[0]][that.multiIndex[1]];
						break;
					}
				}
				// that.uploadData.firmLocation = that.multiArrary[0][that.multiIndex[0]] + "-" + that.multiArrary[1][that.multiIndex[1]] + "-" + that.multiArrary[2][that.multiIndex[2]] ;
				
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
		justify-content: space-between;
		width: 90%;
		/* background-color: aquamarine; */
		margin-top: 10rpx;
		
	}
	.select-mid-left{
		margin-right: 40rpx;
	}
	.select-mid{
		flex: 1;
		display: flex;
		flex-direction: row;
		position: relative;
		align-items: center;
		background-color: whitesmoke;
		border-radius: 20rpx;
	}
	.select-bottom-contain{
		display: flex;
		align-items: center;
		justify-content: space-around;
	}
	.select-bottom{
		/* padding: 0 2.5%; */
		/* margin: 0 ; */
		/* width: 70%; */
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
		/* width: 150rpx; */
		/* border: 1rpx solid black; */
		border-radius: 5rpx;
	}
	.select-picker-input{
		height: 40rpx;
		padding: 15rpx 0 15rpx 0;
		padding-left: 10rpx;
		line-height:40rpx;
		
		/* font-size:35rpx; */
	}
	.ispicker{
		
	}
	.select-search{
		flex: 1;
		/* margin: 10rpx 0px 10rpx 10rpx;*/
		padding: 0;
		
		font-size: 28rpx;

	}
	
	
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
