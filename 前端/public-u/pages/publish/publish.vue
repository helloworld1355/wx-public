<template>
	<view class="content">
		<scroll-view class="scroll-contain" scroll-y="true">
			<view class="classify">
				<button class="classify-item" :class="{'isSelectT':isTransfer}" @click="changeSelect('transfer')">转让</button>
				<button class="classify-item" :class="{'isSelectP':!isTransfer}"  @click="changeSelect('purchase')">求购</button>
			</view>
			<!-- 转让 -->
			<view v-if="isTransfer">
				<view class="row">
					<view class="title" style="background-color: #ff9300;color: black;">
						<view class="scripe" style="background-color: #0088ff;"></view>
						公司基本信息
					</view>
					
					<!-- 公司名 -->
					<view ref="firmName" class="line" style="display: flex;">
						<view class="line-text" >
							公司名称 
							<view >&nbsp*</view>
							
						</view>
						<view class="uni-input" style="display: flex;">
							<input class="uni-input-item"   v-model="uploadData.firmName" type="nickname" placeholder="" @input="saveInputData"/>
							<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
							<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmName" @click="clearIcon('firmName')"></image>
						</view>
					</view>
					
					
					<!-- 行业选择 -->
					<view ref="firmSectorType" class="line">
						<view class="line-text">行业
							<view >
								&nbsp*
							</view>：
						</view>
						<picker class="uni-input" mode="selector" :range="sectorsData" :value="sectorsIndex" @change="pickerClick($event, 'sectorsData')" >
							<view v-if="uploadData.firmSectorType">{{ uploadData.firmSectorType }}</view>

						</picker>
					</view>
					
					
					<!-- 地区选择器 -->
					<view ref="firmLocation" class="line">
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
							<input class="uni-input-item" v-model="uploadData.firmLocationDetail" placeholder=""  @input="saveInputData" />
							<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
							<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmLocationDetail" @click="clearIcon('firmLocationDetail')"></image>
						</view>
						
					</view>
					
					
					<!-- 注册资本 -->
					<view ref="firmRegistCapital" class="line" style="display: flex;">
						<view class="line-text">
							注册资本(万元)
							<view >&nbsp*</view>
							：
						</view>
						<view class="uni-input" style="display: flex;">
							<input class="uni-input-item" type="digit" v-model="uploadData.firmRegistCapital" placeholder=""  @input="saveInputData"/>
							<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
							<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmRegistCapital" @click="clearIcon('firmRegistCapital')"></image>
						</view>
						
					</view>
					
					<!-- 营业范围 -->
					<view  class="line" >
						<view class="line-text">
							营业范围
							<view >&nbsp</view>
							：
						</view>
						<view class="uni-input" style="display: flex;">
							<input class="uni-input-item" placeholder="" v-model="uploadData.firmBusinessScope"  @input="saveInputData" />
							<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
							<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmBusinessScope" @click="clearIcon('firmBusinessScope')"></image>
						</view>
					
					</view>
					
					
					<!-- 纳税性质 -->
					<view ref="firmTaxableType" class="line">
						<view class="line-text">
							纳税性质
							<view >&nbsp*</view>
							：
						</view>
						<picker  class="uni-input" mode="selector" :range="taxableData" :value="taxableIndex" @change="pickerClick($event, 'taxableData')" >
							<view v-if="uploadData.firmTaxableType">{{ uploadData.firmTaxableType }}</view>
		
						</picker>
					</view>
					
					<!-- 注册时间 -->
					<view ref="firmEstablishDate" class="line" >
						<view class="line-text"> 
							注册时间
							<view >&nbsp*</view>
							：
						</view>
						<picker class="uni-input"  @change="pickerClick($event, 'yearData')" :value="yearIndex" :range="yearData" >
							<view v-if="uploadData.firmEstablishDate" >{{uploadData.firmEstablishDate}}</view>

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
							<input class="uni-input-item" placeholder="" v-model="uploadData.firmTaxBelong"  @input="saveInputData"/>
							<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
							<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmTaxBelong" @click="clearIcon('firmTaxBelong')"></image>
						</view>
						
					</view>
					
				</view>
				
				<view class="row" style="margin-top: 10px;">
				<view class="title" style="background-color: #ff9300;color: black;">
					<view class="scripe" style="background-color: #0088ff;"></view>
					联系人信息
				</view>
				<!-- 联系人 -->
				<view ref="firmContacts" class="line" >
					<view class="line-text">
						联系人
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="" v-model="uploadData.firmContacts"  @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContacts" @click="clearIcon('firmContacts')"></image>
					</view>
					
				</view>	
					
				<!-- 联系人电话 -->
				<view ref="firmContactsPhone" class="line" >
					<view class="line-text">电话
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="" v-model="uploadData.firmContactsPhone"  @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContactsPhone" @click="clearIcon('firmContactsPhone')"></image>
					</view>
					
				
				</view>
				
				<!-- 转让价格 -->
				<view class="line" >
					<view class="line-text">
						转让价格(元)
						<view >&nbsp</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" type="digit" v-model="uploadData.firmPriceTransfer" placeholder="未填写则表示'面议'" @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon('firmPriceTransfer')"></image>
					</view>
					
				</view>
				
			</view>
			</view>
			
			<!-- 求购 -->
			<view v-if="!isTransfer">
				<view class="row">
					<view class="title" style="background-color: #0088ff;color: black;">
						<view class="scripe" style="background-color: #ff9300;"></view>
						公司基本信息
					</view>
					
					
					<!-- 行业选择 -->
					<view class="line">
						<view class="line-text">行业
							<view >
								&nbsp*
							</view>：
						</view>
						<picker class="uni-input" mode="selector" :range="sectorsData" :value="sectorsIndex" @change="pickerClick($event, 'sectorsData')" >
							<view v-if="uploadData.firmSectorType">{{ uploadData.firmSectorType }}</view>
			
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
					
				
					
					<!-- 营业范围 -->
					<view class="line" >
						<view class="line-text">
							营业范围
							<view >&nbsp</view>
							：
						</view>
						<view class="uni-input" style="display: flex;">
							<input class="uni-input-item" placeholder="" v-model="uploadData.firmBusinessScope"  @input="saveInputData" />
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
							<view v-if="uploadData.firmTaxableType">{{ uploadData.firmTaxableType }}</view>
					
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
			
						</picker>
						
						
					</view>
					
					
				</view>
				
				<view class="row" style="margin-top: 10px;">
				<view class="title" style="background-color: #0088ff;color: black;">
					<view class="scripe" style="background-color: #ff9300;"></view>
					联系人信息
				</view>
				<!-- 联系人 -->
				<view ref="firmContacts" class="line" >
					<view class="line-text">
						联系人
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="" v-model="uploadData.firmContacts"  @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContacts" @click="clearIcon('firmContacts')"></image>
					</view>
					
				</view>	
					
				<!-- 联系人电话 -->
				<view ref="firmContactsPhone" class="line" >
					<view class="line-text">电话
						<view >&nbsp*</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" placeholder="" v-model="uploadData.firmContactsPhone"  @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmContactsPhone" @click="clearIcon('firmContactsPhone')"></image>
					</view>
					
				
				</view>
				
				<!-- 转让价格 -->
				<view  class="line" >
					<view class="line-text">
						转让价格(元)
						<view >&nbsp</view>
						：
					</view>
					<view class="uni-input" style="display: flex;">
						<input class="uni-input-item" type="digit" v-model="uploadData.firmPriceTransfer" placeholder="未填写则表示'面议'" @input="saveInputData" />
						<!-- <text class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon">&#xe434;</text> -->
						<image src="../../static/image/x.png" class="uni-icon" v-if="uploadData.firmPriceTransfer" @click="clearIcon('firmPriceTransfer')"></image>
					</view>
					
				</view>
				
			</view>
			</view>
			
			<!-- 发布按钮 -->
			<view class="bottom">
				<button class="bottom-btn" @click="saveDataFun">
					暂存
				</button>
				<button class="bottom-btn" @click="publishClick">
					我要发布
				</button>
				
			</view>
			
		
		</scroll-view>
	</view>
</template>

<script>
	import config from "@/config/config.js";
	export default {
		data() {
			return {
				// 行业
				sectorsData: [],
				sectorsIndex: 0,
				
				// 地区
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
				
				// 转让或求购
				isTransfer: 1,
				
				// 日期
				dateIndex:0,
				
				// 延时函数
				saveFun:null,
				
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
				},
				// 求购
				purchaseData: {
					firmSectorType:'',
					firmLocation:'',
					firmTaxableType:'',
					firmEstablishDate:'',
					firmBusinessScope:'',
					firmContacts:'',
					firmContactsPhone:'',
					firmPriceTransfer:'',
				},
			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '我要发布'
			});
		},
		created() {
		},
		
		onLoad() {
			let that = this;
			uni.getStorage({						// 如果有缓存则读缓存
				key:'uploadData-temp-transfer',
				success(res) {
					that.uploadData = res.data;
				}
			})
			that.init();
			
		},
		methods: {
			
			// 保存按钮
			saveDataFun(){
				this.handleSaveTemp();
				uni.showModal({
					content:"保存成功！"
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
			
			/**
			  * @title 检测输入框输入
			  * @desc 输入框输入时，创建一个2s的延时函数，用于保存已填数据，若2s内继续填写数据，则替换掉原来的缓存。
			  */
			saveInputData(){
				if (this.saveFun) {
				        clearTimeout(this.saveFun);
				      }
				      this.saveFun = setTimeout(() => {
				        this.handleSaveTemp();
				      }, 2000); // 1500 毫秒延迟
			},
			handleSaveTemp(){
				let that = this;
				console.log("进行保存");
				let tempkey = '';
				if(that.isTransfer){
					tempkey = 'uploadData-temp-transfer';
				}else{
					tempkey = 'uploadData-temp-purchase';
				}
				uni.setStorage({
					key: tempkey,
					data: that.uploadData,
					success() {
						console.log("将表格数据存入缓存！");
					}
				})
				
			},
			
			
			// 点击切换转让或求购页面
			changeSelect:function(str){
				let that = this;
				let tempkey = '';
				if(str == 'transfer'){
					if(this.isTransfer != 1){
						tempkey = 'uploadData-temp-transfer';
						this.isTransfer = 1;
						this.sectorsData.shift();
						this.yearData.shift();
						this.taxableData.shift();
						
						this.provinces.shift();
						this.cities.shift();
						this.districts.shift();
						this.multiArrary[0] = this.provinces;
						this.multiArrary[1] = this.cities[0];
						this.multiArrary[2] = this.districts[0][0];
						
						console.log();
						this.sectorsIndex = 0;
						this.taxableIndex = 0;
						this.yearIndex = 0;
						this.multiIndex = [0,0,0];
						
						Object.keys(this.uploadData).forEach(key => {
						  this.uploadData[key] = '';
						});
						
					}
				}
				if(str == 'purchase'){
					if(this.isTransfer != 0){
						tempkey = 'uploadData-temp-purchase';
						this.isTransfer = 0;
						this.sectorsData.unshift('全部');
						this.yearData.unshift('全部');
						this.taxableData.unshift('全部');
						
						this.provinces.unshift('全部');
						this.cities.unshift('');
						this.districts.unshift('');
						this.multiArrary[0] = this.provinces;
						this.multiArrary[1] = this.cities[0];
						this.multiArrary[2] = this.districts[0][0];
						
						this.sectorsIndex = 0;
						this.taxableIndex = 0;
						this.yearIndex = 0;
						this.multiIndex = [0,0,0];
						Object.keys(this.uploadData).forEach(key => {
						  this.uploadData[key] = '';
						});
					}
				}
				
				uni.getStorage({						// 如果有缓存则读缓存
					key:tempkey,
					success(res) {
						that.uploadData = res.data;
					}
				})
				
			},
			
			
			
			/**
			  * @title 发布按钮执行函数
			  * @desc 上传到接口upload/addFirmInfo，
			  * 
			  * */
			publishClick:function(){
				let that = this;
				let typeUrl = '';
				let uploadData = '';
				
				if(!that.isTransfer){
					typeUrl = 'addFirmPurchase';
					
					that.purchaseData = Object.keys(that.purchaseData).reduce((acc, key) => {
					    if (that.uploadData.hasOwnProperty(key)) {
					        acc[key] = that.uploadData[key];
					    }
					    return acc;
					}, {});
					
					uploadData = that.purchaseData
					console.log("赋值结束：",that.purchaseData);
				}else{
					typeUrl = 'addFirmInfo';
					uploadData = that.uploadData;
				}
				uni.showLoading({
					title:'请等待',
					mask:true,
				})
				
				
				let tempRuslt = that.checkInput();				// 填写必填项
				if( tempRuslt != ''){
					uni.showModal({
						content:'请填写必填项：'+tempRuslt
					})
					uni.hideLoading();
					return ;
				}
				
				if(uploadData.firmContactsPhone.length != 11){		// 正确手机号
					uni.showModal({
						content:'请输入正确的手机号！'
					})
					uni.hideLoading();
					return ;
				}
				
				console.log("upload: ",this.uploadData);
				uni.request({
					url:config.upload + typeUrl,
					method:'POST',
					data: uploadData,
					dataType:'application/json',
					success(res) {
						console.log("request res :",res.data);
						if(res.statusCode == 200){
							
							let tempkey = '';
							that.saveFun = '';
							if(that.isTransfer){
								tempkey = 'uploadData-temp-transfer';
							}else{
								tempkey = 'uploadData-temp-purchase';
							}
							uni.removeStorage({
								key:tempkey
							});
							
							uni.showModal({
								content:'提交成功！',
								success() {
									that.uploadData = [];
									uni.reLaunch({
										url:'/pages/index/index'
									})
								}
							})
							
							
						}else{
							uni.showModal({
								content:'提交失败！',
							})
						}
						
					},fail() {
						uni.showModal({
							content:'提交失败！',
						})
					},complete() {
						uni.hideLoading();
					}
				})
				
			},
			
			/**
			  * @title 判断是否填写必选项
			  * @desc 判断必选项是否填写，若未填写则跳转到相应位置
			  * 
			  * */
			checkInput(){
				let that = this;
				let ret = '';
				if(that.isTransfer){
					if(that.uploadData.firmName == ''){
						ret += '公司名称、';
					}
					if(that.uploadData.firmSectorType == ''){
						ret += '行业类型、';
					}
					if(that.uploadData.firmLocation == ''){
						ret += '注册地址、';
					}
					if(that.uploadData.firmRegistCapital == ''){
						ret += '注册资本、';
					}
					if(that.uploadData.firmTaxableType == ''){
						ret += '纳税性质、';
					}
					if(that.uploadData.firmEstablishDate == ''){
						ret += '注册时间、';
					}
				}
				if(that.uploadData.firmContacts == ''){
					ret += '联系人、';
				}
				if(that.uploadData.firmContactsPhone == ''){
					ret += '电话、';
				}
				return ret;
			},
			
			
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
						// that.uploadData.firmLocation = that.provinces[0] + "-" + that.cities[0][0] + "-" + that.districts[0][0][0] ;
					},
					fail:function(res){
						console.log("获取缓存失败，重新拉取数据!",res);
						that.uploadAreaData();
					}
				})
				// that.uploadData.firmSectorType = that.sectorsData[that.sectorsIndex];
				// that.uploadData.firmTaxableType = that.taxableData[that.taxableIndex];
				// that.uploadData.firmEstablishDate = that.yearData[that.yearIndex];
			},
			
			// 选择器改变触发函数
			pickerColumnChange:function(e){
				let that = this;
				const column = e.detail.column;
				const value = e.detail.value;
				that.multiIndex[column] = value;
				console.log('修改的列为：' + e.detail.column + '，值为：' + e.detail.value)
				console.log(that.provinces,that.districts,that.cities);
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
					if(value[0]=='0' && !that.isTransfer){
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
					this.uploadData.firmEstablishDate = this.yearData[this.yearIndex];
				}
				if(str === 'sectorsData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.sectorsIndex = e.detail.value;
					this.uploadData.firmSectorType = this.sectorsData[this.sectorsIndex];
				}
				if(str === 'taxableData'){
					console.log("选择：",str);
					console.log("值：:",e.detail.value);
					this.taxableIndex = e.detail.value;
					this.uploadData.firmTaxableType = this.taxableData[this.taxableIndex];
				}
			},
			
			
			/**
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
		height: 94vh;
		display: flex;
		flex-direction: column;
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
	.classify{
		position: sticky;
		top: 0;
		z-index: 100;
		/* height: 10%; */
		width: 100%;
		/* margin-top: 20rpx; */
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
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}
	.bottom-btn{
		/* padding: 0 50px; */
		margin: 0 8%;
		width: 40%;
		background-color: #cbc9c9;
	}

</style>
