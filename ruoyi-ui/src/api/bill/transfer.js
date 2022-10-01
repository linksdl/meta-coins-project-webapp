import request from '@/utils/request'

// 查询转账账单列表
export function listTransfer(query) {
  return request({
    url: '/bill/transfer/list',
    method: 'get',
    params: query
  })
}

// 查询转账账单详细
export function getTransfer(transferId) {
  return request({
    url: '/bill/transfer/' + transferId,
    method: 'get'
  })
}

// 新增转账账单
export function addTransfer(data) {
  return request({
    url: '/bill/transfer',
    method: 'post',
    data: data
  })
}

// 修改转账账单
export function updateTransfer(data) {
  return request({
    url: '/bill/transfer',
    method: 'put',
    data: data
  })
}

// 删除转账账单
export function delTransfer(transferId) {
  return request({
    url: '/bill/transfer/' + transferId,
    method: 'delete'
  })
}

// 获取转账账单下拉框列表
export function selectTransfer() {
  return request({
    url: '/bill/transfer/select',
    method: 'get'
  })
}