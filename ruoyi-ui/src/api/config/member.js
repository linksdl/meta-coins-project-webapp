import request from '@/utils/request'

// 查询成员管理列表
export function listMember(query) {
  return request({
    url: '/config/member/list',
    method: 'get',
    params: query
  })
}

// 查询成员管理详细
export function getMember(memberId) {
  return request({
    url: '/config/member/' + memberId,
    method: 'get'
  })
}

// 新增成员管理
export function addMember(data) {
  return request({
    url: '/config/member',
    method: 'post',
    data: data
  })
}

// 修改成员管理
export function updateMember(data) {
  return request({
    url: '/config/member',
    method: 'put',
    data: data
  })
}

// 删除成员管理
export function delMember(memberId) {
  return request({
    url: '/config/member/' + memberId,
    method: 'delete'
  })
}

// 获取成员管理下拉框列表
export function selectMember(query) {
  return request({
    url: '/config/member/select',
    method: 'get',
    params: query
  })
}
