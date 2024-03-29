<template>
	<view class="content">
		<scroll-view class="scroll-contain" scroll-y="true">
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
				<button class="bottom-btn delete" @click="deleteUpdate">
					删除
				</button>
				<button class="bottom-btn" @click="modifyUpload">
					修改
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
				uploadData:'',
				preUploadData:'',
				isTransfer:'',
			}
		},
		onLoad(event) {
			this.isTransfer = event.isTransfer;
			console.log("this.isTransfer",this.isTransfer);
			this.init(event.id);
		},
		methods: {
			// 清除输入框按键
			clearIcon:function(test){
				if(this.uploadData.hasOwnProperty(test)){
					console.log("存在",this.uploadData[test]);
					this.uploadData[test] = '';
				}else {
					console.log("不存在");
				}
			},
			
			/** @title 数据初始化
			  *
			  */
			init(id){
				let that = this;
				let tempurl = '';
				
				if(that.isTransfer == 1){
					tempurl = 'firmInfoItem';
				}else{
					tempurl = 'firmPurchaseItem';
				}
				
				uni.request({
					url: config.domain + tempurl,
					method:'POST',
					data:id,
					dataType:'application/json',
					success(res) {
						that.uploadData = JSON.parse(res.data).data;
						that.preUploadData = that.uploadData;
						console.log("res::",that.uploadData);
					}
					
				})
			},
			
			/** @title 修改提交 
			*/
			modifyUpload(){
				let that = this;
				
				let tempurl = '';
				if(that.isTransfer == 1){
					tempurl = 'modifyFirmInfo';
				}else{
					tempurl = 'modifyFirmPurchase';
				}
				uni.showModal({
					content: '确定要修改吗？',
					success: function (res) {
						if (res.confirm) {
							console.log('用户点击确定');
							
							uni.showLoading({
								title:"请稍等",
								mask:true
							})
							
							uni.request({
								url: config.upload + tempurl,
								method:'POST',
								data: that.uploadData,
								dataType:'application/json',
								success(res) {
									console.log("modify::",res);
									let temp = JSON.parse(res.data);
									console.log("temp ",temp);
									if(temp.code == 200){
										uni.showModal({
											content: '修改成功！',
											success: function (res) {
												uni.hideLoading();
												uni.navigateBack();
											}
										});

									}
									
								}
							})
							
							uni.hideLoading();
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
				
			},
			deleteUpdate(){
				let that = this;
				let tempurl = '';
				if(that.isTransfer == 1){
					tempurl = 'deleteFirmInfo';
				}else{
					tempurl = 'deleteFirmPurchase';
				}
				uni.showModal({
					content: '确定删除吗？',
					success: function (res) {
						if (res.confirm) {
							console.log('用户点击确定');
							
							uni.showLoading({
								title:"请稍等",
								mask:true
							})
							
							uni.request({
								url: config.domain + tempurl,
								method:'POST',
								data: that.uploadData.id,
								dataType:'application/json',
								success(res) {
									console.log("modify::",res);
									let temp = JSON.parse(res.data);
									console.log("temp ",temp);
									if(temp.code == 200){
										uni.showModal({
											content: '删除成功！',
											success: function (res) {
												uni.hideLoading();
												uni.navigateBack();
											}
										});
										
									}
								}
							})
							
							uni.hideLoading();
							
							
						} else if (res.cancel) {
							console.log('用户点击取消');
							
							
							
						}
					}
				});
			}
			
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
		background-color: #89d961;
	}
	.delete{
		background-color: #f13434;
	}
</style>
