import request from '@/utils/request'

// 查询心情管理列表
export function listEmotion(query) {
  return request({
    url: '/config/emotion/list',
    method: 'get',
    params: query
  })
}

// 查询心情管理详细
export function getEmotion(emotionId) {
  return request({
    url: '/config/emotion/' + emotionId,
    method: 'get'
  })
}

// 新增心情管理
export function addEmotion(data) {
  return request({
    url: '/config/emotion',
    method: 'post',
    data: data
  })
}

// 修改心情管理
export function updateEmotion(data) {
  return request({
    url: '/config/emotion',
    method: 'put',
    data: data
  })
}

// 删除心情管理
export function delEmotion(emotionId) {
  return request({
    url: '/config/emotion/' + emotionId,
    method: 'delete'
  })
}

// 获取心情管理下拉框列表
export function selectEmotion() {
  return request({
    url: '/config/emotion/select',
    method: 'get'
  })
}