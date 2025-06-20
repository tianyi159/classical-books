<template>
  <view
    class="box"
    :style="{
      backgroundColor: currentThemeStyle.bg,
      color: currentThemeStyle.color,
    }"
  >
    <!-- 添加导航栏 -->
    <view class="nav-bar" style="margin-top: 60rpx;">
      <view class="left" @click="goBack">
        <u-icon name="arrow-left" size="32" color="#fff"></u-icon>
      </view>
      <view class="title">{{ chapterTitle || "阅读" }}</view>
      <view class="right" @click="showSettings">
        <u-icon name="setting" size="32" color="#fff"></u-icon>
      </view>
    </view>

    <view class="content">
      <scroll-view
        :scroll-top="scrollY"
        @scroll="scroll"
        scroll-y
        class="content-scroll"
        scroll-with-animation
        style="height: 80vh"
      >
        <u-parse
          :content="content"
          :style="{ fontSize: fontSize + 'rpx' }"
        ></u-parse>
      </scroll-view>
    </view>

    <view class="fotter">
      <view @click="prop" class="prev-page">上一章</view>
      <view class="spacer"></view>
      <view @click="next" class="next-page">下一章</view>
    </view>

    <!-- 添加设置弹出框 -->
    <u-popup
      :show="showSettingPopup"
      mode="bottom"
      @close="closeSettings"
      style="background: none !important"
    >
      <view class="settings-panel">
        <!-- 顶部功能区 -->
        <view class="function-row">
          <view class="function-item" @click="prop">
            <u-icon name="arrow-left" size="30"></u-icon>
            <text>上一章</text>
          </view>
          <view class="function-item" @click="toggleChapterList">
            <u-icon
              :name="showChapterListView ? 'arrow-down' : 'list'"
              size="30"
            ></u-icon>
            <text>{{ showChapterListView ? "收起目录" : "目录" }}</text>
          </view>
          <view class="function-item" @click="addBookmark">
            <u-icon
              :name="showBookmarkListView ? 'arrow-down' : 'bookmark'"
              size="30"
            ></u-icon>
            <text>{{ showBookmarkListView ? "收起书签" : "书签" }}</text>
          </view>
          <view class="function-item" @click="next">
            <u-icon name="arrow-right" size="30"></u-icon>
            <text>下一章</text>
          </view>
        </view>

        <!-- 目录列表 -->
        <template v-if="showChapterListView">
          <view class="chapter-list">
            <scroll-view scroll-y class="chapter-scroll">
              <view
                v-for="(item, idx) in chapterList"
                :key="idx"
                class="chapter-item"
                :class="{ active: index === idx }"
                @click="jumpToChapter(idx)"
              >
                <text>{{ item.name }} · {{ item.chapterName }}</text>
              </view>
            </scroll-view>
          </view>
        </template>

        <!-- 书签列表 -->
        <template v-if="showBookmarkListView">
          <view class="chapter-list">
            <u-button @click="addMarks">添加书签</u-button>
            <scroll-view scroll-y class="chapter-scroll">
              <view
                @click="toPosition(bookmark)"
                v-for="(bookmark, idx) in bookmarkList"
                :key="idx"
                class="chapter-item bookmark-item"
              >
                <view
                  class="bookmark-content"
                  @click="jumpToBookmark(bookmark)"
                >
                  <text class="bookmark-title"
                    >{{ bookmark.ancientBookName }} -
                    {{ bookmark.chapterName }}</text
                  >
                  <text class="bookmark-time">{{
                    new Date(bookmark.createdAt).toLocaleString()
                  }}</text>
                  <text class="bookmark-time"
                    >位置:{{ bookmark.yAddress }}
                  </text>
                </view>
                <view
                  class="bookmark-delete"
                  @click.stop="removeBookmark(bookmark.bookmarkId)"
                >
                  <u-icon name="trash" size="24" color="#999"></u-icon>
                </view>
              </view>
            </scroll-view>
          </view>
        </template>

        <!-- 中间调节区 -->
        <template v-if="!showChapterListView && !showBookmarkListView">
          <view class="adjust-section">
            <view class="adjust-item">
              <text>亮度</text>
              <slider
                :value="brightness"
                @change="changeBrightness"
                @changing="changeBrightness"
                min="0"
                max="100"
                show-value
                block-size="20"
              ></slider>
            </view>
            <view class="adjust-item">
              <text>字号</text>
              <u-slider
                v-model="fontSize"
                :min="24"
                :max="48"
                @change="changeFontSize"
              ></u-slider>
            </view>
          </view>

          <!-- 底部背景选择 -->
          <view class="theme-section">
            <text class="theme-title">背景主题</text>
            <view class="theme-list">
              <view
                v-for="(theme, index) in themes"
                :key="index"
                class="theme-item"
                :style="{ backgroundColor: theme.bg }"
                @click="changeTheme(theme)"
                :class="{ active: currentTheme === theme.name }"
              ></view>
            </view>
          </view>
        </template>
      </view>
    </u-popup>
  </view>
</template>

<script>
import { listPages, ancientPoem, listChapters } from "@/api/guji/guji";
import {
  addBookmarks,
  listBookmarks,
  myMarks,
  delBookmarks,
} from "@/api/guji/bookmarks";
import { addRecord, views } from "@/api/guji/record";
export default {
  data() {
    return {
      scrollY: 0,
      content: `
					<p>露从今夜白，月是故乡明</p>
				`,
      contentList: [],
      chapterList: [],
      index: 0,
      currentScrollY: 0,
      chapterTitle: "", // 添加章节标题
      showSettingPopup: false,
      brightness: 50,
      fontSize: 32,
      currentTheme: "default",
      themes: [
        { name: "default", bg: "#f5e6d3", color: "#2c3e50" },
        { name: "green", bg: "#e8f3e6", color: "#2c3e50" },
        { name: "blue", bg: "#e6f0f5", color: "#2c3e50" },
        { name: "gray", bg: "#f0f0f0", color: "#2c3e50" },
      ],
      showChapterListView: false,
      showBookmarkListView: false,
      bookmarkList: [],
    };
  },
  computed: {
    currentThemeStyle() {
      return (
        this.themes.find((theme) => theme.name === this.currentTheme) ||
        this.themes[0]
      );
    },
  },
  methods: {
    scroll(e) {
      this.currentScrollY = e.detail.scrollTop;
    },
    toPosition(bookmark) {
      // 切换章节
      this.toggerInit(bookmark.chapterId, bookmark.yAddress);
    },
    async toggerInit(chapterId, yAddress) {
      const res = await listPages({ chapterId: chapterId });
      this.content = "";
      res.data.forEach((item) => {
        this.content += item.content;
      });
      this.chapterTitle = res.data[0].chapterName || "阅读"; // 设置章节标题
      this.showSettingPopup = false;
      setTimeout(() => {
        this.scrollY = yAddress;
      }, 100);
    },
    addMarks() {
      addBookmarks({
        ancientBookId: this.bookId,
        chapterId: this.chapterList[this.index].chapterId,
        yAddress: this.currentScrollY,
      }).then((res) => {
        // 提示
        uni.showToast({
          title: "添加书签成功",
          icon: "success",
        });
        this.initMyMark();
      });
    },
    prop() {
      if (this.index > 0) {
        this.index--;
        this.jumpToChapter(this.index);
      } else {
        console.log("aaaaaaa");
      }
    },
    next() {
      if (this.index < this < this.chapterList.length - 1) {
        this.index++;
        this.jumpToChapter(this.index);
      } else {
        console.log("bbbbbbbbbbb");
      }
    },
    goBack() {
      uni.navigateBack();
    },
    showSettings() {
      this.showSettingPopup = true;
      this.showChapterListView = false; // 重置目录显示状态
    },
    closeSettings() {
      this.showSettingPopup = false;
    },
    changeBrightness(e) {
      const value = typeof e === 'object' ? e.detail.value : e;
      this.brightness = value;
      // 将百分比转换为 0-1 之间的值
      const brightnessValue = value / 100;
      
      // 设置屏幕亮度
      uni.setScreenBrightness({
        value: brightnessValue,
        success: () => {
          // 保存亮度设置到本地存储
          uni.setStorageSync('reader-brightness', value);
        },
        fail: (err) => {
          console.error('设置屏幕亮度失败：', err);
          uni.showToast({
            title: '设置亮度失败',
            icon: 'none'
          });
        }
      });
    },
    changeFontSize(value) {
      this.fontSize = value;
      // 保存字体大小设置
      uni.setStorageSync("reader-font-size", value);
    },
    changeTheme(theme) {
      this.currentTheme = theme.name;
      // 保存主题设置
      uni.setStorageSync("reader-theme", theme.name);
      // 更新页面背景色
      uni.setBackgroundColor({
        backgroundColor: theme.bg,
      });
    },
    toggleChapterList() {
      this.showBookmarkListView = false;
      this.showChapterListView = !this.showChapterListView;
    },
    jumpToChapter(idx) {
      this.index = idx;
      listPages({ chapterId: this.chapterList[idx].chapterId }).then((res) => {
        this.content = "";
        res.data.forEach((item) => {
          this.content += item.content;
        });
        this.chapterTitle = res.data[0].chapterName || "阅读"; // 设置章节标题
      });
      this.showSettingPopup = false;
    },
    addBookmark() {
      // 添加书签
      this.initMyMark();
      this.showBookmarkListView = !this.showBookmarkListView;
      this.showChapterListView = false;
    },
    initMyMark() {
      // 获取书签信息
      myMarks({ ancientBookId: this.bookId }).then((res) => {
        this.bookmarkList = res.data;
      });
    },
    jumpToBookmark(bookmark) {
      // 实现跳转到书签所在章节的功能
      console.log("跳转到书签所在章节", bookmark);
    },
    removeBookmark(id) {
      // 确认删除
      uni.showModal({
        title: "提示",
        content: "确定要删除这个书签吗？",
        success: (res) => {
          if (res.confirm) {
            // 确认删除
            delBookmarks(id).then((res) => {
              // 提示
              uni.showToast({
                title: "删除成功",
                icon: "success",
              });
              this.initMyMark();
            });
          }
        },
      });
    },
  },
  onLoad: function (e) {
    if (e.yAddress) {
      this.toggerInit(e.chapterId, e.yAddress);
      return;
    }
    //根据id查询指定的
    this.bookId = e.bookId;
    console.log(e.chapterId, e.bookId, "eeeeeeeeeeeeeeee");
    listChapters({ bookId: e.bookId }).then((res) => {
      res.data.forEach((el, index) => {
        console.log(el.chapterId == e.chapterId);
        if (el.chapterId == e.chapterId) {
          this.index = index;
        }
      });
      this.chapterList = res.data;
      console.log(res, "ssssdasdsadasdasd");
    });
    let obj = {
      chapterId: Number(e.chapterId),
    };
    listPages(obj).then((res) => {
      this.contentList = res.data;
      this.content = "";
      res.data.forEach((item) => {
        this.content += item.content;
      });
      this.chapterTitle = res.data[0].chapterName || "阅读"; // 设置章节标题
      console.log(res, "ddddddddddddddddddddd");
    });
    // 添加阅读记录
    views({
      ancientBookId: Number(e.bookId),
      chapterId: Number(e.chapterId),
    }).then((res) => {});
    // 加载保存的设置
    const savedFontSize = uni.getStorageSync("reader-font-size");
    if (savedFontSize) {
      this.fontSize = savedFontSize;
    }

    const savedTheme = uni.getStorageSync("reader-theme");
    if (savedTheme) {
      this.currentTheme = savedTheme;
      // 设置初始背景色
      const theme = this.themes.find((t) => t.name === savedTheme);
      if (theme) {
        uni.setBackgroundColor({
          backgroundColor: theme.bg,
        });
      }
    }

    // 获取保存的亮度设置
    const savedBrightness = uni.getStorageSync('reader-brightness');
    if (savedBrightness !== '') {
      this.brightness = savedBrightness;
      this.changeBrightness(savedBrightness);
    } else {
      // 获取当前系统亮度
      uni.getScreenBrightness({
        success: (res) => {
          const value = Math.round(res.value * 100);
          this.brightness = value;
        }
      });
    }
  },
};
</script>

<style lang="scss" scoped>
page {
  min-height: 100vh;
}

.nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 88rpx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32rpx;
  background-color: transparent;
  backdrop-filter: blur(10px);
  z-index: 100;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  transition: background-color 0.3s ease;

  .left,
  .right {
    width: 80rpx;
    height: 80rpx;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: opacity 0.2s ease;

    &:active {
      opacity: 0.7;
    }
  }

  .title {
    flex: 1;
    text-align: center;
    font-size: 32rpx;
    font-weight: 500;
    color: #333333;
    padding: 0 20rpx;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.box {
  position: fixed;
  overflow-y: auto;
  height: 100vh;
  width: 100vw;
  margin: 0 auto;
  padding-top: 88rpx; // 为导航栏预留空间
  min-height: 100vh; // 确保整个容器至少占满屏幕高度
  transition: background-color 0.3s ease, color 0.3s ease;

  .content {
    padding: 40rpx 32rpx;
    word-wrap: break-word;
    word-break: break-word;
    line-height: 1.8; // 增加行高，提升可读性
    font-size: 32rpx; // 调整为更合适的阅读字号
    margin: 20px auto;
    letter-spacing: 2rpx; // 适当增加字间距
    transition: font-size 0.3s ease;

    /deep/ p {
      text-indent: 2em; // 段落首行缩进
      margin-bottom: 1em; // 段落间距
    }
  }

  .fotter {
    position: fixed; // 改为固定定位
    bottom: 0;
    left: 0;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 30rpx 40rpx;
    background-color: transparent; // 修改为透明
    backdrop-filter: blur(10px); // 毛玻璃效果
    box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.05);
    transition: all 0.3s ease;

    .spacer {
      flex: 1;
    }

    .prev-page,
    .next-page {
      padding: 16rpx 40rpx;
      background-color: #8b7355; // 典雅的棕色
      color: #fff;
      border-radius: 30rpx;
      font-size: 28rpx;
      transition: all 0.3s ease;

      &:active {
        transform: scale(0.95);
        opacity: 0.9;
      }
    }
  }
}

.settings-panel {
  padding: 30rpx;
  background-color: #fff;
  border-radius: 24rpx 24rpx 0 0;
  * {
    transition: all 0.3s ease;
  }

  .function-row {
    display: flex;
    justify-content: space-around;
    padding: 20rpx 0;
    border-bottom: 1rpx solid #eee;

    .function-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 10rpx;

      text {
        font-size: 24rpx;
        margin-top: 10rpx;
      }

      &:active {
        transform: scale(0.95);
      }

      .u-icon {
        transition: transform 0.3s ease;
      }

      &:active .u-icon {
        transform: scale(0.9);
      }
    }
  }

  .adjust-section {
    padding: 30rpx 0;
    border-bottom: 1rpx solid #eee;

    .adjust-item {
      margin-bottom: 30rpx;

      text {
        font-size: 28rpx;
        margin-bottom: 20rpx;
        display: block;
      }
    }
  }

  .theme-section {
    padding: 30rpx 0;

    .theme-title {
      font-size: 28rpx;
      margin-bottom: 20rpx;
      display: block;
    }

    .theme-list {
      display: flex;
      justify-content: space-around;

      .theme-item {
        width: 80rpx;
        height: 80rpx;
        border-radius: 50%;
        border: 2rpx solid #ddd;
        transition: all 0.3s ease;

        &.active {
          border: 4rpx solid #8b7355;
          transform: scale(1.1);
        }

        &:active {
          transform: scale(0.9);
        }
      }
    }
  }
}

.chapter-list {
  height: 60vh;
  padding: 20rpx 0;
  border-bottom: 1rpx solid #eee;
  animation: slideIn 0.3s ease;

  .chapter-scroll {
    height: 100%;
  }

  .chapter-item {
    padding: 24rpx 40rpx;
    font-size: 28rpx;
    border-bottom: 1rpx solid #f5f5f5;
    display: flex;
    align-items: center;
    transition: all 0.2s ease;

    &.active {
      color: #8b7355;
      font-weight: bold;
      background-color: rgba(139, 115, 85, 0.1);
    }

    &:active {
      transform: translateX(10rpx);
      background-color: #f5f5f5;
    }
  }

  .bookmark-item {
    justify-content: space-between;

    .bookmark-content {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 8rpx;
    }

    .bookmark-time {
      font-size: 24rpx;
      color: #999;
    }

    .bookmark-delete {
      padding: 10rpx;

      &:active {
        opacity: 0.7;
      }
    }
  }
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateY(20rpx);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (prefers-color-scheme: dark) {
  .box {
    .fotter {
      background-color: rgba(0, 0, 0, 0.5);
    }
  }

  .nav-bar {
    background-color: rgba(0, 0, 0, 0.5);
  }

  .settings-panel {
    background-color: #333;
    color: #fff;
    transition: background-color 0.3s ease;

    .function-row {
      border-bottom: 1rpx solid #444;
    }

    .adjust-section {
      border-bottom: 1rpx solid #444;
    }

    .chapter-item {
      &:active {
        transform: translateX(10rpx);
        background-color: #333;
      }
    }
  }

  .chapter-list {
    border-bottom: 1rpx solid #444;

    .chapter-item {
      border-bottom: 1rpx solid #333;

      &.active {
        background-color: rgba(139, 115, 85, 0.2);
      }

      &:active {
        background-color: #333;
      }
    }
  }
}

.bookmark-title {
  white-space: nowrap; // 防止文本换行
  overflow: hidden; // 超出部分隐藏
  text-overflow: ellipsis; // 超出显示省略号
  display: block; // 或者 inline-block
  width: 100%; // 设置宽度
}
</style>
