//定义组件，抽取顶部导航组件
Vue.component('TopNav',{
    //模板
    template:`<div class="topnav">
    <div class="topnav_bd w1210 bc">
        <div class="topnav_left">

        </div>
        <div class="topnav_right fr">
            <ul>
                <li>您好，{{name}},欢迎来到聚美优品！<span v-if="name==null">[<a href="login.html">登录</a>] </span> <span v-if="name!=null"> [<a href="#" @click.prevent="logout">退出</a>] </span> [<a href="register.html">免费注册</a>]</li>
                <li class="line">|</li>
                <li>我的订单</li>
                <li class="line">|</li>
                <li>客户服务</li>

            </ul>
        </div>
    </div>
</div>`,
data:function(){
    return {
        name : localStorage.getItem("name")
    }
},
methods:{
    logout:function(){
        //清空localStorage
        localStorage.removeItem("token");
        localStorage.removeItem("name");
        //跳转到登录页面
        location.href="login.html";
    }
}

})