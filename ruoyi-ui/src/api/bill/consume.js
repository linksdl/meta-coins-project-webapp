import request from '@/utils/request'

// 查询支出账单列表
export function listConsume(query) {
  return request({
    url: '/bill/consume/list',
    method: 'get',
    params: query
  })
}

// 查询支出账单详细
export function getConsume(consumeId) {
  return request({
    url: '/bill/consume/' + consumeId,
    method: 'get'
  })
}

// 新增支出账单
export function addConsume(data) {
  return request({
    url: '/bill/consume',
    method: 'post',
    data: data
  })
}

// 修改支出账单
export function updateConsume(data) {
  return request({
    url: '/bill/consume',
    method: 'put',
    data: data
  })
}

// 删除支出账单
export function delConsume(consumeId) {
  return request({
    url: '/bill/consume/' + consumeId,
    method: 'delete'
  })
}

// 获取支出账单下拉框列表
export function selectConsume() {
  return request({
    url: '/bill/consume/select',
    method: 'get'
  })
}