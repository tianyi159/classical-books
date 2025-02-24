<template>
  <view>
    <view class="top" style="position: relative">
      <image :src="`http://localhost:8088${detail.url}`" style="position: absolute;width: 100%;height: 100%;left: 0;top: 0;opacity: 0.3;pointer-events: none;"></image>
      <view class="book_container" style="z-index: 222">
        <view class="book_image" style="position: relative">
          <image :src="`http://localhost:8088${detail.url}`"></image>
        </view>
        <view class="book_info">
          <view class="name">{{ detail.name }}</view>
          <view class="author">作者：{{ detail.author }}</view>
        </view>
        <view
          class="book_info"
          hover-class="none"
          hover-stop-propagation="false"
          style="
            display: flex;
            justify-content: space-around;
            width: 100%;
            margin-top: 20px;
          "
        >
          <view hover-class="none" hover-stop-propagation="false">
            {{ detail.typeName }} · {{ detail.categoryName }}
          </view>
          <view class="" hover-class="none" hover-stop-propagation="false">
            共{{ list1.length }}章
          </view>
          <view
            class=""
            hover-class="none"
            hover-stop-propagation="false"
            @click="goA()"
          >
            继续阅读
          </view>
        </view>
      </view>
    </view>
    <view class="custom-tabs">
      <view class="tabs-header">
        <view 
          v-for="(item, idx) in list1" 
          :key="idx"
          class="tab-item"
          :class="{ active: index === idx }"
          @click="click({index: idx})"
        >
          {{ item.name }}
          <view v-if="index === idx" class="active-line"></view>
        </view>
      </view>
    </view>
    <view v-if="index === 0">
      <u--text style="padding: 10px" :text="detail.introduce"></u--text>
      <!--      {{detail.introduce}}-->
    </view>
    <view v-else-if="index === 1">
      <u-cell-group v-for="(item, index) in chapter" :key="index">
        <u-cell
          @click="jumpItem(item)"
          :title="index + 1 + '、' + item.chapterName"
        ></u-cell>
      </u-cell-group>
    </view>
    <view v-else-if="index === 2">
      <view class="comments-section">
        <!-- 评论输入框 -->
        <view class="comment-input">
          <u-input
            v-model="newComment"
            placeholder="写下你的评论..."
            border="bottom"
          />
          <u-button size="mini" type="primary" @click="submitComment"
            >发送</u-button
          >
        </view>

        <!-- 评论列表 -->
        <view class="comment-list">
          <view
            v-for="(comment, idx) in comments"
            :key="idx"
            class="comment-item"
          >
            <view class="comment-header">
              <image :src="comment.avatar" class="avatar"></image>
              <view class="user-info">
                <text class="username">{{ comment.username }}</text>
                <text class="time">{{ comment.time }}</text>
              </view>
            </view>
            <view class="comment-content">
              {{ comment.content }}
            </view>
          </view>
        </view>
      </view>
    </view>
    <view v-else-if="index === 3">
      <!-- 收藏按钮 -->
      <u-button
        @click="shoucang"
        :text="text"
        :custom-style="{
          width: '85vw',
          margin: '20px auto',
          borderRadius: '12px',
          color: '#fff',
          background: 'linear-gradient(45deg, #4253d8, #d533ba)',
          boxShadow: '0 4px 15px rgba(213, 51, 186, 0.2)',
          border: 'none',
          fontSize: '16px',
          fontWeight: '500',
          padding: '12px 0',
          transition: 'all 0.3s ease',
        }"
      ></u-button>

      <!-- 书签列表 -->
      <view class="bookmark-section">
        <view class="bookmark-header">
          <text class="title">我的书签</text>
        </view>

        <view class="bookmark-list" v-if="bookmarks.length > 0">
          <view
            v-for="(bookmark, idx) in bookmarks"
            :key="idx"
            class="bookmark-item"
          >
            <view class="bookmark-info">
              <view class="chapter">{{ bookmark.chapterName }}</view>
              <view class="desc">{{ bookmark.createdAt }}</view>
              <view class="time">{{ bookmark.yAddress }}</view>
            </view>
            <view class="bookmark-actions">
              <u-icon
                name="arrow-right"
                size="28"
                @click="jumpToBookmark(bookmark)"
              ></u-icon>
              <u-icon
                name="trash"
                size="28"
                color="#f56c6c"
                @click="deleteBookmark(bookmark.bookmarkId)"
              ></u-icon>
            </view>
          </view>
        </view>

        <view v-else class="empty-bookmark">
          <u-empty text="暂无书签"></u-empty>
        </view>
      </view>
    </view>
    <u-toast ref="uToast"></u-toast>
  </view>
</template>

<script>
import {
  listBooks,
  listChapters,
  getBook,
  addShelf,
  shelfList,
  deleteShelf,
} from "@/api/guji/guji";
import {
  addBookmarks,
  listBookmarks,
  myMarks,
  delBookmarks,
} from "@/api/guji/bookmarks";
import { getRecordInfo } from "@/api/guji/record";
export default {
  data() {
    return {
      list1: [
        { name: "简介" },
        { name: "目录" },
        { name: "评论" },
        { name: "书架/书签" },
      ],
      index: 0,
      detail: undefined,
      chapter: undefined,
      text: "已加入",
      shelfId: undefined,
      newComment: "",
      comments: [
        {
          avatar: "/static/images/avatar.png",
          username: "读者1",
          content: "这本书写得很好，情节引人入胜！",
          time: "2024-03-20",
          likes: 12,
        },
        {
          avatar: "/static/images/avatar.png",
          username: "读者2",
          content: "文笔优美，值得一读。",
          time: "2024-03-19",
          likes: 8,
        },
      ],
      bookmarks: [
        {
          chapterName: "第一章 序章",
          description: "主角初登场的地方",
          time: "2024-03-20 14:30",
          chapterId: 1,
          position: 0,
        },
        {
          chapterName: "第三章 转折",
          description: "精彩的剧情转折点",
          time: "2024-03-21 09:15",
          chapterId: 3,
          position: 0,
        },
      ],
    };
  },
  methods: {
    goA() {
      getRecordInfo(this.bookId).then((res) => {
        uni.navigateTo({
          url: `/pages/gujicontent?chapterId=${res.data.chapterId}&bookId=${this.bookId}`,
        });
      });
    },
    shoucang() {
      if (this.text == "已加入") {
        //取消收藏
        deleteShelf(this.shelfId).then((res) => {
          this.$refs.uToast.show({
            // Toast配置项
            message: "取消加入",
            type: "default",
            title: "默认主题",
          });
          console.log(res, "取消结果");
          this.text = "加入";
        });
      } else {
        let obj = {
          bookId: this.detail.bookId,
        };
        addShelf(obj).then((res) => {
          this.$refs.uToast.show({
            // Toast配置项
            message: "加入成功",
            type: "success",
            title: "默认主题",
          });
          this.text = "已加入";
          this.shelfId = res.data.shelfId;
          console.log(res, "加入结果");
        });
      }
    },
    click(item) {
      this.index = item.index;
    },
    jumpItem(item) {
      let chapterId = item.chapterId;
      this.$tab.navigateTo(
        `/pages/gujicontent?chapterId=${chapterId}&bookId=${this.bookId}`
      );
    },
    checkShouStatus(bookId) {
      let obj = {
        bookId: bookId,
      };
      shelfList(obj).then((res) => {
        if (res.data.length == 0) {
          this.text = "加入";
        } else {
          this.text = "已加入";
          this.shelfId = res.data[0].shelfId;
        }
        console.log(res, "状态");
      });
    },
    submitComment() {
      if (!this.newComment) {
        this.$refs.uToast.show({
          message: "评论内容不能为空",
          type: "warning",
        });
        return;
      }

      // 添加新评论
      this.comments.unshift({
        avatar: "/static/images/avatar.png",
        username: "当前用户",
        content: this.newComment,
        time: new Date().toLocaleDateString(),
        likes: 0,
      });

      this.newComment = ""; // 清空输入框

      this.$refs.uToast.show({
        message: "评论成功",
        type: "success",
      });
    },

    likeComment(index) {
      this.comments[index].likes++;
    },
    addBookmark() {
      // 这里可以弹出添加书签的弹窗
      uni.showModal({
        title: "添加书签",
        editable: true,
        placeholderText: "请输入书签备注",
        success: (res) => {
          if (res.confirm && res.content) {
            this.bookmarks.unshift({
              chapterName: "当前章节",
              description: res.content,
              time: new Date().toLocaleString(),
              chapterId: this.currentChapterId,
              position: 0,
            });

            this.$refs.uToast.show({
              message: "添加书签成功",
              type: "success",
            });
          }
        },
      });
    },

    deleteBookmark(index) {
      uni.showModal({
        title: "提示",
        content: "确定要删除这个书签吗？",
        success: (res) => {
          if (res.confirm) {
            delBookmarks(index).then((res) => {
              this.$refs.uToast.show({
                message: "删除成功",
                type: "success",
              });
              this.initMyMark();
            });
          }
        },
      });
    },
    initMyMark() {
      myMarks({ ancientBookId: this.bookId }).then((res) => {
        this.bookmarks = res.data;
      });
    },
    jumpToBookmark(bookmark) {
      // 跳转到对应章节
      this.$tab.navigateTo(
        `/pages/gujicontent?chapterId=${bookmark.chapterId}&yAddress=${bookmark.yAddress}&bookId=${this.bookId}`
      );
    },
  },
  onShow() {
    this.initMyMark();
  },

  onLoad(e) {
    this.bookId = e.bookId;
    console.log(this.bookId, "skljijiou");
    this.checkShouStatus(e.bookId);
    //根据bookId查询章节
    //根据bookId查询每本书详情
    getBook(e.bookId).then((res) => {
      this.detail = res.data;
      console.log(res, "每本书详情");
    });
    let obj = {
      bookId: Number(e.bookId),
    };
    listChapters(obj).then((res) => {
      this.chapter = res.data;
      console.log(res);
    });
    // console.log(e)
  },
};
</script>

<style lang="scss"  scoped>
.top {
  background: linear-gradient(to bottom, #f8f9fa, #ffffff);
  padding: 20px;
  margin-bottom: 15px;

  .book_container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;

    .book_image {
      width: 120px;
      height: 160px;
      margin-bottom: 15px;

      image {
        width: 100%;
        height: 100%;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      }
    }

    .book_info {
      text-align: center;

      .name {
        font-size: 20px;
        font-weight: bold;
        color: #333;
        margin-bottom: 8px;
      }

      .author {
        font-size: 14px;
        color: #666;
      }
    }
  }
}

.custom-tabs {
  background: #fff;
  margin-bottom: 15px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);

  .tabs-header {
    display: flex;
    justify-content: space-around;
    position: relative;
    
    .tab-item {
      position: relative;
      padding: 12px 20px;
      font-size: 14px;
      color: #606266;
      text-align: center;
      transition: all 0.3s;
      
      &.active {
        color: #303133;
        font-weight: bold;
        transform: scale(1.05);
        
        .active-line {
          position: absolute;
          bottom: 0;
          left: 50%;
          transform: translateX(-50%);
          width: 30px;
          height: 2px;
          background: #f56c6c;
          border-radius: 2px;
        }
      }
      
      &:active {
        opacity: 0.7;
      }
    }
  }
}

// 美化简介内容
:deep(.u--text) {
  line-height: 1.6;
  color: #666;
  padding: 15px !important;
  background: #fff;
  border-radius: 8px;
  margin: 0 15px;
}

// 美化目录列表
:deep(.u-cell) {
  margin: 0 15px;
  border-radius: 8px;

  &:active {
    background-color: #f5f5f5;
  }
}

// 美化收藏按钮
:deep(.u-button) {
  &:active {
    transform: translateY(2px);
    box-shadow: 0 2px 8px rgba(213, 51, 186, 0.15);
  }
}

.comments-section {
  padding: 20rpx;

  .comment-input {
    align-items: center;
    padding: 20rpx;
    background: #fff;
    border-radius: 12rpx;
    margin-bottom: 20rpx;

    :deep(.u-input) {
      flex: 1;
      margin-right: 20rpx;
    }
  }

  .comment-list {
    .comment-item {
      background: #fff;
      border-radius: 12rpx;
      padding: 20rpx;
      margin-bottom: 20rpx;

      .comment-header {
        display: flex;
        align-items: center;
        margin-bottom: 16rpx;

        .avatar {
          width: 80rpx;
          height: 80rpx;
          border-radius: 50%;
          margin-right: 16rpx;
        }

        .user-info {
          .username {
            font-size: 28rpx;
            font-weight: bold;
            color: #333;
            display: block;
          }

          .time {
            font-size: 24rpx;
            color: #999;
          }
        }
      }

      .comment-content {
        font-size: 28rpx;
        color: #666;
        line-height: 1.6;
        margin-bottom: 16rpx;
      }

      .comment-footer {
        display: flex;
        align-items: center;

        .likes-count {
          font-size: 24rpx;
          color: #999;
          margin-left: 8rpx;
        }
      }
    }
  }
}

.bookmark-section {
  padding: 20rpx;

  .bookmark-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20rpx;
    padding: 0 20rpx;

    .title {
      font-size: 32rpx;
      font-weight: bold;
      color: #333;
    }
  }

  .bookmark-list {
    .bookmark-item {
      background: #fff;
      border-radius: 12rpx;
      padding: 20rpx;
      margin-bottom: 20rpx;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2rpx 10rpx rgba(0, 0, 0, 0.05);

      .bookmark-info {
        flex: 1;
        margin-right: 20rpx;

        .chapter {
          font-size: 28rpx;
          font-weight: bold;
          color: #333;
          margin-bottom: 8rpx;
        }

        .desc {
          font-size: 26rpx;
          color: #666;
          margin-bottom: 8rpx;
        }

        .time {
          font-size: 24rpx;
          color: #999;
        }
      }

      .bookmark-actions {
        display: flex;
        gap: 20rpx;

        :deep(.u-icon) {
          padding: 10rpx;

          &:active {
            opacity: 0.7;
          }
        }
      }
    }
  }

  .empty-bookmark {
    padding: 40rpx 0;
  }
}
</style>
