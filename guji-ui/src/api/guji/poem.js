import request from '@/utils/request'

// 查询古诗列表
export function listPoem(query) {
  return request({
    url: '/book/poem/list',
    method: 'get',
    params: query
  })
}

// 查询古诗详细
export function getPoem(ariId) {
  return request({
    url: '/book/poem/' + ariId,
    method: 'get'
  })
}

// 新增古诗
export function addPoem(data) {
  return request({
    url: '/book/poem',
    method: 'post',
    data: data
  })
}

// 修改古诗
export function updatePoem(data) {
  return request({
    url: '/book/poem',
    method: 'put',
    data: data
  })
}

// 删除古诗
export function delPoem(ariId) {
  return request({
    url: '/book/poem/' + ariId,
    method: 'delete'
  })
}
