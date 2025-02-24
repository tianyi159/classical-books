<template>
  <view class="container">
    <view class="books">
      <view @click="jumpBookDetail(item)" v-for="item in books" class="item" :key="item.id">
        <view class="image">
          <!-- <image
              :src="`http://localhost:8088${item.url}`"
              mode=""
              style="width: 100%;height: 100%"
          ></image> -->
          <image
              src="/static/images/reading2.png"
              mode="heightFix"
              style="width: 100%;height: 100%"
          ></image>
        </view>
        <view class="book_info">
          <view class="book_name">{{item.name}}</view>
          <view class="author">作者：{{item.author}}</view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import {shelfList} from "@/api/guji/guji";
  export default {
    data() {
      return {
        books:[]
      }
    },
    methods: {
      click(item) {
        console.log('item', item);
      },
      jumpBookDetail(e){
        this.$tab.navigateTo(`/pages/bookdetail?bookId=${e.bookId}`);
      }
    },
    onLoad() {
      shelfList().then(res=>{
        this.books = res.data
        console.log(res,'sadsaasddsadsadsadsadsa')
      })
    },
    onShow(){
      shelfList().then(res=>{
        this.books = res.data
        console.log(res,'sadsaasddsadsadsadsadsa')
      })
    }

  }
</script>

<style lang="scss" scoped>
.container {
  background-color: #f5f6fa;
  min-height: 100vh;
  padding: 20rpx 0;
}

.books {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 92vw;
  margin: 0 auto;
  
  .item {
    width: 45%;
    background-color: #ffffff;
    border-radius: 12rpx;
    padding: 20rpx;
    margin-bottom: 20rpx;
    box-shadow: 0 2rpx 10rpx rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    
    &:active {
      transform: scale(0.98);
    }
    
    .image {
      height: 240rpx;
      width: 180rpx;
      margin: 0 auto;
      border-radius: 8rpx;
      overflow: hidden;
      box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.15);
      
      image {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
    
    .book_info {
      padding: 16rpx 0;
      
      .book_name {
        text-align: center;
        font-weight: 600;
        font-size: 32rpx;
        color: #2c3e50;
        line-height: 1.4;
        margin-bottom: 8rpx;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
      }
      
      .author {
        text-align: center;
        font-size: 26rpx;
        color: #7f8c8d;
      }
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
