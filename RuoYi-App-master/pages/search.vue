<template>
  <view class="search-result">
    <view class="book-list">
      <view
        class="book-item"
        v-for="(item, index) in bookList"
        :key="index"
        @click="goToDetail(item)"
      >
        <image
          class="book-cover"
          :src="'http://localhost:8088' + item.url"
          mode="aspectFill"
        />
        <view class="book-info">
          <text class="book-name">{{ item.name }}</text>
          <view class="book-meta">
            <view class="meta-item">
              <u-icon name="eye" size="12" color="#999"></u-icon>
              <text>{{ item.author }}</text>
            </view>
            <view class="meta-item">
              <u-icon name="bookmark" size="12" color="#999"></u-icon>
              <text>{{ item.typeName }} · {{ item.categoryName }}</text>
            </view>
          </view>
          <text class="book-desc">{{ item.introduce || "暂无简介" }}</text>
        </view>
      </view>
    </view>

    <!-- 空状态 -->
    <view class="empty-state" v-if="bookList.length === 0">
      <image src="/static/empty-search.png" mode="aspectFit" />
      <text>未找到相关书籍</text>
    </view>
  </view>
</template>

<script>
import { listBooks as listBooksApi } from "@/api/guji/guji";
export default {
  data() {
    return {
      bookList: [],
      keyword: "",
    };
  },
  onLoad(options) {
    this.keyword = options.keyword;
    this.searchBooks();
  },
  methods: {
    searchBooks() {
      // 这里调用你的搜索接口
      // TODO: 实现实际的搜索逻辑
      listBooksApi({
        name: this.keyword,
      }).then((res) => {
        this.bookList = res.data;
      });
    },
    goToDetail(item) {
      uni.navigateTo({
        url: `/pages/bookdetail?bookId=${item.bookId}`,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.search-result {
  min-height: 100vh;
  background: #f8f8f8;
  padding: 20rpx;
}

.book-list {
  .book-item {
    background: #fff;
    border-radius: 16rpx;
    padding: 24rpx;
    margin-bottom: 20rpx;
    display: flex;
    box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.03);
    transition: all 0.3s ease;

    &:active {
      transform: translateY(2rpx);
      background: #fafafa;
    }
  }
}

.book-cover {
  width: 160rpx;
  height: 220rpx;
  border-radius: 8rpx;
  margin-right: 24rpx;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
}

.book-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;

  .book-name {
    font-size: 32rpx;
    color: #333;
    font-weight: 500;
    margin-bottom: 12rpx;
  }

  .book-meta {
    display: flex;
    align-items: center;
    margin-bottom: 12rpx;

    .meta-item {
      display: flex;
      align-items: center;
      margin-right: 24rpx;
      font-size: 24rpx;
      color: #999;

      .u-icon {
        margin-right: 4rpx;
      }
    }
  }

  .book-desc {
    font-size: 26rpx;
    color: #666;
    line-height: 1.5;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }
}

.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 200rpx;

  image {
    width: 240rpx;
    height: 240rpx;
    margin-bottom: 20rpx;
  }

  text {
    font-size: 28rpx;
    color: #999;
  }
}

// 加载更多
.load-more {
  text-align: center;
  padding: 20rpx 0;
  color: #999;
  font-size: 24rpx;
}
</style>
