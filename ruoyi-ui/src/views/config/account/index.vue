<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="accountName">
        <el-input
          v-model="queryParams.accountName"
          placeholder="请输入账户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别" prop="accountClass">
        <el-select v-model="queryParams.accountClass" placeholder="请选择类别" clearable>
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

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd hh:mm:ss"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
	    <el-button type="primary" icon="el-icon-search"  @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          @click="handleAdd"
          v-hasPermi="['config:account:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="accountList"
      row-key="accountId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column sortable label="排序" align="center" prop="orderSort" />
      <el-table-column sortable label="名称" prop="accountName" />
      <el-table-column sortable label="父类" align="center" prop="accountParentName" />
      <el-table-column sortable label="层级" align="center" prop="accountLevel" />
      <el-table-column sortable label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="功能范围" align="center" prop="accountScope">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.accountScope ? scope.row.accountScope.split(',') : []"/>
        </template>
      </el-table-column>

      <el-table-column sortable label="功能类型" align="center" prop="accountType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_type" :value="scope.row.accountType ? scope.row.accountType.split(',') : []"/>
        </template>
      </el-table-column>

      <el-table-column sortable label="功能类别" align="center" prop="accountClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_class" :value="scope.row.accountClass"/>
        </template>
      </el-table-column>

      <el-table-column sortable label="权重" align="center" prop="weight" />
      <el-table-column sortable label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="left" class-name="small-padding"  width="160">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:account:edit']"
          >修改</el-button>
          <el-button
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['config:account:add']"
          >新增</el-button>
          <el-button
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:account:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改账户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="626px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="88px">
        <el-form-item label="账户名称" prop="accountName">
          <el-input v-model="form.accountName" placeholder="请输入账户名称" />
        </el-form-item>

        <el-row>
           <el-col :span="12">
            <el-form-item label="父类账户" prop="accountParentId">
              <treeselect v-model="form.accountParentId" :options="accountOptions" :normalizer="normalizer" placeholder="请选择父类名称" />
            </el-form-item>
         </el-col>
         <el-col :span="12">
            <el-form-item label="层次" prop="accountLevel">
              <el-input-number  v-model="form.accountLevel" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>
           </el-col>
        </el-row>

        <el-form-item label="功能类型" prop="accountType">
          <el-checkbox-group v-model="form.accountType">
              <el-checkbox
                v-for="dict in dict.type.config_function_type"
                :key="dict.value"
                :label="dict.value">
                {{dict.label}}
              </el-checkbox>
          </el-checkbox-group>
        </el-form-item>


        <el-form-item label="功能范围" prop="accountScope">
          <el-checkbox-group v-model="form.accountScope">
            <el-checkbox
              v-for="dict in dict.type.config_function_scope"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>


        <el-form-item label="功能类别" prop="accountClass" >
          <el-radio-group v-model="form.accountClass">
            <el-radio
              v-for="dict in dict.type.config_function_class"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="描述" prop="accountDesc">
          <el-input v-model="form.accountDesc" placeholder="请输入描述" />
        </el-form-item>

        <el-row>
           <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input-number v-model="form.weight" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
           </el-col>
           <el-col :span="12">
              <el-form-item label="排序" prop="orderSort">
                <el-input-number  v-model="form.orderSort" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
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
import { listAccount, getAccount, delAccount, addAccount, updateAccount } from "@/api/config/account";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Account",
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
      // 账户管理表格数据
      accountList: [],
      // 账户管理树选项
      accountOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        accountName: null,
        accountClass: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        accountName: [
          { required: true, message: "账户名称不能为空", trigger: "blur" }
        ],
        accountParentId: [
          { required: true, message: "父类账户不能为空", trigger: "change" }
        ],
        accountType: [
          { required: true, message: "功能类型不能为空", trigger: "change" }
        ],
        accountScope: [
          { required: true, message: "功能范围不能为空", trigger: "blur" }
        ],
        accountClass: [
          { required: true, message: "功能类别不能为空", trigger: "blur" }
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
    /** 查询账户管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listAccount(this.queryParams).then(response => {
        this.accountList = this.handleTree(response.data, "accountId", "accountParentId");
        this.loading = false;
      });
    },
    /** 转换账户管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.accountId,
        label: node.accountName,
        children: node.children
      };
    },
	/** 查询账户管理下拉树结构 */
    getTreeselect() {
      listAccount().then(response => {
        this.accountOptions = [];
        const data = { accountId: 0, accountName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "accountId", "accountParentId");
        this.accountOptions.push(data);
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
        accountId: null,
        accountName: null,
        accountParentId: null,
        accountLevel: null,
        accountScope: [],
        accountType: [],
        accountSort: null,
        accountClass: "0",
        accountDesc: null,
        weight: null,
        icon: null,
        enableStatus: 1,
        orderSort: null,
        remark: null,
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
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.accountId) {
        this.form.accountParentId = row.accountId;
      } else {
        this.form.accountParentId = 0;
      }
      this.open = true;
      this.title = "添加账户管理";
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
        this.form.accountParentId = row.accountId;
      }
      getAccount(row.accountId).then(response => {
        this.form = response.data;
        this.form.accountScope = this.form.accountScope.split(",");
        this.form.accountType = this.form.accountType.split(",");
        this.open = true;
        this.title = "修改账户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.accountScope = this.form.accountScope.join(",");
          this.form.accountType = this.form.accountType.join(",");
          if (this.form.accountId != null) {
            updateAccount(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAccount(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除账户管理编号为"' + row.accountId + '"的数据项？').then(function() {
        return delAccount(row.accountId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
