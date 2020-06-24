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
        src:"./images/index/logo.jpg",
        num:0,
        bool:false,
        obj:{},
        user:"",
        sear:""
       
    },
    created() {
        let user=sessionStorage.getItem("user");
        if(user){
            user=JSON.parse(sessionStorage.getItem("user"));
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
            this.user=user;
            this.num=sessionStorage.getItem("num");
        }
        getAjax("post","./details",{flag:1,id:location.search.split("=")[1]}).then(re=>{
            if(Object.keys(re.data).length!=0){
                this.obj=Object.assign({},this.obj,re.data);
                if(re.data.img){
                    this.obj.img=JSON.parse(re.data.img);
                }
                
            }
            else{
                alert("商品已经下架");
                history.back();
            }
        })


        
    },
    
    methods: {
       re(e){
           let img=e.target;
           let box=this.$refs.box;
           let img1=this.$refs.img;
           let that=this;
           img.onmousemove=function(e){
                that.bool=true;

                //获取鼠标相对元素的位置
                var x= e.clientX-this.offsetLeft;
                var y= e.clientY-this.offsetTop;

                //设置图片相对元素的位置
                box.scrollTop=y*(img1.offsetHeight/img.offsetHeight)-box.offsetHeight/2;
                box.scrollLeft=x*(img1.offsetWidth/img.offsetWidth)-box.offsetWidth/2;
                
                //把大图元素的位置设置为鼠标的位置
                box.style.left=e.clientX+"px";
                box.style.top=e.clientY+"px";
           }
           img.onmouseout=function(){
               that.bool=false;
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
       },
       re2(){
           if(this.user){
               let {id,name,qian,xian,src,num,title}=this.obj;
               let obj={id,name,qian,xian,src,num,title,bool:false}
               obj.username=this.user.username;
               getAjax("post","./details",{flag:2,obj}).then(re=>{
                   if(re.data){
                       alert("商品已经加入购物车😀");
                   }
               })
           }else{
               alert("请先进行登入或注册");
               location.href="./login.html";
           }
       }
    },
    updated() {
        $("#ti").html(this.obj.name);
    },
})