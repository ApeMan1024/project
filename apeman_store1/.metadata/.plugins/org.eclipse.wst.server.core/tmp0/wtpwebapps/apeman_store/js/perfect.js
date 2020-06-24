function ajax(url,method,formData){
    return new Promise((re,re1)=>{
            $.ajax({
                url,
                type: method,
                data: formData,
                cache: false,        // 不缓存数据
                processData: false,  // 不处理数据
                contentType: false,  // 不设置内容类型
                success:function (data) {           //成功回调
                    re(data)
                },
                error(e){
                    re1(e)
                } 
        });
    })
}

let vm=new Vue({
    el:"#app",
    data:{
        arr: [
            {
                id: 1,
                name: "登录",
                url: "./login.html#/login"
            },
            {
                id: 2,
                name: "注册",
                url: "./login.html#/register"
            },
            {
                id: 3,
                name: "我的订单",
                url: "./goods.html"
            },
            {
                id: 4,
                name: "商品分类",
                url: "#"
            },
            {
                id: 5,
                name: "意见反馈",
                url: "./tickling.html"
            },

            {
                id:6,
                name:"主页",
                url:"./index.html"
            },
        ],
        value:"",
        user:{
            card:"",
            pass2:"",
            pass_2:"",
        },
        bool:false
    },
    created() {
        this.user=Object.assign({},this.user,JSON.parse(sessionStorage.getItem("user")));
        let user=sessionStorage.getItem("user");

        if(user!=null){
            user=JSON.parse(sessionStorage.getItem("user"));
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
        }else{
            alert("请先进行登入或注册");
            location.href="./login.html";
        }
    },
    methods: {
        re(){
            let inp=this.$refs.inp;
            let reader=new FileReader();
            reader.readAsDataURL(inp.files[0]);
            let that=this;
            reader.onload=function(e){
                that.$refs.tou.src=e.target.result;
                that.bool=true;
            }
        },
        re2(){
            if(this.user.pass2!==this.user.pass_2){
                alert("两次密码不匹配");
            }
            else{
                let formdata=new FormData();
                for(key of Object.keys(this.user)){
                    formdata.append(key,this.user[key]);
                }
                if(this.bool){
                    formdata.append("head",this.$refs.inp.files[0]);
                    this.bool=false;
                }
                ajax("./perfect","post",formdata).then((re)=>{
                    sessionStorage.setItem("user",re);
                    location.href="./index.html";
                })
            }
        }
    },
})