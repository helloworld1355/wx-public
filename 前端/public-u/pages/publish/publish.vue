<template>
	<view class="content">
		<scroll-view class="scroll-contain" scroll-y="true">
			<!-- 公司名 -->
			<view class="line" style="display: flex;">
				<view class="picker">公司名称：</view>
				<input style="border: 2px solid black;" class="uni-input" v-model="uploadData.firmName" type="nickname" placeholder="输入框" />
				<!-- <view class="number-input" style="padding: 20px 0;">(万元)</view> -->
			</view>
			
			
			<!-- 行业选择 -->
			<view class="line">
				<view class="picker">行业：</view>
				<picker  class="picker" mode="selector" :range="sectorsData" :value="sectorsIndex" @change="sectorsChange" >
					<view style="border: 2px solid black;">{{ sectorsData[sectorsIndex] }}</view>
				</picker>
				
			</view>
			
			<!-- 地区选择器 -->
			<view class="line" style="flex-direction: column;">
				<view style="display: flex;flex-direction: row;">
					<view class="picker">注册地址：</view>
					<picker class="picker" mode="multiSelector"  @columnchange="pickerColumnChange" @change="pickerChange" :value="multiIndex" :range="multiArrary">
						<view class="uni-input" style="border: 2px solid black;">{{ uploadData.firmLocation }}</view>
					</picker>
				</view>
				<!-- 详细地址 -->
				<view style="padding:  10px 0;display: flex;flex-direction: row;">
					<view>详细地址：</view>
					<input style="border: 2px solid black;" class="uni-input" v-model="uploadData.firmLocationDetail" placeholder="带清除按钮的输入框" @input="clearInput" />
					<text class="uni-icon" v-if="showClearIcon" @click="clearIcon">&#xe434;</text>
				</view>
			</view>
			
			
			
			
			<!-- 注册资本 -->
			<view class="line" style="display: flex;">
				<view >注册资本：</view>
				<input style="border: 2px solid black;" class="number-input" type="digit" v-model="uploadData.upload_regist_capital" placeholder="这是一个数字输入框" />
				<view class="number-input" style="padding: 20px 0;">(万元)</view>
			</view>
			
			<!-- 营业范围 -->
			<view class="line" style="flex-direction: column;">
				<view style="padding:  10px 0;display: flex;flex-direction: row;">
					<view>营业范围：</view>
					<input style="border: 2px solid black;" class="uni-input" placeholder="带清除按钮的输入框" v-model="uploadData.firmBusinessScope" @input="clearInput" />
					<text class="uni-icon" v-if="showClearIcon" @click="clearIcon">&#xe434;</text>
				</view>
			</view>
			
			
			<!-- 纳税性质 -->
			<view class="line">
				<view class="picker">纳税性质：</view>
				<picker  class="picker" mode="selector" :range="taxableData" :value="taxableIndex" @change="taxableChange" >
					<view style="border: 2px solid black;">{{ taxableData[taxableIndex] }}</view>
				</picker>
			</view>
			
			<view class="line" style="margin-right: 5px;">
				<view class="picker"> 选择时间：</view>
				<picker class="picker" mode="date" start="2000-1-1" end="2025-1-1" @change="dateChange"  >
					<view style="border: 2px solid black;" class="uni-input" >{{uploadData.firmEstablishDate}}</view>
				</picker>
			</view>
			
			<!-- 归属税局 -->
			<view class="line" style="flex-direction: column;">
				<view style="padding:  10px 0;display: flex;flex-direction: row;">
					<view>归属税局：</view>
					<input style="border: 2px solid black;" class="uni-input" placeholder="带清除按钮的输入框" v-model="uploadData.firmTaxBelong" @input="clearInput" />
					<text class="uni-icon" v-if="showClearIcon" @click="clearIcon">&#xe434;</text>
				</view>
			</view>
			
			<!-- 联系人 -->
			<view class="line" style="flex-direction: column;">
				<view style="padding:  10px 0;display: flex;flex-direction: row;">
					<view>姓名：</view>
					<input style="border: 2px solid black;" class="uni-input" placeholder="带清除按钮的输入框" v-model="uploadData.firmContacts" @input="clearInput" />
					<text class="uni-icon" v-if="showClearIcon" @click="clearIcon">&#xe434;</text>
				</view>
				
				<view style="padding:  10px 0;display: flex;flex-direction: row;">
					<view>电话：</view>
					<input style="border: 2px solid black;" class="uni-input" placeholder="带清除按钮的输入框" v-model="uploadData.firmContactsPhone" @input="clearInput" />
					<text class="uni-icon" v-if="showClearIcon" @click="clearIcon">&#xe434;</text>
				</view>
			</view>
			
			<!-- 转让价格 -->
			<view class="line" style="display: flex;">
				<view >转让价格：</view>
				<input style="border: 2px solid black;" class="number-input" type="digit" v-model="uploadData.firmPriceTransfer" placeholder="这是一个数字输入框" />
				<view class="number-input" style="padding: 20px 0;">(万元)</view>
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
					upload_regist_capital:''
				}
			}
		},
		
		onLoad() {
			let that = this;
			
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
			
			// 发布按钮
			publishClick:function(){
				let that = this;
				uni.showLoading({
					title:'请等待',
					mask:true,
				})
				console.log("upload: ",this.uploadData);
				uni.request({
					url:'http://localhost:8080/upload/addFirmInfo',
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
	}
	.line{
		margin: 5px 0;
		background-color: #97b69a;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.picker{
		padding: 20px 0;
		text-align: center;
		/* height: 50px; */
	}
	.number-input{
		/* padding: 0px 0px; */
		height: auto;
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
