<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="中文名" prop="emotionCname">
        <el-input
          v-model="queryParams.emotionCname"
          placeholder="请输入中文名"
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
          v-hasPermi="['config:emotion:add']"
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
          v-hasPermi="['config:emotion:edit']"
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
          v-hasPermi="['config:emotion:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:emotion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="emotionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />
      <el-table-column label="中文名" align="center" prop="emotionCname" :show-overflow-tooltip="true" />


      <el-table-column label="英文名" align="center" prop="emotionEname" :show-overflow-tooltip="true" />


      <el-table-column label="作用范围" align="center" prop="emotionScope">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.emotionScope ? scope.row.emotionScope.split(',') : []"/>
        </template>
      </el-table-column>


      <el-table-column label="描述" align="center" prop="emotionDesc" :show-overflow-tooltip="true" />


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
            v-hasPermi="['config:emotion:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:emotion:remove']"
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

    <!-- 添加或修改心情管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="666px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

      <el-row>
          <el-col :span="12">
            <el-form-item label="中文名" prop="emotionCname">
              <el-input v-model="form.emotionCname" placeholder="请输入中文名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="英文名" prop="emotionEname">
              <el-input v-model="form.emotionEname" placeholder="请输入英文名" />
            </el-form-item>
          </el-col>
       </el-row>

        <el-form-item label="功能范围" prop="emotionScope">
          <el-checkbox-group v-model="form.emotionScope">
            <el-checkbox
              v-for="dict in dict.type.config_function_scope"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="描述" prop="emotionDesc">
          <el-input v-model="form.emotionDesc" placeholder="请输入描述" />
        </el-form-item>


        <el-row>
            <el-col :span="12">
            <el-form-item label="权重" prop="weight">
              <el-input-number size="medium" v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>
            </el-col>
            <el-col :span="12">
            <el-form-item label="排序" prop="orderSort">
              <el-input-number size="medium" v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
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
          <el-form-item label="可用">
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
import { listEmotion, getEmotion, delEmotion, addEmotion, updateEmotion } from "@/api/config/emotion";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Emotion",
  dicts: ['config_function_scope', 'config_is_enable'],
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
      // 心情管理表格数据
      emotionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        emotionCname: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        emotionCname: [
          { required: true, message: "中文名称不能为空", trigger: "blur" }
        ],
        emotionEname: [
          { required: true, message: "英文名称不能为空",  trigger: "blur" }
        ],
        emotionScope: [
          { required: true, message: "功能范围不能为空", trigger: "change" }
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
    /** 选择心情管理图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询心情管理列表 */
    getList() {
      this.loading = true;
      listEmotion(this.queryParams).then(response => {
        this.emotionList = response.rows;
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
        emotionId: null,
        emotionCname: null,
        emotionEname: null,
        emotionScope: [],
        emotionDesc: null,
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
      this.ids = selection.map(item => item.emotionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加心情管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emotionId = row.emotionId || this.ids
      getEmotion(emotionId).then(response => {
        this.form = response.data;
        this.form.emotionScope = this.form.emotionScope.split(",");
        this.open = true;
        this.title = "修改心情管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.emotionScope = this.form.emotionScope.join(",");
          if (this.form.emotionId != null) {
            updateEmotion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmotion(this.form).then(response => {
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
      const emotionIds = row.emotionId || this.ids;
      this.$modal.confirm('是否确认删除心情管理编号为"' + emotionIds + '"的数据项？').then(function() {
        return delEmotion(emotionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/emotion/export', {
        ...this.queryParams
      }, `emotion_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
