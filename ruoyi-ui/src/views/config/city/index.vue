<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="中文" prop="cityCname">
        <el-input
          v-model="queryParams.cityCname"
          placeholder="请输入中文"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="英文" prop="cityEname">
        <el-input
          v-model="queryParams.cityEname"
          placeholder="请输入英文"
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
          v-hasPermi="['config:city:add']"
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
          v-hasPermi="['config:city:edit']"
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
          v-hasPermi="['config:city:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:city:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="cityList" stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />
      <el-table-column label="中文" align="center" prop="cityCname" :show-overflow-tooltip="true" />


      <el-table-column label="英文" align="center" prop="cityEname" :show-overflow-tooltip="true" />


      <el-table-column label="描述" align="left" prop="cityDesc" :show-overflow-tooltip="true" />


      <el-table-column label="国家" align="center" prop="cityCountry" :show-overflow-tooltip="true" />


      <el-table-column label="省份" align="center" prop="cityProvince" :show-overflow-tooltip="true" />


      <el-table-column label="权重" align="center" prop="weight" :show-overflow-tooltip="true" />


      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />





    <el-table-column label="图标" align="center" prop="icon">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
    </el-table-column>



      <el-table-column label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>









      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:city:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:city:remove']"
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

    <!-- 添加或修改城市对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="中文" prop="cityCname">
          <el-input v-model="form.cityCname" placeholder="请输入中文" />
        </el-form-item>


        <el-form-item label="英文" prop="cityEname">
          <el-input v-model="form.cityEname" placeholder="请输入英文" />
        </el-form-item>


        <el-form-item label="描述" prop="cityDesc">
          <el-input v-model="form.cityDesc" placeholder="请输入描述" />
        </el-form-item>


        <el-form-item label="国家" prop="cityCountry">
          <el-input v-model="form.cityCountry" placeholder="请输入国家" />
        </el-form-item>


        <el-form-item label="省份" prop="cityProvince">
          <el-input v-model="form.cityProvince" placeholder="请输入省份" />
        </el-form-item>


        <el-form-item label="权重" prop="weight">
          <el-input-number size="medium" v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>



        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>


        <el-form-item label="排序" prop="orderSort">
          <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>



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

        <el-form-item label="是否可用">
          <el-radio-group v-model="form.enableStatus">
            <el-radio
              v-for="dict in dict.type.config_is_enable"
              :key="dict.value"
:label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listCity, getCity, delCity, addCity, updateCity } from "@/api/config/city";
import IconSelect from "@/components/IconSelect";

export default {
  name: "City",
  dicts: ['config_is_enable'],
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
      // 城市表格数据
      cityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cityCname: null,
        cityEname: null,
        enableStatus: null,
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
    /** 选择城市图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询城市列表 */
    getList() {
      this.loading = true;
      listCity(this.queryParams).then(response => {
        this.cityList = response.rows;
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
        cityId: null,
        cityCname: null,
        cityEname: null,
        cityDesc: null,
        cityCountry: null,
        cityProvince: null,
        weight: null,
        remark: null,
        orderSort: null,
        icon: null,
        enableStatus: 0,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.cityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加城市";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cityId = row.cityId || this.ids
      getCity(cityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改城市";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cityId != null) {
            updateCity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCity(this.form).then(response => {
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
      const cityIds = row.cityId || this.ids;
      this.$modal.confirm('是否确认删除城市编号为"' + cityIds + '"的数据项？').then(function() {
        return delCity(cityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/city/export', {
        ...this.queryParams
      }, `city_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
