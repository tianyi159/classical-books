<template>
  <view class="contain">
    <view class="search-container">
      <u-search
        @search="search"
        @custom="search"
        :showAction="true"
        v-model="searchValue"
        placeholder="输入古籍名称"
        actionText="搜索"
        :animation="true"
        class="custom-search"
      ></u-search>
    </view>
    <u-tabs
      :list="list1"
      @click="click"
      :activeStyle="{
        color: '#d43f3a',
        fontWeight: 'bold',
      }"
      lineColor="#d43f3a"
      lineWidth="60"
      :inactiveStyle="{
        color: '#303133',
      }"
      itemStyle="padding: 0 20px; height: 34px; text-align: center;"
    ></u-tabs>
    <view style="padding: 10px">
      <view
        class="category_btn"
        hover-class="none"
        hover-stop-propagation="false"
        @click="closeCategory = !closeCategory"
      >
        {{ closeCategory ? "展开分类" : "收起分类" }}
      </view>
    </view>
    <view class="context">
      <transition name="fade">
        <view class="category" v-show="!closeCategory">
          <view
            :class="
              currentIndex == null ? 'active category_item' : 'category_item'
            "
            @click="listBooks('')"
            >全部</view
          >
          <view
            :class="
              currentIndex == index ? 'active category_item' : 'category_item '
            "
            v-for="item in categoryList"
            :key="item.id"
            @click="listBooks(item.categoryId, index)"
            >{{ item.categoryName }}</view
          >
        </view>
      </transition>
      <view class="books">
        <view
          @click="jumpBookDetail(item)"
          v-for="item in books"
          :key="item"
          class="item"
        >
          <view class="image">
            <image
              :src="'http://localhost:8088' + item.url"
              mode=""
              style="width: 100%; height: 100%"
            ></image>
          </view>
          <view class="author" style="font-size: 13px;">{{ item.name }}</view>
          <view class="author">作者：{{ item.author }}</view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { listBooks as listBooksApi } from "@/api/guji/guji";
import { getCategory, listCategory } from "@/api/guji/category";

export default {
  data() {
    return {
      closeCategory: false,
      list1: [
        { name: "经部" },
        { name: "史部" },
        { name: "子部" },
        { name: "集部" },
      ],
      books: [],
      keyword: undefined,
      categoryList: [],
      currentIndex: null,
      index: 0,
      searchValue: "",
    };
  },
  created() {
    this.getBookList(1);
  },
  methods: {
    search() {
      uni.navigateTo({
        url: "/pages/search?keyword=" + this.searchValue,
      });
    },
    getValue(name) {
      let obj = { name: name };
      listBooksApi(obj).then((res) => {
        this.books = res.data;
      });
    },
    click(item) {
      this.index = item.index;
      this.getBookList(item.index + 1);
    },
    listBooks(id, index) {
      listBooksApi({ categoryId: id, type: this.index + 1 }).then((res) => {
        this.books = res.data;
        this.currentIndex = index;
      });
    },
    jumpBookDetail(e) {
      this.$tab.navigateTo(`/pages/bookdetail?bookId=${e.bookId}`);
    },
    getBookList(type) {
      let obj = { type: type };
      listCategory({ type: type }).then((res) => {
        this.categoryList = res.rows;
        this.listBooks(type);
      });
    },
  },
};
</script>

<style scoped lang="scss">
.search-container {
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 10px;
  margin: 10px auto;
  width: 90vw;
}

.custom-search {
  border: 1px solid #d43f3a;
  border-radius: 20px;
  padding: 5px 10px;
  background-color: #fff;
}

.books {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: 90vw;
  margin: 10px auto;
  .item {
    width: 48%;
    height: 200px;
    margin-bottom: 20px;
    .image {
      height: 160px;
      width: 120px;
      margin: 0 auto;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .book_name {
      text-align: center;
      font-weight: bold;
      font-size: 16px;
      margin-top: 5px;
    }
    .author {
      text-align: center;
      font-size: 18px;
      color: #3f2f25;
      font-weight: bold;
      margin-top: 10px;
    }
  }
}
.context {
  display: flex;
  justify-content: space-between;
  .category {
    width: 180px;
    height: 100%;
    text-align: center;
    transition: all 0.3s;
    background-color: #f5f5f5;
    .category_item {
      padding: 20px;
      color: #6d6d6d;
    }
    .active {
      color: #a63c29;
      position: relative;
    }
    .active::before {
      content: "";
      display: block;
      width: 1px;
      height: 30%;
      background-color: #a63c29;
      position: absolute;
      top: 50%;
      border-radius: 50%;
      transform: translateY(-50%);
      right: 0px;
    }
  }
}
.category_btn {
  width: 100px;
  height: 30px;
  background-color: #a63c29;
  color: #fff;
  border-radius: 20px;
  text-align: center;
  line-height: 30px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}
</style>
