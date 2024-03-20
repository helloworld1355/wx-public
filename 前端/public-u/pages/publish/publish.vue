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
						：
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
					<picker class="uni-input" mode="selector" :range="sectorsData" :value="sectorsIndex" @change="sectorsChange" >
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
					<picker class="uni-input" mode="multiSelector"  @columnchange="pickerColumnChange" @change="pickerChange" :value="multiIndex" :range="multiArrary">
						<view  >{{ uploadData.firmLocation }}</view>
					</picker>
				</view>	
				
				<!-- 详细地址选择 -->
				<view class="line">
					<view class="line-text">
						详细地址
						<view >&nbsp*</view>
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
					<picker  class="uni-input" mode="selector" :range="taxableData" :value="taxableIndex" @change="taxableChange" >
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
					<picker class="uni-input" mode="date" start="2000-1-1" end="2025-1-1" @change="dateChange"  >
						<view >{{uploadData.firmEstablishDate}}</view>
					</picker>
				</view>
				
				<!-- 归属税局 -->
				<view class="line" >
					<view class="line-text">
						归属税局：
						<view >&nbsp*</view>
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
						姓名
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
					firmEstablishDate:'2000-01-01',
					firmBusinessScope:'',
					firmTaxBelong:'',
					firmContacts:'',
					firmContactsPhone:'',
					firmPriceTransfer:'',
					firmRegistCapital:''
				}
			}
		},
		
		onLoad() {
			let that = this;
			console.log("config :",config);
			uni.setNavigationBarTitle({
				title: '我要发布'
			});
			
			// 获取区域数据
			// h5请求本地json格式数据
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
					console.log('省份数据：', that.provinces);
					console.log('城市数据：', that.cities);
					console.log('区县数据：', that.districts);
				})
				.catch(error => {
					console.error('请求失败：', error.message);
				});
			
			// 获取行业数据
			that.sectorsData = ['科技','美食','教育'];
			that.uploadData.firmSectorType = that.sectorsData[0];
			
			// 获取行业性质
			that.taxableData = ['性质1','性质2','性质3'];
			that.uploadData.firmTaxableType = that.taxableData[0];
			
			
		},
		methods: {
			
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
				that.uploadData.firmLocation = that.multiArrary[0][that.multiIndex[0]] + "-" + that.multiArrary[1][that.multiIndex[1]] + "-" + that.multiArrary[2][that.multiIndex[2]] ;
				
			},
			
			sectorsChange:function(e){
				let that = this;
				this.sectorsIndex = e.detail.value;
				that.uploadData.firmSectorType = that.sectorsData[this.sectorsIndex];
			},
			taxableChange:function(e){
				let that = this;
				this.taxableIndex = e.detail.value;
				that.uploadData.firmTaxableType = that.taxableData[this.taxableIndex];
			},
			dateChange:function(e){
				let that = this;
				that.uploadData.firmEstablishDate = e.detail.value;
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
			
			// 发布按钮
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
				
			}
			
			
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
