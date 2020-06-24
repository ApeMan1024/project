function getAxios(obj,id,url){
    let params = new URLSearchParams()
    for(item of Object.keys(obj)){
        params.append(item,obj[item]);
    }
    params.append("id",id);
    return axios({
        url,
        method:"post",
        params,
    });
}


function getAjax1(method,url,params){
    return axios({
        method,
        url,
        params
    });
}
const lu1={
    template:"#lu1",
    data(){
        return {
            obj:{
                username:"",
                password:"",
            }
        }
    },
    methods: {
        re(){
            let Axios=getAxios(this.obj,2,"./login");
            Axios.then((res)=>{
                if(res.data){
                    sessionStorage.setItem("user",JSON.stringify(res.data));
                    for(key of Object.keys(res.data)){
                        if(key!="pass1"&&key!="pass2"&&res.data[key]===''){
                            alert("请先完善个人资料");
                            getAjax1("post","./cle",{flag:"num",username:this.obj.username}).then(re=>{
                                if(re.data==0){
                                    sessionStorage.setItem("num","");
                                }else{
                                    sessionStorage.setItem("num",re.data);
                                }
                                location.href="./perfect.html";
                            })
                            return;
                        }
                    }
                    getAjax1("post","./cle",{flag:"num",username:this.obj.username}).then(re=>{
                        if(re.data==0){
                            sessionStorage.setItem("num","");
                        }else{
                            sessionStorage.setItem("num",re.data);
                        }
                        location.href="./index.html";
                    })
                }else{
                    alert("用户名或密码错误")
                }
            })
            
        }
    },
};
const lu2={
    template:"#lu2",
    data(){
        return {
        obj:{
            user:"",
            pass:"",
            pass1:"",
            email:""
         }
        }
    },
    methods: {
        re(){
            let regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
                regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;
            if(regCn.test(this.obj.user)||regEn.test(this.obj.user)){
                alert("用户名不能包含特殊的字符");
                return false;
            }else{
                if(this.obj.pass!==this.obj.pass1){
                    alert("两次密码不匹配");
                    return false;
                }else{
                    let Axios=getAxios(this.obj,1,"./login");
                    Axios.then((res)=>{
                        if(res.data){
                            location.href="./login.html"
                        }
                    })
                }
            }
        }
    },
};
const routes = [
    {path:'/',redirect:"/login"},
    { path: '/login', component: lu1 },
    { path: '/register', component: lu2 },
]
const router = new VueRouter({
    routes
})
let vm=new Vue({
    el:"#app",
    data:{
        name:"login",
    },
    methods: {
        
    },
    created(){
        let s=this.$route.path
        $("#title").html(s==="/register"?"用户注册":"用户登录")
    },
    watch: {
        $route(){
            //监听浏览器地址变化 
            let s=this.$route.path
            $("#title").html(s==="/register"?"用户注册":"用户登录")
        }
    },
    router,
})