import request from '@/utils/request'

// 查询账本类型列表
export function listType(query) {
  return request({
    url: '/config/type/list',
    method: 'get',
    params: query
  })
}

// 查询账本类型详细
export function getType(bookTypeId) {
  return request({
    url: '/config/type/' + bookTypeId,
    method: 'get'
  })
}

// 新增账本类型
export function addType(data) {
  return request({
    url: '/config/type',
    method: 'post',
    data: data
  })
}

// 修改账本类型
export function updateType(data) {
  return request({
    url: '/config/type',
    method: 'put',
    data: data
  })
}

// 删除账本类型
export function delType(bookTypeId) {
  return request({
    url: '/config/type/' + bookTypeId,
    method: 'delete'
  })
}

// 获取账本类型选择框列表
export function selectBookType() {
  return request({
    url: '/config/type/optionselect',
    method: 'get'
  })
}
