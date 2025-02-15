<template>
  <view class="contain">
    <u-search @search="getValue"  :showAction="true" placeholder="请输入古籍名称" actionText="搜索" :animation="true"></u-search>
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
        itemStyle="padding-left: 45px; padding-right: 15px; height: 34px; text-align: center;"
    ></u-tabs>
    <view class="books">
      <view @click="jumpBookDetail(item)" v-for="item in books" class="item">
        <view class="image">
          <image
              :src="`http://localhost:8088${item.url}`"
              mode=""
              style="width: 100%;height: 100%"
          ></image>
        </view>
        <view class="book_name">
          {{item.name}}
        </view>
        <view class="author">
          作者：{{item.author}}
        </view>
      </view>
    </view>
  </view>
</template>
<script>
import { listBooks } from '@/api/guji/guji'
export default {
  data() {
    return{
      list1: [{
        name: '经部',
      }, {
        name: '吏部',
      }, {
        name: '子部'
      }, {
        name: '集部'
      }],
      books:[],
      keyword: undefined,
      index:0
    }
  },
  created() {
    this.getBookList(1)
  },
  methods:{
    getValue(name){
      //调用查询接口
      let obj = {
        // type:this.index+1,
        name:name
      }
      listBooks(obj).then(res =>{
        this.books = res.data
        console.log(res,'sssssssssss')
      })
    },
    click(item) {
      this.index = item.index
      console.log('item', item);
      this.getBookList(item.index+1)
    },
    jumpBookDetail(e){
      this.$tab.navigateTo(`/pages/bookdetail?bookId=${e.bookId}`);
    },
    getBookList(type){
      let obj = {
        type:type
      }
      listBooks(obj).then(res =>{
        this.books = res.data
        console.log(res,'sssssssssss')
      })
    }
  }


}
</script>

<style scoped lang="scss">
.books{
  //border: 1px red solid;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 90vw;
  margin: 10px auto;
  .item{
    width: 48%;
    //background-color: #00afff;
    height: 170px;
    margin-bottom: 10px; /* 可选：添加垂直间距 */
    .image{
      height: 120px;
      width: 90px;
      //border: 1px red solid;
      margin: 0px auto;
      img{
        width: 100%;
        height: 100%;
      }
    }
    .book_name{
      text-align: center;
      font-weight: bold;
      font-size: 18px;
      line-height: 20px;
      margin-top: 5px;
    }
    .author{
      text-align: center;
    }
  }
}
.books .item:last-child {
  margin-right: 0;
}
.books .item:nth-child(3n) {
  margin-right: 0; /* 如果每行有三个项目，则清除每行的最后一个项目的右边距 */
}
</style>
