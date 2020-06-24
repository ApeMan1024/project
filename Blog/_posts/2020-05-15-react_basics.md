---
title: React基础学习
date: 2020-05-15
category: React
layout: see
author: 猿人
---
React 起源于 Facebook 的内部项目，因为该公司对市场上所有 JavaScript MVC 框架，都不满意，就决定自己写一套，用来架设Instagram 的网站。做出来以后，发现这套东西很好用，就在2013年5月开源了

### jsx

+ 优点

  + jsx执行更快，编译为js代码时进行优化
  + 类型更安全，编译过程如果出错就不能编译，及时发现错误
  + jsx编写规范模板更加简单快速

+ 注意：

  + jsx必须要有根节点
  + 正常的普通html元素要小写，如果是大写，默认认为是组件

### jsx表达式

+ 在`{}`中写js代码

+ 特殊的属性class和for：使用className和htmlFor代替

+ 由html元素构成

+ {}中间表达式中可以使用jsx对象

+ 属性和html内容一样，都使用{}插入内容

  ```jsx
  import React from 'react';
  import ReactDOM from 'react-dom';
  import './App.css'
  
  let ele1=(
    <div>
      <h1>jsx语法</h1>
      <h1>jsx学习</h1>
    </div>
  )
  
  let src="./logo192.png"
  let ele2=(
    <div>
      <p className={'box'}>
        <img src={src}/>
      </p>
    </div>
  )
  
  let ele = (
    <div>
      <h1>你好react</h1>
      {10 % 3 === 0 ? <h3>react</h3> : ele1}
      {ele2}
    </div>
  )
  
  ReactDOM.render(ele, document.getElementById("root"))
  ```

### jsx样式

+ 不可以存在多个className属性，否则后面的className会覆盖前面

  ```jsx
  <div className={[box1,'div2'].join(" ")} className={box2}>
        11111
  </div>
  ```

+ style样式中的属性必须使用驼峰命名法

  ```jsx
  let box={
    width:"100px",
    height:"100px",
    backgroundColor:"red"/*驼峰命名*/
  }
  ```

+ 为元素设置多个类名

  ```jsx
  let ele1=(
    <div>
      <div className={[box1,'div2'].join(" ")} >
        11111
      </div>
      <hr/>
      <div className={'box1 '+'box2'} >
        11111
      </div>
    </div>
  )
  ```

+ 注释`{/**/}`

### react组件

+ 函数式组件

  ```jsx
  import React from "react"
  import "./goods.css"
  export default function Ge(){
      let box="box";
      return (
          <div>
              <div className={box}>鼠标移入背景颜色改变</div>
          </div>
      )
  }
  ```

+ 类组件

  ```jsx
  import React from "react"
  import Ge from "../goods/goods.jsx"
  export default class HelloWord extends React.Component {
      constructor() {
          super();
          // 相当于vue中data
          this.state = {
              name: "xiaohong"
          }
      }
      render() {
          console.log(this)
          return (
              <div>
                  <h1>类组件</h1>
                  <p>{this.state.name}</p>
                  {/* this.props相当于vue中的props */}
                  <p>{this.props.name}---{this.props.age}---{this.props.sex}</p>
  
                  <div>
                      <Ge/>
                  </div>
              </div>
          )
      }
  }
  ```

+ 两种组件的区别

  + 函数式组件没有私有数据，类组件有
  + 类组件可以有交互和生命周期函数，函数式没有

### React State

+ 相当于vue中的data
+ 修改state中的数据，使用`this.setState({})`

### React props

+ props用于父向子传递数据，数据是单向流动的，不能子向父传递

+ props中传输的值可以是任意类型

+ props设置默认值的方式

  ```js
  //Props是组件名
  Props.defaultProps={name:"1"}
  ```

+ props可以传递函数，父元素通过props向子元素传递函数，子元素通过该函数修改父元素的state，从而达到子向父传递数据

### React事件

+ react事件的事件名，采用驼峰命名法

+ 事件绑定的函数，使用{}

+ 原生的事件绑定，只要事件函数`return false`就能阻止默认事件，但是react中不能采用这种方式，要阻止默认事件必须使用`event.preventDefault()`

+ 向事件函数传参

  ```jsx
   <button onClick={(e)=>this.show(e)}>按钮</button>
  ```

+ 事件函数中的this

  ```jsx
  //这种情况事件函数show中的this为undefined
  <button onClick={this.show}/>
  
  //这种情况事件函数show中的this为组件实例
  <button onClick={()=>this.show()}/>
  
  //这种情况事件函数show中的this为组件实例
  <button onClick={function(){this.show()}.bind(this)}/>
  
  //绑定this也可以在组件的构造函数中
  this.shu=this.shu.bind(this)
  ```

### React条件渲染

+ react中条件渲染和js中的条件运算相同，即`if...elseif..else`或三元运算符

  ```jsx
  if(this.state.bool){
      return (<Greet/>)
  }
  else{
      return (<Login/>)
  }
  ```

+ 列表渲染

  ```jsx
  //需要将列表中数据拼装成jsx对象
  let arr = [
    { id: 1, text: "react" },
    { id: 2, text: "java" },
    { id: 3, text: "c++" },
    { id: 4, text: "python" },
  ]
  let arr1=arr.map(item => {
    return <p key={item.id}>{item.id}---{item.text}</p>
  })
  ```

### 生命周期

+ 生命周期：组件的创建，渲染，销毁

  <img src="/public/images/react.png"/>

### React插槽

+ 组件中可以写入内容，并且写入的内容能够被识别和控制

+ React中的插槽功能需要自己开发

+ 原理

  + 在组件中写入的内容，传入到props中

    ```jsx
    //内容全部放入组件
    {this.props.children}
    
    //将内容放入指定的位置
    <div className="header">
        {this.props.children.filter(item=>{
            return item.props['data-name']==='header'
        })}
    </div>
    <div className="main">
        {this.props.children.filter(item=>{
            return item.props['data-name']==='main'
        })}
    </div>
    <div className="footer">
        {this.props.children.filter(item=>{
            return item.props['data-name']==='footer'
        })}
    </div>
    ```

### React中的路由

+ 安装路由`cnpm i react-router-dom -s`

+ ReactRouter三大组件

  + Router：所有路由组件的根组件(底层组件)，包裹路由规则的最外层容器

    + basename属性：设置此路由根路径，router可以在1个组件中写多个

  + route：路由规则匹配组件，显示当前规则对应的组件

  + Like：路由跳转的组件
  
  + <span style="color:red">需要精确匹配，可以为route设置exact属性</span>

  ```jsx
  import React from "react"
  import  {BrowserRouter as Router,Link,Route} from "react-router-dom"
  console.log(Route)
  function Me(){
      return (
          <div>
              <h1>java</h1>
          </div>
      )
  }
  
  function Sd(){
      return (
          <div>
              <h1>Vue</h1>
          </div>
      )
  }
  
  function Home(){
      return (
          <div>
              <h1>欢迎使用路由</h1>
          </div>
      )
  }
  
  export default class Router1 extends React.Component{
      constructor(props){
          super(props)
          this.state={}
      }
      render(){
          return (
              <div>
                  <Router basename="/admin">
                      <div className="nav">
                          <Link to="/" >Home</Link>
                          <Link to="/me" >me</Link>
                          <Link to="/sd" >sd</Link>
                      </div>
                      <Route path="/" component={Home} exact></Route>
                      <Route path="/me"  component={Me}></Route>
                      <Route path="/sd"  component={Sd}></Route>
                  </Router>
              </div>
          )
      }
  }
  ```

  + Link中的to属性，设置跳转的路径

    ```jsx
    //直接设置
    <Link to="/me" >me</Link>
    
    //配置对象的形式设置
    let meObj={
                pathname:"/me",//跳转的路径
                search:"?username=name",//gen请求参数
                hash:"#adc",//hash值
                state:{msg:"路由的挂载信息"}//传入组件的数据
                }
    <Link to={ meObj } >me</Link>
    ```

  + Link中的replace属性，点击链接后，将新地址替换成历史访问的原地址

  + 重定向

    ```jsx
    import { BrowserRouter as Router, Route, Link, Redirect } from "react-router-dom"
    //登录信息组件
    function LoginInfo(props) {
      if (props.location.state.loginState === 'success') {
        return <Redirect to="/admin"></Redirect>
      }
      else {
        return <Redirect to="/login"></Redirect>
      }
    }
    ```

  + Switch组件：让switch组件内容中的router只匹配一个，只要匹配到了，剩余的路由规则将不再匹配

### React中状态管理Redux

+ redux解决react数据管理(状态管理)，用于中大型、数据比较庞大，组件之间数据交互多的情况下使用，如果不知道是否需要使用redux，则不需要使用redux

  + 解决组件的数据通信
  + 解决数据和交互多的应用
  + store：数据仓库，保存数据的地方
  + state：state是1个对象，数据仓库里所有数据都放到一个state中
  + action：一个动作，用来触发数据改变的方法
  + dispatch:将动作触发成方法
  + reducer：是一个函数，通过获取动作，改变数据，生成一个新state，从而改变页面

+ 安装`cnpm i redux -S`

+ 初始化数据

  ```jsx
  const store=createStore(reducer)//创建仓库，传入一个回调函数
  
  //通过动作，创建新的state
  //reducer：1.初始化数据，2，通过获取动作，改变数据
  //状态管理
  const reducer=function(state={num:0},action){
    console.log(action)
  
    switch(action.type){
      case 'add':
        state.num++;
        break;
      case 'decrement':
        state.num--
        break;
  
      default:
        break;
    }
    return {...state}
  }
  ```

+ 获取数据

  ```jsx
  store.getState()
  ```

+ 修改数据(通过动作修改数据)

  ```jsx
  //通过仓库的方法dispatch进行修改数据
  store.dispatch({type:"decrement",obj:{id:1,msg:"这是传数据"}})//obj等可选
  ```

+ 修改视图(监听数据的变化，重新渲染内容)

  ```jsx
  //监听store中数据的变化
  store.subscribe(()=>{
    ReactDOM.render(
      <div>
        <Counter></Counter>
      </div>,document.getElementById("root")
    )
  })
  ```

+ [官网]( https://www.redux.org.cn/docs/introduction/ThreePrinciples.html )

### React中的react-redux

+ 安装`cnpm i react-redux -S`

+ Provider组件：自动的将store里的state和组件进行关联

+ connect方法：将组件和数据（方法）进行连接

+ 初始化数据

  ```jsx
  function reducer(state = { num: 0 }, action) {
    switch (action.type) {
      case 'add': {
        state.num++;
        break;
      }
    }
    return { ...state }
  }
  
  const store = createStore(reducer)
  ```

+ 数据的获取，数据的修改

  ```jsx
  //将state映射到组件里，将修改数据的方法映射到组件的props中
  //将state映射到props函数，state必须
  function mapStateToProps(state) {
    return {
      value: state.num
    }
  }
  
  //将修改数据的方法，映射到props,默认传入store里的dispatch方法，dispatch必须
  function mapDispatchToProps(dispatch) {
    return {
      onAddClick: () => { dispatch(addAction) }
    }
  }
  
  class Counter extends React.Component {
    render() {
      //用于计数的，通过store的state传给props，直接通过props就可以将state的数据获取
      const value = this.props.value;
      //将修改数据的事件或者方法传入到props
      const onAddClick = this.props.onAddClick;
  
      return (
        <div>
          <h1>计数数量：{value}</h1>
          <button onClick={onAddClick}>+1</button>
        </div>
      )
    }
  }
  ```


### Ant 蚂蚁框架

* [移动端官网]( https://mobile.ant.design/docs/react/introduce-cn )

+ 安装使用`cnpm i antd-mobile -S`

+ 全部导入样式和js

  ```jsx
  import 'antd-mobile/dist/antd-mobile.css';
  import { Button } from 'antd-mobile';
  ```

+ 按需导入

  + 安装插件` cnpm i babel-plugin-imponpm i babel-plugin-import -S`

  + 配置

    ```jsx
    //npm run eject开启全部的配置
    //在packjson中进行相应的配置
     "babel": {
        "presets": [
          "react-app"
        ],
        "plugins": [
          ["import", { "libraryName": "antd-mobile", "style": "css" }]
        ]
      }
    ```

    

