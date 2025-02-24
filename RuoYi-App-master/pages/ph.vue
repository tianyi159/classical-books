<template>
  <view class="ranking-page">
    <view class="header">
      <text class="title">古籍阅读排行榜</text>
      <text class="subtitle">最受欢迎的典籍</text>
    </view>
    <view class="ranking-list">
      <view
        v-for="(item, index) in rankList"
        :key="index"
        class="ranking-item"
        @click="goToDetail(item)"
      >
        <view class="rank-number" :class="{ 'top-three': index < 3 }">
          {{ index + 1 }}
        </view>
        <view class="book-info">
          <text class="book-name">{{ item.ancientBookName }}</text>
          <view class="views-count">
            <u-icon name="eye" size="14"></u-icon>
            <text>{{ item.views }}次阅读</text>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { ph } from "@/api/guji/views";

export default {
  data() {
    return {
      rankList: [],
    };
  },
  onLoad() {
    this.getRankingList();
  },
  methods: {
    getRankingList() {
      ph()
        .then((res) => {
          console.log("排行榜数据:", res);
          if (res.code === 200) {
            this.rankList = res.rows;
          }
        })
        .catch((err) => {
          console.error("获取排行榜数据失败:", err);
        });
    },
    goToDetail(item) {
      uni.navigateTo({
        url: `/pages/bookdetail?bookId=${item.ancientBookId}`,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.ranking-page {
  min-height: 100vh;
  background: #f8f8f8;
  padding: 20rpx;
}

.header {
  padding: 40rpx 20rpx;
  text-align: center;

  .title {
    font-size: 36rpx;
    font-weight: bold;
    color: #333;
    display: block;
  }

  .subtitle {
    font-size: 24rpx;
    color: #999;
    margin-top: 10rpx;
    display: block;
  }
}

.ranking-list {
  background: #fff;
  border-radius: 16rpx;
  padding: 20rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.05);
}

.ranking-item {
  display: flex;
  align-items: center;
  padding: 30rpx 20rpx;
  border-bottom: 1px solid #f5f5f5;

  &:last-child {
    border-bottom: none;
  }

  &:active {
    background: #f9f9f9;
  }
}

.rank-number {
  width: 50rpx;
  height: 50rpx;
  border-radius: 25rpx;
  background: #eee;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24rpx;
  color: #666;
  margin-right: 20rpx;

  &.top-three {
    color: #fff;
    font-weight: bold;

    &:nth-child(1) {
      background: linear-gradient(45deg, #ffd700, #ffa500);
    }

    &:nth-child(2) {
      background: linear-gradient(45deg, #c0c0c0, #a9a9a9);
    }

    &:nth-child(3) {
      background: linear-gradient(45deg, #cd7f32, #8b4513);
    }
  }
}

.book-info {
  flex: 1;

  .book-name {
    font-size: 28rpx;
    color: #333;
    margin-bottom: 8rpx;
  }

  .views-count {
    display: flex;
    align-items: center;
    font-size: 24rpx;
    color: #999;

    .u-icon {
      margin-right: 6rpx;
    }
  }
}
</style>
