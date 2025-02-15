import request from '@/utils/request'

// 查询章节页面列表
export function listPage(query) {
  return request({
    url: '/book/page/list',
    method: 'get',
    params: query
  })
}

// 查询章节页面详细
export function getPage(pageId) {
  return request({
    url: '/book/page/' + pageId,
    method: 'get'
  })
}

// 新增章节页面
export function addPage(data) {
  return request({
    url: '/book/page',
    method: 'post',
    data: data
  })
}

// 修改章节页面
export function updatePage(data) {
  return request({
    url: '/book/page',
    method: 'put',
    data: data
  })
}

// 删除章节页面
export function delPage(pageId) {
  return request({
    url: '/book/page/' + pageId,
    method: 'delete'
  })
}
