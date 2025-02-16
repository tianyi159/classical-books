import request from '@/utils/request'

// 查询阅读记录列表
export function listRecord(query) {
  return request({
    url: '/guji/record/list',
    method: 'get',
    params: query
  })
}

// 查询阅读记录详细
export function getRecord(recordId) {
  return request({
    url: '/guji/record/' + recordId,
    method: 'get'
  })
}

// 新增阅读记录
export function addRecord(data) {
  return request({
    url: '/guji/record',
    method: 'post',
    data: data
  })
}

// 修改阅读记录
export function updateRecord(data) {
  return request({
    url: '/guji/record',
    method: 'put',
    data: data
  })
}

// 删除阅读记录
export function delRecord(recordId) {
  return request({
    url: '/guji/record/' + recordId,
    method: 'delete'
  })
}
