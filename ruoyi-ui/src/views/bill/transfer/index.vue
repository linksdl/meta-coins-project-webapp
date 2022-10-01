<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="转账名称" prop="transferName">
        <el-input
          v-model="queryParams.transferName"
          placeholder="请输入转账名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="转账日期" prop="transferDatetime">
        <el-date-picker clearable
          v-model="queryParams.transferDatetime"
          type="date"
          value-format="yyyy-MM-dd hh:mm:ss"
          placeholder="请选择转账日期">
        </el-date-picker>
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
          v-hasPermi="['bill:transfer:add']"
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
          v-hasPermi="['bill:transfer:edit']"
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
          v-hasPermi="['bill:transfer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:transfer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="transferList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="转账ID" align="center" prop="transferId" />



      <el-table-column label="转账名称" align="center" prop="transferName" :show-overflow-tooltip="true" />


      <el-table-column label="转账类型" align="center" prop="transferType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_count" :value="scope.row.transferType"/>
        </template>
      </el-table-column>


      <el-table-column label="转账金额" align="center" prop="transferAmount" :show-overflow-tooltip="true" />


      <el-table-column label="描述" align="center" prop="transferDesc" :show-overflow-tooltip="true" />


      <el-table-column label="凭证" align="center" prop="transferImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.transferImgs" :width="50" :height="50"/>
        </template>
      </el-table-column>



      <el-table-column label="转账日期" align="center" prop="transferDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.transferDatetime, '{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>





      <el-table-column label="父类名" align="center" prop="transferParentName" :show-overflow-tooltip="true" />


      <el-table-column label="是否删除" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>


      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />


      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>




      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />


      <el-table-column label="权重" align="center" prop="weight" :show-overflow-tooltip="true" />
















      <el-table-column label="账本名" align="center" prop="transferBookName" :show-overflow-tooltip="true" />




      <el-table-column label="分类名" align="center" prop="transferCategoryName" :show-overflow-tooltip="true" />




      <el-table-column label="实体名" align="center" prop="transferEntityName" :show-overflow-tooltip="true" />




      <el-table-column label="账户名" align="center" prop="transferAccountName" :show-overflow-tooltip="true" />




      <el-table-column label="城市名" align="center" prop="transferCityName" :show-overflow-tooltip="true" />




      <el-table-column label="心情名" align="center" prop="transferEmotionName" :show-overflow-tooltip="true" />




      <el-table-column label="标签名" align="center" prop="transferLabelName" :show-overflow-tooltip="true" />


      <el-table-column label="成员名" align="center" prop="transferMemberName" :show-overflow-tooltip="true" />




      <el-table-column label="币种名" align="center" prop="transferMoneyName" :show-overflow-tooltip="true" />






      <el-table-column label="项目名" align="center" prop="transferProjectName" :show-overflow-tooltip="true" />




      <el-table-column label="天气名" align="center" prop="transferWeatherName" :show-overflow-tooltip="true" />





























      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:transfer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:transfer:remove']"
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

    <!-- 添加或修改转账账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="转账名称" prop="transferName">
          <el-input v-model="form.transferName" placeholder="请输入转账名称" />
        </el-form-item>


        <el-form-item label="转账类型" prop="transferType">
          <el-select v-model="form.transferType" placeholder="请选择转账类型">
            <el-option
              v-for="dict in dict.type.config_function_count"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="转账金额" prop="transferAmount">
          <el-input-number size="medium" v-model="form.transferAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
        </el-form-item>



        <el-form-item label="描述" prop="transferDesc">
          <el-input v-model="form.transferDesc" type="textarea" placeholder="请输入描述" />
        </el-form-item>


        <el-form-item label="凭证">
          <image-upload v-model="form.transferImgs"/>
        </el-form-item>


        <el-form-item label="转账日期" prop="transferDatetime">
          <el-date-picker clearable
            v-model="form.transferDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择转账日期">
          </el-date-picker>
        </el-form-item>


        <el-form-item label="父类ID" prop="transferParentId">
          <el-input v-model="form.transferParentId" placeholder="请输入父类ID" />
        </el-form-item>


        <el-form-item label="是否删除">
          <el-radio-group v-model="form.isDeleted">
            <el-radio
              v-for="dict in dict.type.config_is_deleted"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
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


        <el-form-item label="账本ID" prop="transferBookId">
          <el-select v-model="form.transferBookId" placeholder="请选择账本ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="分类ID" prop="transferCategoryId">
          <el-select v-model="form.transferCategoryId" placeholder="请选择分类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="实体ID" prop="transferEntityId">
          <el-select v-model="form.transferEntityId" placeholder="请选择实体ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账户ID" prop="transferAccountId">
          <el-select v-model="form.transferAccountId" placeholder="请选择账户ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="城市ID" prop="transferCityId">
          <el-select v-model="form.transferCityId" placeholder="请选择城市ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="心情ID" prop="transferEmotionId">
          <el-select v-model="form.transferEmotionId" placeholder="请选择心情ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="标签ID" prop="transferLabelId">
          <el-select v-model="form.transferLabelId" placeholder="请选择标签ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="成员ID" prop="transferMemberId">
          <el-select v-model="form.transferMemberId" placeholder="请选择成员ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="币种ID" prop="transferMoneyId">
          <el-select v-model="form.transferMoneyId" placeholder="请选择币种ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="项目ID" prop="transferProjectId">
          <el-select v-model="form.transferProjectId" placeholder="请选择项目ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="天气ID" prop="transferWeatherId">
          <el-select v-model="form.transferWeatherId" placeholder="请选择天气ID">
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

<script>
import { listTransfer, getTransfer, delTransfer, addTransfer, updateTransfer } from "@/api/bill/transfer";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Transfer",
  dicts: ['config_is_enable', 'config_is_deleted', 'config_function_count'],
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
      // 转账账单表格数据
      transferList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        transferName: null,
        transferDatetime: null,
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
    /** 选择转账账单图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询转账账单列表 */
    getList() {
      this.loading = true;
      listTransfer(this.queryParams).then(response => {
        this.transferList = response.rows;
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
        transferId: null,
        transferName: null,
        transferType: null,
        transferAmount: null,
        transferDesc: null,
        transferImgs: null,
        transferDatetime: null,
        transferParentId: null,
        transferParentName: null,
        isDeleted: 0,
        orderSort: null,
        enableStatus: 0,
        icon: null,
        remark: null,
        weight: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        transferUserId: null,
        transferUserName: null,
        transferBookId: null,
        transferBookName: null,
        transferCategoryId: null,
        transferCategoryName: null,
        transferEntityId: null,
        transferEntityName: null,
        transferAccountId: null,
        transferAccountName: null,
        transferCityId: null,
        transferCityName: null,
        transferEmotionId: null,
        transferEmotionName: null,
        transferLabelId: null,
        transferLabelName: null,
        transferMemberName: null,
        transferMemberId: null,
        transferMoneyName: null,
        transferMoneyId: null,
        transferProjectId: null,
        transferProjectName: null,
        transferWeatherId: null,
        transferWeatherName: null,
        transferAddress: null,
        transferCity: null,
        transferCountry: null,
        transferStreet: null,
        transferCounty: null,
        transferProvince: null,
        transferDate: null,
        transferYear: null,
        transferQuarter: null,
        transferMonth: null,
        transferPeriod: null,
        transferWeek: null,
        transferYearWeek: null,
        transferDay: null
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
      this.ids = selection.map(item => item.transferId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加转账账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const transferId = row.transferId || this.ids
      getTransfer(transferId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改转账账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.transferId != null) {
            updateTransfer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTransfer(this.form).then(response => {
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
      const transferIds = row.transferId || this.ids;
      this.$modal.confirm('是否确认删除转账账单编号为"' + transferIds + '"的数据项？').then(function() {
        return delTransfer(transferIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/transfer/export', {
        ...this.queryParams
      }, `transfer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
