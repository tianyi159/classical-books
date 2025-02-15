<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="章节id" prop="chapterId">
        <el-input
          v-model="queryParams.chapterId"
          placeholder="请输入章节id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column align="center" type="index" label="序号" width="70px">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}</span>
        </template >
      </el-table-column>
      <el-table-column label="章节" align="center" prop="chapterName" />
      <el-table-column show-overflow-tooltip label="内容" align="center" prop="content" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改章节页面对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="章节名">
          <el-col>
            <el-select @change="industryChange" v-model="form.chapterId" placeholder="请选择">
              <el-option
                v-for="item in listChapters"
                :key="item.chapterId"
                :label="item.chapterName"
                :value="item.chapterId">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listPage, getPage, delPage, addPage, updatePage } from "@/api/guji/page";
  import { listChapters, getChapter, delChapter, addChapter, updateChapter } from "@/api/guji/chapter";
  export default {
    name: "Page",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 章节页面表格数据
        pageList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          chapterId: null,
          content: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          content: [
            { required: true, message: "内容不能为空", trigger: "blur" }
          ]
        },
        listChapters:[]
      };
    },
    created() {
      this.getList();
      this.getAllChapters()
    },
    methods: {
      industryChange(e){
        console.log(e,'eeeeeeeeee')
      },
      /** 查询章节页面列表 */
      getList() {
        this.loading = true;
        listPage(this.queryParams).then(response => {
          this.pageList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      getAllChapters(){
        listChapters().then(res=>{
          this.listChapters = res.data
          console.log(res,'ssssssssssssssss')
        })
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          pageId: null,
          chapterId: null,
          content: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.pageId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加章节页面";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const pageId = row.pageId || this.ids
        getPage(pageId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改章节页面";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.pageId != null) {
              updatePage(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addPage(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const pageIds = row.pageId || this.ids;
        this.$modal.confirm('是否确认删除章节页面编号为"' + pageIds + '"的数据项？').then(function() {
          return delPage(pageIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('book/page/export', {
          ...this.queryParams
        }, `page_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
