import request from '@/utils/request'

// 查询账户管理列表
export function listAccount(query) {
  return request({
    url: '/config/account/list',
    method: 'get',
    params: query
  })
}

// 查询账户管理详细
export function getAccount(accountId) {
  return request({
    url: '/config/account/' + accountId,
    method: 'get'
  })
}

// 新增账户管理
export function addAccount(data) {
  return request({
    url: '/config/account',
    method: 'post',
    data: data
  })
}

// 修改账户管理
export function updateAccount(data) {
  return request({
    url: '/config/account',
    method: 'put',
    data: data
  })
}

// 删除账户管理
export function delAccount(accountId) {
  return request({
    url: '/config/account/' + accountId,
    method: 'delete'
  })
}

// 获取账户管理下拉框列表
export function selectAccount(query) {
  return request({
    url: '/config/account/select',
    method: 'get',
    params: query
  })
}
