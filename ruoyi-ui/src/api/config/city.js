import request from '@/utils/request'

// 查询城市列表
export function listCity(query) {
  return request({
    url: '/config/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市详细
export function getCity(cityId) {
  return request({
    url: '/config/city/' + cityId,
    method: 'get'
  })
}

// 新增城市
export function addCity(data) {
  return request({
    url: '/config/city',
    method: 'post',
    data: data
  })
}

// 修改城市
export function updateCity(data) {
  return request({
    url: '/config/city',
    method: 'put',
    data: data
  })
}

// 删除城市
export function delCity(cityId) {
  return request({
    url: '/config/city/' + cityId,
    method: 'delete'
  })
}

// 获取城市下拉框列表
export function selectCity(query) {
  return request({
    url: '/config/city/select',
    method: 'get',
    params: query
  })
}
