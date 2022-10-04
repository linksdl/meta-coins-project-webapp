import request from '@/utils/request'

// 查询商家管理列表
export function listEnterprise(query) {
  return request({
    url: '/config/enterprise/list',
    method: 'get',
    params: query
  })
}

// 查询商家管理详细
export function getEnterprise(enterpriseId) {
  return request({
    url: '/config/enterprise/' + enterpriseId,
    method: 'get'
  })
}

// 新增商家管理
export function addEnterprise(data) {
  return request({
    url: '/config/enterprise',
    method: 'post',
    data: data
  })
}

// 修改商家管理
export function updateEnterprise(data) {
  return request({
    url: '/config/enterprise',
    method: 'put',
    data: data
  })
}

// 删除商家管理
export function delEnterprise(enterpriseId) {
  return request({
    url: '/config/enterprise/' + enterpriseId,
    method: 'delete'
  })
}

// 获取商家管理下拉框列表
export function selectEnterprise() {
  return request({
    url: '/config/enterprise/select',
    method: 'get'
  })
}