<template>
  <view class="container">
    <u-search
      @search="search"
      v-model="searchValue"
      @custom="search"
      :showAction="true"
      placeholder="请输入古诗名称"
      actionText="搜索"
      :animation="true"
      class="search-box"
      shape="round"
      :clearabled="true"
      searchIconColor="#409EFF"
      placeholderColor="#999"
      color="#333"
      bgColor="#ffffff"
      height="100rpx"
      actionStyle="color: #409EFF; font-size: 28rpx; font-weight: 500;"
    ></u-search>

    <view class="category-section" style="margin-top: 20px;">
      <view class="section-title">
        <text class="title-text">作者</text>
        <view class="title-line"></view>
      </view>
      <u-grid :border="false">
        <u-grid-item
          v-for="(baseListItem, baseListIndex) in list"
          :key="baseListIndex"
          class="grid-item"
        >
          <text @click="click1(baseListItem)" class="item-box">{{
            baseListItem.author
          }}</text>
        </u-grid-item>
      </u-grid>
    </view>

    <view class="category-section">
      <view class="section-title">
        <text class="title-text">合集</text>
        <view class="title-line"></view>
      </view>
      <u-grid :border="false">
        <u-grid-item
          v-for="(baseListItem, baseListIndex) in poemType"
          :key="baseListIndex"
          class="grid-item"
        >
          <text @click="click2(baseListItem)" class="item-box">{{
            baseListItem.dictLabel
          }}</text>
        </u-grid-item>
      </u-grid>
    </view>

    <view class="category-section">
      <view class="section-title">
        <text class="title-text">朝代</text>
        <view class="title-line"></view>
      </view>
      <u-grid :border="false">
        <u-grid-item
          v-for="(item, baseListIndex) in dynastyList"
          :key="baseListIndex"
          class="grid-item"
        >
          <text @click="click3(item)" class="item-box">{{
            item.dictLabel
          }}</text>
        </u-grid-item>
      </u-grid>
    </view>

    <view class="category-section">
      <view class="section-title">
        <text class="title-text">分类</text>
        <view class="title-line"></view>
      </view>
      <u-grid :border="false">
        <u-grid-item
          v-for="(item, index) in ponemCategory"
          :key="index"
          class="grid-item"
        >
          <text @click="click4(item)" class="item-box">{{
            item.dictLabel
          }}</text>
        </u-grid-item>
      </u-grid>
    </view>
  </view>
</template>
<script>
import { dictType, ancientPoemList } from "@/api/guji/guji";
export default {
  name: "index",
  data() {
    return {
      dynastyList: [],
      poemType: [],
      ponemCategory: [],
      list: [],
    };
  },
  methods: {
    //作者
    click1(item) {
      console.log(item.author);
      this.$tab.navigateTo(`/pages/gushidetail?author=${item.author}`);
    },
    click2(item) {
      console.log(item.dictLabel);
      this.$tab.navigateTo(`/pages/gushidetail?type=${item.dictValue}`);
    },
    click3(item) {
      console.log(item);
      this.$tab.navigateTo(`/pages/gushidetail?dynasty=${item.dictValue}`);
    },
    click4(item) {
      console.log(item.dictLabel);
      this.$tab.navigateTo(`/pages/gushidetail?category=${item.dictValue}`);
    },
    search(e) {
      uni.navigateTo({
        url: `/pages/gushidetail?name=${this.searchValue}`,
      });
    },
  },
  onLoad() {
    let obj = {};
    ancientPoemList(obj).then((res) => {
      const uniqueAuthors = res.data.reduce((acc, current) => {
        if (!acc.some((item) => item.author === current.author)) {
          acc.push({ author: current.author });
        }
        return acc;
      }, []);
      this.list = uniqueAuthors;
      // console.log(uniqueAuthors);
      // console.log(res,'redssss')
    });
    //古诗分类
    dictType("ponem_category").then((res) => {
      this.ponemCategory = res.data;
    });
    //朝代
    dictType("poem_dynasty").then((res) => {
      this.dynastyList = res.data;
    });
    //合集
    dictType("poem_type").then((res) => {
      this.poemType = res.data;
    });
  },
};
</script>
<style scoped lang="scss">
.container {
  padding: 30rpx;
  background-color: #f9f9f9;
  min-height: 100vh;
}

.search-box {
  margin-bottom: 40rpx;

  :deep(.u-search) {
    background-color: #fff !important;
    padding: 12rpx 16rpx;
    border-radius: 16rpx;
    box-shadow: 0 8rpx 20rpx rgba(64, 158, 255, 0.08);

    .u-search__content {
      background-color: #eef2f8 !important;
      border: 2rpx solid transparent;

      &:hover,
      &:focus-within {
        border-color: #409eff;
        background-color: #f5f8fc !important;
      }
    }

    .u-search__action {
      font-weight: 500;
      padding-left: 16rpx;

      &:active {
        opacity: 0.8;
      }
    }

    .u-search__input {
      font-size: 26rpx;
    }
  }
}

.category-section {
  background-color: #fff;
  border-radius: 16rpx;
  padding: 30rpx;
  margin-bottom: 30rpx;
  box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.04);

  &:hover {
    transform: translateY(-2rpx);
    transition: all 0.3s ease;
  }
}

.section-title {
  display: flex;
  align-items: center;
  margin-bottom: 30rpx;

  .title-text {
    color: #2c3e50;
    font-size: 34rpx;
    font-weight: 600;
    margin-right: 24rpx;
    position: relative;
    padding-left: 24rpx;

    &::before {
      content: "";
      position: absolute;
      left: 0;
      top: 50%;
      transform: translateY(-50%);
      width: 8rpx;
      height: 32rpx;
      background-color: #409eff;
      border-radius: 4rpx;
    }
  }

  .title-line {
    flex: 1;
    height: 2rpx;
    // background: linear-gradient(to right, #409EFF 0%, rgba(64,158,255,0.1) 100%);
  }
}

.grid-item {
  padding: 12rpx;
}

.item-box {
  display: inline-block;
  background-color: #f8fafc;
  padding: 16rpx 24rpx;
  border-radius: 8rpx;
  text-align: center;
  font-size: 28rpx;
  color: #4a5568;
  transition: all 0.2s ease;
  border: 1rpx solid rgba(0, 0, 0, 0.05);

  &:active {
    background-color: #409eff;
    color: #ffffff;
    transform: scale(0.98);
  }
}

// 添加响应式布局
@media screen and (min-width: 768px) {
  .container {
    padding: 40rpx;
  }

  .category-section {
    padding: 40rpx;
  }

  .item-box {
    font-size: 30rpx;
    padding: 20rpx 28rpx;
  }
}
</style>
