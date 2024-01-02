var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var path = require('path')
// 引入路由导航
var routes = require('./route/routes')

//解决跨域
var cors = require('cors')


//静态资源文件
var pathstatic = path.join(__dirname,'public')
var pathView = path.join(__dirname,'views')
app.use(express.static(pathstatic))
//解决跨域问题
app.use(cors())
app.use(express.json())
app.use(bodyParser.urlencoded({extended:false}))
app.set('views',pathView);
app.set('view engine','art')
app.engine('art',require('express-art-template'))

// 创建服务连接
var hostName = '127.0.0.1';
app.listen(8080,hostName,(err)=>{
    if(err){
        console.log(err)
        return
    }else{
        console.log("---------服务器启动成功----------");
    }

})

//外部文件
app.use('/api',routes)

