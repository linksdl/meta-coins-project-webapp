<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="debtName">
        <el-input
          v-model="queryParams.debtName"
          placeholder="请输入借贷名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="debtType">
        <el-select v-model="queryParams.debtType" placeholder="请选择借贷类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_debt"
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
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
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
          v-hasPermi="['bill:debt:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"

          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="debtList"
      row-key="debtId"
      :row-class-name="tableRowClassName"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column sortable label="名称" align="center" prop="debtName" :show-overflow-tooltip="true" />
      <el-table-column sortable label="父类" align="center" prop="debtParentName" />
      <el-table-column sortable label="类型" align="center" prop="debtType" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_debt" :value="scope.row.debtType"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="日期" align="center" prop="debtDatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.debtDatetime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="金额" align="center" prop="debtAmount" :show-overflow-tooltip="true" />
      <el-table-column sortable label="凭证" align="center" prop="debtImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.debtImgs" :width="25" :height="25"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="描述" align="center" prop="debtDesc" :show-overflow-tooltip="true" />
      <el-table-column sortable label="是否可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>

      <!-- <el-table-column sortable label="备注" align="center" prop="remark" :show-overflow-tooltip="true" /> -->
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
            v-hasPermi="['bill:debt:edit']"
          >修改</el-button>
          <el-button

            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['bill:debt:add']"
          >新增</el-button>
          <el-button

            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:debt:remove']"
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

    <!-- 添加或修改借贷账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="868px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="88px">
        <el-form-item label="父类" prop="debtParentId">
          <treeselect v-model="form.debtParentId" :options="debtOptions" :normalizer="normalizer" placeholder="请选择父类ID" />
        </el-form-item>
          <el-row>
             <el-col :span="8">
                <el-form-item label="时间" prop="debtDatetime">
                  <el-date-picker
                    clearable
                    v-model="form.debtDatetime"
                    type="datetime"
                    placeholder="请选择借贷日期"
                    value-format="yyyy-MM-dd HH:mm:SS"
                    align="right"
                    :picker-options="pickerOptions"
                    >
                  </el-date-picker>
                </el-form-item>
              </el-col>

              <el-col :span="16">
                <el-form-item label="名称" prop="debtName">
                  <el-input :disabled="true" clearable v-model="form.debtName" placeholder="请输入借贷名称" />
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
              <el-form-item label="类型" prop="debtType">
                <el-radio-group v-model="form.debtType" placeholder="请选择借贷类型" @change="handleDebtTypeChange" >
                  <el-radio
                    v-for="dict in dict.type.config_function_debt"
                    :key="dict.value"
                    :label="dict.value"
                    :value="dict.value">
                {{dict.label}}</el-radio>
                </el-radio-group>
              </el-form-item>
             </el-col>
           </el-row>

           <el-row>
              <el-col :span="8">
                <el-form-item label="金额" prop="debtAmount">
                  <el-input-number v-model="form.debtAmount" type="input-number" :min="0.01" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
                </el-form-item>
              </el-col>

             <el-col :span="16">
                <el-form-item label="币种" prop="debtMoneyId">
                  <el-radio-group v-model="form.debtMoneyId" placeholder="请选择币种">
                    <el-radio
                      v-for="item in moneyOptions"
                      :label="item.moneyId"
                      :value="item.moneyId"
                      :key="item.moneyId"
                      :disabled="item.disabled">
                    {{item.moneyCname}}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
             </el-col>
            </el-row>

            <el-row>
             <el-col :span="8">
              <el-form-item label="分类" prop="debtCategoryId">
                  <el-cascader
                    clearable
                    filterable
                    placeholder="请选择分类"
                    v-model="form.debtCategoryId"
                    :options="categoryOptions"
                    :props="{ expandTrigger: 'click', value:'categoryId',label:'categoryName',children: 'children', disabled :'disabled'}"
                  ></el-cascader>
              </el-form-item>
            </el-col>

             <el-col :span="16">
              <el-form-item label="账户" prop="debtAccountId">
                <el-cascader
                    clearable
                    filterable
                    placeholder="请选择账户"
                    v-model="form.debtAccountId"
                    :options="accountOptions"
                    :props="{ expandTrigger: 'click',value:'accountId', label:'accountName',children: 'children', disabled :'disabled'}"
                ></el-cascader>
              </el-form-item>
             </el-col>

          </el-row>

          <el-row>
            <el-col :span="8">
                <el-form-item label="实体" clearable filterable prop="debtEntityId">
                  <el-select clearable v-model="form.debtEntityId" filterable placeholder="请选择实体名称">
                    <el-option
                      v-for="item in entityOptions"
                      :key="item.entityId"
                      :label="item.entityName"
                      :value="item.entityId"
                      :disabled="item.disabled">
                    </el-option>
                  </el-select>
                </el-form-item>
             </el-col>

             <el-col :span="16" v-if='false'>
              <el-form-item label="实体" prop="debtEntityName" >
                <el-autocomplete
                  class="inline-input"
                  clearable
                  v-model="form.debtEntityName"
                  :fetch-suggestions="querySearchEntity"
                  :trigger-on-focus="true"
                  placeholder="请输入实体名称"
                  @select="handleSelect"
                  @clear="handleClear"
                ></el-autocomplete>
              </el-form-item>
            </el-col>

            <el-col :span="8" v-if='false'>
                <el-form-item label="项目" clearable filterable prop="debtProjectId">
                  <el-select clearable @change="handleSelectProjectName" v-model="form.debtProjectId" filterable placeholder="请选择项目名称">
                    <el-option
                      v-for="item in projectOptions"
                      :key="item.projectId"
                      :label="item.projectName"
                      :value="item.projectId"
                      :disabled="item.disabled">
                    </el-option>
                  </el-select>
                </el-form-item>
             </el-col>

             <el-col :span="8">
              <el-form-item label="项目" prop="debtProjectName" style="width: 800px;" >
                <el-autocomplete
                  class="inline-input"
                  clearable
                  v-model="form.debtProjectName"
                  :fetch-suggestions="querySearch"
                  :trigger-on-focus="true"
                  placeholder="请输入项目名称"
                  @select="handleSelect"
                ></el-autocomplete>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="标签" prop="debtLabelName">
                <el-select v-model="form.debtLabelName" multiple clearable filterable collapse-tags placeholder="请选择标签">
                  <el-option
                    v-for="item in labelOptions"
                    :key="parseInt(item.labelId)"
                    :label="item.labelCname"
                    :value="item.labelCname">
                    <span style="float: left">{{ item.labelCname }}</span>
                    <span style="float: right; color: #8492a6; font-size: 12px">{{ item.labelEname}}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="16">
              <el-form-item label="标签" prop="debtLabelName">
                <el-tag
                  :key="tag"
                  v-for="tag in form.debtLabelName"
                  closable
                  :disable-transitions="false"
                  @close="handleClose(tag)"
                  type='success'
                  effect="dark">
                  {{tag}}
                </el-tag>
                <el-input
                  class="input-new-tag"
                  v-if="inputVisible"
                  v-model="inputValue"
                  ref="saveTagInput"
                  @keyup.enter.native="handleInputConfirm"
                  @blur="handleInputConfirm"
                >
                </el-input>
                <el-button v-else class="button-new-tag" @click="showInput">+ New 标签</el-button>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
           <el-col :span="24">
            <el-form-item label="城市" prop="debtCityId">
              <el-radio-group v-model="form.debtCityId" placeholder="请选择城市名">
                <el-radio-button
                  v-for="item in cityOptions"
                  :key="item.cityId"
                  :label="item.cityId"
                  :value="item.cityId"
                  :disabled="item.disabled">{{item.cityCname}}
                </el-radio-button>
              </el-radio-group>
            </el-form-item>
           </el-col>
         </el-row>

        <el-row>
          <el-col :span="24">
             <el-form-item label="描述" prop="debtDesc">
              <el-input :disabled="true" v-model="form.debtDesc" type="textarea" clearable placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="成员"  prop="debtMemberId">
              <el-select v-model="form.debtMemberId" clearable filterable placeholder="请选择成员名">
                <el-option
                  v-for="item in memberOptions"
                  :key="item.memberId"
                  :label="item.memberName"
                  :value="item.memberId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="16" >
            <el-form-item label="成员" prop="debtMemberName">
            <el-tag
              style="margin-left: 5px; margin-top:4px;"
              v-for="item in memberOptions"
              :key="item.memberId"
              type='danger'
              effect="light">
              {{ item.memberName }}
            </el-tag>
            </el-form-item>
          </el-col>
        </el-row>


      <el-row v-if="emotionOptions != undefined && emotionOptions != null && emotionOptions.length > 0 ">
         <el-col :span="8">
          <el-form-item label="心情"  prop="debtEmotionId">
            <el-select v-model="form.debtEmotionId" clearable filterable placeholder="请选择心情名">
              <el-option
                v-for="item in emotionOptions"
                :key="item.emotionId"
                :label="item.emotionCname"
                :value="item.emotionId">
              </el-option>
            </el-select>
          </el-form-item>
         </el-col>
         <el-col :span="16" >
            <el-form-item label="心情" prop="debtEmotionName">
            <el-tag
              style="margin-left: 5px; margin-top:4px;"
              v-for="item in emotionOptions"
              :key="item.emotionId"
              type='success'
              effect="dark">
              {{ item.emotionCname }}
            </el-tag>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row v-if="weatherOptions != undefined && weatherOptions != null && weatherOptions.length > 0 ">
          <el-col :span="8">
          <el-form-item label="天气" prop="debtWeatherId">
            <el-select v-model="form.debtWeatherId" clearable filterable placeholder="请选择天气名">
              <el-option
                v-for="item in weatherOptions"
                :key="item.weatherId"
                :label="item.weatherCname"
                :value="item.weatherId">
              </el-option>
            </el-select>
          </el-form-item>
          </el-col>

          <el-col :span="16" >
            <el-form-item label="天气" prop="debtWeatherName">
            <el-tag
              style="margin-left: 5px; margin-top:4px;"
              v-for="item in weatherOptions"
              :key="item.weatherId"
              type='success'
              effect="plain">
              {{ item.weatherCname }}
            </el-tag>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input v-model="form.remark" clearable type="textarea" autosize placeholder="请输入备注" />
              </el-form-item>
          </el-col>
        </el-row>

        <el-row>
            <el-col :span="24">
                <el-form-item label="进账">
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
import { listDebt, getDebt, delDebt, addDebt, updateDebt } from "@/api/bill/debt";
import { selectAccount as getAccountOptionSelect } from "@/api/config/account";
import { selectBook as getBookOptionSelect } from "@/api/config/book";
import { selectCategory as getCategoryOptionSelect } from "@/api/config/category";
import { selectCity as getCityOptionSelect } from "@/api/config/city";
import { selectEmotion as getEmotionOptionSelect } from "@/api/config/emotion";
import { selectEntity as getEntityOptionSelect } from "@/api/config/entity";
import { selectGoods as getGoodsOptionSelect } from "@/api/config/goods";
import { selectLabel as getLabelOptionSelect } from "@/api/config/label";
import { selectMember as getMemberOptionSelect } from "@/api/config/member";
import { selectMoney as getMoneyOptionSelect } from "@/api/config/money";
import { selectProject as getProjectOptionSelect,listProject } from "@/api/config/project";
import { selectWeather as getWeatherOptionSelect } from "@/api/config/weather";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Debt",
  dicts: ['config_is_enable', 'config_is_deleted', 'config_function_debt'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 新建标签输入
      inputVisible: false,
      inputValue: '',
      projects: [],
      entities: [],
      //账户
      accountOptions: [],
      //账本
      bookOptions: [],
      //分类
      categoryOptions: [],
      //城市
      cityOptions: [],
      //心情
      emotionOptions: [],
      //实体
      entityOptions: [],
      //商品
      goodsOptions: [],
      //标签
      labelOptions: [],
      //成员
      memberOptions: [],
      //币种
      moneyOptions: [],
      //项目
      projectOptions: [],
      //天气
      weatherOptions: [],
      // 日期选择
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        },{
          text: '二周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7 * 2);
            picker.$emit('pick', date);
          }
        },{
          text: '四周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7 * 4);
            picker.$emit('pick', date);
          }
        }
        ]
      },
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 借贷账单表格数据
      debtList: [],
      // 借贷账单树选项
      debtOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        debtName: null,
        debtType: null,
        createTime: null,
        labelType: 'debt',
        categoryType: 'debt',
        accountType: 'debt',
        categoryScope: 'debt',
        accountScope: 'debt',
        labelScope: 'debt',
        moneyScope: 'debt',
        entityScope: 'debt',
        projectScope: 'debt',
        memberScope: 'debt',
        weatherScope: 'debt',
        emotionScope: 'debt'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        debtDatetime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        debtType: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        debtMoneyId: [
          { required: true, message: "币种不能为空", trigger: "blur" }
        ],
        debtAccountId: [
          { required: true, message: "账户不能为空", trigger: "change" }
        ],
        debtCategoryId: [
          { required: true, message: "分类不能为空", trigger: "change" }
        ],
        debtEntityId: [
          { required: true, message: "实体不能为空", trigger: "change" }
        ],
        debtEntityName: [
          { required: true, message: "实体不能为空", trigger: "change" }
        ],
        debtProjectName: [
          { required: true, message: "项目不能为空", trigger: "change" }
        ],
        debtAmount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        debtCityId: [
          { required: true, message: "城市不能为空", trigger: "blur" }
        ],
        debtLabelName: [
          { required: true, message: "标签不能为空", trigger: "blur" }
        ],
        debtMemberId: [
          { required: true, message: "成员不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getAccountList();
    this.getBookList();
    this.getCategoryList();
    this.getCityList();
    this.getEmotionList();
    this.getEntityList();
    this.getGoodsList();
    this.getLabelList();
    this.getMemberList();
    this.getMoneyList();
    this.getProjectList();
    this.getWeatherList();
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
        if (row.debtAmount > 8000 && row.debtAmount < 10000) {
          return 'warning-row';
        } else if (row.debtAmount > 10000) {
          return 'success-row';
        }
        return '';
      },
   handleClose(tag) {
        this.form.debtLabelName.splice(this.form.debtLabelName.indexOf(tag), 2);
      },
   showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
   handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.form.debtLabelName.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
   },
   handleSelectProjectName(val) {
      this.projectOptions.forEach((item, index)=>{
        if(item.projectId === val)
        {
          this.form.debtProjectName = item.projectName;
          this.form.debtProjectId = item.projectId;
        }else {
          this.form.debtProjectId = null;
        }
      });
   },
    /**选择类型 级联*/
   handleDebtTypeChange(val) {
        this.queryParams.moneyScope=val;
        getMoneyOptionSelect(this.queryParams).then(response => {
          this.moneyOptions = response.data;
          if(this.moneyOptions != null)
          {
            this.form.debtMoneyId = this.moneyOptions[0].moneyId;
          }
        });
        this.queryParams.entityScope=val;
        getEntityOptionSelect(this.queryParams).then(response => {
          this.entityOptions = response.data;
        });
        this.queryParams.projectScope=val;
        getProjectOptionSelect(this.queryParams).then(response => {
          this.projectOptions = response.data;
        });
        this.queryParams.categoryScope=val;
        getCategoryOptionSelect(this.queryParams).then(response => {
          this.categoryOptions = response.data;
        });
        this.queryParams.accountScope=val;
        if(val == 'borrow' || val == 'loan-in' || val == 'debt-in')
        {
          this.queryParams.accountType = 'debt';
        }else {
          this.queryParams.accountType = 'consume';
        }
        getAccountOptionSelect(this.queryParams).then(response => {
          this.accountOptions = response.data;
        });
        this.queryParams.labelScope=val;
        getLabelOptionSelect(this.queryParams).then(response => {
          this.labelOptions = response.data;
        });
        this.queryParams.memberScope=val;
        getMemberOptionSelect(this.queryParams).then(response => {
          this.memberOptions = response.data;
          if(this.memberOptions != null)
          {
            this.form.debtMemberId = this.memberOptions[0].memberId;
          }
        });
        this.queryParams.weatherScope=val;
        getWeatherOptionSelect(this.queryParams).then(response => {
          this.weatherOptions = response.data;
          if(this.weatherOptions != null)
          {
            this.form.debtWeatherId = this.weatherOptions[0].weatherId;
          }
        });
        this.queryParams.emotionScope=val;
        getEmotionOptionSelect(this.queryParams).then(response => {
          this.emotionOptions = response.data;
          if(this.emotionOptions != null)
          {
            this.form.debtEmotionId = this.emotionOptions[0].emotionId;
          }
        });
        this.form.debtCityId = this.cityOptions[0].cityId;
      },
   /** 查询账户下拉 */
   getAccountList() {
      getAccountOptionSelect(this.queryParams).then(response => {
        this.accountOptions = response.data;
      });
    },
    /** 查询账本下拉 */
   getBookList() {
      getBookOptionSelect(this.queryParams).then(response => {
        this.bookOptions = response.data;
      });
    },
    /** 查询分类下拉 */
   getCategoryList() {
      getCategoryOptionSelect(this.queryParams).then(response => {
        this.categoryOptions = response.data;
      });
    },
    /** 查询城市下拉 */
   getCityList() {
      getCityOptionSelect(this.queryParams).then(response => {
        this.cityOptions = response.data;
      });
    },
    /** 查询心情下拉 */
   getEmotionList() {
      getEmotionOptionSelect(this.queryParams).then(response => {
        this.emotionOptions = response.data;
      });
    },
    /** 查询实体下拉 */
   getEntityList() {
      getEntityOptionSelect(this.queryParams).then(response => {
        this.entityOptions = response.data;
        this.entities = response.data;
      });
    },
    /** 查询商品下拉 */
   getGoodsList() {
      getGoodsOptionSelect(this.queryParams).then(response => {
        this.goodsOptions = response.data;
      });
    },
    /** 查询标签下拉 */
   getLabelList() {
      getLabelOptionSelect(this.queryParams).then(response => {
        this.labelOptions = response.data;
      });
    },
    /** 查询成员下拉 */
   getMemberList() {
      getMemberOptionSelect(this.queryParams).then(response => {
        this.memberOptions = response.data;
      });
    },
    /** 查询币种下拉 */
   getMoneyList() {
      getMoneyOptionSelect(this.queryParams).then(response => {
        this.moneyOptions = response.data;
      });
    },
    /** 查询项目下拉 */
   getProjectList() {
      getProjectOptionSelect(this.queryParams).then(response => {
        this.projectOptions = response.data;
        this.projects = response.data;
      });
    },
    /** 查询天气下拉 */
   getWeatherList() {
      getWeatherOptionSelect(this.queryParams).then(response => {
        this.weatherOptions = response.data;
      });
    },
    /** 查询借贷账单列表 */
   getList() {
      this.loading = true;
      listDebt(this.queryParams).then(response => {
        this.debtList = this.handleTree(response.data, "debtId", "debtParentId");
        this.loading = false;
      });
    },
    /** 转换借贷账单数据结构 */
   normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.debtId,
        label: node.debtName,
        children: node.children
      };
    },
	/** 查询借贷账单下拉树结构 */
   getTreeselect() {
      listDebt().then(response => {
        this.debtOptions = [];
        const data = { debtId: 0, debtName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "debtId", "debtParentId");
        this.debtOptions.push(data);
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
        debtId: null,
        debtName: null,
        debtType: null,
        debtImgs: null,
        debtDesc: null,
        debtDatetime: null,
        debtAmount: null,
        enableStatus: 0,

        debtParentId: null,
        createBy: null,
        debtParentName: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        icon: null,
        isDeleted: 0,

        orderSort: null,
        weight: null,
        debtUserId: null,
        debtUserName: null,
        debtAccountId: null,
        debtAccountName: null,
        debtBookId: null,
        debtBookName: null,
        debtCategoryId: null,
        debtCategoryName: null,
        debtCityId: null,
        debtCityName: null,
        debtEmotionId: null,
        debtEmotionName: null,
        debtEntityId: null,
        debtEntityName: null,
        debtLabelId: null,
        debtLabelName: null,
        debtMemberId: null,
        debtMemberName: null,
        debtMoneyId: null,
        debtMoneyName: null,
        debtProjectId: null,
        debtProjectName: null,
        debtWeatherId: null,
        debtWeatherName: null,
        debtAddress: null,
        debtCity: null,
        debtCountry: null,
        debtProvince: null,
        debtCounty: null,
        debtStreet: null,
        debtYear: null,
        debtDate: null,
        debtDay: null,
        debtMonth: null,
        debtPeriod: null,
        debtQuarter: null,
        debtWeek: null,
        debtYearWeek: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 搜索操作 */
    querySearch(queryString, cb) {
        var projects = this.projects;
        var results = queryString ? projects.filter(this.createFilter(queryString)) : projects;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
    querySearchEntity(queryString, cb) {
        var entities = this.entities;
        var results = queryString ? entities.filter(this.createFilter(queryString)) : entities;
        // 调用 callback 返回建议列表的数据
        cb(results);
    },
    createFilter(queryString) {
        return (item) => {
           return item.value.toUpperCase().match(queryString.toUpperCase());
           // 第一个匹配
           //return (item.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
    },
    handleSelect(item) {
        if(item != null && item != undefined)
        {
          this.form.debtProjectId = item.projectId;
        }
    },
    handleClear()
    {
      this.form.debtProjectId = null;
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.debtId) {
        this.form.debtParentId = row.debtId;
      } else {
        this.form.debtParentId = 0;
      }
      this.open = true;
      this.title = "添加借贷账单";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.debtParentId = row.debtId;
      }
      getDebt(row.debtId).then(response => {
        this.form = response.data;
        if(this.form.debtAccountId != null) {
          this.form.debtAccountId  = this.form.debtAccountId.split(",");
        }
        if (this.form.debtCategoryId != null) {
          this.form.debtCategoryId = this.form.debtCategoryId.split(",");
        }
        if (this.form.debtLabelName != null) {
          this.form.debtLabelName  = this.form.debtLabelName.split(" ");
        }
        this.open = true;
        this.title = "修改借贷账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.debtAccountId = this.form.debtAccountId.join(",");
          this.form.debtCategoryId = this.form.debtCategoryId.join(",");
          this.form.debtLabelName = this.form.debtLabelName.join(" ");
          if (this.form.debtId != null) {
            updateDebt(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDebt(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除借贷账单编号为"' + row.debtId + '"的数据项？').then(function() {
        return delDebt(row.debtId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
