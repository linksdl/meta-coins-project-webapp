import request from '@/utils/request'

// 查询商家类型列表
export function listEnterpriseType(query) {
  return request({
    url: '/config/enterpriseType/list',
    method: 'get',
    params: query
  })
}

// 查询商家类型详细
export function getEnterpriseType(enterpriseTypeId) {
  return request({
    url: '/config/enterpriseType/' + enterpriseTypeId,
    method: 'get'
  })
}

// 新增商家类型
export function addEnterpriseType(data) {
  return request({
    url: '/config/enterpriseType',
    method: 'post',
    data: data
  })
}

// 修改商家类型
export function updateEnterpriseType(data) {
  return request({
    url: '/config/enterpriseType',
    method: 'put',
    data: data
  })
}

// 删除商家类型
export function delEnterpriseType(enterpriseTypeId) {
  return request({
    url: '/config/enterpriseType/' + enterpriseTypeId,
    method: 'delete'
  })
}

// 获取商家类型下拉框列表
export function selectEnterpriseType(query) {
  return request({
    url: '/config/enterpriseType/select',
    method: 'get',
    params: query
  })
}
