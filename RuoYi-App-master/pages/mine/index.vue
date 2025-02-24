<template>
  <view class="mine-container" :style="{height: `${windowHeight}px`}">
    <!--顶部个人信息栏-->
    <view class="header-section">
      <view class="flex padding justify-between">
        <view class="flex align-center">
          <view v-if="!avatar" class="cu-avatar xl round bg-white">
            <view class="iconfont icon-people text-gray icon"></view>
          </view>
          <image v-if="avatar" @click="handleToAvatar" :src="avatar" class="cu-avatar xl round" mode="widthFix">
          </image>
          <view v-if="!name" @click="handleToLogin" class="login-tip">
            点击登录
          </view>
          <view v-if="name" @click="handleToInfo" class="user-info">
            <view class="u_title">
              用户名：{{ name }}
            </view>
          </view>
        </view>
        <view @click="handleToInfo" class="flex align-center">
          <text>个人信息</text>
          <view class="iconfont icon-right"></view>
        </view>
      </view>
    </view>

    <view class="content-section">
<!--      <view class="mine-actions grid col-4 text-center">-->
<!--        <view class="action-item" @click="handleJiaoLiuQun">-->
<!--          <view class="iconfont icon-friendfill text-pink icon"></view>-->
<!--          <text class="text">交流群</text>-->
<!--        </view>-->
<!--        <view class="action-item" @click="handleBuilding">-->
<!--          <view class="iconfont icon-service text-blue icon"></view>-->
<!--          <text class="text">在线客服</text>-->
<!--        </view>-->
<!--        <view class="action-item" @click="handleBuilding">-->
<!--          <view class="iconfont icon-community text-mauve icon"></view>-->
<!--          <text class="text">反馈社区</text>-->
<!--        </view>-->
<!--        <view class="action-item" @click="handleBuilding">-->
<!--          <view class="iconfont icon-dianzan text-green icon"></view>-->
<!--          <text class="text">点赞我们</text>-->
<!--        </view>-->
      </view>

      <view class="menu-list">
        <view class="list-cell list-cell-arrow" @click="toOrder">
          <view class="menu-item-box">
            <view class="iconfont icon-user menu-icon"></view>
            <view>阅读记录</view>
          </view>
        </view>
        <view class="list-cell list-cell-arrow" @click="handleToEditInfo">
          <view class="menu-item-box">
            <view class="iconfont icon-user menu-icon"></view>
            <view>编辑资料</view>
          </view>
        </view>
<!--        <view class="list-cell list-cell-arrow" @click="handleHelp">-->
<!--          <view class="menu-item-box">-->
<!--            <view class="iconfont icon-help menu-icon"></view>-->
<!--            <view>常见问题</view>-->
<!--          </view>-->
<!--        </view>-->
<!--        <view class="list-cell list-cell-arrow" @click="handleAbout">-->
<!--          <view class="menu-item-box">-->
<!--            <view class="iconfont icon-aixin menu-icon"></view>-->
<!--            <view>关于我们</view>-->
<!--          </view>-->
<!--        </view>-->
        <view class="list-cell list-cell-arrow" @click="handleToSetting">
          <view class="menu-item-box">
            <view class="iconfont icon-setting menu-icon"></view>
            <view>应用设置</view>
          </view>
        </view>
      </view>

    </view>
  </view>
</template>

<script>
import storage from "@/utils/storage";

export default {
  data() {
    return {
      name: this.$store.state.user.name,
      version: getApp().globalData.config.appInfo.version,
    };
  },
  computed: {
    avatar() {
      return this.$store.state.user.avatar;
    },
    windowHeight() {
      return uni.getSystemInfoSync().windowHeight - 50;
    },
  },
  methods: {
    toOrder() {
      uni.navigateTo({
        url: "/pages/mine/order/order",
      });
    },
    handleToInfo() {
      this.$tab.navigateTo("/pages/mine/info/index");
    },
    handleToEditInfo() {
      this.$tab.navigateTo("/pages/mine/info/edit");
    },
    handleToSetting() {
      this.$tab.navigateTo("/pages/mine/setting/index");
    },
    handleToLogin() {
      this.$tab.reLaunch("/pages/login");
    },
    handleToAvatar() {
      this.$tab.navigateTo("/pages/mine/avatar/index");
    },
    handleLogout() {
      this.$modal.confirm("确定注销并退出系统吗？").then(() => {
        this.$store.dispatch("LogOut").then(() => {
          this.$tab.reLaunch("/pages/index");
        });
      });
    },
    handleHelp() {
      this.$tab.navigateTo("/pages/mine/help/index");
    },
    handleAbout() {
      this.$tab.navigateTo("/pages/mine/about/index");
    },
    handleJiaoLiuQun() {
      this.$modal.showToast("QQ群：①133713780、②146013835");
    },
    handleBuilding() {
      this.$modal.showToast("模块建设中~");
    },
  },
};
</script>

<style lang="scss">
page {
  background-color: #f8f9fa;
}

.mine-container {
  width: 100%;
  height: 100%;

  .header-section {
    padding: 25px 15px 65px 15px;
    background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
    color: white;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);

    .login-tip {
      font-size: 18px;
      margin-left: 15px;
      font-weight: 500;
      position: relative;

      &::after {
        content: "";
        position: absolute;
        bottom: -2px;
        left: 0;
        width: 100%;
        height: 2px;
        background-color: rgba(255, 255, 255, 0.6);
      }
    }

    .cu-avatar {
      border: 3px solid rgba(255, 255, 255, 0.8);
      transition: all 0.3s ease;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);

      &:active {
        transform: scale(0.95);
      }

      .icon {
        font-size: 40px;
      }
    }

    .user-info {
      margin-left: 20px;

      .u_title {
        font-size: 18px;
        line-height: 30px;
        font-weight: 500;
      }
    }
  }

  .content-section {
    position: relative;
    top: -50px;
    padding: 0 15px;

    .menu-list {
      background: white;
      border-radius: 12px;
      box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
      overflow: hidden;

      .list-cell {
        padding: 16px;
        position: relative;
        transition: all 0.3s ease;

        &:active {
          background-color: #f5f5f5;
        }

        &::after {
          content: "";
          position: absolute;
          left: 16px;
          right: 16px;
          bottom: 0;
          height: 1px;
          background-color: #f0f0f0;
        }

        &:last-child::after {
          display: none;
        }

        .menu-item-box {
          display: flex;
          align-items: center;

          .menu-icon {
            font-size: 20px;
            margin-right: 12px;
            color: #4facfe;
          }
        }
      }
    }
  }
}
</style>
