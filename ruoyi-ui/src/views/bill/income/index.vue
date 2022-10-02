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
    <el-dialog :title="title" :visible.sync="open" width="888px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="68px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="名称" prop="incomeName">
                <el-input v-model="form.incomeName" placeholder="请输入收入名称" />
              </el-form-item>
            </el-col>
          </el-row>



          <el-row>
           <el-col :span="11">
            <el-form-item label="类型" prop="incomeType">
              <el-radio-group v-model="form.incomeType" placeholder="请选择收入类型" @change="handleIncomeTypeChange" >
                <el-radio
                  v-for="dict in dict.type.config_function_in"
                  :key="dict.value"
                  :label="dict.value"
                  :value="dict.value">
              {{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
           </el-col>

           <el-col :span="13">
              <el-form-item label="币种" prop="incomeMoneyId">
                <el-radio-group v-model="form.incomeMoneyId" size="small" placeholder="请选择币种">
                  <el-radio-button
                    v-for="item in moneyOptions"
                    :label="item.moneyCname"
                    :value="item.moneyId"
                    :disabled="item.disabled">
                  </el-radio-button>
                </el-radio-group>
              </el-form-item>
           </el-col>


         </el-row>

          <el-row>
           <el-col :span="8">
            <el-form-item label="日期" prop="incomeDatetime">
              <el-date-picker
                clearable
                v-model="form.incomeDatetime"
                type="datetime"
                placeholder="请选择收入日期"
                value-format="yyyy-MM-dd HH:mm:SS"
                align="right"
                :picker-options="pickerOptions"
                >
              </el-date-picker>
            </el-form-item>
          </el-col>

           <el-col :span="8">
            <el-form-item label="账户" prop="incomeAccountId">
              <el-cascader
                  clearable
                  size="medium"
                  filterable
                  placeholder="请选择账户"
                  v-model="form.incomeAccountId"
                  :options="accountOptions"
                  :props="{ expandTrigger: 'hover',value:'accountId',label:'accountName',children: 'children', disabled :'disabled' }"
              ></el-cascader>
            </el-form-item>
           </el-col>

          <el-col :span="8">
            <el-form-item label="金额" prop="incomeAmount">
              <el-input-number size="medium" v-model="form.incomeAmount" type="input-number" :min="0.00" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
           <el-col :span="8">
            <el-form-item label="分类" prop="incomeCategoryId">
                <el-cascader
                  clearable
                  filterable
                  size="medium"
                  placeholder="请选择分类"
                  v-model="form.incomeCategoryId"
                  :options="categoryOptions"
                  :props="{ expandTrigger: 'hover',value:'categoryId',label:'categoryName',children: 'children', disabled :'disabled'}"
                ></el-cascader>
            </el-form-item>
          </el-col>

          <el-col :span="8">
          <el-form-item label="主体" prop="incomeEntityId">
            <el-select v-model="form.incomeEntityId" filterable placeholder="请选择主体名">
              <el-option
                v-for="item in enterpriseOptions"
                :key="item.enterpriseId"
                :label="item.enterpriseName"
                :value="item.enterpriseId"
                :disabled="item.disabled">
              </el-option>
            </el-select>
          </el-form-item>
         </el-col>

          <el-col :span="8">
          <el-form-item label="项目" prop="incomeProjectId">
            <el-select v-model="form.incomeProjectId" filterable placeholder="请选择项目名">
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

        </el-row>

       <el-row>
         <el-col :span="24">
          <el-form-item label="城市" prop="incomeCityName">
            <el-radio-group v-model="form.incomeCityName" placeholder="请选择城市名">
              <el-radio-button
                v-for="item in cityOptions"
                :key="item.cityId"
                :label="item.cityCname"
                :value="item.cityCname"
                :disabled="item.disabled">
              </el-radio-button>
            </el-radio-group>
          </el-form-item>
         </el-col>
       </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="标签" prop="incomeLabelName">
            <el-select v-model="form.incomeLabelName" multiple clearable filterable placeholder="请选择标签">
              <el-option
                v-for="item in labelOptions"
                :key="parseInt(item.labelId)"
                :label="item.labelCname"
                :value="item.labelCname">
                <span style="float: left">{{ item.labelCname }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.labelEname}}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="16" >
          <el-form-item label="标签" prop="incomeLabelId">
            <el-tag
              size="medium"
              style="margin-left: 5px; margin-top:4px;"
              v-for="item in labelOptions"
              :key="item.labelCname"
              type='info'
              effect="plain"
            >
            {{ item.labelCname }}
            </el-tag>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="8">
          <el-form-item label="成员" prop="incomeMemberId">
            <el-select v-model="form.incomeMemberId" placeholder="请选择成员名">
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
          <el-form-item label="成员" prop="incomeLabelId">
          <el-tag
            size="medium"
            style="margin-left: 5px; margin-top:4px;"
            v-for="item in memberOptions"
            :key="item.memberName"
            type='danger'
            effect="light">
            {{ item.memberName }}
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
              v-for="item in emotionOptions"
              :key="item.emotionId"
              :label="item.emotionCname"
              :value="item.emotionId">
            </el-option>
          </el-select>
        </el-form-item>
       </el-col>
       <el-col :span="16" >
          <el-form-item label="心情" prop="incomeLabelId">
          <el-tag
            size="medium"
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



      <el-row>
        <el-col :span="8">
        <el-form-item label="天气" prop="incomeWeatherId">
          <el-select v-model="form.incomeWeatherId" placeholder="请选择天气名">
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
          <el-form-item label="天气" prop="incomeWeatherId">
          <el-tag
            size="medium"
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
           <el-form-item label="描述" prop="incomeDesc">
            <el-input v-model="form.incomeDesc" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
          <el-col :span="24">
              <el-form-item label="是否记录">
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

<script>
import { listIncome, getIncome, delIncome, addIncome, updateIncome } from "@/api/bill/income";
import IconSelect from "@/components/IconSelect";

import { selectAccount as getAccountOptionSelect } from "@/api/config/account";
import { selectBook as getBookOptionSelect } from "@/api/config/book";
import { selectCategory as getCategoryOptionSelect } from "@/api/config/category";
import { selectCity as getCityOptionSelect } from "@/api/config/city";
import { selectEmotion as getEmotionOptionSelect } from "@/api/config/emotion";
import { selectEnterprise as getEnterpriseOptionSelect } from "@/api/config/enterprise";
import { selectGoods as getGoodsOptionSelect } from "@/api/config/goods";
import { selectLabel as getLabelOptionSelect } from "@/api/config/label";
import { selectMember as getMemberOptionSelect } from "@/api/config/member";
import { selectMoney as getMoneyOptionSelect } from "@/api/config/money";
import { selectProject as getProjectOptionSelect } from "@/api/config/project";
import { selectWeather as getWeatherOptionSelect } from "@/api/config/weather";

export default {
  name: "Income",
  dicts: ['config_function_in', 'config_is_enable'],
  components: {IconSelect},
  data() {
    return {
      memberArr: null,
      accountArr: null,
      categoryArr: null,
      labelArr: null,
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
      enterpriseOptions: [],
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
        labelType: 'income',
        labelScope: 'income',
        categoryType: 'income',
        accountType: 'income',
        moneyScope: 'income',
        enterpriseScope: 'income',
        projectScope: 'income',
        categoryScope: 'income',
        weatherScope: 'income',
        emotionScope: 'income'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        incomeAmount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
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
    this.getEnterpriseList();
    this.getGoodsList();
    this.getLabelList();
    this.getMemberList();
    this.getMoneyList();
    this.getProjectList();
    this.getWeatherList();
  },
  methods: {
    handleIncomeTypeChange(val) {
      this.queryParams.moneyScope=val;
      getMoneyOptionSelect(this.queryParams).then(response => {
        this.moneyOptions = response.data;
      });

      this.queryParams.enterpriseScope=val;
      getEnterpriseOptionSelect(this.queryParams).then(response => {
        this.enterpriseOptions = response.data;
      });

      this.queryParams.projectScope=val;
      getProjectOptionSelect(this.queryParams).then(response => {
        this.projectOptions = response.data;
      });

      this.queryParams.categoryScope=val;
      getCategoryOptionSelect(this.queryParams).then(response => {
        this.categoryOptions = response.data;
      });

      this.queryParams.labelScope=val;
      getLabelOptionSelect(this.queryParams).then(response => {
        this.labelOptions = response.data;
      });

      this.queryParams.weatherScope=val;
      getWeatherOptionSelect(this.queryParams).then(response => {
        this.weatherOptions = response.data;
      });

      this.queryParams.emotionScope=val;
      getEmotionOptionSelect(this.queryParams).then(response => {
        this.emotionOptions = response.data;
      });
    },
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
    getEnterpriseList() {
      getEnterpriseOptionSelect(this.queryParams).then(response => {
        this.enterpriseOptions = response.data;
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
      });
    },
    /** 查询天气下拉 */
    getWeatherList() {
      getWeatherOptionSelect(this.queryParams).then(response => {
        this.weatherOptions = response.data;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.memberArr= [];
      this.accountArr= [];
      this.categoryArr= [];
      this.labelArr= [];
      this.form = {
        incomeId: null,
        incomeName: null,
        incomeType: null,
        incomeAmount: null,
        incomeDatetime: null,
        incomeDesc: null,
        incomeImgs: null,
        enableStatus: 1,
        icon: null,
        remark: null,
        orderSort: null,
        weight: null,
        incomeParentId: null,
        incomeParentName: null,
        incomeBookId: null,
        incomeBookName: null,
        incomeAccountId: [],
        incomeAccountName: [],
        incomeCategoryId: [],
        incomeCategoryName: [],
        incomeCityId: null,
        incomeCityName: null,
        incomeEmotionId: null,
        incomeEmotionName: null,
        incomeEntityId: null,
        incomeEntityName: null,
        incomeAddress: null,
        incomeLabelId: [],
        incomeLabelName: [],
        incomeMemberId: null,
        incomeMemberName: null,
        incomeMoneyId: null,
        incomeMoneyName: null,
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
        this.form.incomeLabelId = this.form.incomeLabelId.split(",");
        this.form.incomeAccountId = this.form.incomeAccountId.split(",");
        this.form.incomeCategoryId = this.form.incomeCategoryId.split(",");
        this.open = true;
        this.title = "修改收入账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        this.form.incomeLabelId = this.form.incomeLabelId.join(",");
        this.form.incomeAccountId = this.form.incomeAccountId.join(",");
        this.form.incomeCategoryId = this.form.incomeCategoryId.join(",");
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
