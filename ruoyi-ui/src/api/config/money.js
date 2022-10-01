import request from '@/utils/request'

// 查询币种管理列表
export function listMoney(query) {
  return request({
    url: '/config/money/list',
    method: 'get',
    params: query
  })
}

// 查询币种管理详细
export function getMoney(moneyId) {
  return request({
    url: '/config/money/' + moneyId,
    method: 'get'
  })
}

// 新增币种管理
export function addMoney(data) {
  return request({
    url: '/config/money',
    method: 'post',
    data: data
  })
}

// 修改币种管理
export function updateMoney(data) {
  return request({
    url: '/config/money',
    method: 'put',
    data: data
  })
}

// 删除币种管理
export function delMoney(moneyId) {
  return request({
    url: '/config/money/' + moneyId,
    method: 'delete'
  })
}

// 获取币种管理下拉框列表
export function selectMoney(query) {
  return request({
    url: '/config/money/select',
    method: 'get',
    params: query
  })
}
