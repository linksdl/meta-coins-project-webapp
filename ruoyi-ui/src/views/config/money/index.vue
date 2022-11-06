<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="币种名称" prop="moneyCname">
        <el-input
          v-model="queryParams.moneyCname"
          placeholder="请输入币种"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['config:money:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['config:money:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['config:money:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          @click="handleExport"
          v-hasPermi="['config:money:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="moneyList" @selection-change="handleSelectionChange">
      <el-table-column  type="selection" width="55" align="center" :show-overflow-tooltip="true" />
      <el-table-column sortable label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />
      <el-table-column sortable label="币种" align="center" prop="moneyCname" :show-overflow-tooltip="true" />
      <el-table-column sortable label="英文名" align="center" prop="moneyEname" :show-overflow-tooltip="true" />
      <el-table-column sortable label="类型" align="center" prop="moneyTypeName" :show-overflow-tooltip="true" />
      <el-table-column sortable label="描述" align="center" prop="moneyDesc" :show-overflow-tooltip="true" />
      <el-table-column sortable label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="汇率" align="center" prop="moneyRate" :show-overflow-tooltip="true" />
      <el-table-column sortable label="功能范围" align="center" prop="moneyScope">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.moneyScope ? scope.row.moneyScope.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="权重" align="center" prop="weight" :show-overflow-tooltip="true" />
      <el-table-column sortable label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
      <el-table-column sortable label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="账本" align="center" prop="bookName" :show-overflow-tooltip="true" />
      <el-table-column  label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:money:edit']"
          >修改</el-button>
          <el-button
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:money:remove']"
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

    <!-- 添加或修改币种管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="626px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="88px">
      <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="moneyCname">
              <el-input v-model="form.moneyCname" placeholder="请输入币种中文名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="英文" prop="moneyEname">
              <el-input v-model="form.moneyEname" placeholder="请输入英文名" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
           <el-col :span="12">
              <el-form-item label="类型" prop="moneyTypeId">
                <el-select v-model="form.moneyTypeId" placeholder="请选择币种类型名称">
                  <el-option
                    v-for="item in typeOptions"
                    :key="item.moneyTypeId"
                    :label="item.moneyTypeCname"
                    :value="item.moneyTypeId"
                    :disabled="item.disabled">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
               <el-form-item label="汇率" prop="moneyRate">
                <el-input-number  v-model="form.moneyRate" type="input-number" :precision="4" :step="0.0001" :max="9999999999" :min="0.0001" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>

        <el-form-item label="描述" prop="moneyDesc">
          <el-input v-model="form.moneyDesc" placeholder="请输入描述" />
        </el-form-item>

      <el-row>
        <el-col :span="24">
          <el-form-item label="功能类型" prop="moneyType">
            <el-checkbox-group v-model="form.moneyType">
              <el-checkbox
                v-for="dict in dict.type.config_function_type"
                :key="dict.value"
                :label="dict.value">
                {{dict.label}}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
      </el-row>

        <el-form-item label="功能范围" prop="moneyScope">
          <el-checkbox-group v-model="form.moneyScope">
            <el-checkbox
              v-for="dict in dict.type.config_function_scope"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-row>
           <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input-number v-model="form.weight" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
           </el-col>

           <el-col :span="12">
            <el-form-item label="排序" prop="orderSort">
              <el-input-number v-model="form.orderSort" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>
            </el-col>
          </el-row>

        <el-row>
           <el-col :span="12">
              <el-form-item label="图标" prop="icon">
                  <el-popover
                  placement="bottom-start"
                  width="460"
                  trigger="click"
                  @show="$refs['iconSelect'].reset()"
                >
                  <IconSelect ref="iconSelect" @selected="selected" />
                  <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                    <svg-icon
                      v-if="form.icon"
                      slot="prefix"
                      :icon-class="form.icon"
                      class="el-input__icon"
                      style="height: 32px;width: 16px;"
                    />
                    <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                  </el-input>
                </el-popover>
              </el-form-item>
           </el-col>

           <el-col :span="12">
              <el-form-item label="可用" prop="enableStatus">
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

          <el-form-item label="备注" type="textarea" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listMoney, getMoney, delMoney, addMoney, updateMoney } from "@/api/config/money";
import IconSelect from "@/components/IconSelect";
import { selectMoneyType as getMoneyTypeOptionSelect } from "@/api/config/moneyType";

export default {
  name: "Money",
  dicts: ['config_function_type','config_function_scope', 'config_is_enable'],
  components: {IconSelect},
  data() {
    return {
      // 币种类型列表
      typeOptions: [],
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
      // 币种管理表格数据
      moneyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        moneyType: null,
        moneyCname: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        moneyCname: [
          { required: true, message: "中文名称不能为空", trigger: "blur" }
        ],
        moneyEname: [
          { required: true, message: "英文名称不能为空",  trigger: "blur" }
        ],
        moneyTypeId: [
          { required: true, message: "币种类型不能为空",  trigger: "change" }
        ],
        moneyType: [
          { required: true, message: "功能类型不能为空",  trigger: "blur" }
        ],
        moneyScope: [
          { required: true, message: "功能范围不能为空",  trigger: "blur" }
        ],
        enableStatus: [
          { required: true, message: "数据状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getMoneyTypeList();
  },
  methods: {
    /** 选择币种管理图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询币种管理列表 */
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
      listMoney(this.queryParams).then(response => {
        this.moneyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询币种类型列表 */
    getMoneyTypeList() {
      getMoneyTypeOptionSelect().then(response => {
        this.typeOptions = response.data;
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
        moneyId: null,
        moneyCname: null,
        moneyEname: null,
        moneyDesc: null,
        moneyIcon: null,
        moneyRate: null,
        moneyType:[],
        moneyScope: [],
        moneyTypeId: null,
        weight: null,
        moneyTypeName: null,
        remark: null,
        orderSort: null,
        icon: null,
        enableStatus: 1,
        bookId: null,
        bookName: null,
        userId: null,
        userName: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.moneyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加币种管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const moneyId = row.moneyId || this.ids
      getMoney(moneyId).then(response => {
        this.form = response.data;
        if(this.form.moneyType != null) {
          this.form.moneyType = this.form.moneyType.split(",");
        }
        this.form.moneyScope = this.form.moneyScope.split(",");
        this.open = true;
        this.title = "修改币种管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.moneyType = this.form.moneyType.join(",");
          this.form.moneyScope = this.form.moneyScope.join(",");
          if (this.form.moneyId != null) {
            updateMoney(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMoney(this.form).then(response => {
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
      const moneyIds = row.moneyId || this.ids;
      this.$modal.confirm('是否确认删除币种管理编号为"' + moneyIds + '"的数据项？').then(function() {
        return delMoney(moneyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/money/export', {
        ...this.queryParams
      }, `money_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
