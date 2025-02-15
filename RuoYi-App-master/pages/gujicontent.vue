<template>
	<view class="box">
    <view class="content">
      <u-parse :content="content"></u-parse>
    </view>

    <view class="fotter">
      <view @click="prop" class="prev-page">上一页</view>
      <view class="spacer"></view>
      <view @click="next" class="next-page">下一页</view>
    </view>
	</view>
</template>

<script>
	import { listPages,ancientPoem} from '@/api/guji/guji'
	export default {
		data() {
			return {
        content: `
					<p>露从今夜白，月是故乡明</p>
				`,
        contentList:[],
        index:0
			}
		},
		methods: {
      prop(){
        if(this.index>0){
          this.index--
          this.content = this.contentList[this.index].content
        }else{
          console.log('aaaaaaa')
        }
      },
      next(){
        if(this.index<this.contentList.length-1){
          this.index++
          this.content = this.contentList[this.index].content
        }else{
          console.log('bbbbbbbbbbb')
        }
      }
		},
    onLoad: function(e) {

      //根据id查询指定的
      console.log(e.chapterId,'eeeeeeeeeeeeeeee')
      let obj = {
        chapterId:Number(e.chapterId)
      }
      listPages(obj).then(res=>{
        this.contentList = res.data
        this.content = res.data[0].content
        console.log(res,'ddddddddddddddddddddd')
      })
    }
	}
</script>

<style lang="scss" scoped>
page{
  background-color: #38352e;
  color: #fff;
}
.box{
  position: relative;
  height: 100vh;
  width: 90vw;
  margin: 0px auto;
  .content{
    padding: 24rpx;
    /* 允许单词断行 */
    word-wrap: break-word;
    /* 当单词过长时，进行断行 */
    word-break: break-word;
    line-height: 1.6;
    font-size: 6px;
    margin:20px auto;
    .content::first-line {
      text-indent: 2em; /* 缩进两个字符 */
    }
  }
  .fotter{
    position: absolute;
    bottom: 10px;
    width: 100%; // 确保footer宽度为100%
    display: flex;
    justify-content: space-between; /* 在主轴上两端对齐 */
    align-items: center; /* 垂直居中对齐 */
    .spacer {
      flex: 1; /* 使 spacer 占据剩余空间，推动 prev-page 和 next-page 到两端 */
    }
    .prev-page,.next-page {

      /* 你可以根据需要添加样式 */
    }
  }
}

</style>
