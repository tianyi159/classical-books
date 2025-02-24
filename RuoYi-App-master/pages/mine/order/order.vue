<template>
  <view class="record-page">
    <view class="header">
      <text class="title">阅读记录</text>
    </view>

    <view class="record-list">
      <view
        class="record-item"
        v-for="(item, index) in recordList"
        :key="index"
        @click="goToDetail(item)"
      >
        <image
          :src="'http://localhost:8088' + item.ancientBookUrl"
          class="book-image"
          mode="aspectFill"
        />
        <view class="book-info">
          <view class="book-name"
            >{{ item.ancientBookName }}·{{ item.author }}</view
          >
          <view class="book-name">
            {{ item.typeName }}·{{ item.categoryName }}
          </view>
          <view class="book-stats">
            <view class="last-read">
              <u-icon name="clock" size="12" color="#999"></u-icon>
              <text>{{ item.lastUpdated }}</text>
            </view>
          </view>
        </view>
        <view class="arrow">
          <u-icon name="arrow-right" color="#999" size="16"></u-icon>
        </view>
      </view>
    </view>

    <!-- 空状态 -->
    <view class="empty-state" v-if="recordList.length === 0">
      <image src="/static/empty.png" mode="aspectFit" />
      <text>暂无阅读记录</text>
    </view>
  </view>
</template>

<script>
import { myRecord } from "@/api/guji/record";
export default {
  data() {
    return {
      recordList: [],
    };
  },
  onLoad() {
    this.getReadingRecords();
  },
  methods: {
    getReadingRecords() {
      // 这里调用你的阅读记录接口
      myRecord().then((res) => {
        this.recordList = res.rows;
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
.record-page {
  min-height: 100vh;
  background: #f8f8f8;
  padding: 20rpx;
}

.header {
  padding: 30rpx 20rpx;
  .title {
    font-size: 36rpx;
    font-weight: 600;
    color: #333;
  }
}

.record-list {
  .record-item {
    background: #fff;
    border-radius: 16rpx;
    padding: 20rpx;
    margin-bottom: 20rpx;
    display: flex;
    align-items: center;
    box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.03);
    transition: all 0.3s ease;

    &:active {
      transform: scale(0.98);
      background: #fafafa;
    }
  }
}

.book-image {
  width: 120rpx;
  height: 160rpx;
  border-radius: 8rpx;
  margin-right: 20rpx;
  box-shadow: 0 2rpx 8rpx rgba(0, 0, 0, 0.1);
}

.book-info {
  flex: 1;
  overflow: hidden;

  .book-name {
    font-size: 30rpx;
    color: #333;
    margin-bottom: 16rpx;
    font-weight: 500;
  }

  .book-stats {
    display: flex;
    align-items: center;
    font-size: 24rpx;
    color: #999;

    .views,
    .last-read {
      display: flex;
      align-items: center;
      margin-right: 20rpx;

      .u-icon {
        margin-right: 4rpx;
      }
    }
  }
}

.arrow {
  padding: 0 10rpx;
}

.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 200rpx;

  image {
    width: 200rpx;
    height: 200rpx;
    margin-bottom: 20rpx;
  }

  text {
    font-size: 28rpx;
    color: #999;
  }
}
</style>
