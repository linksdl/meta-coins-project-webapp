<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="图表" prop="icon">
        <el-input
          v-model="queryParams.icon"
          placeholder="请输入图表"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="权重" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入权重"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否默认账本" prop="bookDefault">
        <el-input
          v-model="queryParams.bookDefault"
          placeholder="请输入是否默认账本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="描述" prop="bookDesc">
        <el-input
          v-model="queryParams.bookDesc"
          placeholder="请输入描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账本名称" prop="bookName">
        <el-input
          v-model="queryParams.bookName"
          placeholder="请输入账本名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账本类型ID" prop="bookTypeId">
        <el-input
          v-model="queryParams.bookTypeId"
          placeholder="请输入账本类型ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
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
          v-hasPermi="['config:book:add']"
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
          v-hasPermi="['config:book:edit']"
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
          v-hasPermi="['config:book:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:book:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="bookId" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否可用" align="center" prop="enable" />
      <el-table-column label="图表" align="center" prop="icon" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="权重" align="center" prop="weight" />
      <el-table-column label="是否默认账本" align="center" prop="bookDefault" />
      <el-table-column label="描述" align="center" prop="bookDesc" />
      <el-table-column label="账本名称" align="center" prop="bookName" />
      <el-table-column label="账本类型" align="center" prop="bookType" />
      <el-table-column label="账本类型ID" align="center" prop="bookTypeId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:book:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:book:remove']"
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

    <!-- 添加或修改ruoyi-config对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图表" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入图表" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="权重" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入权重" />
        </el-form-item>
        <el-form-item label="是否默认账本" prop="bookDefault">
          <el-input v-model="form.bookDefault" placeholder="请输入是否默认账本" />
        </el-form-item>
        <el-form-item label="描述" prop="bookDesc">
          <el-input v-model="form.bookDesc" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="账本名称" prop="bookName">
          <el-input v-model="form.bookName" placeholder="请输入账本名称" />
        </el-form-item>
        <el-form-item label="账本类型ID" prop="bookTypeId">
          <el-input v-model="form.bookTypeId" placeholder="请输入账本类型ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-divider content-position="center">ruoyi-config信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBookType">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBookType">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bookTypeList" :row-class-name="rowBookTypeIndex" @selection-change="handleBookTypeSelectionChange" ref="bookType">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="" prop="enable" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.enable" placeholder="请选择">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="" prop="icon" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.icon" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="remark" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="weight" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.weight" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="bookTypeDesc" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bookTypeDesc" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="orderSort" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.orderSort" placeholder="请输入" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBook, getBook, delBook, addBook, updateBook } from "@/api/config/book";

export default {
  name: "Book",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBookType: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ruoyi-config表格数据
      bookList: [],
      // ruoyi-config表格数据
      bookTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: null,
        createTime: null,
        enable: null,
        icon: null,
        updateBy: null,
        updateTime: null,
        weight: null,
        bookDefault: null,
        bookDesc: null,
        bookName: null,
        bookType: null,
        bookTypeId: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookDefault: [
          { required: true, message: "是否默认账本不能为空", trigger: "blur" }
        ],
        bookName: [
          { required: true, message: "账本名称不能为空", trigger: "blur" }
        ],
        bookType: [
          { required: true, message: "账本类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询ruoyi-config列表 */
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
        createBy: null,
        createTime: null,
        enable: "0",
        icon: null,
        remark: null,
        updateBy: null,
        updateTime: null,
        weight: null,
        bookDefault: null,
        bookDesc: null,
        bookName: null,
        bookType: null,
        bookTypeId: null,
        userId: null
      };
      this.bookTypeList = [];
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
      this.title = "添加ruoyi-config";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bookId = row.bookId || this.ids
      getBook(bookId).then(response => {
        this.form = response.data;
        this.bookTypeList = response.data.bookTypeList;
        this.open = true;
        this.title = "修改ruoyi-config";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.bookTypeList = this.bookTypeList;
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
      this.$modal.confirm('是否确认删除ruoyi-config编号为"' + bookIds + '"的数据项？').then(function() {
        return delBook(bookIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ruoyi-config序号 */
    rowBookTypeIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ruoyi-config添加按钮操作 */
    handleAddBookType() {
      let obj = {};
      obj.enable = "";
      obj.icon = "";
      obj.remark = "";
      obj.weight = "";
      obj.bookTypeDesc = "";
      obj.orderSort = "";
      this.bookTypeList.push(obj);
    },
    /** ruoyi-config删除按钮操作 */
    handleDeleteBookType() {
      if (this.checkedBookType.length == 0) {
        this.$modal.msgError("请先选择要删除的ruoyi-config数据");
      } else {
        const bookTypeList = this.bookTypeList;
        const checkedBookType = this.checkedBookType;
        this.bookTypeList = bookTypeList.filter(function(item) {
          return checkedBookType.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleBookTypeSelectionChange(selection) {
      this.checkedBookType = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/book/export', {
        ...this.queryParams
      }, `book_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
