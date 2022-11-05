<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">

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
          v-hasPermi="['bill:consumeGoods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"

          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bill:consumeGoods:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"

          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bill:consumeGoods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"

          @click="handleExport"
          v-hasPermi="['bill:consumeGoods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="consumeGoodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />

      <el-table-column sortable label="ID" align="center" prop="consumeGoodsId" />
      <el-table-column sortable label="商品" align="center" prop="goodsId" :show-overflow-tooltip="true" />
      <el-table-column sortable label="商品名称" align="center" prop="goodsCname" :show-overflow-tooltip="true" />
      <el-table-column sortable label="商品英文" align="center" prop="goodsEname" :show-overflow-tooltip="true" />
      <el-table-column sortable label="单价" align="center" prop="goodsPrice" :show-overflow-tooltip="true" />
      <el-table-column sortable label="数量" align="center" prop="goodsTotal" :show-overflow-tooltip="true" />
      <el-table-column sortable label="总价" align="center" prop="goodsTotalPrice" :show-overflow-tooltip="true" />
      <el-table-column sortable label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="是否删除" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button

            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:consumeGoods:edit']"
          >修改</el-button>
          <el-button

            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:consumeGoods:remove']"
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

    <!-- 添加或修改支出商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="688px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

      <el-row>
          <el-col :span="12">
            <el-form-item label="商品" prop="goodsId">
              <el-select v-model="form.goodsId" clearable filterable placeholder="请选择商品">
                <el-option
                  v-for="item in goodsOptions"
                  :key="item.goodsId"
                  :label="item.goodsCname"
                  :value="item.goodsId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="goodsCname">
              <el-input v-model="form.goodsCname" placeholder="请输入商品名称" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
            <el-col :span="12">
              <el-form-item label="英文" prop="goodsEname">
                <el-input v-model="form.goodsEname" placeholder="请输入商品英文" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="数量" prop="goodsTotal">
                  <el-input v-model="form.goodsTotal" placeholder="请输入数量" />
                </el-form-item>
            </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="单价" prop="goodsPrice">
              <el-input-number  v-model="form.goodsPrice" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
             <el-form-item label="总价" prop="goodsTotalPrice">
              <el-input-number  v-model="form.goodsTotalPrice" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="描述" prop="goodsDesc">
          <el-input v-model="form.goodsDesc" placeholder="请输入描述" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item label="排序" prop="orderSort">
              <el-input-number  v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="权重" prop="weight">
              <el-input-number  v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
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
import { listConsumeGoods, getConsumeGoods, delConsumeGoods, addConsumeGoods, updateConsumeGoods } from "@/api/bill/consumeGoods";
import IconSelect from "@/components/IconSelect";
import { selectGoods as getGoodsOptionSelect } from "@/api/config/goods";

export default {
  name: "ConsumeGoods",
  dicts: ['config_is_enable', 'config_is_deleted'],
  components: {IconSelect},
  data() {
    return {
      //商品
      goodsOptions: [],
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
      // 支出商品表格数据
      consumeGoodsList: [],
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
      }
    };
  },
  created() {
    this.getList();
    this.getGoodsList();
  },
  methods: {
    /** 选择支出商品图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询支出商品列表 */
    getList() {
      this.loading = true;
      listConsumeGoods(this.queryParams).then(response => {
        this.consumeGoodsList = response.rows;
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
        consumeGoodsId: null,
        consumeBillId: null,
        goodsId: null,
        goodsCname: null,
        goodsEname: null,
        goodsPrice: null,
        goodsTotal: null,
        goodsTotalPrice: null,
        enableStatus: 0,
        weight: null,
        icon: null,
        isDeleted: 0,
        orderSort: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        goodsDesc: null
      };
      this.resetForm("form");
    },
    /** 查询商品下拉 */
    getGoodsList() {
      getGoodsOptionSelect(this.queryParams).then(response => {
        this.goodsOptions = response.data;
      });
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
      this.ids = selection.map(item => item.consumeGoodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支出商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumeGoodsId = row.consumeGoodsId || this.ids
      getConsumeGoods(consumeGoodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支出商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumeGoodsId != null) {
            updateConsumeGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsumeGoods(this.form).then(response => {
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
      const consumeGoodsIds = row.consumeGoodsId || this.ids;
      this.$modal.confirm('是否确认删除支出商品编号为"' + consumeGoodsIds + '"的数据项？').then(function() {
        return delConsumeGoods(consumeGoodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/consumeGoods/export', {
        ...this.queryParams
      }, `consumeGoods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
