<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="消费名" prop="consumeName">
        <el-input
          v-model="queryParams.consumeName"
          placeholder="请输入消费名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="消费类型" prop="consumeType">
        <el-select v-model="queryParams.consumeType" placeholder="请选择消费类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_out"
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

      <el-form-item label="账本名" prop="consumeBookName">
        <el-input
          v-model="queryParams.consumeBookName"
          placeholder="请输入账本名"
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
          v-hasPermi="['bill:consume:add']"
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
          v-hasPermi="['bill:consume:edit']"
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
          v-hasPermi="['bill:consume:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:consume:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="consumeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="消费ID" align="center" prop="consumeId" />



      <el-table-column label="消费名" align="center" prop="consumeName" :show-overflow-tooltip="true" />


      <el-table-column label="消费类型" align="center" prop="consumeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_out" :value="scope.row.consumeType"/>
        </template>
      </el-table-column>


      <el-table-column label="消费时间" align="center" prop="consumeDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.consumeDatetime, '{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>



      <el-table-column label="消费金额" align="center" prop="consumeAmount" :show-overflow-tooltip="true" />


      <el-table-column label="描述" align="center" prop="consumeDesc" :show-overflow-tooltip="true" />


      <el-table-column label="凭证" align="center" prop="consumeImgs" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.consumeImgs, '{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>





      <el-table-column label="父类名" align="center" prop="consumeParentName" :show-overflow-tooltip="true" />


      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>


























      <el-table-column label="天气名" align="center" prop="consumeWeatherName" :show-overflow-tooltip="true" />




      <el-table-column label="项目名" align="center" prop="consumeProjectName" :show-overflow-tooltip="true" />




      <el-table-column label="标签名" align="center" prop="consumeLabelName" :show-overflow-tooltip="true" />




      <el-table-column label="成员名" align="center" prop="consumeMemberName" :show-overflow-tooltip="true" />




      <el-table-column label="币种名" align="center" prop="consumeMoneyName" :show-overflow-tooltip="true" />




      <el-table-column label="实体名" align="center" prop="consumeEntityName" :show-overflow-tooltip="true" />




      <el-table-column label="分类名" align="center" prop="consumeCategoryName" :show-overflow-tooltip="true" />




      <el-table-column label="账户名" align="center" prop="consumeAccountName" :show-overflow-tooltip="true" />

































      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:consume:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:consume:remove']"
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

    <!-- 添加或修改支出账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="消费名" prop="consumeName">
          <el-input v-model="form.consumeName" placeholder="请输入消费名" />
        </el-form-item>


        <el-form-item label="消费类型" prop="consumeType">
          <el-select v-model="form.consumeType" placeholder="请选择消费类型">
            <el-option
              v-for="dict in dict.type.config_function_out"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="消费时间" prop="consumeDatetime">
          <el-date-picker clearable
            v-model="form.consumeDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择消费时间">
          </el-date-picker>
        </el-form-item>


        <el-form-item label="消费金额" prop="consumeAmount">
          <el-input-number size="medium" v-model="form.consumeAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
        </el-form-item>



        <el-form-item label="描述" prop="consumeDesc">
          <el-input v-model="form.consumeDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>


        <el-form-item label="凭证" prop="consumeImgs">
          <el-date-picker clearable
            v-model="form.consumeImgs"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择凭证">
          </el-date-picker>
        </el-form-item>


        <el-form-item label="父类ID" prop="consumeParentId">
          <el-select v-model="form.consumeParentId" placeholder="请选择父类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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


        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>


        <el-form-item label="天气ID" prop="consumeWeatherId">
          <el-select v-model="form.consumeWeatherId" placeholder="请选择天气ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="项目ID" prop="consumeProjectId">
          <el-select v-model="form.consumeProjectId" placeholder="请选择项目ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="标签ID" prop="consumeLabelId">
          <el-select v-model="form.consumeLabelId" placeholder="请选择标签ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="成员ID" prop="consumeMemberId">
          <el-select v-model="form.consumeMemberId" placeholder="请选择成员ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="币种ID" prop="consumeMoneyId">
          <el-select v-model="form.consumeMoneyId" placeholder="请选择币种ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="实体ID" prop="consumeEntityId">
          <el-select v-model="form.consumeEntityId" placeholder="请选择实体ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="分类ID" prop="consumeCategoryId">
          <el-select v-model="form.consumeCategoryId" placeholder="请选择分类ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账户ID" prop="consumeAccountId">
          <el-select v-model="form.consumeAccountId" placeholder="请选择账户ID">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="账本ID" prop="consumeBookId">
          <el-select v-model="form.consumeBookId" placeholder="请选择账本ID">
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
import { listConsume, getConsume, delConsume, addConsume, updateConsume } from "@/api/bill/consume";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Consume",
  dicts: ['config_is_enable', 'config_is_deleted', 'config_function_out'],
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
      // 支出账单表格数据
      consumeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        consumeName: null,
        consumeType: null,
        createTime: null,
        consumeBookName: null,
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
    /** 选择支出账单图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询支出账单列表 */
    getList() {
      this.loading = true;
      listConsume(this.queryParams).then(response => {
        this.consumeList = response.rows;
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
        consumeId: null,
        consumeName: null,
        consumeType: null,
        consumeDatetime: null,
        consumeAmount: null,
        consumeDesc: null,
        consumeImgs: null,
        consumeParentId: null,
        consumeParentName: null,
        enableStatus: 0,
        icon: null,
        remark: null,
        weight: null,
        isDeleted: 0,
        orderSort: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        consumeUserId: null,
        consumeUserName: null,
        consumeWeatherId: null,
        consumeWeatherName: null,
        consumeProjectId: null,
        consumeProjectName: null,
        consumeLabelId: null,
        consumeLabelName: null,
        consumeMemberId: null,
        consumeMemberName: null,
        consumeMoneyId: null,
        consumeMoneyName: null,
        consumeEntityId: null,
        consumeEntityName: null,
        consumeCategoryId: null,
        consumeCategoryName: null,
        consumeAccountId: null,
        consumeAccountName: null,
        consumeBookId: null,
        consumeBookName: null,
        consumeCounty: null,
        consumeProvince: null,
        consumeCity: null,
        consumeCountry: null,
        consumeAddress: null,
        consumeStreet: null,
        consumeDate: null,
        consumeYear: null,
        consumeQuarter: null,
        consumeMonth: null,
        consumeWeek: null,
        consumeDay: null,
        consumePeriod: null,
        consumeYearWeek: null
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
      this.ids = selection.map(item => item.consumeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支出账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumeId = row.consumeId || this.ids
      getConsume(consumeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支出账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumeId != null) {
            updateConsume(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsume(this.form).then(response => {
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
      const consumeIds = row.consumeId || this.ids;
      this.$modal.confirm('是否确认删除支出账单编号为"' + consumeIds + '"的数据项？').then(function() {
        return delConsume(consumeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/consume/export', {
        ...this.queryParams
      }, `consume_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
