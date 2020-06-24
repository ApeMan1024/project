function getAjax(method,url,params){
    return axios({
        method,
        url,
        params
    });
}
let bool1=true;
let bool=true;
Vue.component("xuan",{
    template:"#xuan",
    props:["list"],
    data(){
        return {
           
        }
    }
})
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
        arr2:[],
        src:"./images/index/logo.jpg",
        time:"24:00",
        obj:"",
        timer:"",
        lun:"",
        sear:"",
        title:[],
        list:[],
        num:0
    },
    created(){
        this.num=sessionStorage.getItem("num");
        let user=sessionStorage.getItem("user");
        let date=new Date();

        getAjax("post","./index",{flag:1,num:1,lun:6,date:`${(date.getFullYear()+"").padStart(4,"0")}-${((date.getMonth()+1)+"").padStart(2,"0")}-${(date.getDate()+"").padStart(2,"0")}`}).then((re)=>{
            this.title=re.data[0];
            this.list.push(re.data[1])
            this.arr1=re.data[3];
            if(re.data[2].length!=0){
            	this.time=re.data[4];
                this.arr2=[...re.data[2]];
                while(this.arr2.length<10){
                    this.arr2=[...this.arr2,...re.data[2]];
                }
                setInterval(this.timer1()(),1000);
            }
        })

        if(user!=null){
            user=JSON.parse(sessionStorage.getItem("user"));
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
        }
        window.onscroll=()=>{
            //变量scrollTop是滚动条滚动时，距离顶部的距离
            var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
            //变量windowHeight是可视区的高度
            var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
            //变量scrollHeight是滚动条的总高度
            var scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
           //滚动条到底部的条件
           if(scrollTop+windowHeight+10>=scrollHeight){
               if(this.list.length<this.title.length){
                  if(bool){
                        bool=false;
                      getAjax("post","./index",{flag:2,title:this.title[this.list.length],num:1}).then((re)=>{
                        this.list.push(re.data);
                        bool=true;
                      })
                  } 
               }
           } 
        }
    },
    beforeMount() {
    
    },
    mounted() {
        if(this.arr2.length!=0){

            let lun=this.$refs.lun;
            $(lun).width(($(lun).children().width()+1)*$(lun).children().length);
            $(lun).parent().width(($(lun).children().width()+1)*5);
            this.lun=$(lun);
            this.timer=this.re($(lun));
        }
    },
    updated() {
        if(this.arr2.length!=0){
            let lun=this.$refs.lun;
            $(lun).width(($(lun).children().width()+1)*$(lun).children().length);
            $(lun).parent().width(($(lun).children().width()+1)*5);
            this.lun=$(lun);
            this.timer=this.re($(lun));
        }
    },
    methods: {
        re(m){
            this.re1();
            return setInterval(function(){
                let n=parseInt(m.css("left"))-1;
                Math.abs(n)<=m.width()/2?"":n=-1;
                m.css({left:n+"px"});
            },10);
        },
        re1(){
            clearInterval(this.timer);
        },
        re2(){
           this.timer=this.re(this.lun); 
        },
        timer1(){
            let tim=new Date();
            let list1=this.time.split(":");
            let tim1=new Date(tim.getFullYear(),tim.getMonth(),tim.getDate(),list1[0],list1[1]);
            if(tim1===tim||tim1<tim){
                clearInterval(this.timer);
                this.obj={
                    h:"00",
                    m:"00",
                    s:"00"
                }
                return;
            };
        
            let h=(Math.floor((tim1-tim)/(1000*60*60))+"").padStart(2,"0");
            let m=(Math.floor((tim1-tim)/(1000*60)-h*60)+"").padStart(2,"0");
            let s=(Math.floor((tim1-tim)/1000-h*60*60-m*60)+"").padStart(2,"0");
            this.obj={
                h,m,s
            }
           return this.timer1
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

