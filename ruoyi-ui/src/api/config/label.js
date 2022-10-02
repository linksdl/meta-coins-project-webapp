import request from '@/utils/request'

// 查询标签管理列表
export function listLabel(query) {
  return request({
    url: '/config/label/list',
    method: 'get',
    params: query
  })
}

// 查询标签管理详细
export function getLabel(labelId) {
  return request({
    url: '/config/label/' + labelId,
    method: 'get'
  })
}

// 新增标签管理
export function addLabel(data) {
  return request({
    url: '/config/label',
    method: 'post',
    data: data
  })
}

// 修改标签管理
export function updateLabel(data) {
  return request({
    url: '/config/label',
    method: 'put',
    data: data
  })
}

// 删除标签管理
export function delLabel(labelId) {
  return request({
    url: '/config/label/' + labelId,
    method: 'delete'
  })
}

// 获取标签管理下拉框列表
export function selectLabel() {
  return request({
    url: '/config/label/select',
    method: 'get'
  })
}