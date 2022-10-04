import request from '@/utils/request'

// 查询商品类型列表
export function listGoodsType(query) {
  return request({
    url: '/config/goodsType/list',
    method: 'get',
    params: query
  })
}

// 查询商品类型详细
export function getGoodsType(goodsTypeId) {
  return request({
    url: '/config/goodsType/' + goodsTypeId,
    method: 'get'
  })
}

// 新增商品类型
export function addGoodsType(data) {
  return request({
    url: '/config/goodsType',
    method: 'post',
    data: data
  })
}

// 修改商品类型
export function updateGoodsType(data) {
  return request({
    url: '/config/goodsType',
    method: 'put',
    data: data
  })
}

// 删除商品类型
export function delGoodsType(goodsTypeId) {
  return request({
    url: '/config/goodsType/' + goodsTypeId,
    method: 'delete'
  })
}

// 获取商品类型下拉框列表
export function selectGoodsType() {
  return request({
    url: '/config/goodsType/select',
    method: 'get'
  })
}