<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input v-model="data.className" prefix-icon="Search" style="width: 240px; margin-right: 10px" placeholder="请输入班级名称查询"></el-input>
      <el-input v-model="data.textbookName" prefix-icon="Search" style="width: 240px; margin-right: 10px" placeholder="请输入教材名称查询"></el-input>
      <el-button type="info" plain @click="load">查询</el-button>
      <el-button type="warning" plain style="margin: 0 10px" @click="reset">重置</el-button>
    </div>
    <div class="card" style="margin-bottom: 5px" v-if="data.user.role !== 'STUDENT'">
      <el-button type="primary" plain @click="handleAdd" v-if="data.user.role === 'TEACHER'">发放教材</el-button>
      <el-button type="danger" plain @click="delBatch" v-if="data.user.role === 'ADMIN'">批量删除</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table stripe :data="data.tableData" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"  v-if="data.user.role === 'ADMIN'"/>
        <el-table-column prop="textbookName" label="教材名称"></el-table-column>
        <el-table-column prop="className" label="班级名称"></el-table-column>
        <el-table-column prop="number" label="出库数量"></el-table-column>
        <el-table-column prop="address" label="领取地点"></el-table-column>
        <el-table-column prop="outTime" label="申请时间"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template v-slot="scope">
            <el-tag type="info" v-if="scope.row.status === '待审批'">待审批</el-tag>
            <el-tag type="warning" v-if="scope.row.status === '待领取'">待领取</el-tag>
            <el-tag type="danger" v-if="scope.row.status === '拒绝'">拒绝</el-tag>
            <el-tag type="success" v-if="scope.row.status === '已领取'">已领取</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
        <el-table-column prop="getTime" label="领取时间"></el-table-column>
        <el-table-column label="操作" width="240" fixed="right">
          <template v-slot="scope">
            <el-button plain type="success" v-if="data.user.role === 'ADMIN' &&  scope.row.status === '待审批'" @click="changeStatus(scope.row, '待领取')">通过</el-button>
            <el-button plain type="danger" v-if="data.user.role === 'ADMIN' &&  scope.row.status === '待审批'" @click="changeStatus(scope.row, '拒绝')">拒绝</el-button>
            <el-button plain type="success" v-if="data.user.role === 'TEACHER' &&  scope.row.status === '待领取'" @click="changeStatus(scope.row, '已领取')">已领取</el-button>
            <el-button type="primary" circle :icon="Edit" @click="handleEdit(scope.row)"  v-if="data.user.role === 'TEACHER' && scope.row.status === '待审批'"></el-button>
            <el-button type="danger" circle :icon="Delete" @click="del(scope.row.id)"  v-if="data.user.role === 'ADMIN'"></el-button>

            <el-button plain type="primary" @click="addFeedback"  v-if="data.user.role === 'STUDENT'">反馈</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="prev, pager, next" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total" />
    </div>

    <el-dialog title="申请发放教材" v-model="data.formVisible" width="40%" destroy-on-close>
      <el-form ref="form" :model="data.form" label-width="70px" style="padding: 20px">
        <el-form-item label="教材" prop="textbookId">
          <el-select style="width: 100%" v-model="data.form.textbookId">
            <el-option v-for="item in data.textbookList" :key="item.id" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select style="width: 100%" v-model="data.form.classId" @change="getStudentNumber">
            <el-option v-for="item in data.classList" :key="item.id" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input-number v-model="data.form.number" placeholder="数量"></el-input-number>
        </el-form-item>
        <el-form-item label="领取地点" prop="address">
          <el-input type="textarea" :rows="3" v-model="data.form.address" placeholder="领取地点"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" :rows="3" v-model="data.form.remark" placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog title="反馈信息" v-model="data.formVisible1" width="40%" destroy-on-close>
      <el-form ref="form" :model="data.form" label-width="70px" style="padding: 20px">
        <el-form-item prop="content" label="反馈内容">
          <el-input type="textarea" :rows="4" v-model="data.form.content" placeholder="请输入反馈内容"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="saveFeedback">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>

import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {Delete, Edit} from "@element-plus/icons-vue";


const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  formVisible: false,
  formVisible1: false,
  form: {},
  tableData: [],
  pageNum: 1,
  pageSize: 10,
  total: 0,
  className: null,
  textbookName: null,
  ids: [],
  textbookList: [],
  classList: []
})

const addFeedback = () => {
  data.formVisible1 = true
  data.form = {}
}

const saveFeedback = () => {
  request.post('/feedback/add', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible1 = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const changeStatus = (row, status) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.form.status = status
  request.put('/outStock/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const getStudentNumber = () => {
  if (data.form.classId) {
    request.get('/student/selectAll', {
      params: {
        classId: data.form.classId
      }
    }).then(res => {
      data.form.number = res.data?.length   //  res.data 是所有学生的数组
    })
  }
}

const load = () => {
  request.get('/outStock/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      classId: data.user.classId,  // 只对学生角色生效
      className: data.className,
      textbookName: data.textbookName
    }
  }).then(res => {
    if (res.code === '200') {
      data.tableData = res.data?.list || []
      data.total = res.data?.total
    }
  })
}

request.get('/textbook/selectAll').then(res => {
  data.textbookList = res.data
})

request.get('/classInfo/selectAll').then(res => {
  data.classList = res.data
})

const handleAdd = () => {
  data.form = { number: 1 }
  data.formVisible = true
}
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}
const add = () => {
  request.post('/outStock/add', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const update = () => {
  request.put('/outStock/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const save = () => {
  data.form.id ? update() : add()
}

const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗？', '删除确认', { type: 'warning' }).then(res => {
    request.delete('/outStock/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success("删除成功")
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
    console.error(err)
  })
}
const delBatch = () => {
  if (!data.ids.length) {
    ElMessage.warning("请选择数据")
    return
  }
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗？', '删除确认', { type: 'warning' }).then(res => {
    request.delete("/outStock/delete/batch", {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
    console.error(err)
  })
}
const handleSelectionChange = (rows) => {
  data.ids = rows.map(v => v.id)
}

const reset = () => {
  data.className = null
  data.textbookName = null
  load()
}

load()
</script>