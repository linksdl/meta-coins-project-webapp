import request from '@/utils/request'

// 查询支出商品列表
export function listConsumeGoods(query) {
  return request({
    url: '/bill/consumeGoods/list',
    method: 'get',
    params: query
  })
}

// 查询支出商品详细
export function getConsumeGoods(consumeGoodsId) {
  return request({
    url: '/bill/consumeGoods/' + consumeGoodsId,
    method: 'get'
  })
}

// 新增支出商品
export function addConsumeGoods(data) {
  return request({
    url: '/bill/consumeGoods',
    method: 'post',
    data: data
  })
}

// 修改支出商品
export function updateConsumeGoods(data) {
  return request({
    url: '/bill/consumeGoods',
    method: 'put',
    data: data
  })
}

// 删除支出商品
export function delConsumeGoods(consumeGoodsId) {
  return request({
    url: '/bill/consumeGoods/' + consumeGoodsId,
    method: 'delete'
  })
}

// 获取支出商品下拉框列表
export function selectConsumeGoods() {
  return request({
    url: '/bill/consumeGoods/select',
    method: 'get'
  })
}