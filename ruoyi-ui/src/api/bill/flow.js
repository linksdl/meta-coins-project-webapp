import request from '@/utils/request'

// 查询账单流水列表
export function listFlow(query) {
  return request({
    url: '/bill/flow/list',
    method: 'get',
    params: query
  })
}

// 查询账单流水详细
export function getFlow(flowId) {
  return request({
    url: '/bill/flow/' + flowId,
    method: 'get'
  })
}

// 新增账单流水
export function addFlow(data) {
  return request({
    url: '/bill/flow',
    method: 'post',
    data: data
  })
}

// 修改账单流水
export function updateFlow(data) {
  return request({
    url: '/bill/flow',
    method: 'put',
    data: data
  })
}

// 删除账单流水
export function delFlow(flowId) {
  return request({
    url: '/bill/flow/' + flowId,
    method: 'delete'
  })
}

// 获取账单流水下拉框列表
export function selectFlow() {
  return request({
    url: '/bill/flow/select',
    method: 'get'
  })
}