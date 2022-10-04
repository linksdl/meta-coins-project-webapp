import request from '@/utils/request'

// 查询项目管理列表
export function listProject(query) {
  return request({
    url: '/config/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目管理详细
export function getProject(projectId) {
  return request({
    url: '/config/project/' + projectId,
    method: 'get'
  })
}

// 新增项目管理
export function addProject(data) {
  return request({
    url: '/config/project',
    method: 'post',
    data: data
  })
}

// 修改项目管理
export function updateProject(data) {
  return request({
    url: '/config/project',
    method: 'put',
    data: data
  })
}

// 删除项目管理
export function delProject(projectId) {
  return request({
    url: '/config/project/' + projectId,
    method: 'delete'
  })
}

// 获取项目管理下拉框列表
export function selectProject() {
  return request({
    url: '/config/project/select',
    method: 'get'
  })
}