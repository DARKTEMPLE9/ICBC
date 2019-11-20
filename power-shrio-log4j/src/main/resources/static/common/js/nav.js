//主导航
var elNav = document.getElementById("nav");
var elLi = elNav.getElementsByTagName('li');
var domLi = document.querySelectorAll(".header #nav li");
for(var i=0; i<elLi.length; i++){
    elLi[i].addEventListener('click', function() {
       for(var j=0; j<elLi.length; j++){
           removeClass(elLi[j], "active");
       }
       addClass(this, "active");
    }, false);
}



/*domLi.forEach(function(v, i) {
  v.addEventListener('click', function() {
    let li = document.querySelectorAll("#nav li");
    li.forEach(function(liv, lii) {
      //liv.classList.remove("active");
    removeClass(this,"active");
    });
    //this.classList.add("active");
    addClass(this,"active");
}, false);
});*/

//添加
function addClass (elements, className) {
     if(!hasClass(elements,className)){
         elements.className += " " + className;
     }
}
//删除
function removeClass (elements, className) {
     if(hasClass(elements,className)){
         let newArr = elements.className.split(" ");
         newArr.splice(newArr.indexOf(className), 1);
         elements.className = newArr.join(" ");
     }
}
//是否存在
function hasClass (elements, cName) {
    return elements.className.indexOf(cName) > -1;
}
//侧栏
/*
resize();
windowResizeEvent(resize);
function resize(){
  var clientH = document.body.clientHeight;
  var headerH = document.querySelector(".header").offsetHeight;
  var aside = document.querySelector(".aside");
  var contentH = document.querySelector(".content");
  if(aside!=null)
  {
    aside.style.height = clientH - headerH + "px";
  }
  contentH.style.height = clientH - headerH - 40 + "px";
}*/
