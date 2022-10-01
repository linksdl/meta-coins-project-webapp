import request from '@/utils/request'

// 查询成员类型列表
export function listMemberType(query) {
  return request({
    url: '/config/memberType/list',
    method: 'get',
    params: query
  })
}

// 查询成员类型详细
export function getMemberType(memberTypeId) {
  return request({
    url: '/config/memberType/' + memberTypeId,
    method: 'get'
  })
}

// 新增成员类型
export function addMemberType(data) {
  return request({
    url: '/config/memberType',
    method: 'post',
    data: data
  })
}

// 修改成员类型
export function updateMemberType(data) {
  return request({
    url: '/config/memberType',
    method: 'put',
    data: data
  })
}

// 删除成员类型
export function delMemberType(memberTypeId) {
  return request({
    url: '/config/memberType/' + memberTypeId,
    method: 'delete'
  })
}

// 获取成员类型下拉框列表
export function selectMemberType(query) {
  return request({
    url: '/config/memberType/select',
    method: 'get',
    params: query
  })
}
