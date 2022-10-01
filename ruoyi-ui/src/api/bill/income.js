import request from '@/utils/request'

// 查询收入账单列表
export function listIncome(query) {
  return request({
    url: '/bill/income/list',
    method: 'get',
    params: query
  })
}

// 查询收入账单详细
export function getIncome(incomeId) {
  return request({
    url: '/bill/income/' + incomeId,
    method: 'get'
  })
}

// 新增收入账单
export function addIncome(data) {
  return request({
    url: '/bill/income',
    method: 'post',
    data: data
  })
}

// 修改收入账单
export function updateIncome(data) {
  return request({
    url: '/bill/income',
    method: 'put',
    data: data
  })
}

// 删除收入账单
export function delIncome(incomeId) {
  return request({
    url: '/bill/income/' + incomeId,
    method: 'delete'
  })
}

// 获取收入账单下拉框列表
export function selectIncome() {
  return request({
    url: '/bill/income/select',
    method: 'get'
  })
}