import request from '@/utils/request'

// 查询个人账本列表
export function listBook(query) {
  return request({
    url: '/config/book/list',
    method: 'get',
    params: query
  })
}

// 查询个人账本详细
export function getBook(bookId) {
  return request({
    url: '/config/book/' + bookId,
    method: 'get'
  })
}

// 新增个人账本
export function addBook(data) {
  return request({
    url: '/config/book',
    method: 'post',
    data: data
  })
}

// 修改个人账本
export function updateBook(data) {
  return request({
    url: '/config/book',
    method: 'put',
    data: data
  })
}

// 删除个人账本
export function delBook(bookId) {
  return request({
    url: '/config/book/' + bookId,
    method: 'delete'
  })
}


// 获取个人账本下拉框列表
export function selectBook(query) {
  return request({
    url: '/config/book/select',
    method: 'get',
    params: query
  })
}
