<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="名称" prop="consumeName">
        <el-input
          v-model="queryParams.consumeName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="类型" prop="consumeType">
        <el-select v-model="queryParams.consumeType" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.config_function_out"
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
          v-hasPermi="['bill:consume:add']"
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
          v-hasPermi="['bill:consume:edit']"
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
          v-hasPermi="['bill:consume:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bill:consume:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :row-class-name="tableRowClassName" :data="consumeList" @selection-change="handleSelectionChange">

     <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.consumeId }}</span>
            </el-form-item>
            <el-form-item label="名称">
              <span>{{ props.row.consumeName }}</span>
            </el-form-item>
            <el-form-item label="类型">
              <template slot-scope="scope">
              <dict-tag :options="dict.type.config_function_out" :value="props.row.consumeType"/>
               </template>
            </el-form-item>
            <el-form-item label="时间">
              <span>{{ props.row.consumeDate }}</span>
            </el-form-item>
            <el-form-item label="金额">
              <span>{{ props.row.consumeAmount }}</span>
            </el-form-item>
            <el-form-item label="币种">
              <span>{{ props.row.consumeMoneyName }}</span>
            </el-form-item>
            <el-form-item label="账户">
              <span>{{ props.row.consumeAccountName }}</span>
            </el-form-item>
            <el-form-item label="分类">
              <span>{{ props.row.consumeCategoryName }}</span>
            </el-form-item>
            <el-form-item label="实体">
              <span>{{ props.row.consumeEntityName }}</span>
            </el-form-item>
            <el-form-item label="项目">
              <span>{{ props.row.consumeProjectName }}</span>
            </el-form-item>
            <el-form-item label="标签">
              <span>{{ props.row.consumeLabelName }}</span>
            </el-form-item>
            <el-form-item label="城市">
              <span>{{ props.row.consumeCityName }}</span>
            </el-form-item>
            <el-form-item label="成员">
              <span>{{ props.row.consumeMemberName }}</span>
            </el-form-item>
            <el-form-item label="心情">
              <span>{{ props.row.consumeEmotionName }}</span>
            </el-form-item>
            <el-form-item label="天气">
              <span>{{ props.row.consumeWeatherName }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="selection" width="55" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="名称" align="center" prop="consumeName" :show-overflow-tooltip="true" />
      <el-table-column label="类型" align="center" prop="consumeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_function_out" :value="scope.row.consumeType"/>
        </template>
      </el-table-column>
      <el-table-column label="日期" align="center" prop="consumeDatetime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.consumeDatetime, '{y}-{m}-{d} {h}:{i}:{s}')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center" prop="consumeAmount" :show-overflow-tooltip="true" />
      <el-table-column label="描述" align="center" prop="consumeDesc" :show-overflow-tooltip="true" />
      <el-table-column label="凭证" align="center" prop="consumeImgs" width="180">
        <template slot-scope="scope">
          <image-preview :src="scope.row.consumeImgs" :width="25" :height="25"/>
        </template>
      </el-table-column>
      <el-table-column label="可用" align="center" prop="enableStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.config_is_enable" :value="scope.row.enableStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="项目" align="center" prop="consumeProjectName" :show-overflow-tooltip="true" />
      <el-table-column label="标签" align="center" prop="consumeLabelName" :show-overflow-tooltip="true" />
      <el-table-column label="币种" align="center" prop="consumeMoneyName" :show-overflow-tooltip="true" />
      <el-table-column label="分类" align="center" prop="consumeCategoryName" :show-overflow-tooltip="true" />
      <el-table-column label="账户" align="center" prop="consumeAccountName" :show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bill:consume:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bill:consume:remove']"
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

    <!-- 添加或修改支出账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="868px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="88px">
        <el-row>
            <el-col :span="8">
              <el-form-item label="时间" prop="consumeDatetime">
                <el-date-picker
                  clearable
                  v-model="form.consumeDatetime"
                  type="datetime"
                  placeholder="请选择支出日期"
                  value-format="yyyy-MM-dd HH:mm:SS"
                  align="right"
                  :picker-options="pickerOptions"
                  >
                </el-date-picker>
              </el-form-item>
            </el-col>

            <el-col :span="16">
              <el-form-item label="名称" prop="consumeName">
                <el-input :disabled="true" clearable v-model="form.consumeName" placeholder="请输入支出名称" />
              </el-form-item>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="24">
            <el-form-item label="类型" prop="consumeType">
              <el-radio-group v-model="form.consumeType" placeholder="请选择支出类型" @change="handleConsumeTypeChange" >
                <el-radio
                  v-for="dict in dict.type.config_function_out"
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
              <el-form-item label="金额" prop="consumeAmount">
                <el-input-number v-model="form.consumeAmount" type="input-number" :min="0.01" :step="0.01" :precision="2" :max="999999999.00" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label="币种" prop="consumeMoneyId">
                <el-radio-group v-model="form.consumeMoneyId" placeholder="请选择币种">
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
            <el-form-item label="分类" prop="consumeCategoryId">
                <el-cascader
                  clearable
                  filterable
                  placeholder="请选择分类"
                  v-model="form.consumeCategoryId"
                  :options="categoryOptions"
                  :props="{ expandTrigger: 'click', value:'categoryId',label:'categoryName',children: 'children', disabled :'disabled'}"
                ></el-cascader>
            </el-form-item>
          </el-col>

           <el-col :span="16">
            <el-form-item label="账户" prop="consumeAccountId">
              <el-cascader
                  clearable
                  filterable
                  placeholder="请选择账户"
                  v-model="form.consumeAccountId"
                  :options="accountOptions"
                  :props="{ expandTrigger: 'click',value:'accountId', label:'accountName',children: 'children', disabled :'disabled'}"
              ></el-cascader>
            </el-form-item>
           </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
              <el-form-item label="实体" clearable filterable prop="consumeEntityId">
                <el-select v-model="form.consumeEntityId" filterable placeholder="请选择实体名称">
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
            <el-form-item label="实体" prop="consumeEntityName" >
              <el-autocomplete
                class="inline-input"
                clearable
                v-model="form.consumeEntityName"
                :fetch-suggestions="querySearchEntity"
                :trigger-on-focus="true"
                placeholder="请输入实体名称"
                @select="handleSelect"
              ></el-autocomplete>
            </el-form-item>
          </el-col>

           <el-col :span="8" v-if='false'>
              <el-form-item label="项目" clearable filterable prop="consumeProjectId">
                <el-select clearable @change="handleSelectProjectName" v-model="form.consumeProjectId" filterable placeholder="请选择项目名称">
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
            <el-form-item label="项目" prop="consumeProjectName" style="width: 800px;" >
              <el-autocomplete
                class="inline-input"
                clearable
                v-model="form.consumeProjectName"
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
            <el-form-item label="标签" prop="consumeLabelName">
              <el-select v-model="form.consumeLabelName" multiple clearable filterable collapse-tags placeholder="请选择标签">
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
            <el-form-item label="标签" prop="consumeLabelName">
              <el-tag
                :key="tag"
                v-for="tag in form.consumeLabelName"
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
          <el-form-item label="城市" prop="consumeCityId">
            <el-radio-group v-model="form.consumeCityId" placeholder="请选择城市名">
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
           <el-form-item label="描述" prop="consumeDesc">
            <el-input :disabled="true" v-model="form.consumeDesc" type="textarea" clearable placeholder="请输入内容" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="成员"  prop="consumeMemberId">
            <el-select v-model="form.consumeMemberId" clearable filterable placeholder="请选择成员名">
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
          <el-form-item label="成员" prop="consumeMemberName">
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
        <el-form-item label="心情"  prop="consumeEmotionId">
          <el-select v-model="form.consumeEmotionId" clearable filterable placeholder="请选择心情名">
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
          <el-form-item label="心情" prop="consumeEmotionName">
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
        <el-form-item label="天气" prop="consumeWeatherId">
          <el-select v-model="form.consumeWeatherId" clearable filterable placeholder="请选择天气名">
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
          <el-form-item label="天气" prop="consumeWeatherName">
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
          <el-col :span="21">
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

          <el-col :span="3">
            <el-button  type="success"  icon="el-icon-edit" @click="innerVisible = true">增加商品</el-button>
          </el-col>
      </el-row>



      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
        width="800px"
        height="600px"
        title="增加商品列表"
        :visible.sync="innerVisible"
        append-to-body>
        <!-- <el-divider content-position="center">消费商品信息</el-divider> -->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBillConsumeGoods">增加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBillConsumeGoods">删除</el-button>
          </el-col>
        </el-row>

        <el-table :data="billConsumeGoodsList"
            :row-class-name="rowBillConsumeGoodsIndex"
            @selection-change="handleBillConsumeGoodsSelectionChange"
            ref="billConsumeGoods">

          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="商品" prop="goodsId" width="200">
            <template slot-scope="scope">
              <el-select v-model="scope.row.goodsId" clearable filterable placeholder="请选择商品">
                <el-option
                  v-for="item in goodsOptions"
                  :key="item.goodsId"
                  :label="item.goodsCname"
                  :value="item.goodsId">
                </el-option>
               </el-select>
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="goodsTotal" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.goodsTotal" :step="1" placeholder="请输入数量" />
            </template>
          </el-table-column>
          <el-table-column label="价格" prop="goodsPrice" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.goodsPrice" :min="0.01" :precision="2" :step="0.01" :max="999999.99" placeholder="请输入数量" />
            </template>
          </el-table-column>
          <el-table-column label="总价" prop="goodsTotal" width="150">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.goodsTotalPrice" :min="0.01" :precision="2" :step="0.01" :max="999999.99" placeholder="请输入数量" />
            </template>
          </el-table-column>

        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="innerVisible = false">确 定</el-button>
          <el-button @click="innerVisible = false">取 消</el-button>
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
import { listConsume, getConsume, delConsume, addConsume, updateConsume } from "@/api/bill/consume";
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
  name: "Consume",
  dicts: ['config_is_enable', 'config_is_deleted', 'config_function_out'],
  components: {IconSelect},
  data() {
    return {
      innerVisible: false,
      // 新建标签输入
      inputVisible: false,
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
      // 子表选中数据
      checkedBillConsumeGoods: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 支出账单表格数据
      consumeList: [],
      // 消费商品表格数据
      billConsumeGoodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        consumeName: null,
        consumeType: null,
        consumeDatetime: null,
        createTime: null,
        labelType: 'consume',
        categoryType: 'consume',
        accountType: 'consume',
        accountScope: 'consume',
        labelScope: 'consume',
        moneyScope: 'consume',
        entityScope: 'consume',
        projectScope: 'consume',
        categoryScope: 'consume',
        memberScope: 'consume',
        weatherScope: 'consume',
        emotionScope: 'consume'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        consumeDatetime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        consumeType: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        consumeMoneyId: [
          { required: true, message: "币种不能为空", trigger: "blur" }
        ],
        consumeAccountId: [
          { required: true, message: "账户不能为空", trigger: "change" }
        ],
        consumeCategoryId: [
          { required: true, message: "分类不能为空", trigger: "change" }
        ],
        consumeEntityId: [
          { required: true, message: "实体不能为空", trigger: "change" }
        ],
        consumeEntityName: [
          { required: true, message: "实体不能为空", trigger: "change" }
        ],
        consumeProjectId: [
          { required: true, message: "项目不能为空", trigger: "change" }
        ],
        consumeProjectName: [
          { required: true, message: "项目不能为空", trigger: "change" }
        ],
        consumeAmount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        consumeCityId: [
          { required: true, message: "城市不能为空", trigger: "blur" }
        ],
        consumeLabelName: [
          { required: true, message: "标签不能为空", trigger: "blur" }
        ],
        consumeMemberId: [
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
        if (row.consumeAmount > 50 && row.consumeAmount < 1000) {
          return 'warning-row';
        } else if (row.consumeAmount > 1000) {
          return 'success-row';
        }
        return '';
      },
    handleClose(tag) {
        this.form.consumeLabelName.splice(this.form.consumeLabelName.indexOf(tag), 2);
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
          this.form.consumeLabelName.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
    handleConsumeTypeChange(val) {
      this.queryParams.moneyScope=val;
      getMoneyOptionSelect(this.queryParams).then(response => {
        this.moneyOptions = response.data;
        if(this.moneyOptions != null)
        {
          this.form.consumeMoneyId = this.moneyOptions[0].moneyId;
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
          this.form.consumeMemberId = this.memberOptions[0].memberId;
        }
      });

      this.queryParams.weatherScope=val;
      getWeatherOptionSelect(this.queryParams).then(response => {
        this.weatherOptions = response.data;
         if(this.weatherOptions != null)
        {
          this.form.consumeWeatherId = this.weatherOptions[0].weatherId;
        }
      });

      this.queryParams.emotionScope=val;
      getEmotionOptionSelect(this.queryParams).then(response => {
        this.emotionOptions = response.data;
        if(this.emotionOptions != null)
        {
          this.form.consumeEmotionId = this.emotionOptions[0].emotionId;
        }
      });

      this.form.consumeCityId = this.cityOptions[0].cityId;
    },
    handleSelectProjectName(val) {
      this.projectOptions.forEach((item, index)=>{
        if(item.projectId === val)
        {
          this.form.consumeProjectName = item.projectName;
          this.form.consumeProjectId = item.projectId;
        }else {
          this.form.consumeProjectId = null;
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
    /** 选择支出账单图标 */
    selected(name) {
      this.form.icon = name;
    },
    /** 查询支出账单列表 */
    getList() {
      this.loading = true;
      listConsume(this.queryParams).then(response => {
        this.consumeList = response.rows;
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
        consumeId: null,
        consumeName: null,
        consumeType: null,
        consumeDatetime: null,
        consumeAmount: null,
        consumeDesc: null,
        consumeImgs: null,
        consumeParentId: null,
        consumeParentName: null,
        enableStatus: 1,
        icon: null,
        remark: null,
        weight: null,
        isDeleted: 0,
        orderSort: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        consumeUserId: null,
        consumeUserName: null,
        consumeWeatherId: null,
        consumeWeatherName: null,
        consumeEmotionId: null,
        consumeEmotionName: null,
        consumeCityId: null,
        consumeCityName: null,
        consumeProjectId: null,
        consumeProjectName: null,
        consumeLabelId: null,
        consumeLabelName: null,
        consumeMemberId: null,
        consumeMemberName: null,
        consumeMoneyId: null,
        consumeMoneyName: null,
        consumeEntityId: null,
        consumeEntityName: null,
        consumeCategoryId: null,
        consumeCategoryName: null,
        consumeAccountId: null,
        consumeAccountName: null,
        consumeBookId: null,
        consumeBookName: null,
        consumeCounty: null,
        consumeProvince: null,
        consumeCity: null,
        consumeCountry: null,
        consumeAddress: null,
        consumeStreet: null,
        consumeDate: null,
        consumeYear: null,
        consumeQuarter: null,
        consumeMonth: null,
        consumeWeek: null,
        consumeDay: null,
        consumePeriod: null,
        consumeYearWeek: null
      };
      this.billConsumeGoodsList = [];
      this.resetForm("form");
    },
    /** 搜索操作 */
    querySearch(queryString, cb) {
        var projects = this.projects;
        var results = queryString ? labels.filter(this.createFilter(queryString)) : projects;
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
        console.log(item);
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
      this.ids = selection.map(item => item.consumeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支出账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumeId = row.consumeId || this.ids
      getConsume(consumeId).then(response => {
        this.form = response.data;
        this.billConsumeGoodsList = response.data.billConsumeGoodsList;
        if(this.form.consumeAccountId != null) {
          this.form.consumeAccountId  = this.form.consumeAccountId.split(",");
        }
        if (this.form.consumeCategoryId != null) {
          this.form.consumeCategoryId = this.form.consumeCategoryId.split(",");
        }
        if (this.form.consumeLabelName != null) {
          this.form.consumeLabelName  = this.form.consumeLabelName.split(" ");
        }
        this.open = true;
        this.title = "修改支出账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
        this.form.billConsumeGoodsList = this.billConsumeGoodsList;
          this.form.consumeAccountId = this.form.consumeAccountId.join(",");
          this.form.consumeCategoryId = this.form.consumeCategoryId.join(",");
          this.form.consumeLabelName = this.form.consumeLabelName.join(" ");
          if (this.form.consumeId != null) {
            updateConsume(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsume(this.form).then(response => {
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
      const consumeIds = row.consumeId || this.ids;
      this.$modal.confirm('是否确认删除支出账单编号为"' + consumeIds + '"的数据项？').then(function() {
        return delConsume(consumeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 消费商品序号 */
    rowBillConsumeGoodsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 消费商品添加按钮操作 */
    handleAddBillConsumeGoods() {
      let obj = {};
      obj.goodsId = "";
      obj.goodsCname = "";
      obj.goodsEname = "";
      obj.goodsPrice = "";
      obj.goodsTotal = "";
      obj.goodsTotalPrice = "";
      obj.enableStatus = "";
      obj.isDeleted = "";
      this.billConsumeGoodsList.push(obj);
    },
    /** 消费商品删除按钮操作 */
    handleDeleteBillConsumeGoods() {
      if (this.checkedBillConsumeGoods.length == 0) {
        this.$modal.msgError("请先选择要删除的消费商品数据");
      } else {
        const billConsumeGoodsList = this.billConsumeGoodsList;
        const checkedBillConsumeGoods = this.checkedBillConsumeGoods;
        this.billConsumeGoodsList = billConsumeGoodsList.filter(function(item) {
          return checkedBillConsumeGoods.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleBillConsumeGoodsSelectionChange(selection) {
      this.checkedBillConsumeGoods = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bill/consume/export', {
        ...this.queryParams
      }, `consume_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
