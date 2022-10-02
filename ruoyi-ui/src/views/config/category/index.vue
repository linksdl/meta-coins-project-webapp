<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入分类名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="功能分类" prop="categoryClass">
        <el-select v-model="queryParams.categoryClass" placeholder="请选择功能分类" clearable>
          <el-option
            v-for="dict in dict.type.config_function_class"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否可用" prop="enableStatus">
        <el-select v-model="queryParams.enableStatus" placeholder="请选择是否可用" clearable>
          <el-option
            v-for="dict in dict.type.config_is_enable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['config:category:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="categoryList"
      row-key="categoryId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="分类名称" prop="categoryName" />
      <el-table-column label="父类名称" align="center" prop="categoryParentName" />
      <el-table-column label="功能分类" align="center" prop="categoryClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_class" :value="scope.row.categoryClass"/>
        </template>
      </el-table-column>
      <el-table-column label="层次" align="center" prop="categoryLevel" />
      <el-table-column label="描述" align="center" prop="categoryDesc" />
      <el-table-column label="功能范围" align="center" prop="categoryScope">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.categoryScope ? scope.row.categoryScope.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="功能类型" align="center" prop="categoryType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_type" :value="scope.row.categoryType"/>
        </template>
      </el-table-column>
      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="orderSort" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="权重" align="center" prop="weight" />
      <el-table-column label="操作" align="center" class-name="small-padding" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:category:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['config:category:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:category:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改分类管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="666px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入分类名" />
        </el-form-item>

      <el-row>
        <el-col :span="12">
        <el-form-item label="父类名称" prop="categoryParentId">
          <treeselect v-model="form.categoryParentId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择父ID" />
        </el-form-item>
        </el-col>
        <el-col :span="12">
              <el-form-item label="分类层次" prop="categoryLevel">
                <el-input-number size="medium" v-model="form.categoryLevel" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
         </el-row>

        <el-form-item label="描述" prop="categoryDesc">
          <el-input v-model="form.categoryDesc" placeholder="请输入描述" />
        </el-form-item>

      <el-row>
        <el-col :span="24">
          <el-form-item label="功能类型" prop="categoryType">
            <el-radio-group v-model="form.categoryType">
              <el-radio
                v-for="dict in dict.type.config_function_type"
                :key="dict.value"
                :label="dict.value">
                {{dict.label}}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

        <el-form-item label="功能范围" prop="categoryScope">
          <el-checkbox-group v-model="form.categoryScope">
            <el-checkbox
              v-for="item in dict.type.config_function_scope"
              :key="item.value"
              :label="item.value">
              {{item.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-row>
           <el-col :span="24" >
              <el-form-item label="功能类别" prop="categoryClass">
                <el-radio-group v-model="form.categoryClass">
                  <el-radio
                    v-for="dict in dict.type.config_function_class"
                    :key="dict.value"
      :label="dict.value"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
         </el-row>

        <el-row>
            <el-col :span="12">
              <el-form-item label="排序" prop="orderSort">
                <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input-number size="medium" v-model="form.weight" type="input-number" :min="1" :max="999999999" placeholder="请输入权重" />
              </el-form-item>
            </el-col>
        </el-row>

        <el-form-item label="可用" prop="enableStatus">
          <el-radio-group v-model="form.enableStatus">
            <el-radio
              v-for="dict in dict.type.config_is_enable"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>



        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
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
import { listCategory, getCategory, delCategory, addCategory, updateCategory } from "@/api/config/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Category",
  dicts: ['config_function_scope', 'config_function_type', 'config_is_enable', 'config_function_class'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 分类管理表格数据
      categoryList: [],
      // 分类管理树选项
      categoryOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        categoryName: null,
        categoryClass: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryName: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
        categoryParentId: [
          { required: true, message: "父类名称不能为空", trigger: "change" }
        ],
        categoryType: [
          { required: true, message: "功能类型不能为空", trigger: "change" }
        ],
        categoryScope: [
          { required: true, message: "功能范围不能为空", trigger: "blur" }
        ],
        categoryClass: [
          { required: true, message: "功能类别不能为空", trigger: "change" }
        ],
        enableStatus: [
          { required: true, message: "数据状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询分类管理列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        this.categoryList = this.handleTree(response.data, "categoryId", "categoryParentId");
        this.loading = false;
      });
    },
    /** 转换分类管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.categoryId,
        label: node.categoryName,
        children: node.children
      };
    },
	/** 查询分类管理下拉树结构 */
    getTreeselect() {
      listCategory().then(response => {
        this.categoryOptions = [];
        const data = { categoryId: 0, categoryName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "categoryId", "categoryParentId");
        this.categoryOptions.push(data);
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
        categoryId: null,
        categoryName: null,
        categoryParentId: null,
        categoryParentName: null,
        categoryClass: null,
        categoryLevel: null,
        categoryDesc: null,
        categoryScope: [],
        categorySort: null,
        categoryType: null,
        enableStatus: 0,
        icon: null,
        orderSort: null,
        remark: null,
        weight: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        bookId: null,
        bookName: null,
        userId: null,
        userName: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.categoryId) {
        this.form.categoryParentId = row.categoryId;
      } else {
        this.form.categoryParentId = 0;
      }
      this.open = true;
      this.title = "添加分类管理";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.categoryParentId = row.categoryId;
      }
      getCategory(row.categoryId).then(response => {
        this.form = response.data;
        this.form.categoryScope = this.form.categoryScope.split(",");
        this.open = true;
        this.title = "修改分类管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.categoryScope = this.form.categoryScope.join(",");
          if (this.form.categoryId != null) {
            updateCategory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategory(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除分类管理编号为"' + row.categoryId + '"的数据项？').then(function() {
        return delCategory(row.categoryId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
