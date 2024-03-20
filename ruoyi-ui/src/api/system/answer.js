import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAnswer(query) {
  return request({
    url: '/xljk/answer/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAnswer(id) {
  return request({
    url: '/xljk/answer/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAnswer(data) {
  return request({
    url: '/xljk/answer',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAnswer(data) {
  return request({
    url: '/xljk/answer',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAnswer(id) {
  return request({
    url: '/xljk/answer/' + id,
    method: 'delete'
  })
}
