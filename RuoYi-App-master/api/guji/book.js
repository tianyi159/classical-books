import request from '@/utils/request'

// 查询古籍列表
export function listBook(query) {
  return request({
    url: '/guji/book/list',
    method: 'get',
    params: query
  })
}
export function listBooks(data) {
  return request({
    url: '/guji/book/lists',
    method: 'get',
    data: data
  })
}
// 查询古籍详细
export function getBook(bookId) {
  return request({
    url: '/guji/book/' + bookId,
    method: 'get'
  })
}

// 新增古籍
export function addBook(data) {
  return request({
    url: '/guji/book',
    method: 'post',
    data: data
  })
}

// 修改古籍
export function updateBook(data) {
  return request({
    url: '/guji/book',
    method: 'put',
    data: data
  })
}

// 删除古籍
export function delBook(bookId) {
  return request({
    url: '/guji/book/' + bookId,
    method: 'delete'
  })
}
