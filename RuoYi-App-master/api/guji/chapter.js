import request from '@/utils/request'

// 查询古籍章节列表
export function listChapter(query) {
  return request({
    url: '/book/chapter/list',
    method: 'get',
    params: query
  })
}
export function listChapters() {
  return request({
    url: '/book/chapter/lists',
    method: 'get'
  })
}
// 查询古籍章节详细
export function getChapter(chapterId) {
  return request({
    url: '/book/chapter/' + chapterId,
    method: 'get'
  })
}

// 新增古籍章节
export function addChapter(data) {
  return request({
    url: '/book/chapter',
    method: 'post',
    data: data
  })
}

// 修改古籍章节
export function updateChapter(data) {
  return request({
    url: '/book/chapter',
    method: 'put',
    data: data
  })
}

// 删除古籍章节
export function delChapter(chapterId) {
  return request({
    url: '/book/chapter/' + chapterId,
    method: 'delete'
  })
}
