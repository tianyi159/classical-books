<template>
  <view class="container">
    <view
      v-for="item in list"
      :key="item.ariId"
      @click="jumpGushiDetail(item)"
      class="poem-card"
    >
      <view class="poem-header">
        <view class="poem-title">{{ item.name }}</view>
        <view class="poem-meta">
          <text class="author">{{ item.author }}</text>
          <text class="dynasty">{{ item.dynastyName }}</text>
        </view>
      </view>
      <u--text
        class="poem-intro"
        size="14"
        :lines="3"
        :text="item.introduce"
      />
    </view>
  </view>
</template>
<script>
import { dictType, ancientPoemList } from "@/api/guji/guji";
export default {
  name: "gushidetail",
  data() {
    return {
      list: [],
    };
  },
  methods: {
    jumpGushiDetail(item) {
      this.$tab.navigateTo(`/pages/gushicontent?ariId=${item.ariId}`);
      console.log(item, "sadsdasdasasdsa");
    },
  },
  onLoad(e) {
    console.log(e, "ssssssssssssssss");
    if (e.author != undefined) {
      let obj = {
        author: e.author,
      };
      ancientPoemList(obj).then((res) => {
        this.list = res.data;
        console.log(this.list, "listlist");
        console.log(res, "jkjkk");
      });
    } else if (e.type != undefined) {
      let obj = {
        list: e.type,
      };
      ancientPoemList(obj).then((res) => {
        this.list = res.data;
        console.log(res, "jkjkk");
      });
      console.log(e.type);
    } else if (e.dynasty != undefined) {
      let obj = {
        dynasty: e.dynasty,
      };
      ancientPoemList(obj).then((res) => {
        this.list = res.data;
        console.log(res, "jkjkk");
      });
      console.log(e.dynasty);
    } else if (e.category != undefined) {
      let obj = {
        category: e.category,
      };
      ancientPoemList(obj).then((res) => {
        this.list = res.data;
        console.log(res, "jkjkk");
      });
    } else {
      let obj = {
        name: e.name,
      };
      ancientPoemList(obj).then((res) => {
        this.list = res.data;
        console.log(res, "jkjkk");
      });
    }
  },
};
</script>
<style scoped lang="scss">
page {
  background-color: #f5f5f5;
  min-height: 100vh;
}

.container {
  padding: 20rpx;
}

.poem-card {
  background-color: #ffffff;
  border-radius: 16rpx;
  padding: 24rpx;
  margin-bottom: 24rpx;
  box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  
  &:active {
    transform: scale(0.98);
  }
}

.poem-header {
  margin-bottom: 16rpx;
}

.poem-title {
  font-size: 32rpx;
  font-weight: 600;
  color: #333333;
  margin-bottom: 12rpx;
}

.poem-meta {
  display: flex;
  align-items: center;
  gap: 16rpx;
  
  .author {
    font-size: 28rpx;
    color: #666666;
    font-weight: 500;
  }
  
  .dynasty {
    font-size: 24rpx;
    color: #999999;
  }
}

.poem-intro {
  padding-top: 16rpx;
  border-top: 1px solid #eee;
  font-size: 26rpx;
  line-height: 1.6;
  color: #666666;
}
</style>
