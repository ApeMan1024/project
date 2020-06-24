//将base64转换为blob    
function dataURLtoBlob(dataurl) 
{ 
    var arr = dataurl.split(','), 
    mime = arr[0].match(/:(.*?);/)[1], 
    bstr = atob(arr[1]), n = bstr.length, 
    u8arr = new Uint8Array(n); 
    while (n--) { 
        u8arr[n] = bstr.charCodeAt(n); 
    } 
    return new Blob([u8arr], { type: mime }); 
}
//将blob转换为file   
function blobToFile (theBlob, fileName)
{       
    theBlob.lastModifiedDate = new Date();       
    theBlob.name = fileName;       
    return theBlob;    
}
function dataURLtoFile(dataurl, filename) {//将base64转换为文件
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}    

function re(text) {
    let regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
        regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;
    if (regCn.test(text) || regEn.test(text)) {
        return true;
    }
}

function getAjax(method, url, params) {
    return axios({
        method,
        url,
        params
    });
}

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
function JQajax(url,type,data){
    return new Promise((re,re1)=>{
        $.ajax({
            url,type,data,
            success(su){
                re(su);
            },
            error(err){
                re1(err);
            }
        })
    })
}

let card = {
    template: "#card",
    data() {
        return {
            obj: {
                username: "",
                card: "",
                qian: "",
            },

        }
    },
    methods: {
        pd(e) {
            if (re(this.obj.username) || re(this.obj.card) || re(this.obj.qian)) {
                alert("不能包含特殊字符");
            } else {
                if (this.obj.username == '' || this.obj.card == '' || this.obj.qian == '') {
                    alert("输入完成的数据");
                } else {
                    getAjax("post", "../recharge", { flag: 1, ...this.obj }).then((re) => {
                        if (re.data) {
                            alert("充值成功😀");
                            this.obj.username = "";
                            this.obj.card = "";
                            this.obj.qian = "";
                        } else {
                            alert("充值失败😔");
                        }
                    })
                }
            }
        }

    }
};//构建组件
let add = {
    template: "#add",
    data() {
        return {
            arr: [],
            com: {
                id: "", name: "", qian: "", xian: "", title: "",src:""
            },
            arr1:[]
        }
    },

    methods: {
        re() {
            let inp = this.$refs.inp;
            if(inp.value){
                let reader = new FileReader();
                reader.readAsDataURL(inp.files[0]);
                let that = this;
                reader.onload = function (e) {
                    that.com.src = e.target.result;
                }
            }
        },
        re1(){
            let inp1=this.$refs.inp1
            if(inp1.value){
                for(let i=0;i<inp1.files.length;i++){
                    let reader = new FileReader();
                    reader.readAsDataURL(inp1.files[i]);
                    let n=inp1.files[i].name.split(".");
                    let that = this;
                    reader.onload = function (e) {
                        that.arr.push(e.target.result);
                        that.arr1.push(n[n.length-1]);
                    }
                }
            }
        },
        re2(){
            let formData=new FormData();
            for(let key of Object.keys(this.com)){
                if(key==='src'){
                    formData.append(key,this.$refs.inp.files[0]);
                }else{
                    formData.append(key,this.com[key]);
                }
            }

            for(let i=0;i<this.arr.length;i++){
                var file1=dataURLtoFile(this.arr[i], this.com.id+i+"."+this.arr1[i]);
                formData.append("img",file1);
            }
            ajax("../recharge2","post",formData).then(re=>{
                console.log(re);
                if(re){
                    alert("商品添加成功");
                    this.com= {id: "", name: "", qian: "", xian: "", title: "",src:""};
                    this.arr=[];
                    this.arr1=[];
                }
            })
        },
        re3(){
        	
            getAjax("post", "../recharge", {flag:2}).then((re)=>{
                this.com.id=re.data;
            }) 
        },
        qu(index){
            this.arr.splice(index,1);
            this.arr1.splice(index,1);
            this.$refs.inp1.value="";
            this.$refs.inp1.files[0]
        }
    }
};
let miao = {
    template: "#miao",
    data() {
        return {
            te: "",
            time: "",
            arr1: [//显示区元素
            ],
            arr2: [//已经显示的元素
            ],
            arr3: [],
            arr4: [
                //被选做秒杀的元素
            ],
            bool:true
        }
    },
    created() {
        getAjax("post", "../recharge3", {flag:2}).then(re=>{
            this.arr3=re.data;
            if(re.data.length<10)this.bool=false;
            if (this.arr3.length > 5) {
                for (let i = 0; i < 5; i++) {
                    this.arr1.push(this.arr3[i]);
                }
            } else {
                this.arr1 = [...this.arr3];
            }
        })
        
    },
    methods: {
        re() {
            //右
            if(this.bool){
                getAjax("post", "../recharge3", {flag:3,start:this.arr3.length,len:5}).then(re=>{
                    this.arr3=[...this.arr3,...re.data];
                    this.arr2 = [...this.arr1, ...this.arr2];
                    re.data.length==0?this.bool=false:"";
                    this.arr1 = [];
                    for (let i = this.arr2.length; i < this.arr3.length; i++) {
                        this.arr1.push(this.arr3[i]);
                        if (this.arr1.length == 5) {
                            break;
                        }
                    }
                })
            }
            else{
                this.arr2 = [...this.arr1, ...this.arr2];
                this.arr1 = [];
                for (let i = this.arr2.length; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length == 5) {
                        break;
                    }
                }
            }
        },
        re1() {
            //左
            this.arr1 = [];
            for (let i = 0; i < this.arr2.length; i++) {
                this.arr1.push(this.arr2[i]);
                if (this.arr1.length === 5) break;
            }
            this.arr2.splice(0, this.arr1.length);
        },
        re3(index) {
        	
            if (this.arr1[index].bool) {
                let n = this.arr4.findIndex(item => item === this.arr1[index]);
                this.arr4.splice(n, 1);
                this.arr1[index].bool = false;
            } else {
                if (this.arr1[index].xian === "" || parseFloat(this.arr1[index].xian) > parseFloat(this.arr1[index].qian) || this.arr1[index].xian == 0) {
                    alert("请输入正确的数据");
                    return;
                }
                
                this.arr1[index].bool = true;
                console.log(this.arr1[index]);
                this.arr4.push(this.arr1[index]);
            }
            
        },
        re4() {
            if(this.te&&this.time&&this.arr4.length!=0){
                let obj={data:this.te,time:this.time,arr4:JSON.stringify(this.arr4)};
                JQajax("../recharge3","post",{flag:4,json:JSON.stringify(obj)}).then(re=>{
                    console.log(typeof re);
                    if(re){
                        alert("设置成功");
                        this.te="";
                        this.time="";
                        this.arr4=[];
                        this.arr3.forEach((item)=>{
                            item.bool=false;
                        })
                    }
                    else{
                        alert("设置失败");
                    }
                })
            }
        }
    },
}
let fan = {
    template: "#fan",
    data() {
        return {
            arr1: [],
            arr2: [],//已经显示的
            arr3: [],
            arr4:[],//保存已经删除的元素
            bool:true,
            length:0
        }
    },
    created() {
        getAjax("post", "../recharge", {flag:5}).then(re=>{
            if(re.data.length==0||re.data.length<10)this.bool=false;
            this.arr3=re.data;
            this.length=this.arr3.length;
            if (this.arr3.length > 5) {
                for (let i = 0; i < 5; i++) {
                    this.arr1.push(this.arr3[i]);
                }
            } else {
                this.arr1 = [...this.arr3];
            }
        })
    },
    methods: {
        re() {
            this.arr1 = [];
            for (let i = 0; i < this.arr2.length; i++) {
                this.arr1.push(this.arr2[i]);
                if (this.arr1.length === 5) break;
            }
            this.arr2.splice(0, this.arr1.length);
        },
        re1() {
            if(this.bool){
                getAjax("post", "../recharge", {flag:6,start:this.length,len:5}).then(re=>{
                    if(re.data.length==0)this.bool=false;
                    this.arr3=[...this.arr3,...re.data];
                    this.length+=re.data.length;
                    this.re2();
                })
            }else{
                this.re2();
            }
        },
        remove(index) {
            let n = this.arr3.findIndex(item => item === this.arr1[index]);
            this.arr1 = [];
            let obj =this.arr3[n];
            this.arr3.splice(n, 1);
            if(this.bool&&this.arr3.length<10){
                //如果还有没有请求回来的数据，继续发起请求
                getAjax("post", "../recharge", {flag:6,start:this.length,len:1}).then(re=>{
                    this.arr3=[...this.arr3,...re.data];
                    re.data.length==0?this.bool=false:"";
                    for (let i = this.arr2.length; i < this.arr3.length; i++) {
                        this.arr1.push(this.arr3[i]);
                        if (this.arr1.length === 5) break;
                    }
                    getAjax("post", "../recharge", {obj,flag:7}).then(re=>{
                        if(re.data)this.length=this.arr3.length;
                    })
                })
            }
            else{
                getAjax("post", "../recharge", {obj,flag:7});
                for (let i = this.arr2.length; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length === 5) break;
                }
                if(this.arr1.length==0&&this.arr2.length>0){
                    this.re();
                }
            }            
        },
        re2(){
            this.arr2 = [...this.arr1, ...this.arr2];
            if (this.arr2.length < this.arr3.length) {
                this.arr1 = [];
                for (let i = this.arr2.length; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length == 5) {
                        break;
                    }
                }
            }
        }
    },
}
let shan = {
    template: "#shan",
    data() {
        return {
            arr1: [],
            arr2: [],
            arr3: [],
            bool:true
        }
    },
    created() {
        //当已经请求完数据时re.data.length=0
        getAjax("post", "../recharge", {flag:3}).then(re=>{
            this.arr3=re.data;
            if(re.data.length==0||re.data.length<12)this.bool=false;
            if (this.arr3.length > 6) {
                for (let i = 0; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length === 6) break;
                }
            }
            else {
                this.arr1 = [...this.arr3]
            }
        })
        
    },
    methods: {
        re() {
            //左
            this.arr1 = [];
            for (let i = 0; i < this.arr2.length; i++) {
                this.arr1.push(this.arr2[i]);
                if (this.arr1.length === 6) break;
            }
            this.arr2.splice(0, this.arr1.length);
        },
        re1() {
            //右
            if(this.bool){
                getAjax("post", "../recharge", {flag:4,start:this.arr3.length,len:6}).then(re=>{
                    if(re.data.length==0){
                        this.bool=false;
                    }
                    this.arr3=[...this.arr3,...re.data];
                    this.re2();
                })
            }
            else{
                this.re2();
            }
        },
        remove(index) {
            let n = this.arr3.findIndex(item => item === this.arr1[index]);
            this.arr1 = [];
            length=this.arr3.length;
            let obj=this.arr3[n];
            this.arr3.splice(n, 1);

            //1.当前还有没请求回来的数据，先请求回来，再删除
            //2.当前已经没有要请求的数据，直接删除
            //3.如果当前页显示的元素已经全部删除，自动跳转到上一页，调用re方法，此情况只会发生在2中
            //4.arr1是正在显示的数据，当arr1已经没有显示的数据了，才进行3
            if(this.bool){
                getAjax("post", "../recharge", {flag:4,start:length,len:1}).then(re=>{
                    this.arr3=[...this.arr3,...re.data];
                    re.data.length==0?this.bool=false:"";
                    for (let i = this.arr2.length; i < this.arr3.length; i++) {
                        this.arr1.push(this.arr3[i]);
                        if (this.arr1.length === 6) break;
                    }
                    getAjax("post", "../recharge3", {obj,flag:1}).then(re=>{
                        if(re.data)this.length=this.arr3.length;
                    })
                })
            }
            else{
                getAjax("post", "../recharge3", {obj,flag:1});
                for (let i = this.arr2.length; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length === 6) break;
                }
                if(this.arr1.length==0&&this.arr2.length>0){
                    this.re();
                }
            }
        },
        re2(){
            this.arr2 = [...this.arr1, ...this.arr2];
            if (this.arr2.length < this.arr3.length) {
                this.arr1 = [];
                for (let i = this.arr2.length; i < this.arr3.length; i++) {
                    this.arr1.push(this.arr3[i]);
                    if (this.arr1.length == 6) {
                        break;
                    }
                }
            }
        }
    }
}
let routes = [

    { path: "/name", component: card },
    { path: "/add", component: add },
    { path: "/miao", component: miao },
    { path: "/fan", component: fan },
    { path: "/shan", component: shan },
    { path: "/", redirect: "/name" },
];//配置路由
let router = new VueRouter({//注册路由实例
    routes
})

let vm = new Vue({
    el: "#app",
    data: {
        arr: [

            { lei: "icon-beibao", url: "/name", name: "猿人卡充值" },
            { lei: "icon-banxie", url: "/add", name: "添加商品" },
            { lei: "icon-fanbuxie", url: "/shan", name: "删除商品" },
            { lei: "icon-tuoxie", url: "#", name: "修改商品信息" },
            { lei: "icon-lanqiu", url: "/miao", name: "秒杀设置" },
            { lei: "icon-duanxiu", url: "/fan", name: "意见反馈查阅" },

        ],
        user:""
    },
    created(){
        let user=sessionStorage.getItem("user_1");
        if(!user)location.href="./index.html";
        this.user=JSON.parse(user);
        
    },
    methods: {

    },
    router//挂载路由
})