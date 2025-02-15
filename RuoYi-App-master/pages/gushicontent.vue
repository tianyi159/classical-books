<template>
	<view class="box">
    <view class="tit">
      {{detail.name}}
    </view>
    <view class="author">
     {{detail.dynasty}}   {{detail.author}}
    </view>
    <view class="content">
      <u-parse :content="detail.content"></u-parse>
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
page{
  background-color: #d1cec5;
}
.box{
  .tit{
    text-align: center;
    font-size: 14px;
    font-weight: 700;
  }
  .author{
    text-align: right;
    padding-right: 20px;
    color: #545252;
    margin-top: 10px;
  }
  .content{
    text-align: center;
    width: 50vw;
    //border: 1px saddlebrown solid;
    font-weight: 700;
    /* 允许单词断行 */
    word-wrap: break-word;
    /* 当单词过长时，进行断行 */
    word-break: break-word;
    /* 保持英文单词完整性，但中文无需此设置 */
    white-space: pre-wrap; /* 或者使用 'normal' */
    /* 基本文本样式，根据需要调整 */
    font-size: 16px;
    line-height: 1.6;
    margin:10px auto;
  }
}

</style>
