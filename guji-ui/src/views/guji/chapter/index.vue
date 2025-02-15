<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="章节名称" prop="chapterName">
        <el-input
          v-model="queryParams.chapterName"
          placeholder="请输入章节名称"
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

    <el-table v-loading="loading" :data="chapterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="书名" align="center" prop="name" />
      <el-table-column label="章节名称" align="center" prop="chapterName" />
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
    <!-- 添加或修改古籍章节对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书名">
          <el-col>
            <el-select @change="industryChange" v-model="form.bookId" placeholder="请选择">
              <el-option
                v-for="item in bookList"
                :key="item.bookId"
                :label="item.name"
                :value="item.bookId">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="章节名称" prop="chapterName">
          <el-input v-model="form.chapterName" placeholder="请输入章节名称" />
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
  import { listChapter, getChapter, delChapter, addChapter, updateChapter } from "@/api/guji/chapter";
  import { listBooks, getBook, delBook, addBook, updateBook } from "@/api/guji/book";
  export default {
    name: "Chapter",
    data() {
      return {
        bookList:[],
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
        // 古籍章节表格数据
        chapterList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          chapterName: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          chapterName: [
            { required: true, message: "章节名称不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getBookList()
    },
    methods: {
      //输出:value="item.industryId"绑定的值
      industryChange(e){
        console.log(e,'eeeeeeeeee')
      },
      getBookList(){
        listBooks().then(res=>{
          this.bookList = res.data
          console.log(res,'dddddddddddddddd')
        })
      },
      /** 查询古籍章节列表 */
      getList() {
        this.loading = true;
        listChapter(this.queryParams).then(response => {
          this.chapterList = response.rows;
          console.log(this.chapterList,'llllllllllllllllllllllllll')
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          chapterId: null,
          bookId: null,
          chapterName: null
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
        this.ids = selection.map(item => item.chapterId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加古籍章节";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const chapterId = row.chapterId || this.ids
        getChapter(chapterId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改古籍章节";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.chapterId != null) {
              updateChapter(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addChapter(this.form).then(response => {
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
        const chapterIds = row.chapterId || this.ids;
        this.$modal.confirm('是否确认删除古籍章节编号为"' + chapterIds + '"的数据项？').then(function() {
          return delChapter(chapterIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('book/chapter/export', {
          ...this.queryParams
        }, `chapter_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
