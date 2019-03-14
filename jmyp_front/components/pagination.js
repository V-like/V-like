Vue.component("pagination", {
    //定义属性，父组件向子组件传递值
    props: ['total_page', 'page'],
    //抽取模板
    template: `<div class="page mt20">
    <a href="" @click.prevent="pageClick(1)">首页</a>
    <a href="" @click.prevent="pageClick(--page)">上一页</a>
    <a  v-for="p in pageRange" href="" @click.prevent="pageClick(p)" :class="{cur:p==page}">{{p}}</a>
    <a href="" @click.prevent="pageClick(++page)">下一页</a>
    <a href="" @click.prevent="pageClick(total_page)">尾页</a>&nbsp;&nbsp;
    <span>
        <em>共{{total_page}}页&nbsp;&nbsp;到第 <input type="text" class="page_num" v-model="cur_page"/> 页</em>
        <a href="" class="skipsearch" href="javascript:;" @click.prevent="pageClick(cur_page)">确定</a>
    </span>
    </div>`,
    data: function () {
        return {
            cur_page:1
        }
    },
    methods: {

        //实现分页方法
        pageClick: function (p) {

            p = parseInt(p);

            //判断当前页码是否小于0
            if (p < 1) {
                //从新赋值
                this.cur_page = 1;
                return;
            }
            //this.page = p;
            //把当前页码参数传递到searchMap
            this.cur_page = p;

            //声明一个自定义方法
            this.$emit("page_changed",this.cur_page);

        }

    },
    computed: {
        //定义计算属性方法就是定义一个函数
			pageRange:function(){
				//定义数组存储页面
				var pages = [];
				//获取动态开始页码
				var first_page = Math.max(this.page-5,1);
				//获取动态结束页码
				var last_page = Math.min(this.page+4,this.total_page);


				//点击1： first_page=1  last_page = 5 显示 前5页
				//点击2： first_page=1  last_page = 6 显示 前6页
				//点击3： first_page=1  last_page = 7 显示 前7页

				//点击7： first_page=2  last_page=11

				//循环页码范围
				for(var i=first_page; i<=last_page;i++){
					//把动员页码数放入数组
					pages.push(i);
				}

				return pages;

			}
    }


})