<template>
	<view class="content">
		<scroll-view class="scroll-contain" scroll-y="true">
			
			<view class="row">
				<view class="title">
					<view class="scripe"></view>
					公司基本信息
				</view>
				
				<!-- 公司名 -->
				<view class="line" style="display: flex;">
					<view class="line-text" >
						公司名称 
						<view >&nbsp*</view>
						
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item"  v-model="uploadData.firmName" type="nickname" placeholder="请输入公司名称" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmName" @click="clearIcon('firmName')"></image>
					</view>
				</view>
				
				
				<!-- 行业选择 -->
				<view class="line">
					<view class="line-text">行业
						<view >
							&nbsp*
						</view>：
					</view>
					<picker class="uni-input" mode="selector" :range="sectorsData" :value="sectorsIndex" @change="pickerClick($event, 'sectorsData')" >
						<view >{{ sectorsData[sectorsIndex] }}</view>
					</picker>
				</view>
				
				
				<!-- 地区选择器 -->
				<view class="line">
					<view class="line-text">
						注册地址
						<view >&nbsp*</view>
						：
					</view>
					<picker class="uni-input" mode="multiSelector"  @columnchange="pickerColumnChange" @change="pickerClick($event, 'multiArrary')" :value="multiIndex" :range="multiArrary">
						<view  >{{ uploadData.firmLocation }}</view>
					</picker>
				</view>	
				
				<!-- 详细地址 -->
				<view class="line">
					<view class="line-text">
						详细地址
						<view >&nbsp</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" v-model="uploadData.firmLocationDetail" placeholder="请输入公司所在详细地址" @input="clearInput" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmLocationDetail" @click="clearIcon('firmLocationDetail')"></image>
					</view>
					
				</view>
				
				
				<!-- 注册资本 -->
				<view class="line" style="display: flex;">
					<view class="line-text">
						注册资本(万元)
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" type="digit" v-model="uploadData.firmRegistCapital" placeholder="请输入公司注册资本" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmRegistCapital" @click="clearIcon('firmRegistCapital')"></image>
					</view>
					
				</view>
				
				<!-- 营业范围 -->
				<view class="line" >
					<view class="line-text">
						营业范围
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="请输入公司营业范围" v-model="uploadData.firmBusinessScope" @input="clearInput" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmBusinessScope" @click="clearIcon('firmBusinessScope')"></image>
					</view>
				
				</view>
				
				
				<!-- 纳税性质 -->
				<view class="line">
					<view class="line-text">
						纳税性质
						<view >&nbsp*</view>
						：
					</view>
					<picker  class="uni-input" mode="selector" :range="taxableData" :value="taxableIndex" @change="pickerClick($event, 'taxableData')" >
						<view >{{ taxableData[taxableIndex] }}</view>
					</picker>
				</view>
				
				<!-- 注册时间 -->
				<view class="line" >
					<view class="line-text"> 
						注册时间
						<view >&nbsp*</view>
						：
					</view>
					<picker class="uni-input"  @change="pickerClick($event, 'yearData')" :value="yearIndex" :range="yearData" >
						<view v-if="uploadData.firmEstablishDate" >{{uploadData.firmEstablishDate}}</view>
						<view v-else>请选择注册时间</view>
					</picker>
					
					
				</view>
				
				<!-- 归属税局 -->
				<view class="line" >
					<view class="line-text">
						归属税局：
						<view >&nbsp</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="请输入公司所属税务局" v-model="uploadData.firmTaxBelong" @input="clearInput" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmTaxBelong" @click="clearIcon('firmTaxBelong')"></image>
					</view>
					
				</view>
				
			</view>
			
			<view class="row" style="margin-top: 10px;">
				<view class="title">
					<view class="scripe"></view>
					联系人信息
				</view>
				<!-- 联系人 -->
				<view class="line" >
					<view class="line-text">
						联系人
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="请输入联系人姓名" v-model="uploadData.firmContacts" @input="clearInput" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContacts" @click="clearIcon('firmContacts')"></image>
					</view>
					
				</view>	
					
				<!-- 联系人电话 -->
				<view class="line" >
					<view class="line-text">电话
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="请输入联系人电话" v-model="uploadData.firmContactsPhone" @input="clearInput" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContactsPhone" @click="clearIcon('firmContactsPhone')"></image>
					</view>
					
				
				</view>
				
				<!-- 转让价格 -->
				<view class="line" >
					<view class="line-text">
						转让价格(元)
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" type="digit" v-model="uploadData.firmPriceTransfer" placeholder="请输入转让价格(元)" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon('firmPriceTransfer')"></image>
					</view>
					
				</view>
				
			</view>
			
			
			<!-- 发布按钮 -->
			<view class="bottom">
				<button class="bottom-btn" @click="publishClick">
					我要发布
				</button>
			</view>
			
		
		</scroll-view>
	</view>
</template>

<script>
	import config from "@/config/config.js"
	export default {
		data() {
			return {
				// 行业
				sectorsData: [],
				sectorsIndex: 0,
				
				// 地区
				area_display:'',
				provinces: [],
				cities: [],
				districts: [],
				multiArrary:[[], [], []],
				multiIndex: [0,0,0],
				
				// 时间选项
				yearData:[],
				yearIndex:0,
				
				// 纳税性质
				taxableData: [],
				taxableIndex: 0,
				index: 0,
				
				// 日期
				dateIndex:0,
				
				
				
				// 数据
				uploadData:{
					firmName:'',
					firmSectorType:'',
					firmLocation:'',
					firmLocationDetail:'',
					firmTaxableType:'',
					firmEstablishDate:'',
					firmBusinessScope:'',
					firmTaxBelong:'',
					firmContacts:'',
					firmContactsPhone:'',
					firmPriceTransfer:'',
					firmRegistCapital:''
				}
			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '我要发布'
			});
		},
		
		
		onLoad() {
			let that = this;
			
			that.init();
		},
		methods: {
			/**
			 * @desc 数据初始化，从缓存中获取配置信息。从后端获取公司详情
			 */
			init:function(){
				let that = this;
				uni.getStorage({
					key: 'optionConfig',
					success: function (res) {
						that.sectorsData = res.data.sectorsData;
						that.taxableData = res.data.taxableData;
						that.yearData = res.data.yearData;
						console.log("获取选项配置成功！：",res.data);
					},
					fail:function(res) {
						console.error("获取选项配置缓存失败！",res);
						that.opentionInit();
						
					}
				});
				
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
			},
			
			// 清除输入框按键
			clearIcon:function(test){
				if(this.uploadData.hasOwnProperty(test)){
					console.log("存在",this.uploadData[test]);
					this.uploadData[test] = '';
				}else {
					console.log("不存在");
				}
			},
			
			// 选择器改变触发函数
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
				//that.uploadData.firmLocation = that.multiArrary[0][that.multiIndex[0]] + "-" + that.multiArrary[1][that.multiIndex[1]] + "-" + that.multiArrary[2][that.multiIndex[2]] ;
				
			},
			
			/**
			  * @desc 选择器选择事件
			  * */
			pickerClick:function(e,str){
				let that = this;
				if(str === 'multiArrary'){
					const value = e.detail.value;
					if(value[0]=='0'){
						that.uploadData.firmLocation = '全部';
						return;
					}
					that.multiIndex = value;
					that.multiArrary[1] = that.cities[that.multiIndex[0]];
					that.multiArrary[2] = that.districts[that.multiIndex[0]][that.multiIndex[1]];
					that.uploadData.firmLocation = that.multiArrary[0][value[0]] + "-" + that.multiArrary[1][value[1]] + "-" + that.multiArrary[2][value[2]] ;
					console.log("选择：",str);
					console.log("值：:",value);
					
				}
				if(str === 'yearData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.yearIndex = e.detail.value;
					
				}
				if(str === 'sectorsData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.sectorsIndex = e.detail.value;
					
				}
				if(str === 'taxableData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.taxableIndex = e.detail.value;
				}
			},
			
			/**
			  * @title 发布按钮执行函数
			  * @desc 上传到接口upload/addFirmInfo，
			  * 
			  * */
			publishClick:function(){
				let that = this;
				uni.showLoading({
					title:'请等待',
					mask:true,
				})
				console.log("upload: ",this.uploadData);
				uni.request({
					url:config.upload + 'addFirmInfo',
					method:'POST',
					data:that.uploadData,
					dataType:'application/json',
					success(res) {
						if(res.statusCode == 200){
							uni.showModal({
								content:'提交成功！',
							})
						}else{
							uni.showModal({
								content:'上传失败！',
							})
						}
						
					},fail() {
						uni.showModal({
							content:'上传失败！',
						})
					},complete() {
						uni.hideLoading();
					}
				})
				
			},
			
			/**
			  * @TODO 需写缓存
			  * @title 地区文件读取
			  * @desc 从static/data中读取地区json数据
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
						that.provinces = Object.keys(data);
						that.cities = [];
						that.districts = [];
						
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
						
						that.multiArrary[0] = that.provinces;
						that.multiArrary[1] = that.cities[0];
						that.multiArrary[2] = that.districts[0][0];
						
						that.uploadData.firmLocation = that.provinces[0] + "-" + that.cities[0][0] + "-" + that.districts[0][0][0] ;
						
						
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
						console.error('请求失败：', error.message);
					});
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
							imagelist.push(item);
						}
						uni.setStorage({
							key:'optionConfig',
							data:{
								yearData: that.yearData,
								sectorsData: that.sectorsData,
								taxableData: that.taxableData,
								imageList: imagelist
							},
							success:function(res){
								console.log("成功",res);
							}
						})
						that.yearData.unshift('全部');
						that.sectorsData.unshift('全部');
						that.taxableData.unshift('全部');
					}
				})
			},
			
		}
	}
</script>

<style>
	.content{
		/* width: 100%; */
		height: 100vh-50px;
		display: flex;
		flex-direction: column;
		/* justify-content: center; */
		/* align-items: center;  */
		/* height: 100vh;  */
		position: relative;
		background: whitesmoke;
	}
	.scroll-contain{
		/* margin-top: 60px; */
		height: 100%;
		width: 100%;
		font-size: 15px;
		background-color: whitesmoke;
	}
	.row{
		display: flex;
		flex-direction: column;
		background-color: white;
		padding-bottom: 10px;
	}
	.title{
		font-size: 30rpx;
		padding: 5px 10px;
		width: 100%;
		height: 20px;
		background-color: #d4d9ec;
		display: flex;
		align-items: center;
		flex-direction: row;
	}
	.scripe{
		background-color: aqua;
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
		margin-left: 10px;
		font-size: 26rpx;
		display: flex;
	}
	.line-text view{
		color: red;
	}
	.uni-input{
		/* padding: 0px 0px; */
		height: auto;
		margin-right: 20px;
		flex-grow: 1;
		border-radius: 5px;
		border: 2rpx solid black;
		padding: 5rpx 0;
		padding-left: 10px;
		height: 60rpx;
		line-height: 60rpx;
		/* justify-items: center; */
		align-items: center;
		/* text-align: center; */
	}
	.uni-input-item{
		width: calc(100% - 50rpx);
	}
	.uni-icon{
		height: 80%;
		width: 50rpx;
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
