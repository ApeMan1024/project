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
        check:false,
        src:"./images/index/logo.jpg",
        sear:"",
    },
    created() {
        sessionStorage.getItem("num")===null
        ||sessionStorage.getItem("num")!=this.arr1.length
        ?sessionStorage.setItem("num",this.arr1.length):""; 
        
        let user=sessionStorage.getItem("user");
        if(user!=null){
            user=JSON.parse(sessionStorage.getItem("user"));
            this.arr.splice(0,2,{id:0,name:`你好,${user.username}`,url:"./perfect.html"});
            getAjax("post","./cle",{flag:1,username:user.username}).then((re)=>{
                this.arr1=re.data;
                let mk=this.arr1.some((item)=>item.bool===false);
                if(!mk)this.check=true;
            })
        }else{
            alert("请先进行登入或注册");
            location.href="./login.html";
        }

        

        
    },
    updated() {
        sessionStorage.setItem("num",this.arr1.length);
        
    },
    methods: {
        re(index){
            let mk=this.arr1.some((item)=>item.bool==false);
            if(mk){
                this.check?this.check=false:"";
                getAjax("post","./cle",{flag:2,id:this.arr1[index].id,username:this.arr1[index].username,bool:this.arr1[index].bool});
                return;
            }else{
                this.check=true;
            }
        },
        re1(a,b){
            if(a==='-'){
                this.arr1[b].num===1?"":this.arr1[b].num--;
            }
            else{
                this.arr1[b].num++;
            }
            getAjax("post","./cle",{flag:4,id:this.arr1[b].id,username:this.arr1[b].username,num:this.arr1[b].num}).then((e)=>{
                console.log(e.data);
            })
        },
        re2(a){
            getAjax("post","./cle",{flag:6,id:this.arr1[a].id,username:this.arr1[a].username});
            this.arr1.splice(a,1);
            if(this.arr1.length===0){

                sessionStorage.setItem("num","");
            }
            else{
                sessionStorage.setItem("num",this.arr1.length);
            }
        },
        re3(){
            let b=confirm("你确定要清空购物车吗");
            if(b){
                let user=JSON.parse(sessionStorage.getItem("user"));
                getAjax("post","./cle",{flag:5,username:user.username}).then(re=>{
                    if(re.data===true){
                        this.arr1.splice(0,this.arr1.length);
                        this.check=false;
                        sessionStorage.setItem("num","");
                    }
                });
                
            }
        },
        re4(){
            let list2=[];
            list2=this.arr1.filter((item)=>{
                return item.bool;
            });
            if(list2.length==0){
                alert("请选择需要购买的商品");
            }else{
                sessionStorage.setItem("list_2",JSON.stringify(list2));
                location.href="./kink.html";
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
    computed: {
        num(){
            let nu=0;
            this.arr1.forEach((item)=>{
                item.bool?nu++:"";
            })
            return nu;
        },
        qian(){
            let qian1=0;
            this.arr1.forEach((item)=>{
                if(item.bool){
                    qian1+=item.num*parseFloat(item.xian?item.xian:item.qian);
                }
            })
            return qian1;
        }
    },
    watch: {
        check(n){
            let user=sessionStorage.getItem("user");
            if(n){
                this.arr1.forEach((item)=>{
                    item.bool=true;
                })
                getAjax("post","./cle",{flag:3,bool:true,user});
            }
            else{
                let b=this.arr1.some((item)=>item.bool===false);//检测数组中元素是否有满足条件的元素，有返回true
                if(!b){
                    //当全选取消时，判断此时所有的元素是否都选定，如果是，全部取消，否则不操作
                    this.arr1.forEach((item)=>{
                        item.bool=false;
                    })
                    getAjax("post","./cle",{flag:3,bool:false,user});
                }
            }
        },
    },
})
