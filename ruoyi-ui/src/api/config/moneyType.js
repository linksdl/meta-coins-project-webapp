import request from '@/utils/request'

// 查询币种类型列表
export function listMoneyType(query) {
  return request({
    url: '/config/moneyType/list',
    method: 'get',
    params: query
  })
}

// 查询币种类型详细
export function getMoneyType(moneyTypeId) {
  return request({
    url: '/config/moneyType/' + moneyTypeId,
    method: 'get'
  })
}

// 新增币种类型
export function addMoneyType(data) {
  return request({
    url: '/config/moneyType',
    method: 'post',
    data: data
  })
}

// 修改币种类型
export function updateMoneyType(data) {
  return request({
    url: '/config/moneyType',
    method: 'put',
    data: data
  })
}

// 删除币种类型
export function delMoneyType(moneyTypeId) {
  return request({
    url: '/config/moneyType/' + moneyTypeId,
    method: 'delete'
  })
}

// 获取币种类型下拉框列表
export function selectMoneyType(query) {
  return request({
    url: '/config/moneyType/select',
    method: 'get',
    params: query
  })
}
