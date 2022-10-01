import request from '@/utils/request'

// 查询分类管理列表
export function listCategory(query) {
  return request({
    url: '/config/category/list',
    method: 'get',
    params: query
  })
}

// 查询分类管理详细
export function getCategory(categoryId) {
  return request({
    url: '/config/category/' + categoryId,
    method: 'get'
  })
}

// 新增分类管理
export function addCategory(data) {
  return request({
    url: '/config/category',
    method: 'post',
    data: data
  })
}

// 修改分类管理
export function updateCategory(data) {
  return request({
    url: '/config/category',
    method: 'put',
    data: data
  })
}

// 删除分类管理
export function delCategory(categoryId) {
  return request({
    url: '/config/category/' + categoryId,
    method: 'delete'
  })
}

// 获取分类管理下拉框列表
export function selectCategory(query) {
  return request({
    url: '/config/category/select',
    method: 'get',
    params: query
  })
}
