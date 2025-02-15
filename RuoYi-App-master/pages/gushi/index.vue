<template>
<view>
  <u-search @search="search" :showAction="true" placeholder="请输入古诗名称" actionText="搜索" :animation="true"></u-search>
  <view>
    <view style="color: red;padding-left: 15px;">作者</view>
    <u-grid
        :border="false"
    >
      <u-grid-item
          v-for="(baseListItem,baseListIndex) in list"
          :key="baseListIndex"
          style="margin-top: 20px;"
      >
        <text   @click="click1(baseListItem)" class="grid-text">{{baseListItem.author}}</text>
      </u-grid-item>
    </u-grid>
  </view>
<!-- 合集 -->
  <view>
    <view style="color: red;padding-left: 15px;">合集</view>
    <u-grid
        :border="false"
    >
      <u-grid-item
          v-for="(baseListItem,baseListIndex) in poemType"
          :key="baseListIndex"
          style="margin-top: 20px;"
      >
        <text     @click="click2(baseListItem)" class="grid-text">{{baseListItem.dictLabel}}</text>
      </u-grid-item>
    </u-grid>
  </view>
<!-- 朝代 -->
  <view>
    <view style="color: red;padding-left: 15px;">朝代</view>
    <u-grid
        :border="false"

    >
      <u-grid-item
          v-for="(item,baseListIndex) in dynastyList"
          :key="baseListIndex"
          style="margin-top: 20px;"
      >
        <text     @click="click3(item)" class="grid-text">{{item.dictLabel}}</text>
      </u-grid-item>
    </u-grid>
  </view>
<!--  分类-->
  <view>
    <view style="color: red;padding-left: 15px;">分类</view>
    <u-grid
        :border="false"
    >
      <u-grid-item
          v-for="(item,index) in ponemCategory"
          :key="index"
          style="margin-top: 20px;"
      >
        <text    @click="click4(item)" class="grid-text">{{item.dictLabel}}</text>
      </u-grid-item>
    </u-grid>
  </view>
</view>
</template>
<script>
import { dictType,ancientPoemList} from '@/api/guji/guji'
export default {
  name: "index",
  data(){
    return{
      dynastyList:[],
      poemType:[],
      ponemCategory:[],
      list:[]
    }
  },
  methods:{
    //作者
    click1(item) {
      console.log(item.author)
      this.$tab.navigateTo(`/pages/gushidetail?author=${item.author}`)
    },
    click2(item) {
      console.log(item.dictLabel)
      this.$tab.navigateTo(`/pages/gushidetail?type=${item.dictValue}`)
    },
    click3(item) {
      console.log(item)
      this.$tab.navigateTo(`/pages/gushidetail?dynasty=${item.dictValue}`)
    },
    click4(item) {
      console.log(item.dictLabel)
      this.$tab.navigateTo(`/pages/gushidetail?category=${item.dictValue}`)
    },search(e){
      this.$tab.navigateTo(`/pages/gushidetail?name=${e}`)
      console.log(e,'eeeeeeeeeeeeeeeeeeeeee')
    }
  },
  onLoad(){
    let obj =  {

    }
    ancientPoemList(obj).then(res => {
      const uniqueAuthors = res.data.reduce((acc, current) => {
        if (!acc.some(item => item.author === current.author)) {
          acc.push({ author: current.author });
        }
        return acc;
      }, []);
      this.list = uniqueAuthors;
      // console.log(uniqueAuthors);
      // console.log(res,'redssss')
    })
    //古诗分类
    dictType('ponem_category').then(res => {
      this.ponemCategory = res.data
    })
    //朝代
    dictType('poem_dynasty').then(res => {
      this.dynastyList = res.data
    })
    //合集
    dictType('poem_type').then(res => {
      this.poemType = res.data
    })

  }
}
</script>
<style scoped lang="scss">

</style>
