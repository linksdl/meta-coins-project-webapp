import request from '@/utils/request'

// 查询ruoyi-config列表
export function listBook(query) {
  return request({
    url: '/config/book/list',
    method: 'get',
    params: query
  })
}

// 查询ruoyi-config详细
export function getBook(bookId) {
  return request({
    url: '/config/book/' + bookId,
    method: 'get'
  })
}

// 新增ruoyi-config
export function addBook(data) {
  return request({
    url: '/config/book',
    method: 'post',
    data: data
  })
}

// 修改ruoyi-config
export function updateBook(data) {
  return request({
    url: '/config/book',
    method: 'put',
    data: data
  })
}

// 删除ruoyi-config
export function delBook(bookId) {
  return request({
    url: '/config/book/' + bookId,
    method: 'delete'
  })
}
