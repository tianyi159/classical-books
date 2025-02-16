import request from '@/utils/request'

// 查询古籍类型分类列表
export function listCategory(query) {
  return request({
    url: '/guji/category/list',
    method: 'get',
    params: query
  })
}

// 查询古籍类型分类详细
export function getCategory(categoryId) {
  return request({
    url: '/guji/category/' + categoryId,
    method: 'get'
  })
}

// 新增古籍类型分类
export function addCategory(data) {
  return request({
    url: '/guji/category',
    method: 'post',
    data: data
  })
}

// 修改古籍类型分类
export function updateCategory(data) {
  return request({
    url: '/guji/category',
    method: 'put',
    data: data
  })
}

// 删除古籍类型分类
export function delCategory(categoryId) {
  return request({
    url: '/guji/category/' + categoryId,
    method: 'delete'
  })
}
