function getAjax(method,url,params){
    return axios({
        method,
        url,
        params
    });
}
function getAjax1(method,url,params){
    return new Promise((re1,re2)=>{
        $.ajax({
            type:method,
            url,
            data:params,
            success(re){
                re1(re)
            },
            error(err){
                re2(err);
            }
        })
    });
}
let bool1=true;
let vm=new Vue({
    el:"#app",
    data:{
        num:"300.00",
        arr:[
            {
                id:1,
                name:"登录",
                url:"./login.html#/login"
            },
            {
                id:2,
                name:"注册",
                url:"./login.html#/register"
            },
            {
                id:3,
                name:"我的订单",
                url:"./goods.html"
            },
            {
                id:4,
                name:"商品分类",
                url:"#"
            },
            {
                id:5,
                name:"意见反馈",
                url:"./tickling.html"
            },
            {
                id:6,
                name:"主页",
                url:"./index.html"
            },
        ],
        src:"./images/index/logo.jpg",
        sear:"",
        user:{
            card:"",
            pass2:""
        }
    },
    created() {
        let user=sessionStorage.getItem("user");
        if(user!=null){
            user=JSON.parse(user);
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
        }else{
            alert("请先进行登入或注册");
            location.href="./login.html";
        }
        let list_2=sessionStorage.getItem("list_2");
        if(list_2){
            list_2=JSON.parse(list_2);
            let num=0;
            for(let i=0;i<list_2.length;i++){
                num+=parseFloat(list_2[i].xian?list_2[i].xian:list_2[i].qian)*list_2[i].num;
            }
            this.num=num;
        }
       
    },
    methods: {
        search(){
            if(this.sear===''){
                alert("请输入要搜索的商品信息");
            }
            else{
                if(bool1){
                    bool1=false;
                    getAjax("post","./index",{flag:3,sear:this.sear}).then((re)=>{
                        if(re.data.length==0){
                            alert("你要搜索的商品不存在");
                            this.sear="";
                        }else{
                            sessionStorage.setItem("arr_1",JSON.stringify(re.data));
                            location.href="./search.html";
                        }
                        bool1=true;
                    })
                }
            }
        },
        re(){
            let user=JSON.parse(sessionStorage.getItem("user"));
            user=Object.assign({},user,this.user);
            user=JSON.stringify(user);
            let list_2=JSON.parse(sessionStorage.getItem("list_2"));
            for(let i=0;i<list_2.length;i++){
                list_2[i]=JSON.stringify(list_2[i]);
            }
            list_2=JSON.stringify(list_2);
            getAjax1("post","./kink",{flag:1,num:this.num,list_2,user}).then((re)=>{
                if(re==1){
                    alert("卡号或密码不正确");
                }else if(re==2){
                    alert("猿人卡余额不足");
                }else if(re==3){
                    location.href="./goods.html";
                }
               
            })
        }
    },
})