import request from '@/utils/request'

// 查询账本类型列表
export function listBookType(query) {
  return request({
    url: '/config/bookType/list',
    method: 'get',
    params: query
  })
}

// 查询账本类型详细
export function getBookType(bookTypeId) {
  return request({
    url: '/config/bookType/' + bookTypeId,
    method: 'get'
  })
}

// 新增账本类型
export function addBookType(data) {
  return request({
    url: '/config/bookType',
    method: 'post',
    data: data
  })
}

// 修改账本类型
export function updateBookType(data) {
  return request({
    url: '/config/bookType',
    method: 'put',
    data: data
  })
}

// 删除账本类型
export function delBookType(bookTypeId) {
  return request({
    url: '/config/bookType/' + bookTypeId,
    method: 'delete'
  })
}

// 获取账本类型下拉框列表
export function selectBookType() {
  return request({
    url: '/config/bookType/select',
    method: 'get'
  })
}