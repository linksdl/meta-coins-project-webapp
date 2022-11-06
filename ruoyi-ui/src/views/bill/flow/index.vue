<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="flowType">
        <el-select v-model="queryParams.flowType" placeholder="请选择流水类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_scope"
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
          v-hasPermi="['bill:flow:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bill:flow:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"

          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bill:flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          @click="handleExport"
          v-hasPermi="['bill:flow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :row-class-name="tableRowClassName" :data="flowList" @selection-change="handleSelectionChange">

      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.flowId }}</span>
            </el-form-item>
            <el-form-item label="名称">
              <span>{{ props.row.flowName }}</span>
            </el-form-item>
            <el-form-item label="类型">
              <template slot-scope="scope">
              <dict-tag :options="dict.type.config_function_scope" :value="props.row.flowType"/>
               </template>
            </el-form-item>
            <el-form-item label="时间">
              <span>{{ props.row.flowDate }}</span>
            </el-form-item>
            <el-form-item label="金额">
              <span>{{ props.row.flowAmount }}</span>
            </el-form-item>
            <el-form-item label="币种">
              <span>{{ props.row.flowMoneyName }}</span>
            </el-form-item>
            <el-form-item label="账户">
              <span>{{ props.row.flowAccountName }}</span>
            </el-form-item>
            <el-form-item label="分类">
              <span>{{ props.row.flowCategoryName }}</span>
            </el-form-item>
            <el-form-item label="实体">
              <span>{{ props.row.flowEntityName }}</span>
            </el-form-item>
            <el-form-item label="项目">
              <span>{{ props.row.flowProjectName }}</span>
            </el-form-item>
            <el-form-item label="标签">
              <span>{{ props.row.flowLabelName }}</span>
            </el-form-item>
            <el-form-item label="城市">
              <span>{{ props.row.flowCityName }}</span>
            </el-form-item>
            <el-form-item label="成员">
              <span>{{ props.row.flowMemberName }}</span>
            </el-form-item>
            <el-form-item label="心情">
              <span>{{ props.row.flowEmotionName }}</span>
            </el-form-item>
            <el-form-item label="天气">
              <span>{{ props.row.flowWeatherName }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />
      <el-table-column sortable label="名称" align="center" prop="flowName" :show-overflow-tooltip="true" />
      <el-table-column sortable label="源头" align="center" prop="flowSource" :show-overflow-tooltip="true" />
      <el-table-column sortable label="目的" align="center" prop="flowTarget" :show-overflow-tooltip="true" />
      <el-table-column sortable label="金额" align="center" prop="flowAmount" :show-overflow-tooltip="true" />
      <el-table-column sortable label="币种" align="center" prop="flowMoneyName" :show-overflow-tooltip="true" />
      <el-table-column sortable label="标志" align="center" prop="flowSign">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_sign" :value="scope.row.flowSign"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="类型" align="center" prop="flowType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_scope" :value="scope.row.flowType"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="日期" align="center" prop="flowDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.flowDatetime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="描述" align="center" prop="flowDesc" :show-overflow-tooltip="true" />
      <el-table-column sortable label="图片凭证" align="center" prop="flowImgs" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.flowImgs" :width="25" :height="25"/>
        </template>
      </el-table-column>
       <el-table-column sortable label="标签" align="center" prop="flowLabelName" :show-overflow-tooltip="true" />
      <el-table-column sortable label="是否入账" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column sortable label="是否删除" align="center" prop="isDeleted">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_deleted" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>
  <!--
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button

            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:flow:edit']"
          >修改</el-button>
          <el-button

            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:flow:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改账单流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="888px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="88px">

        <el-form-item label="名称" prop="flowName">
          <el-input v-model="form.flowName" placeholder="请输入流水名称" />
        </el-form-item>

        <el-row>
          <el-col :span="8">
            <el-form-item label="源头" prop="flowSource">
              <el-select v-model="form.flowSource" placeholder="请选择流水源头">
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
            <el-form-item label="目的" prop="flowTarget">
              <el-select v-model="form.flowTarget" placeholder="请选择流水目的">
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
             <el-form-item label="标志" prop="flowSign">
              <el-select v-model="form.flowSign" placeholder="请选择流水标志">
                <el-option
                  v-for="dict in dict.type.config_function_sign"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


          <el-row>
            <el-col :span="8">
                  <el-form-item label="类型" prop="flowType">
                  <el-select v-model="form.flowType" placeholder="请选择流水类型">
                    <el-option
                      v-for="dict in dict.type.config_function_scope"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"></el-option>
                  </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="金额" prop="flowAmount">
                <el-input-number v-model="form.flowAmount" type="input-number" :min="0.01" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="币种" prop="flowMoneyId">
                <el-radio-group v-model="form.flowMoneyId" placeholder="请选择币种">
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

        <el-form-item label="日期" prop="flowDatetime">
          <el-date-picker clearable
            v-model="form.flowDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择流水日期">
          </el-date-picker>
        </el-form-item>
            </el-col>
            <el-col :span="8">

        <el-form-item label="父类" prop="flowParentId">
          <el-select v-model="form.flowParentId" placeholder="请选择父类ID">
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
        <el-form-item label="账单" prop="flowBillId">
          <el-select v-model="form.flowBillId" placeholder="请选择流水账单ID">
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
            <el-form-item label="分类" prop="flowCategoryId">
                <el-cascader
                  clearable
                  filterable
                  placeholder="请选择分类"
                  v-model="form.flowCategoryId"
                  :options="categoryOptions"
                  :props="{ expandTrigger: 'click', value:'categoryId',label:'categoryName',children: 'children', disabled :'disabled'}"
                ></el-cascader>
            </el-form-item>
          </el-col>

           <el-col :span="8">
            <el-form-item label="账户" prop="flowAccountId">
              <el-cascader
                  clearable
                  filterable
                  placeholder="请选择账户"
                  v-model="form.flowAccountId"
                  :options="accountOptions"
                  :props="{ expandTrigger: 'click',value:'accountId', label:'accountName',children: 'children', disabled :'disabled'}"
              ></el-cascader>
            </el-form-item>
           </el-col>

           <el-col :span="8">
              <el-form-item label="实体" clearable filterable prop="flowEntityId">
                <el-select v-model="form.flowEntityId" clearable filterable placeholder="请选择实体名称">
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
            <el-form-item label="实体" prop="flowEntityName" >
              <el-autocomplete
                class="inline-input"
                clearable
                v-model="form.flowEntityName"
                :fetch-suggestions="querySearchEntity"
                :trigger-on-focus="true"
                placeholder="请输入实体名称"
                @select="handleSelect"
              ></el-autocomplete>
            </el-form-item>
          </el-col>

        </el-row>


      <el-row>
          <el-col :span="8" v-if='false'>
              <el-form-item label="项目" clearable filterable prop="flowProjectId">
                <el-select clearable @change="handleSelectProjectName" v-model="form.flowProjectId" filterable placeholder="请选择项目名称">
                  <el-option
                    clearable
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
            <el-form-item label="项目" prop="flowProjectName" style="width: 800px;" >
              <el-autocomplete
                class="inline-input"
                clearable
                v-model="form.flowProjectName"
                :fetch-suggestions="querySearch"
                :trigger-on-focus="true"
                placeholder="请输入项目名称"
                @select="handleSelect"
                @clear="handleClear"
              ></el-autocomplete>
            </el-form-item>
          </el-col>

          <el-col :span="8" v-if='false'>
            <el-form-item label="标签" prop="flowLabelName">
              <el-select v-model="form.flowLabelName" multiple clearable filterable collapse-tags placeholder="请选择标签">
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

          <el-col :span="8">
            <el-form-item label="标签" prop="flowLabelName">
              <el-tag
                :key="tag"
                v-for="tag in form.flowLabelName"
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

          <el-col :span="8" v-if='false' >
          <el-form-item label="成员"  prop="flowMemberId">
            <el-select v-model="form.flowMemberId" clearable filterable placeholder="请选择成员名">
              <el-option
                v-for="item in memberOptions"
                :key="item.memberId"
                :label="item.memberName"
                :value="item.memberId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8" >
          <el-form-item label="成员" prop="flowMemberName">
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
          <el-form-item label="心情"  prop="flowEmotionId">
            <el-select v-model="form.flowEmotionId" clearable filterable placeholder="请选择心情名">
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
            <el-form-item label="心情" prop="flowEmotionName">
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
        <el-form-item label="天气" prop="flowWeatherId">
          <el-select v-model="form.flowWeatherId" clearable filterable placeholder="请选择天气名">
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
          <el-form-item label="天气" prop="flowWeatherName">
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
          <el-form-item label="城市" prop="flowCityId">
            <el-radio-group v-model="form.flowCityId" placeholder="请选择城市名">
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

        <el-form-item label="描述" prop="flowDesc">
          <el-input v-model="form.flowDesc" type="textarea" placeholder="请输入描述" />
        </el-form-item>

        <el-row>
           <el-col :span="8">
        <el-form-item label="权重" prop="weight">
          <el-input-number  v-model="form.weight" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
        </el-form-item>
            </el-col>
            <el-col :span="8">
        <el-form-item label="排序" prop="orderSort">
          <el-input-number  v-model="form.orderSort" type="input-number" :min="0" :max="999999999" placeholder="请输入内容"/>
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

        <el-form-item label="图片凭证">
          <image-upload v-model="form.flowImgs"/>
        </el-form-item>


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
import { listFlow, getFlow, delFlow, addFlow, updateFlow } from "@/api/bill/flow";
import IconSelect from "@/components/IconSelect";

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


export default {
  name: "Flow",
  dicts: ['config_function_scope', 'config_is_enable', 'config_is_deleted', 'config_function_sign'],
  components: {IconSelect},
  data() {
    return {
      tableRowClassName({row, rowIndex}) {
        if (row.flowAmount > 10 && row.flowAmount < 100) {
          return 'warning-row';
        } else if (row.flowAmount > 100) {
          return 'success-row';
        }
        return '';
      },
      // 新建标签输入
      inputVisible: false,
      //凭证是否显示
      isImageShow: false,
      inputValue: '',
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
      projects: [],
      entities: [],
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
      // 账单流水表格数据
      flowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        createTime: null,
        flowName: null,
        flowType: null,
        flowDatetime: null,
        createTime: null,
        labelType: 'flow',
        categoryType: 'flow',
        accountType: 'flow',
        accountScope: 'flow',
        labelScope: 'flow',
        moneyScope: 'flow',
        entityScope: 'flow',
        projectScope: 'flow',
        categoryScope: 'flow',
        memberScope: 'flow',
        weatherScope: 'flow',
        emotionScope: 'flow'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      flowDatetime: [
          { required: true, message: "日期时间不能为空", trigger: "blur" }
          ],
          flowType: [
            { required: true, message: "类型不能为空", trigger: "blur" }
          ],
          flowMoneyId: [
            { required: true, message: "币种不能为空", trigger: "blur" }
          ],
          flowCategoryId: [
            { required: true, message: "分类不能为空", trigger: "change" }
          ],
          flowAccountId: [
            { required: true, message: "账户不能为空", trigger: "change" }
          ],
          flowEntityId: [
            { required: true, message: "实体不能为空", trigger: "change" }
          ],
          flowEntityName: [
            { required: true, message: "实体不能为空", trigger: "blur" }
          ],
          flowProjectName: [
            { required: true, message: "项目不能为空", trigger: "change" }
          ],
          flowAmount: [
            { required: true, message: "金额不能为空", trigger: "blur" }
          ],
          flowCityId: [
            { required: true, message: "城市不能为空", trigger: "blur" }
          ],
          flowLabelName: [
            { required: true, message: "标签不能为空", trigger: "blur" }
          ],
          flowMemberId: [
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
        if (row.flowAmount > 8000 && row.flowAmount < 10000) {
          return 'warning-row';
        } else if (row.flowAmount > 10000) {
          return 'success-row';
        }
        return '';
      },
      handleClose(tag) {
        this.form.flowLabelName.splice(this.form.flowLabelName.indexOf(tag), 2);
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
          this.form.flowLabelName.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      handleFlowTypeChange(val) {
        this.queryParams.moneyScope=val;
        getMoneyOptionSelect(this.queryParams).then(response => {
          this.moneyOptions = response.data;
          if(this.moneyOptions != null)
          {
            this.form.flowMoneyId = this.moneyOptions[0].moneyId;
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
            this.form.flowMemberId = this.memberOptions[0].memberId;
          }
        });

        this.queryParams.weatherScope=val;
        getWeatherOptionSelect(this.queryParams).then(response => {
          this.weatherOptions = response.data;
          if(this.weatherOptions != null)
          {
            this.form.flowWeatherId = this.weatherOptions[0].weatherId;
          }
        });

        this.queryParams.emotionScope=val;
        getEmotionOptionSelect(this.queryParams).then(response => {
          this.emotionOptions = response.data;
          if(this.emotionOptions != null)
          {
            this.form.flowEmotionId = this.emotionOptions[0].emotionId;
          }
        });

        this.form.flowCityId = this.cityOptions[0].cityId;
    },
    handleSelectProjectName(val) {
      this.projectOptions.forEach((item, index)=>{
        if(item.projectId === val)
        {
          this.form.flowProjectName = item.projectName;
          this.form.flowProjectId = item.projectId;
        }else {
          this.form.flowProjectId = null;
        }
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
    /** 选择账单流水图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询账单流水列表 */
    getList() {
      this.loading = true;
      listFlow(this.queryParams).then(response => {
        this.flowList = response.rows;
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
        flowId: null,
        flowName: null,
        flowSource: null,
        flowTarget: null,
        flowAmount: null,
        flowSign: null,
        flowType: null,
        flowDatetime: null,
        flowParentId: null,
        flowParentName: null,
        flowBillId: null,
        isDeleted: 0,
        orderSort: null,
        flowDesc: null,
        flowImgs: null,
        flowRemark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        enableStatus: 0,
        icon: null,
        remark: null,
        weight: null,
        flowUserId: null,
        flowUserName: null,
        flowWeatherId: null,
        flowWeatherName: null,
        flowMoneyName: null,
        flowMoneyId: null,
        flowMemberName: null,
        flowMemberId: null,
        flowLabelName: null,
        flowLabelId: null,
        flowProjectId: null,
        flowProjectName: null,
        flowEmotionId: null,
        flowEmotionName: null,
        flowCategoryName: null,
        flowCategoryId: null,
        flowAccountName: null,
        flowAccountId: null,
        flowEntityName: null,
        flowEntityId: null,
        flowCityId: null,
        flowCityName: null,
        flowBookId: null,
        flowBookName: null,
        flowYear: null,
        flowMonth: null,
        flowDate: null,
        flowDay: null,
        flowQuarter: null,
        flowPeriod: null,
        flowWeek: null,
        flowYearWeek: null,
        flowCounty: null,
        flowProvince: null,
        flowStreet: null,
        flowCountry: null,
        flowCity: null,
        flowAddress: null
      };
      this.resetForm("form");
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
          this.form.flowProjectId = item.projectId;
        }
    },
    handleClear()
    {
      this.form.flowProjectId = null;
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
      this.ids = selection.map(item => item.flowId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账单流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const flowId = row.flowId || this.ids
      getFlow(flowId).then(response => {
        this.form = response.data;
        if(this.form.flowAccountId != null) {
          this.form.flowAccountId  = this.form.flowAccountId.split(",");
        }
        if (this.form.flowCategoryId != null) {
          this.form.flowCategoryId = this.form.flowCategoryId.split(",");
        }
        if (this.form.flowLabelName != null) {
          this.form.flowLabelName  = this.form.flowLabelName.split(" ");
        }
        this.open = true;
        this.title = "修改账单流水";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.flowAccountId = this.form.flowAccountId.join(",");
          this.form.flowCategoryId = this.form.flowCategoryId.join(",");
          this.form.flowLabelName = this.form.flowLabelName.join(" ");
          if (this.form.flowId != null) {
            updateFlow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFlow(this.form).then(response => {
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
      const flowIds = row.flowId || this.ids;
      this.$modal.confirm('是否确认删除账单流水编号为"' + flowIds + '"的数据项？').then(function() {
        return delFlow(flowIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/flow/export', {
        ...this.queryParams
      }, `flow_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
