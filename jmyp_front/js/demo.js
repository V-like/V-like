let  data  =  Mock.mock({
    'list|5':  [{
        'id':  1,
        'name': '测试',
        'phone|11': '1'
    }]
})
// 输出结果
console.log(JSON.stringify(data, null, 2))