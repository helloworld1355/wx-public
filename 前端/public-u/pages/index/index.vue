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
					<swiper-item v-for="item in images" :key="item.id"  >
						<image :src="item.src" mode="scaleToFill"></image>
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
					<image style="width: 20px;height: 20px;" @click="clearBtn" src="../../static/image/叉.png"></image>
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
			
			<view class="scroll-firms" v-if="firms != '' || firms != undefined ">
				<view v-for="item in firms" class="firms" :key="firms.id" @click="firmClick(item.id)">
					<view class="firms-item">
						{{item.name}}
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
				images:[],
			}
		},
		onReady(){
			this.init();
			this.setctorInit();
			this.taxableInit();
			this.firmsInit();
			this.areaInit();
			
		},
		onLoad() {
			this.init();
			this.setctorInit();
			this.taxableInit();
			this.firmsInit();
			this.areaInit();
		},
		methods: {
			init:function(){
				this.images = [
					{
						id: 1,
						src:'../../static/image/4.png'
					},
					{
						id: 2,
						src:'../../static/image/4.png'
					},
					{
						id: 3,
						src:'../../static/image/4.png'
					},
					{
						id: 4,
						src:'../../static/image/4.png'
					}
				]
			},
			
			
			// 行业初始化
			setctorInit:function(){
				// 测试用
				this.sectorsData = ['全部','科技','法律','美容'];
				
				// 正式用
				
			},
			
			// 纳税性质初始化
			taxableInit:function(){
				// 测试用
				this.taxableData = ['全部','所得税','消费税','增值税'];
				
				// 正式用
			},
			
			// 公司列表初始化
			firmsInit:function(){
				this.firms = [{
						id:1,
						name:'test1'
					},
					{
						id:2,
						name:'test2'
					},
					{
						id:3,
						name:'test3'
					},
					{
						id:5,
						name:'test4'
					},
					{
						id:6,
						name:'test5'
					},
					{
						id:7,
						name:'test6'
					},
					{
						id:8,
						name:'test7'
					},
					{
						id:9,
						name:'test8'
					},
					{
						id:10,
						name:'test9'
					},
					{
						id:11,
						name:'test10'
					},
					{
						id:12,
						name:'test11'
					},]
				
				
			},
			
			// 区域初始化
			areaInit:function(){
				this.areaData = ['全部','廊坊','北京','天津'];
				
			},
			
			 bindPickerChange: function(e) {
				console.log("picker:",e);
				console.log('picker发送选择改变，携带值为', e.detail.value)
				this.index = e.detail.value
			},
			
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
		height: 28%;
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
		font-size: 35rpx;
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
		font-size: 35rpx;
		
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
		font-size:35rpx;
	}
	.select-search{
		/* flex: 1; */
		
		margin: 5px 0px 5px 5px;
		/* margin: 5px 0px 0px 0px; */
		padding-top: 0;
		padding-bottom: 0;
			font-size: 35rpx;
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
	
	
	
	
</style>
