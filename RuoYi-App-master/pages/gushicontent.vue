<template>
	<view class="container">
		<view class="poetry-card">
			<view class="title">
				{{detail.name}}
			</view>
			<view class="author-info">
				<text class="dynasty">{{detail.dynastyName}}</text>
				<text class="dot">·</text>
				<text class="author-name">{{detail.author}}</text>
			</view>
			<view class="content">
				<u-parse :content="detail.content"></u-parse>
			</view>
		</view>
	</view>
</template>

<script>
import { listPages,ancientPoem} from '@/api/guji/guji'
	export default {
		data() {
			return {
        detail:undefined
			}
		},
		methods: {

		},
    onLoad(e) {
      ancientPoem(e.ariId).then(res=>{
        this.detail = res.data
        console.log(res,'resresresres')
      })
      console.log(e,';;;;;;;;;;;;;;;;;')
    },
	}
</script>

<style lang="scss" scoped>
page {
  background-color: #f5f5f5;
}

.container {
  padding: 20px;
  min-height: 100vh;
}

.poetry-card {
  background-color: #fff;
  border-radius: 16px;
  padding: 30px 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  
  .title {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: #333;
    margin-bottom: 16px;
    letter-spacing: 2px;
  }
  
  .author-info {
    text-align: center;
    margin-bottom: 30px;
    font-size: 15px;
    color: #666;
    
    .dynasty, .author-name {
      color: #888;
    }
    
    .dot {
      margin: 0 8px;
      color: #999;
    }
  }
  
  .content {
    margin: 0 auto;
    width: 80%;
    line-height: 2;
    
    :deep(p) {
      margin-bottom: 16px;
      font-size: 18px;
      color: #444;
      text-align: center;
    }
  }
}
</style>
