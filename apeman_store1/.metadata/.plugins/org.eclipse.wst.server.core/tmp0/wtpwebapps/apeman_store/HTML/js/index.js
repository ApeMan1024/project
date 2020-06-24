function getAjax(method, url, params) {
    return axios({
        method,
        url,
        params
    });
}

let vm=new Vue({
	el:"#app",
	data:{
		bool:false,
		user:{username:"",password:""}
	},
	methods: {
		re(){
			getAjax("post", "../queen", {...this.user}).then((re)=>{
				if(!re.data){
					this.bool=true;
				}else{
					sessionStorage.setItem("user_1",JSON.stringify(re.data));
					location.href="./admin.html";
				}
			}) 
		}
	},
})