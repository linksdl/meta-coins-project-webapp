<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="商品名称" prop="goodsCname">
        <el-input
          v-model="queryParams.goodsCname"
          placeholder="请输入商品名称"
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
          v-hasPermi="['config:goods:add']"
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
          v-hasPermi="['config:goods:edit']"
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
          v-hasPermi="['config:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column label="排序" align="center" prop="orderSort" :show-overflow-tooltip="true" />

      <el-table-column label="商品名称" align="center" prop="goodsCname" :show-overflow-tooltip="true" />


      <el-table-column label="英文名称" align="center" prop="goodsEname" :show-overflow-tooltip="true" />


      <el-table-column label="价格" align="center" prop="goodsPrice" :show-overflow-tooltip="true" />


      <el-table-column label="描述" align="center" prop="goodsDesc" :show-overflow-tooltip="true" />

      <el-table-column label="类型名称" align="center" prop="goodsTypeName" :show-overflow-tooltip="true" />


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
            v-hasPermi="['config:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:goods:remove']"
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

    <!-- 添加或修改商品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="666px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="名称" prop="goodsCname">
          <el-input v-model="form.goodsCname" placeholder="请输入商品名称" />
        </el-form-item>

        <el-form-item label="英文" prop="goodsEname">
          <el-input v-model="form.goodsEname" placeholder="请输入英文名称" />
        </el-form-item>

        <el-row>
           <el-col :span="12">
        <el-form-item label="类型" prop="goodsTypeId">
          <el-select v-model="form.goodsTypeId" placeholder="请选择商品类型">
            <el-option
              v-for="item in typeOptions"
              :key="item.goodsTypeId"
              :label="item.goodsTypeName"
              :value="item.goodsTypeId"
              :disabled="item.disabled">
            </el-option>
          </el-select>
        </el-form-item>
           </el-col>

           <el-col :span="12">
        <el-form-item label="价格" prop="goodsPrice">
          <el-input-number size="medium" v-model="form.goodsPrice" type="input-number" :precision="4" :step="0.0001" :max="100000" :min="0" placeholder="请输入内容"/>
        </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="描述" prop="goodsDesc">
          <el-input v-model="form.goodsDesc" placeholder="请输入描述" />
        </el-form-item>


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
import { listGoods, getGoods, delGoods, addGoods, updateGoods } from "@/api/config/goods";
import IconSelect from "@/components/IconSelect";
import { selectGoodsType as getGoodsTypeOptionSelect } from "@/api/config/goodsType";

export default {
  name: "Goods",
  dicts: ['config_is_enable'],
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
      // 商品管理表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsCname: null,
        goodsPrice: null,
        enableStatus: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsCname: [
          { required: true, message: "中文名称不能为空", trigger: "blur" }
        ],
        goodsEname: [
          { required: true, message: "英文名称不能为空",  trigger: "blur" }
        ],
        goodsTypeId: [
          { required: true, message: "标签类型不能为空",  trigger: "change" }
        ],
        enableStatus: [
          { required: true, message: "数据状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getGoodsTypeList();
  },
  methods: {
    /** 选择商品管理图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询商品管理列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询商品类型列表 */
    getGoodsTypeList() {
      getGoodsTypeOptionSelect().then(response => {
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
        goodsId: null,
        goodsCname: null,
        goodsEname: null,
        goodsPrice: null,
        goodsDesc: null,
        goodsTypeId: null,
        goodsTypeName: null,
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getGoods(goodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsId != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
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
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品管理编号为"' + goodsIds + '"的数据项？').then(function() {
        return delGoods(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
