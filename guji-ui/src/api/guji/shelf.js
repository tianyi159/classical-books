import request from '@/utils/request'

// 查询收藏列表
export function listShelf(query) {
  return request({
    url: '/book/shelf/list',
    method: 'get',
    params: query
  })
}

// 查询收藏详细
export function getShelf(shelfId) {
  return request({
    url: '/book/shelf/' + shelfId,
    method: 'get'
  })
}

// 新增收藏
export function addShelf(data) {
  return request({
    url: '/book/shelf',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateShelf(data) {
  return request({
    url: '/book/shelf',
    method: 'put',
    data: data
  })
}

// 删除收藏
export function delShelf(shelfId) {
  return request({
    url: '/book/shelf/' + shelfId,
    method: 'delete'
  })
}
