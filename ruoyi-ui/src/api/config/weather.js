import request from '@/utils/request'

// 查询天气管理列表
export function listWeather(query) {
  return request({
    url: '/config/weather/list',
    method: 'get',
    params: query
  })
}

// 查询天气管理详细
export function getWeather(weatherId) {
  return request({
    url: '/config/weather/' + weatherId,
    method: 'get'
  })
}

// 新增天气管理
export function addWeather(data) {
  return request({
    url: '/config/weather',
    method: 'post',
    data: data
  })
}

// 修改天气管理
export function updateWeather(data) {
  return request({
    url: '/config/weather',
    method: 'put',
    data: data
  })
}

// 删除天气管理
export function delWeather(weatherId) {
  return request({
    url: '/config/weather/' + weatherId,
    method: 'delete'
  })
}

// 获取天气管理下拉框列表
export function selectWeather(query) {
  return request({
    url: '/config/weather/select',
    method: 'get',
    params: query
  })
}
