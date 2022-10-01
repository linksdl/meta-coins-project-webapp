import request from '@/utils/request'

// 查询借贷账单列表
export function listDebt(query) {
  return request({
    url: '/bill/debt/list',
    method: 'get',
    params: query
  })
}

// 查询借贷账单详细
export function getDebt(debtId) {
  return request({
    url: '/bill/debt/' + debtId,
    method: 'get'
  })
}

// 新增借贷账单
export function addDebt(data) {
  return request({
    url: '/bill/debt',
    method: 'post',
    data: data
  })
}

// 修改借贷账单
export function updateDebt(data) {
  return request({
    url: '/bill/debt',
    method: 'put',
    data: data
  })
}

// 删除借贷账单
export function delDebt(debtId) {
  return request({
    url: '/bill/debt/' + debtId,
    method: 'delete'
  })
}

// 获取借贷账单下拉框列表
export function selectDebt() {
  return request({
    url: '/bill/debt/select',
    method: 'get'
  })
}