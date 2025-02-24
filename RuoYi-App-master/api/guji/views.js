import request from '@/utils/request'

// 查询古籍浏览列表
export function listViews(query) {
  return request({
    url: '/guji/views/list',
    method: 'get',
    params: query
  })
}

// 查询古籍浏览详细
export function getViews(id) {
  return request({
    url: '/guji/views/' + id,
    method: 'get'
  })
}

// 新增古籍浏览
export function addViews(data) {
  return request({
    url: '/guji/views',
    method: 'post',
    data: data
  })
}

// 新增古籍浏览
export function ph() {
  return request({
    url: '/guji/views/paihang',
    method: 'get'
  })
}

// 修改古籍浏览
export function updateViews(data) {
  return request({
    url: '/guji/views',
    method: 'put',
    data: data
  })
}

// 删除古籍浏览
export function delViews(id) {
  return request({
    url: '/guji/views/' + id,
    method: 'delete'
  })
}
