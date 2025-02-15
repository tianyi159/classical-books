import upload from '@/utils/upload'
import request from '@/utils/request'
import {data} from "../../uni_modules/uview-ui/libs/mixin/mixin";
// 查询古籍列表
export function listPages(query) {
    return request({
        url: '/book/page/lists',
        method: 'get',
        data: query,
        headers: {
            isToken: true
        },
    })
}

export function listChapters(data) {
    return request({
        url: '/book/chapter/lists',
        method: 'get',
        data:data
    })
}
export function listBook(query) {
    return request({
        url: '/guji/book/list',
        method: 'get',
        params: query,
        headers: {
            isToken: true
        },
    })
}
export function listBooks(data) {
    return request({
        url: '/guji/book/lists',
        method: 'get',
        data: data,
        headers: {
            isToken: true
        },
    })
}
// 查询古籍详细
export function getBook(bookId) {
    return request({
        url: '/guji/book/' + bookId,
        method: 'get',
        headers: {
            isToken: true
        },
    })
}

// 新增古籍
export function addBook(data) {
    return request({
        url: '/guji/book',
        method: 'post',
        data: data,
        headers: {
            isToken: true
        },
    })
}

// 修改古籍
export function updateBook(data) {
    return request({
        url: '/guji/book',
        method: 'put',
        data: data,
        headers: {
            isToken: true
        },
    })
}

// 删除古籍
export function delBook(bookId) {
    return request({
        url: '/guji/book/' + bookId,
        method: 'delete',
        headers: {
            isToken: true
        },
    })
}

//
export function dictType(dictType) {
    return request({
        url: '/system/dict/data/type/' + dictType,
        method: 'get',
        headers: {
            isToken: true
        },
    })
}

//查询诗集列表
export function ancientPoemList(data) {
    return request({
        url: '/book/poem/lists',
        method: 'get',
        data:data,
        headers: {
            isToken: true
        },
    })
}
//查询诗集列表
export function ancientPoem(data) {
    return request({
        url: '/book/poem/'+data,
        method: 'get',
        headers: {
            isToken: true
        },
    })
}
export function shelfList(data) {
    return request({
        url: '/book/shelf/lists',
        method: 'get',
        headers: {
            isToken: true
        },
        data:data
    })
}
// 新增收藏
export function addShelf(data) {
    return request({
        url: '/book/shelf',
        method: 'post',
        data: data,
        headers: {
            isToken: true
        },
    })
}
//取消收藏
export function deleteShelf(shelfId) {
    return request({
        url: '/book/shelf/'+shelfId,
        method: 'delete',
        headers: {
            isToken: true
        },
    })
}
