<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="借贷名称" prop="debtName">
        <el-input
          v-model="queryParams.debtName"
          placeholder="请输入借贷名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="借贷类型" prop="debtType">
        <el-select v-model="queryParams.debtType" placeholder="请选择借贷类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_debt"
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
          value-format="yyyy-MM-dd hh:mm:ss"
          placeholder="请选择创建时间">
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
          v-hasPermi="['bill:debt:add']"
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
          v-hasPermi="['bill:debt:edit']"
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
          v-hasPermi="['bill:debt:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:debt:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="debtList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="借贷ID" align="center" prop="debtId" />



      <el-table-column label="借贷名称" align="center" prop="debtName" :show-overflow-tooltip="true" />


      <el-table-column label="借贷类型" align="center" prop="debtType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_debt" :value="scope.row.debtType"/>
        </template>
      </el-table-column>


      <el-table-column label="凭证" align="center" prop="debtImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.debtImgs" :width="50" :height="50"/>
        </template>
      </el-table-column>



      <el-table-column label="描述" align="center" prop="debtDesc" :show-overflow-tooltip="true" />


      <el-table-column label="日期" align="center" prop="debtDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.debtDatetime, '{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>



      <el-table-column label="金额" align="center" prop="debtAmount" :show-overflow-tooltip="true" />


      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>






      <el-table-column label="父类名称" align="center" prop="debtParentName" :show-overflow-tooltip="true" />








      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />




      <el-table-column label="是否删除" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>


      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />


      <el-table-column label="权重" align="center" prop="weight" :show-overflow-tooltip="true" />








      <el-table-column label="账户名" align="center" prop="debtAccountName" :show-overflow-tooltip="true" />




      <el-table-column label="账本名" align="center" prop="debtBookName" :show-overflow-tooltip="true" />




      <el-table-column label="分类名" align="center" prop="debtCategoryName" :show-overflow-tooltip="true" />




      <el-table-column label="城市名" align="center" prop="debtCityName" :show-overflow-tooltip="true" />




      <el-table-column label="心情名" align="center" prop="debtEmotionName" :show-overflow-tooltip="true" />




      <el-table-column label="实体名" align="center" prop="debtEntityName" :show-overflow-tooltip="true" />




      <el-table-column label="标签名" align="center" prop="debtLabelName" :show-overflow-tooltip="true" />








      <el-table-column label="币种名" align="center" prop="debtMoneyName" :show-overflow-tooltip="true" />




      <el-table-column label="项目名" align="center" prop="debtProjectName" :show-overflow-tooltip="true" />




      <el-table-column label="天气名" align="center" prop="debtWeatherName" :show-overflow-tooltip="true" />





























      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:debt:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:debt:remove']"
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

    <!-- 添加或修改借贷账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="借贷名称" prop="debtName">
          <el-input v-model="form.debtName" placeholder="请输入借贷名称" />
        </el-form-item>


        <el-form-item label="借贷类型" prop="debtType">
          <el-select v-model="form.debtType" placeholder="请选择借贷类型">
            <el-option
              v-for="dict in dict.type.config_function_debt"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="凭证">
          <image-upload v-model="form.debtImgs"/>
        </el-form-item>


        <el-form-item label="描述" prop="debtDesc">
          <el-input v-model="form.debtDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>


        <el-form-item label="日期" prop="debtDatetime">
          <el-date-picker clearable
            v-model="form.debtDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>


        <el-form-item label="金额" prop="debtAmount">
          <el-input-number size="medium" v-model="form.debtAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
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

        <el-form-item label="父类ID" prop="debtParentId">
          <el-select v-model="form.debtParentId" placeholder="请选择父类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>


        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>


        <el-form-item label="权重" prop="weight">
          <el-input-number size="medium" v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>


        <el-form-item label="账户ID" prop="debtAccountId">
          <el-select v-model="form.debtAccountId" placeholder="请选择账户ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账本ID" prop="debtBookId">
          <el-select v-model="form.debtBookId" placeholder="请选择账本ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="分类ID" prop="debtCategoryId">
          <el-select v-model="form.debtCategoryId" placeholder="请选择分类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="城市ID" prop="debtCityId">
          <el-select v-model="form.debtCityId" placeholder="请选择城市ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="心情ID" prop="debtEmotionId">
          <el-select v-model="form.debtEmotionId" placeholder="请选择心情ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="实体ID" prop="debtEntityId">
          <el-select v-model="form.debtEntityId" placeholder="请选择实体ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="标签ID" prop="debtLabelId">
          <el-select v-model="form.debtLabelId" placeholder="请选择标签ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="成员ID" prop="debtMemberId">
          <el-select v-model="form.debtMemberId" placeholder="请选择成员ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="币种ID" prop="debtMoneyId">
          <el-input v-model="form.debtMoneyId" placeholder="请输入币种ID" />
        </el-form-item>


        <el-form-item label="项目ID" prop="debtProjectId">
          <el-input v-model="form.debtProjectId" placeholder="请输入项目ID" />
        </el-form-item>


        <el-form-item label="天气ID" prop="debtWeatherId">
          <el-input v-model="form.debtWeatherId" placeholder="请输入天气ID" />
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
import { listDebt, getDebt, delDebt, addDebt, updateDebt } from "@/api/bill/debt";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Debt",
  dicts: ['config_is_enable', 'config_is_deleted', 'config_function_debt'],
  components: {IconSelect},
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
      // 借贷账单表格数据
      debtList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        debtName: null,
        debtType: null,
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
    /** 选择借贷账单图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询借贷账单列表 */
    getList() {
      this.loading = true;
      listDebt(this.queryParams).then(response => {
        this.debtList = response.rows;
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
        debtId: null,
        debtName: null,
        debtType: null,
        debtImgs: null,
        debtDesc: null,
        debtDatetime: null,
        debtAmount: null,
        enableStatus: 0,
        debtParentId: null,
        createBy: null,
        debtParentName: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        icon: null,
        isDeleted: 0,
        orderSort: null,
        weight: null,
        debtUserId: null,
        debtUserName: null,
        debtAccountId: null,
        debtAccountName: null,
        debtBookId: null,
        debtBookName: null,
        debtCategoryId: null,
        debtCategoryName: null,
        debtCityId: null,
        debtCityName: null,
        debtEmotionId: null,
        debtEmotionName: null,
        debtEntityId: null,
        debtEntityName: null,
        debtLabelId: null,
        debtLabelName: null,
        debtMemberId: null,
        debtMemberName: null,
        debtMoneyId: null,
        debtMoneyName: null,
        debtProjectId: null,
        debtProjectName: null,
        debtWeatherId: null,
        debtWeatherName: null,
        debtAddress: null,
        debtCity: null,
        debtCountry: null,
        debtProvince: null,
        debtCounty: null,
        debtStreet: null,
        debtYear: null,
        debtDate: null,
        debtDay: null,
        debtMonth: null,
        debtPeriod: null,
        debtQuarter: null,
        debtWeek: null,
        debtYearWeek: null
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
      this.ids = selection.map(item => item.debtId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加借贷账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const debtId = row.debtId || this.ids
      getDebt(debtId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改借贷账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.debtId != null) {
            updateDebt(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDebt(this.form).then(response => {
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
      const debtIds = row.debtId || this.ids;
      this.$modal.confirm('是否确认删除借贷账单编号为"' + debtIds + '"的数据项？').then(function() {
        return delDebt(debtIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/debt/export', {
        ...this.queryParams
      }, `debt_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
