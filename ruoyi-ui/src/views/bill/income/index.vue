<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="收入名称" prop="incomeName">
        <el-input
          v-model="queryParams.incomeName"
          placeholder="请输入收入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收入日期" prop="incomeDatetime">
        <el-date-picker clearable
          v-model="queryParams.incomeDatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择收入日期">
        </el-date-picker>
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
          v-hasPermi="['bill:income:add']"
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
      :data="incomeList"
      row-key="incomeId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="收入名称" prop="incomeName" />
      <el-table-column label="收入类型" align="center" prop="incomeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_class" :value="scope.row.incomeType"/>
        </template>
      </el-table-column>
      <el-table-column label="收入金额" align="center" prop="incomeAmount" />
      <el-table-column label="收入日期" align="center" prop="incomeDatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.incomeDatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收入描述" align="center" prop="incomeDesc" :show-overflow-tooltip="true" />
      <el-table-column label="收入凭证" align="center" prop="incomeImgs" />
      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="父类名称" align="center" prop="incomeParentName" />
      //<el-table-column label="账本名" align="center" prop="incomeBookName" />
      //<el-table-column label="账户名" align="center" prop="incomeAccountName" />
      //<el-table-column label="分类名" align="center" prop="incomeCategoryName" />
      //<el-table-column label="城市名" align="center" prop="incomeCityName" />
      //<el-table-column label="心情名" align="center" prop="incomeEmotionName" />
      //<el-table-column label="主体名" align="center" prop="incomeEnterpriseName" />
      //<el-table-column label="地址" align="center" prop="incomeAddress" />
      //<el-table-column label="标签名" align="center" prop="incomeLabelName" />
      //<el-table-column label="成员名" align="center" prop="incomeMemberName" />
      //<el-table-column label="币种名" align="center" prop="incomeMoneyName" />
      //<el-table-column label="项目名" align="center" prop="incomeProjectName" />
      //<el-table-column label="天气名" align="center" prop="incomeWeatherName" />
      //<el-table-column label="用户名" align="center" prop="incomeUserName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:income:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['bill:income:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:income:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改收入账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收入名称" prop="incomeName">
          <el-input v-model="form.incomeName" placeholder="请输入收入名称" />
        </el-form-item>
        <el-form-item label="收入类型" prop="incomeType">
          <el-select v-model="form.incomeType" placeholder="请选择收入类型">
            <el-option
              v-for="dict in dict.type.config_function_class"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收入金额" prop="incomeAmount">
          <el-input-number size="medium" v-model="form.incomeAmount" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="收入日期" prop="incomeDatetime">
          <el-date-picker clearable
            v-model="form.incomeDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择收入日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收入描述" prop="incomeDesc">
          <el-input v-model="form.incomeDesc" placeholder="请输入收入描述" />
        </el-form-item>
        <el-form-item label="收入凭证" prop="incomeImgs">
          <el-input v-model="form.incomeImgs" placeholder="请输入收入凭证" />
        </el-form-item>
        <el-form-item label="是否可用">
          <el-radio-group v-model="form.enableStatus">
            <el-radio
              v-for="dict in dict.type.config_is_enable"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="权重" prop="weight">
          <el-input-number size="medium" v-model="form.weight" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="父类ID" prop="incomeParentId">
          <treeselect v-model="form.incomeParentId" :options="incomeOptions" :normalizer="normalizer" placeholder="请选择父类ID" />
        </el-form-item>
        <el-form-item label="账本ID" prop="incomeBookId">
          <el-input v-model="form.incomeBookId" placeholder="请输入账本ID" />
        </el-form-item>
        <el-form-item label="账户ID" prop="incomeAccountId">
          <el-input v-model="form.incomeAccountId" placeholder="请输入账户ID" />
        </el-form-item>
        <el-form-item label="分类ID" prop="incomeCategoryId">
          <el-input v-model="form.incomeCategoryId" placeholder="请输入分类ID" />
        </el-form-item>
        <el-form-item label="城市ID" prop="incomeCityId">
          <el-input v-model="form.incomeCityId" placeholder="请输入城市ID" />
        </el-form-item>
        <el-form-item label="心情ID" prop="incomeEmotionId">
          <el-input v-model="form.incomeEmotionId" placeholder="请输入心情ID" />
        </el-form-item>
        <el-form-item label="主体ID" prop="incomeEnterpriseId">
          <el-input v-model="form.incomeEnterpriseId" placeholder="请输入主体ID" />
        </el-form-item>
        <el-form-item label="地址" prop="incomeAddress">
          <el-input v-model="form.incomeAddress" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="标签ID" prop="incomeLabelId">
          <el-input v-model="form.incomeLabelId" placeholder="请输入标签ID" />
        </el-form-item>
        <el-form-item label="成员ID" prop="incomeMemberId">
          <el-input v-model="form.incomeMemberId" placeholder="请输入成员ID" />
        </el-form-item>
        <el-form-item label="币种ID" prop="incomeMoneyId">
          <el-input v-model="form.incomeMoneyId" placeholder="请输入币种ID" />
        </el-form-item>
        <el-form-item label="项目ID" prop="incomeProjectId">
          <el-input v-model="form.incomeProjectId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="天气ID" prop="incomeWeatherId">
          <el-input v-model="form.incomeWeatherId" placeholder="请输入天气ID" />
        </el-form-item>
        <el-form-item label="用户名" prop="incomeUserName">
          <el-input v-model="form.incomeUserName" placeholder="请输入用户名" />
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
import { listIncome, getIncome, delIncome, addIncome, updateIncome } from "@/api/bill/income";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Income",
  dicts: ['config_is_enable', 'config_function_class'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 收入账单表格数据
      incomeList: [],
      // 收入账单树选项
      incomeOptions: [],
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
        incomeName: null,
        incomeDatetime: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询收入账单列表 */
    getList() {
      this.loading = true;
      listIncome(this.queryParams).then(response => {
        this.incomeList = this.handleTree(response.data, "incomeId", "incomeParentId");
        this.loading = false;
      });
    },
    /** 转换收入账单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.incomeId,
        label: node.incomeName,
        children: node.children
      };
    },
	/** 查询收入账单下拉树结构 */
    getTreeselect() {
      listIncome().then(response => {
        this.incomeOptions = [];
        const data = { incomeId: 0, incomeName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "incomeId", "incomeParentId");
        this.incomeOptions.push(data);
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
        incomeId: null,
        incomeName: null,
        incomeType: null,
        incomeAmount: null,
        incomeDatetime: null,
        incomeDesc: null,
        incomeImgs: null,
        enableStatus: 0,

        icon: null,
        remark: null,
        orderSort: null,
        weight: null,
        incomeParentId: null,
        incomeParentName: null,
        incomeBookId: null,
        incomeBookName: null,
        incomeAccountId: null,
        incomeAccountName: null,
        incomeCategoryId: null,
        incomeCategoryName: null,
        incomeCityId: null,
        incomeCityName: null,
        incomeEmotionId: null,
        incomeEmotionName: null,
        incomeEnterpriseId: null,
        incomeEnterpriseName: null,
        incomeAddress: null,
        incomeLabelId: null,
        incomeLabelName: null,
        incomeMemberId: null,
        incomeMemberName: null,
        incomeMoneyId: null,
        incomeMoneyName: null,
        incomeProjectId: null,
        incomeProjectName: null,
        incomeWeatherId: null,
        incomeWeatherName: null,
        incomeUserId: null,
        incomeUserName: null,
        incomeCountry: null,
        incomeProvince: null,
        incomeCounty: null,
        incomeCity: null,
        incomeStreet: null,
        incomeDate: null,
        incomeYear: null,
        incomeQuarter: null,
        incomeMonth: null,
        incomeYearWeek: null,
        incomeWeek: null,
        incomeDay: null,
        incomePeriod: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        isDeleted: null
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
      if (row != null && row.incomeId) {
        this.form.incomeParentId = row.incomeId;
      } else {
        this.form.incomeParentId = 0;
      }
      this.open = true;
      this.title = "添加收入账单";
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
        this.form.incomeParentId = row.incomeId;
      }
      getIncome(row.incomeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收入账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.incomeId != null) {
            updateIncome(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIncome(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除收入账单编号为"' + row.incomeId + '"的数据项？').then(function() {
        return delIncome(row.incomeId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
