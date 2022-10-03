import request from '@/utils/request'

// 查询商家管理列表
export function listEntity(query) {
  return request({
    url: '/config/entity/list',
    method: 'get',
    params: query
  })
}

// 查询商家管理详细
export function getEntity(entityId) {
  return request({
    url: '/config/entity/' + entityId,
    method: 'get'
  })
}

// 新增商家管理
export function addEntity(data) {
  return request({
    url: '/config/entity',
    method: 'post',
    data: data
  })
}

// 修改商家管理
export function updateEntity(data) {
  return request({
    url: '/config/entity',
    method: 'put',
    data: data
  })
}

// 删除商家管理
export function delEntity(entityId) {
  return request({
    url: '/config/entity/' + entityId,
    method: 'delete'
  })
}

// 获取商家管理下拉框列表
export function selectEntity(query) {
  return request({
    url: '/config/entity/select',
    method: 'get',
    params: query
  })
}
