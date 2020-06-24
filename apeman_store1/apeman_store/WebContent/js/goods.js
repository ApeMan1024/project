function getAjax(method,url,params){
    return axios({
        method,
        url,
        params
    });
}
let bool1=true;
let vm=new Vue({
    el:"#app",
    data:{
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
        arr1:[],
        src:"./images/index/logo.jpg",
        num:0,
        sear:""
    },
    created() {
        
        let user=JSON.parse(sessionStorage.getItem("user"));
        if(user!=null){

            getAjax("post","./cle",{flag:"num",username:user.username}).then(re=>{
                if(re.data==0){
                    sessionStorage.setItem("num","");
                }else{
                    sessionStorage.setItem("num",re.data);
                }
                this.num=sessionStorage.getItem("num");
            })

            
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
            getAjax("post","./good",{flag:1,username:user.username}).then(re=>{
                this.arr1=re.data;
            })
            
        }else{
            alert("请先进行登入或注册");
            location.href="./login.html";
        }

        

    },
    methods: {
        re2(a){
            user=JSON.parse(sessionStorage.getItem("user"));
            getAjax("post","./good",{flag:2,username:user.username,number:this.arr1[a].number}).then(re=>{
                if(re.data===true){
                    this.arr1.splice(a,1);
                }
            })
        },
        re3(){
            let b=confirm("你确定要清空订单列表吗");
            if(b){
                user=JSON.parse(sessionStorage.getItem("user"));
                getAjax("post","./good",{flag:3,username:user.username}).then(re=>{
                    if(re.data===true){
                        this.arr1.splice(0,this.arr1.length);
                    }
                })
            }
        },
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
        }
    },
})
