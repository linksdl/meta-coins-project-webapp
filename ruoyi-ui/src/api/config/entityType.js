import request from '@/utils/request'

// 查询商家类型列表
export function listEntityType(query) {
  return request({
    url: '/config/entityType/list',
    method: 'get',
    params: query
  })
}

// 查询商家类型详细
export function getEntityType(entityTypeId) {
  return request({
    url: '/config/entityType/' + entityTypeId,
    method: 'get'
  })
}

// 新增商家类型
export function addEntityType(data) {
  return request({
    url: '/config/entityType',
    method: 'post',
    data: data
  })
}

// 修改商家类型
export function updateEntityType(data) {
  return request({
    url: '/config/entityType',
    method: 'put',
    data: data
  })
}

// 删除商家类型
export function delEntityType(entityTypeId) {
  return request({
    url: '/config/entityType/' + entityTypeId,
    method: 'delete'
  })
}

// 获取商家类型下拉框列表
export function selectEntityType(query) {
  return request({
    url: '/config/entityType/select',
    method: 'get',
    params: query
  })
}
