import request from '@/utils/request'

// 查询商品管理列表
export function listGoods(query) {
  return request({
    url: '/config/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品管理详细
export function getGoods(goodsId) {
  return request({
    url: '/config/goods/' + goodsId,
    method: 'get'
  })
}

// 新增商品管理
export function addGoods(data) {
  return request({
    url: '/config/goods',
    method: 'post',
    data: data
  })
}

// 修改商品管理
export function updateGoods(data) {
  return request({
    url: '/config/goods',
    method: 'put',
    data: data
  })
}

// 删除商品管理
export function delGoods(goodsId) {
  return request({
    url: '/config/goods/' + goodsId,
    method: 'delete'
  })
}

// 获取商品管理下拉框列表
export function selectGoods() {
  return request({
    url: '/config/goods/select',
    method: 'get'
  })
}