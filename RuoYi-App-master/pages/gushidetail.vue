<template>
<view  >
    <view  @click="jumpGushiDetail(item)" class="box" :key="item.ariId"  v-for="item in list" style="border-radius: 5px;background-color: #fffefe;margin-top: 20px;min-height: 80px;">
      <view class="tit">
        {{item.name}}
      </view>
      <view class="top">
    <span style="padding-left:5px;font-size: 12px;font-weight: 700;">
      {{item.author}}
    </span>
        <span style="margin-left: 10px;color: silver;font-size: 5px">
      {{item.dynasty}}
    </span>
      </view>
      <u--text size="12" style="border-top: 1px solid #9d9393;padding: 10px;" :lines="3" :text="item.introduce">
      </u--text>
    </view>
</view>
</template>
<script>
import { dictType,ancientPoemList} from '@/api/guji/guji'
export default {
  name: "gushidetail",
  data(){
    return{
      list:[]
    }
  },
  methods:{
    jumpGushiDetail(item){
      this.$tab.navigateTo(`/pages/gushicontent?ariId=${item.ariId}`)
      console.log(item,'sadsdasdasasdsa')
    }
  },
  onLoad(e){
    console.log(e,'ssssssssssssssss')
    if(e.author!=undefined){
      let obj = {
        author:e.author
      }
      ancientPoemList(obj).then(res=>{
        this.list = res.data
        console.log(this.list,'listlist')
        console.log(res,'jkjkk')
      })
    }else if(e.type!=undefined){
      let obj = {
        list:e.type
      }
      ancientPoemList(obj).then(res=>{
        this.list = res.data
        console.log(res,'jkjkk')
      })
      console.log(e.type)
    }else if(e.dynasty!=undefined){
      let obj = {
        dynasty:e.dynasty
      }
      ancientPoemList(obj).then(res=>{
        this.list = res.data
        console.log(res,'jkjkk')
      })
      console.log(e.dynasty)
    }else if(e.category!=undefined) {
      let obj = {
        category:e.category
      }
      ancientPoemList(obj).then(res=>{
        this.list = res.data
        console.log(res,'jkjkk')
      })
    }else{
      let obj = {
        name:e.name
      }
      ancientPoemList(obj).then(res=>{
        this.list = res.data
        console.log(res,'jkjkk')
      })
    }

  }

}
</script>
<style scoped lang="scss">
page{
  background-color: #dcc2c2;
}
.box{
  background-color: #ffffff;
  border-radius: 5px;
  width: 95vw;
  margin: 5px auto;
  .top{
    padding-bottom: 5px;
  }
  .tit{
    text-align: right;
    font-weight: 700;
    padding-right: 5px;
    padding-top: 5px;
  }

}
</style>
