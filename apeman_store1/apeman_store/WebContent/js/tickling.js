function getAjax(method,url,params){
    return axios({
        method,
        url,
        params
    });
}
let bool1=true;
let vm = new Vue({
    el: "#app",
    data: {
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
                url: "#"
            },
            {
                id:6,
                name:"主页",
                url:"./index.html"
            },
        ],
        arr1: [],
        src: "./images/index/logo.jpg",
        text: "",
        num: 0,
        user:"",
        sear:"",
        bool:true
    },
    created() {
        this.num=sessionStorage.getItem("num");
        let user=sessionStorage.getItem("user");
        if(user!=null){
            user=JSON.parse(sessionStorage.getItem("user"));
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
            this.user=user;
            getAjax("get","./remark",{num:this.arr1.length,flag:1}).then((re)=>{
                this.arr1=JSON.parse(re.data.list);
            });
        }else{
            alert("请先进行登入或注册");
            location.href="./login.html";
        }
        window.onscroll=()=>{
            var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
            var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
            var scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
            if(scrollTop+windowHeight+10>=scrollHeight){
                if(this.bool){
                    getAjax("get","./remark",{num:this.arr1.length,flag:1}).then((re)=>{
                        this.arr1=[...this.arr1,... JSON.parse(re.data.list)];
                        JSON.parse(re.data.list).length==0?this.bool=false:"";
                    });
                }
            } 
        }

    },
    methods: {
        re1() {
            if (this.text !== '') {
                let date = new Date();
                let time =
                    `
                    ${date.getFullYear()}-${(parseInt(date.getMonth()) + 1+"").padStart(2,"0")}-${(date.getDate()+"").padStart(2,"0")} 
                    ${(date.getHours()+"").padStart(2,"0")}:${(date.getMinutes()+"").padStart(2,"0")}:${(date.getSeconds()+"").padStart(2,"0")}
                `
                let obj = {
                    id: this.arr1.length + 1,
                    head:this.user.head,
                    username:this.user.username,
                    time, nei:this.inputEmoji(this.text)
                }
                this.text="";
                this.arr1.unshift(obj);
                // sessionStorage.setItem("ping",JSON.stringify(this.arr1));
                getAjax("post","./remark",{...obj,flag:2}).then((re)=>{
                    if(re.data){
                        let ping=JSON.parse(sessionStorage.getItem("ping"));
                        ping["count"]=parseInt(ping['count'])+1;
                        ping["list"]=JSON.stringify(this.arr1);
                        sessionStorage.setItem("ping",JSON.stringify(ping));
                    }
                })
            }
            else{
                alert("请先输入内容")
            }
        },
        inputEmoji(value) 
        { 
            var regStr = /[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF][\u200D|\uFE0F]|[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF]|[0-9|*|#]\uFE0F\u20E3|[0-9|#]\u20E3|[\u203C-\u3299]\uFE0F\u200D|[\u203C-\u3299]\uFE0F|[\u2122-\u2B55]|\u303D|[\A9|\AE]\u3030|\uA9|\uAE|\u3030/ig; 
            if (regStr.test(value)) {
                 value =value.replace(regStr, ""); 
            } 
            return value;
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
