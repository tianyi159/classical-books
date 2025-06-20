<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable @change="handleTypeChange">
          <el-option
            v-for="dict in dict.type.book_type"
            :key="dict.value"
            :label="dict.label"
            :value="parseInt(dict.value)"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择分类" clearable>
          <el-option
            v-for="category in categories"
            :key="category.categoryId"
            :label="category.categoryName"
            :value="parseInt(category.categoryId)"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
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

    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="书名" align="center" prop="name" />
      <el-table-column align="center" label="封面" >
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="`http://localhost:8088${scope.row.url}`"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.book_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="categoryName"> 
      </el-table-column>
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="简介" align="center" prop="introduce" />
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

    <!-- 添加或修改古籍对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书名" prop="name">
          <el-input v-model="form.name" placeholder="请输入书名" />
        </el-form-item>

        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型" @change="handleFormTypeChange">
            <el-option
              v-for="dict in dict.type.book_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="form.categoryId" placeholder="请选择分类">
            <el-option
              v-for="category in formCategories"
              :key="category.categoryId"
              :label="category.categoryName"
              :value="parseInt(category.categoryId)"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="图书封面" prop="url">
          <image-upload v-model="form.url"/>
        </el-form-item>
        <el-form-item  label="简介" prop="introduce">
          <el-input type="textarea" v-model="form.introduce"/>
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
  import { listBook, getBook, delBook, addBook, updateBook } from "@/api/guji/book";
  import { listCategory } from "@/api/guji/category";

  export default {
    name: "Book",
    dicts: ['book_type'],
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
        // 古籍表格数据
        bookList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          type: null,
          author: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          type: [
            { required: true, message: "类型不能为空", trigger: "change" }
          ],
          author: [
            { required: true, message: "作者不能为空", trigger: "blur" }
          ],
          name: [
            { required: true, message: "书名不能为空", trigger: "blur" }
          ],
          introduce:[
            { required: true, message: "简介不能为空", trigger: "blur" }
          ]
        },
        categories: [],
        formCategories: []
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询古籍列表 */
      getList() {
        this.loading = true;
        listBook(this.queryParams).then(response => {
          this.bookList = response.rows;
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
          bookId: null,
          type: null,
          author: null
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
        this.ids = selection.map(item => item.bookId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加古籍";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const bookId = row.bookId || this.ids
        getBook(bookId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改古籍";
          this.getCategories(this.form.type, false); // 修改: 调用getCategories方法获取分类
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.bookId != null) {
              updateBook(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addBook(this.form).then(response => {
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
        const bookIds = row.bookId || this.ids;
        this.$modal.confirm('是否确认删除古籍编号为"' + bookIds + '"的数据项？').then(function() {
          return delBook(bookIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('guji/book/export', {
          ...this.queryParams
        }, `book_${new Date().getTime()}.xlsx`)
      },
      handleTypeChange() {
        this.queryParams.categoryId = null;
        this.getCategories(this.queryParams.type,true); // 修改: 调用getCategories方法获取分类
      },
      handleFormTypeChange() {
        this.form.categoryId = null;
        this.getCategories(this.form.type,false); // 修改: 调用getCategories方法获取分类
      },
      
      getCategories(type,status) {
        listCategory({ type }).then(response => {
          if(status){
            this.categories = response.rows;
          }else{
            this.formCategories = response.rows;
          }
        });
      }
    }
  };
</script>
