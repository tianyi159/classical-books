import request from '@/utils/request'

// 查询书签列表
export function listBookmarks(query) {
  return request({
    url: '/guji/bookmarks/list',
    method: 'get',
    params: query
  })
}
export function myMarks(query) {
  return request({
    url: '/guji/bookmarks/myMarks',
    method: 'get',
    params: query
  })
}

// 查询书签详细
export function getBookmarks(bookmarkId) {
  return request({
    url: '/guji/bookmarks/' + bookmarkId,
    method: 'get'
  })
}

// 新增书签
export function addBookmarks(data) {
  return request({
    url: '/guji/bookmarks',
    method: 'post',
    data: data
  })
}

// 修改书签
export function updateBookmarks(data) {
  return request({
    url: '/guji/bookmarks',
    method: 'put',
    data: data
  })
}

// 删除书签
export function delBookmarks(bookmarkId) {
  return request({
    url: '/guji/bookmarks/' + bookmarkId,
    method: 'delete'
  })
}
