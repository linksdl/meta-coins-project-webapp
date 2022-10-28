<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="flowType">
        <el-select v-model="queryParams.flowType" placeholder="请选择流水类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_scope"
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
          v-hasPermi="['bill:flow:add']"
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
          v-hasPermi="['bill:flow:edit']"
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
          v-hasPermi="['bill:flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:flow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :row-class-name="tableRowClassName" :data="flowList" @selection-change="handleSelectionChange">

      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.flowId }}</span>
            </el-form-item>
            <el-form-item label="名称">
              <span>{{ props.row.flowName }}</span>
            </el-form-item>
            <el-form-item label="类型">
              <template slot-scope="scope">
              <dict-tag :options="dict.type.config_function_scope" :value="props.row.flowType"/>
               </template>
            </el-form-item>
            <el-form-item label="时间">
              <span>{{ props.row.flowDate }}</span>
            </el-form-item>
            <el-form-item label="金额">
              <span>{{ props.row.flowAmount }}</span>
            </el-form-item>
            <el-form-item label="币种">
              <span>{{ props.row.flowMoneyName }}</span>
            </el-form-item>
            <el-form-item label="账户">
              <span>{{ props.row.flowAccountName }}</span>
            </el-form-item>
            <el-form-item label="分类">
              <span>{{ props.row.flowCategoryName }}</span>
            </el-form-item>
            <el-form-item label="实体(商家，个人，收入账户)">
              <span>{{ props.row.flowEntityName }}</span>
            </el-form-item>
            <el-form-item label="项目">
              <span>{{ props.row.flowProjectName }}</span>
            </el-form-item>
            <el-form-item label="标签">
              <span>{{ props.row.flowLabelName }}</span>
            </el-form-item>
            <el-form-item label="城市">
              <span>{{ props.row.flowCityName }}</span>
            </el-form-item>
            <el-form-item label="成员">
              <span>{{ props.row.flowMemberName }}</span>
            </el-form-item>
            <el-form-item label="心情">
              <span>{{ props.row.flowEmotionName }}</span>
            </el-form-item>
            <el-form-item label="天气">
              <span>{{ props.row.flowWeatherName }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="名称" align="center" prop="flowName" :show-overflow-tooltip="true" />
      <el-table-column label="源头" align="center" prop="flowSource" :show-overflow-tooltip="true" />
      <el-table-column label="目的" align="center" prop="flowTarget" :show-overflow-tooltip="true" />
      <el-table-column label="金额" align="center" prop="flowAmount" :show-overflow-tooltip="true" />
      <el-table-column label="标志" align="center" prop="flowSign">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_sign" :value="scope.row.flowSign"/>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="flowType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.flowType"/>
        </template>
      </el-table-column>
      <el-table-column label="日期" align="center" prop="flowDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.flowDatetime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="flowDesc" :show-overflow-tooltip="true" />
      <el-table-column label="图片凭证" align="center" prop="flowImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.flowImgs" :width="25" :height="25"/>
        </template>
      </el-table-column>
      <el-table-column label="币种" align="center" prop="flowMoneyName" :show-overflow-tooltip="true" />
      <el-table-column label="标签" align="center" prop="flowLabelName" :show-overflow-tooltip="true" />
      <el-table-column label="实体" align="center" prop="flowEntityName" :show-overflow-tooltip="true" />
      <el-table-column label="是否入账" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="是否删除" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>
  <!--
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:flow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:flow:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改账单流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="流水名称" prop="flowName">
          <el-input v-model="form.flowName" placeholder="请输入流水名称" />
        </el-form-item>


        <el-form-item label="流水源头" prop="flowSource">
          <el-select v-model="form.flowSource" placeholder="请选择流水源头">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="流水目的" prop="flowTarget">
          <el-select v-model="form.flowTarget" placeholder="请选择流水目的">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="流水金额" prop="flowAmount">
          <el-input-number size="medium" v-model="form.flowAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
        </el-form-item>



        <el-form-item label="流水标志" prop="flowSign">
          <el-select v-model="form.flowSign" placeholder="请选择流水标志">
            <el-option
              v-for="dict in dict.type.config_function_sign"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="流水类型" prop="flowType">
          <el-select v-model="form.flowType" placeholder="请选择流水类型">
            <el-option
              v-for="dict in dict.type.config_function_scope"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="流水日期" prop="flowDatetime">
          <el-date-picker clearable
            v-model="form.flowDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择流水日期">
          </el-date-picker>
        </el-form-item>


        <el-form-item label="父类ID" prop="flowParentId">
          <el-select v-model="form.flowParentId" placeholder="请选择父类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="流水账单ID" prop="flowBillId">
          <el-select v-model="form.flowBillId" placeholder="请选择流水账单ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>


        <el-form-item label="描述" prop="flowDesc">
          <el-input v-model="form.flowDesc" type="textarea" placeholder="请输入描述" />
        </el-form-item>


        <el-form-item label="图片凭证">
          <image-upload v-model="form.flowImgs"/>
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


        <el-form-item label="权重" prop="weight">
          <el-input-number size="medium" v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>


        <el-form-item label="天气ID" prop="flowWeatherId">
          <el-select v-model="form.flowWeatherId" placeholder="请选择天气ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="币种ID" prop="flowMoneyId">
          <el-select v-model="form.flowMoneyId" placeholder="请选择币种ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="成员ID" prop="flowMemberId">
          <el-select v-model="form.flowMemberId" placeholder="请选择成员ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="标签ID" prop="flowLabelId">
          <el-select v-model="form.flowLabelId" placeholder="请选择标签ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="项目ID" prop="flowProjectId">
          <el-select v-model="form.flowProjectId" placeholder="请选择项目ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="心情ID" prop="flowEmotionId">
          <el-select v-model="form.flowEmotionId" placeholder="请选择心情ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="分类ID" prop="flowCategoryId">
          <el-select v-model="form.flowCategoryId" placeholder="请选择分类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账户ID" prop="flowAccountId">
          <el-select v-model="form.flowAccountId" placeholder="请选择账户ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="实体ID" prop="flowEntityId">
          <el-select v-model="form.flowEntityId" placeholder="请选择实体ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="城市ID" prop="flowCityId">
          <el-select v-model="form.flowCityId" placeholder="请选择城市ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账本ID" prop="flowBookId">
          <el-select v-model="form.flowBookId" placeholder="请选择账本ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 20px;
    line-height: 20px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 150px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }

  .demo-table-expand {
    font-size: 12;
  }
  .demo-table-expand label {
    width: 80px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 33%;
  }
</style>

<script>
import { listFlow, getFlow, delFlow, addFlow, updateFlow } from "@/api/bill/flow";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Flow",
  dicts: ['config_function_scope', 'config_is_enable', 'config_is_deleted', 'config_function_sign'],
  components: {IconSelect},
  data() {
    return {
      tableRowClassName({row, rowIndex}) {
        if (row.flowAmount > 10 && row.flowAmount < 100) {
          return 'warning-row';
        } else if (row.flowAmount > 100) {
          return 'success-row';
        }
        return '';
      },
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
      // 账单流水表格数据
      flowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
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
    /** 选择账单流水图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询账单流水列表 */
    getList() {
      this.loading = true;
      listFlow(this.queryParams).then(response => {
        this.flowList = response.rows;
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
        flowId: null,
        flowName: null,
        flowSource: null,
        flowTarget: null,
        flowAmount: null,
        flowSign: null,
        flowType: null,
        flowDatetime: null,
        flowParentId: null,
        flowParentName: null,
        flowBillId: null,
        isDeleted: 0,
        orderSort: null,
        flowDesc: null,
        flowImgs: null,
        flowRemark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        enableStatus: 0,
        icon: null,
        remark: null,
        weight: null,
        flowUserId: null,
        flowUserName: null,
        flowWeatherId: null,
        flowWeatherName: null,
        flowMoneyName: null,
        flowMoneyId: null,
        flowMemberName: null,
        flowMemberId: null,
        flowLabelName: null,
        flowLabelId: null,
        flowProjectId: null,
        flowProjectName: null,
        flowEmotionId: null,
        flowEmotionName: null,
        flowCategoryName: null,
        flowCategoryId: null,
        flowAccountName: null,
        flowAccountId: null,
        flowEntityName: null,
        flowEntityId: null,
        flowCityId: null,
        flowCityName: null,
        flowBookId: null,
        flowBookName: null,
        flowYear: null,
        flowMonth: null,
        flowDate: null,
        flowDay: null,
        flowQuarter: null,
        flowPeriod: null,
        flowWeek: null,
        flowYearWeek: null,
        flowCounty: null,
        flowProvince: null,
        flowStreet: null,
        flowCountry: null,
        flowCity: null,
        flowAddress: null
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
      this.ids = selection.map(item => item.flowId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账单流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const flowId = row.flowId || this.ids
      getFlow(flowId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账单流水";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.flowId != null) {
            updateFlow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFlow(this.form).then(response => {
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
      const flowIds = row.flowId || this.ids;
      this.$modal.confirm('是否确认删除账单流水编号为"' + flowIds + '"的数据项？').then(function() {
        return delFlow(flowIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/flow/export', {
        ...this.queryParams
      }, `flow_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
