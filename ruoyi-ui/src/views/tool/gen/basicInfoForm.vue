<template>
  <el-form ref="basicInfoForm" :model="info" :rules="rules" label-width="150px">
    <el-row>
      <el-col :span="12">
        <el-form-item label="表名称" prop="tableName">
          <el-input placeholder="请输入仓库名称" v-model="info.tableName" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="表描述" prop="tableComment">
          <el-input placeholder="请输入" v-model="info.tableComment" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="实体类名称" prop="className">
          <el-input placeholder="请输入" v-model="info.className" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="作者" prop="functionAuthor">
          <el-input placeholder="请输入" v-model="info.functionAuthor" />
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="表组件类型" prop="tableType">
          <el-select placeholder="请选择" v-model="info.tableType">
            <el-option label="基础表格" value="table-basic" />
            <el-option label="展开表格" value="table-slot" />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="表组件属性" prop="tableAttribute">
          <el-input placeholder="请输入" v-model="info.tableAttribute" />
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="表下拉框" prop="tableType">
          <el-select placeholder="请选择" v-model="info.tableSelect">
            <el-option label="无参下拉" value="select" />
            <el-option label="有参下拉" value="select-query" />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="24">
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" :rows="3" v-model="info.remark"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
export default {
  props: {
    info: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      rules: {
        tableName: [
          { required: true, message: "请输入表名称", trigger: "blur" }
        ],
        tableComment: [
          { required: true, message: "请输入表描述", trigger: "blur" }
        ],
        className: [
          { required: true, message: "请输入实体类名称", trigger: "blur" }
        ],
        functionAuthor: [
          { required: true, message: "请输入作者", trigger: "blur" }
        ],
        tableType: [
          { required: true, message: "请输入表组件类型", trigger: "blur" }
        ],
        tableAttribute: [
          { required: true, message: "请输入表显示属性", trigger: "blur" }
        ]
      }
    };
  }
};
</script>
