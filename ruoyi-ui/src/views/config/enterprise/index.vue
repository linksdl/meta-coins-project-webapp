<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="商家名称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入商家名称"
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
          v-hasPermi="['config:enterprise:add']"
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
          v-hasPermi="['config:enterprise:edit']"
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
          v-hasPermi="['config:enterprise:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:enterprise:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="enterpriseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />

      <el-table-column label="商家名称" align="center" prop="enterpriseName" :show-overflow-tooltip="true" />
      <el-table-column label="商家类型" align="center" prop="enterpriseTypeName" :show-overflow-tooltip="true" />

      <el-table-column label="商家地址" align="center" prop="enterpriseAddress" :show-overflow-tooltip="true" />


      <el-table-column label="描述" align="center" prop="enterpriseDesc" :show-overflow-tooltip="true" />


      <el-table-column label="图片" align="center" prop="enterpriseImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.enterpriseImgs" :width="50" :height="50"/>
        </template>
      </el-table-column>



      <el-table-column label="地图地址" align="center" prop="enterpriseMapLocation" :show-overflow-tooltip="true" />


      <el-table-column label="功能范围" align="center" prop="enterpriseScope">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.enterpriseScope ? scope.row.enterpriseScope.split(',') : []"/>
        </template>
      </el-table-column>




      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>


    <el-table-column label="图标" align="center" prop="icon">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
    </el-table-column>





      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />


      <el-table-column label="权重" align="center" prop="weight" :show-overflow-tooltip="true" />


      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:enterprise:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:enterprise:remove']"
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

    <!-- 添加或修改商家管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="666px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
           <el-col :span="12">
              <el-form-item label="名称" prop="enterpriseName">
                <el-input v-model="form.enterpriseName" placeholder="请输入商家名称" />
              </el-form-item>
           </el-col>
           <el-col :span="12">
              <el-form-item label="类型" prop="enterpriseTypeId">
                <el-select v-model="form.enterpriseTypeId" placeholder="请选择商家类型">
                  <el-option
                    v-for="item in typeOptions"
                    :key="item.enterpriseTypeId"
                    :label="item.enterpriseTypeName"
                    :value="item.enterpriseTypeId"
                    :disabled="item.disabled">
                  </el-option>
                </el-select>
              </el-form-item>
           </el-col>
        </el-row>

        <el-form-item label="地址" prop="enterpriseAddress">
          <el-input v-model="form.enterpriseAddress" placeholder="请输入商家地址" />
        </el-form-item>


        <el-form-item label="描述" prop="enterpriseDesc">
          <el-input v-model="form.enterpriseDesc" placeholder="请输入描述" />
        </el-form-item>


        <el-form-item label="图片">
          <image-upload v-model="form.enterpriseImgs"/>
        </el-form-item>


        <el-form-item label="地图" prop="enterpriseMapLocation">
          <el-input v-model="form.enterpriseMapLocation" placeholder="请输入地图地址" />
        </el-form-item>


        <el-form-item label="功能范围" prop="enterpriseScope">
          <el-checkbox-group v-model="form.enterpriseScope">
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
        </el-row>

        <el-row>
           <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input-number size="medium" v-model="form.weight" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
           </el-col>
           <el-col :span="12">
              <el-form-item label="排序" prop="orderSort">
                <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="1" :max="999999999" placeholder="请输入内容"/>
              </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="备注" prop="remark">
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
import { listEnterprise, getEnterprise, delEnterprise, addEnterprise, updateEnterprise } from "@/api/config/enterprise";
import IconSelect from "@/components/IconSelect";
import { selectEnterpriseType as getEnterpriseTypeOptionSelect } from "@/api/config/enterpriseType";

export default {
  name: "Enterprise",
  dicts: ['config_function_scope', 'config_is_enable'],
  components: {IconSelect},
  data() {
    return {
      // 商品类型列表
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
      // 商家管理表格数据
      enterpriseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enterpriseName: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        enterpriseName: [
          { required: true, message: "商家名称不能为空", trigger: "blur" }
        ],
        enterpriseAddress: [
          { required: true, message: "商家地址不能为空", trigger: "blur" }
        ],
        enterpriseTypeId: [
          { required: true, message: "商家类型不能为空", trigger: "change" }
        ],
        enterpriseScope: [
          { required: true, message: "功能范围不能为空", trigger: "blur" }
        ],
        enableStatus: [
          { required: true, message: "数据状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getEnterpriseTypeList();
  },
  methods: {
    /** 选择商家管理图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询商家管理列表 */
    getList() {
      this.loading = true;
      listEnterprise(this.queryParams).then(response => {
        this.enterpriseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询商家类型列表 */
    getEnterpriseTypeList() {
      getEnterpriseTypeOptionSelect().then(response => {
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
        enterpriseId: null,
        enterpriseName: null,
        enterpriseAddress: null,
        enterpriseDesc: null,
        enterpriseImgs: null,
        enterpriseMapLocation: null,
        enterpriseScope: [],
        enterpriseTypeId: null,
        enterpriseTypeName: null,
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
      this.ids = selection.map(item => item.enterpriseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商家管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const enterpriseId = row.enterpriseId || this.ids
      getEnterprise(enterpriseId).then(response => {
        this.form = response.data;
        this.form.enterpriseScope = this.form.enterpriseScope.split(",");
        this.open = true;
        this.title = "修改商家管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.enterpriseScope = this.form.enterpriseScope.join(",");
          if (this.form.enterpriseId != null) {
            updateEnterprise(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEnterprise(this.form).then(response => {
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
      const enterpriseIds = row.enterpriseId || this.ids;
      this.$modal.confirm('是否确认删除商家管理编号为"' + enterpriseIds + '"的数据项？').then(function() {
        return delEnterprise(enterpriseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/enterprise/export', {
        ...this.queryParams
      }, `enterprise_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
