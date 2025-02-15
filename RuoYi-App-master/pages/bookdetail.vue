<template>
	<view>
    <view class="top">
        <view class="book_image">
          <image
              style="width: 90px;height: 90px; margin-top: 50px"
              :src="`http://localhost:8088${detail.url}`"
          ></image>
        </view>
      <view class="name">
        {{detail.name}}
      </view>
      <view class="author">
        作者：{{detail.author}}
      </view>
    </view>
    <u-tabs
        :list="list1"
        @click="click"
        :activeStyle="{
            color: '#303133',
            fontWeight: 'bold',
            transform: 'scale(1.05)'
        }"
        lineColor="#f56c6c"
        lineWidth="60"
        :inactiveStyle="{
            color: '#606266',
            transform: 'scale(1)'
        }"
        itemStyle="padding-left: 30px; padding-right: 15px; height: 34px; text-align: center;"
    ></u-tabs>
    <view v-if="index===0">
      <u--text style="padding: 10px" :text="detail.introduce"></u--text>
<!--      {{detail.introduce}}-->
    </view>
    <view v-else-if="index===1">
      <u-cell-group v-for="(item, index) in chapter" :key="index">
        <u-cell @click="jumpItem(item)" :title="index+1 + '、' + item.chapterName"></u-cell>
      </u-cell-group>
    </view>
    <view v-else-if="index===2">
      <u-button @click="shoucang" style="width: 95vw;margin-top: 20px" :text="text" color="linear-gradient(to right, rgb(66, 83, 216), rgb(213, 51, 186))"></u-button>
    </view>
    <u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
import { listBooks,listChapters,getBook,addShelf,shelfList,deleteShelf} from '@/api/guji/guji'
	export default {
		data() {
			return {
        list1: [{
          name: '简介',
        }, {
          name: '目录',
        }, {
          name: '收藏'
        }],
        index:0,
        detail:undefined,
        chapter:undefined,
        text:'已收藏',
        shelfId:undefined
			}
		},
		methods: {
      shoucang(){
        if(this.text=='已收藏'){
          //取消收藏
          deleteShelf(this.shelfId).then(res=>{
            this.$refs.uToast.show({
              // Toast配置项
              message: '取消收藏',
              type: 'default',
              title: "默认主题",
            });
            console.log(res,'取消结果')
            this.text = '收藏'
          })
        }else{
          let obj = {
            bookId: this.detail.bookId
          }
          addShelf(obj).then(res=>{
            this.$refs.uToast.show({
              // Toast配置项
              message: '收藏成功',
              type: 'success',
              title: "默认主题",
            });
            this.text = '已收藏'
            this.shelfId = res.data.shelfId
            console.log(res,'收藏结果')
          })
        }
      },
      click(item) {
        this.index = item.index
        console.log('item', item);
      },
      jumpItem(item){
        let chapterId = item.chapterId
        this.$tab.navigateTo(`/pages/gujicontent?chapterId=${chapterId}`);
      },
      checkShouStatus(bookId){
        let obj = {
          bookId:bookId
        }
        shelfList(obj).then(res=>{
          if(res.data.length==0){
            this.text = '收藏'
          }else{
            this.text = '已收藏'
            this.shelfId = res.data[0].shelfId
          }
          console.log(res,'状态')
        })
      }
		},
    onLoad(e){
      this.checkShouStatus(e.bookId)
      //根据bookId查询章节
      //根据bookId查询每本书详情
      getBook(e.bookId).then(res=>{
        this.detail = res.data
        console.log(res,'每本书详情')
      })
      let obj = {
        bookId:Number(e.bookId)
      }
      listChapters(obj).then(res => {
        this.chapter = res.data;
        console.log(res)
      })
      // console.log(e)
    }
	}
</script>

<style lang="scss"  scoped>
.top{
  //border: 1px solid #997575;
  .book_image{
    width: 100px;
    //border: 1px solid red;
    height: 160px;
    margin: 0px auto;
  }
  .name{
    text-align: center;
  }
  .author{
    text-align: center;
  }
}



</style>
