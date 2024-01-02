//导航文件
const { Router } = require('express');
var express = require('express');
var conn = require('../model/index')
var routes = express.Router();

// 用户登陆
routes.get('/loadstate',async (req,res)=>{
    let account  = req.query.account

    let sqlstr = `select * from login where account = '${account}'`
    await conn.query(sqlstr,(err,data)=>{
        if(err){
            res.send({code:400,message:'查询失败'})
        }
        console.log(data) 
        res.json(data)
    })

})
//商品展示
routes.post('/tradeShow',async (req,res)=>{
    let sql = 'select * from tradeShow';
    await conn.query(sql,(err,data)=>{
        if(err){
            res.status(400).render('../views/err',{msg:'商品展示错误'})
        }
        res.json(data)  
    }) 
})
//添加购物车
routes.get('/insertById',async (req,res)=>{
    let productId = req.query.id
    let account = req.query.account
    let sql = `select * from tradeInformation where id = ${productId}`
	console.log(account)
    await conn.query(sql,(err,data)=>{
        if(err){
            res.status(400).render('../views/err',{msg:'商品展示错误'})
        }
        let dataproduct = data[0]
        conn.query(`insert into shoopingCar(account,id,name,area,introduce,price) value("${account}","${dataproduct.id}","${dataproduct.name}","${dataproduct.area}",
        "${dataproduct.introduce}","${dataproduct.price}")`)
        res.send(`${dataproduct.name}成功添加购物车`)
        
    }) 
})
// 订单管理
routes.get('/searchproduct',(req,res)=>{
    let selectSql = "select * from tradeInformation"
     conn.query(selectSql,(err,data)=>{
         res.json(data)
     })
})
//查询购物车   
routes.get('/seacherShopCart',(req,res)=>{
    let account = req.query.account;
	console.log(account)
    let selectSql = `select * from shoopingcar where account = '${account}'`
     conn.query(selectSql,(err,data)=>{
         res.json(data)
		 console.log(data)
     })
})
// 商品查询
routes.get('/tradeInformation',(req,res)=>{
     let id = req.query.id
     let selectSql = `select * from tradeInformation where id = ${id}`
     conn.query(selectSql,(err,data)=>{
         res.json(data)
     })
})
module.exports = routes



