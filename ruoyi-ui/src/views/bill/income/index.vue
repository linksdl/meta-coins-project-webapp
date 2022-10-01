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
          value-format="yyyy-MM-dd hh:mm:ss"
          placeholder="请选择收入日期">
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
          v-hasPermi="['bill:income:add']"
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
          v-hasPermi="['bill:income:edit']"
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
          v-hasPermi="['bill:income:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:income:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="incomeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="收入ID" align="center" prop="incomeId" />



      <el-table-column label="收入名称" align="center" prop="incomeName" :show-overflow-tooltip="true" />


      <el-table-column label="收入类型" align="center" prop="incomeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_in" :value="scope.row.incomeType"/>
        </template>
      </el-table-column>


      <el-table-column label="收入金额" align="center" prop="incomeAmount" :show-overflow-tooltip="true" />


      <el-table-column label="收入日期" align="center" prop="incomeDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.incomeDatetime, '{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>



      <el-table-column label="收入描述" align="center" prop="incomeDesc" :show-overflow-tooltip="true" />


      <el-table-column label="收入凭证" align="center" prop="incomeImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.incomeImgs" :width="50" :height="50"/>
        </template>
      </el-table-column>



      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>












      <el-table-column label="父类名称" align="center" prop="incomeParentName" :show-overflow-tooltip="true" />






























      <el-table-column label="标签名" align="center" prop="incomeLabelName" :show-overflow-tooltip="true" />




      <el-table-column label="成员名" align="center" prop="incomeMemberName" :show-overflow-tooltip="true" />




      <el-table-column label="币种名" align="center" prop="incomeMoneyName" :show-overflow-tooltip="true" />




      <el-table-column label="项目名" align="center" prop="incomeProjectName" :show-overflow-tooltip="true" />




      <el-table-column label="天气名" align="center" prop="incomeWeatherName" :show-overflow-tooltip="true" />






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
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:income:remove']"
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

    <!-- 添加或修改收入账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="868px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
         <el-row>
          <el-col :span="14">
            <el-form-item label="名称" prop="incomeName">
              <el-input v-model="form.incomeName" placeholder="请输入收入名称" />
            </el-form-item>
          </el-col>

         <el-col :span="10">
          <el-form-item label="日期" prop="incomeDatetime">
            <el-date-picker clearable
              v-model="form.incomeDatetime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择收入日期">
            </el-date-picker>
          </el-form-item>
        </el-col>

         </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="类型" prop="incomeType">
              <el-select v-model="form.incomeType" placeholder="请选择收入类型">
                <el-option
                  v-for="dict in dict.type.config_function_in"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value">
              </el-option>
              </el-select>
            </el-form-item>
           </el-col>

           <el-col :span="8">
            <el-form-item label="账户" prop="incomeAccountId">
              <el-select v-model="form.incomeAccountId" placeholder="请选择账户名">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
           </el-col>

           <el-col :span="8">
            <el-form-item label="分类" prop="incomeCategoryId">
              <el-select v-model="form.incomeCategoryId" placeholder="请选择分类名">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

           </el-row>

           <el-row>
              <el-col :span="8">
                <el-form-item label="金额" prop="incomeAmount">
                  <el-input-number size="medium" v-model="form.incomeAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
                </el-form-item>
              </el-col>

             <el-col :span="16">
              <el-form-item label="币种" prop="incomeMoneyId">
                <el-radio-group v-model="form.incomeMoneyId" placeholder="请选择币种">
                  <el-radio-button
                    v-for="item in moneyOptions"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled">
                  </el-radio-button>
                </el-radio-group>
              </el-form-item>
             </el-col>
        </el-row>

       <el-row>
        <el-col :span="8">
          <el-form-item label="主体" prop="incomeEntityId">
            <el-select v-model="form.incomeEntityId" filterable placeholder="请选择主体名">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
         </el-col>

          <el-col :span="8">
          <el-form-item label="项目" prop="incomeProjectId">
            <el-select v-model="form.incomeProjectId" filterable placeholder="请选择项目名">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          </el-col>

          <el-col :span="8">
          <el-form-item label="父类" prop="incomeParentId">
            <el-select v-model="form.incomeParentId" placeholder="请选择父类名">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          </el-col>

       </el-row>

       <el-row>
         <el-col :span="24">
          <el-form-item label="城市" prop="incomeCityId">
            <el-radio-group v-model="form.incomeCityId" placeholder="请选择城市名">
              <el-radio-button
                v-for="item in cityOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled">
              </el-radio-button>
            </el-radio-group>
          </el-form-item>
         </el-col>
       </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="标签" prop="incomeLabelId">
            <el-select v-model="form.incomeLabelId" placeholder="请选择标签">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="16" >
          <el-form-item label="标签" prop="incomeLabelId">
          <el-tag
            size="medium"
            style="margin-left: 5px; margin-top:4px;"
            v-for="item in items"
            :key="item.label"
            :type="item.type"
            effect="plain"
          >
            {{ item.label }}
          </el-tag>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="8">
          <el-form-item label="成员" prop="incomeMemberId">
            <el-select v-model="form.incomeMemberId" placeholder="请选择成员名">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="16" >
          <el-form-item label="成员" prop="incomeLabelId">
          <el-tag
            size="medium"
            style="margin-left: 5px; margin-top:4px;"
            v-for="item in items"
            :key="item.label"
            :type="item.type"
            effect="light">
            {{ item.label }}
          </el-tag>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
        </el-col>
      </el-row>

    <el-row>
       <el-col :span="8">
        <el-form-item label="心情" prop="incomeEmotionId">
          <el-select v-model="form.incomeEmotionId" placeholder="请选择心情名">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
       </el-col>
       <el-col :span="16" >
          <el-form-item label="心情" prop="incomeLabelId">
          <el-tag
            size="medium"
            style="margin-left: 5px; margin-top:4px;"
            v-for="item in items"
            :key="item.label"
            :type="item.type"
            effect="light">
            {{ item.label }}
          </el-tag>
          </el-form-item>
        </el-col>
      </el-row>



      <el-row>
        <el-col :span="8">
        <el-form-item label="天气" prop="incomeWeatherId">
          <el-select v-model="form.incomeWeatherId" placeholder="请选择天气名">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        </el-col>

        <el-col :span="16" >
          <el-form-item label="天气" prop="incomeWeatherId">
          <el-tag
            size="medium"
            style="margin-left: 5px; margin-top:4px;"
            v-for="item in items"
            :key="item.label"
            :type="item.type"
            effect="light">
            {{ item.label }}
          </el-tag>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
          <el-col :span="8">
            <el-form-item label="排序" prop="orderSort">
              <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>

          </el-col>
            <el-col :span="8">
            <el-form-item label="权重" prop="weight">
              <el-input-number size="medium" v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
              <el-form-item label="是否可用">
                <el-radio-group v-model="form.enableStatus">
                  <el-radio
                    v-for="dict in dict.type.config_is_enable"
                    :key="dict.value"
      :label="parseInt(dict.value)"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-form-item>
          </el-col>
        </el-row>

      <el-row>
              <el-col :span="8">
           <el-form-item label="描述" prop="incomeDesc">
            <el-input v-model="form.incomeDesc" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="凭证">
            <image-upload v-model="form.incomeImgs"/>
          </el-form-item>
        </el-col>
      </el-row>


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
import IconSelect from "@/components/IconSelect";

export default {
  name: "Income",
  dicts: ['config_function_in', 'config_is_enable'],
  components: {IconSelect},
  data() {
    return {
      //城市
      cityOptions: [
        { disabled: false,  label: '上海' },
        { disabled: false,  label: '伦敦' },
        { disabled: false,  label: '利兹' },
        { disabled: false,  label: '北京' },
        { disabled: false,  label: '杭州' },
        { disabled: false,  label: '苏州' },
        { disabled: false,  label: '南京' },
        { disabled: true,   label: '郑州' },
        { disabled: false,  label: '洛阳' },
        { disabled: false,  label: '信阳' },
        { disabled: false,  label: '合肥' },
        { disabled: false,  label: '广州' },
        { disabled: true,   label: '纽约' },
      ],
      //币种
      moneyOptions: [
        { disabled: false, label: '人币' },
        { disabled: false, label: '英镑' },
        { disabled: false, label: '欧元' },
        { disabled: false, label: '美元' },
        { disabled: false, label: '韩元' },
        { disabled: false, label: '加元' },
        { disabled: true,  label: '港币' },
        { disabled: true,  label: '日元' },
      ],
      //标签
      items: [
            { type: 'success', label: '标签一' },
            { type: 'success', label: '标签二' },
            { type: 'success', label: '标签三' },
            { type: 'success', label: '标签ddd五' },
            { type: 'success', label: '标签dd五' },
            { type: 'success', label: '标签ddddd五' },
          ],
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
      // 收入账单表格数据
      incomeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
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
    /** 选择收入账单图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询收入账单列表 */
    getList() {
      this.loading = true;
      listIncome(this.queryParams).then(response => {
        this.incomeList = response.rows;
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
        incomeEntityId: null,
        incomeEntityName: null,
        incomeAddress: null,
        incomeLabelId: null,
        incomeLabelName: null,
        incomeMemberId: null,
        incomeMemberName: null,
        incomeMoneyId: '人币',
        incomeMoneyName: '人币',
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
        isDeleted: 0
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
      this.ids = selection.map(item => item.incomeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收入账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const incomeId = row.incomeId || this.ids
      getIncome(incomeId).then(response => {
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
      const incomeIds = row.incomeId || this.ids;
      this.$modal.confirm('是否确认删除收入账单编号为"' + incomeIds + '"的数据项？').then(function() {
        return delIncome(incomeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/income/export', {
        ...this.queryParams
      }, `income_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
