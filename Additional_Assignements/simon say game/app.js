let gameseq=[];
let userseq=[];

let started=false;
let level=0;

let h2=document.querySelector("h2");
let btns=["yellow","red","green","orange"];

document.addEventListener("keypress",function(){
    if(started==false){
        console.log("game started");
        started=true;
    }
    levelup();
});

function gameFlash(btn){
    btn.classList.add("Flash");

    setTimeout(() => {
        btn.classList.remove("Flash");
    }, 250);
}

function userFlash(btn){
    btn.classList.add("userFlash");

    setTimeout(() => {
        btn.classList.remove("userFlash");
    }, 250);
}

function levelup(){
    userseq=[];
    level++;
    h2.innerText=`Level ${level}`;

    //random button flash
    let randidx=Math.floor(Math.random()*4);
    let randcolor=btns[randidx];
    let randbtn=document.querySelector(`.${randcolor}`);
    gameseq.push(randcolor);
    console.log(gameseq);
   gameFlash(randbtn);

}

function checkAns(idx){
    // console.log("curr level".level);
    // let idx=level-1;
    if(userseq[idx]===gameseq[idx]){
        console.log("same value");
        if(userseq.length==gameseq.length){
            setTimeout(levelup,1000);
        }
    }else{
        h2.innerHTML=`Game Over!! Your score was <b>${level*10}</b> <br> Press any key to start.`;
        document.querySelector("body").style.backgroundColor="red";
        setTimeout(function(){
            document.querySelector("body").style.backgroundColor="white";
        },150);
        reset();
    }
}

function btnPress(){
    // console.log(this);
    let btn=this;
    userFlash(btn);

    let userColor = btn.getAttribute("id");
    console.log(userColor);
    userseq.push(userColor);

    checkAns(userseq.length-1);
}

let allbtns=document.querySelectorAll(".btn");
for(btn of allbtns){
    btn.addEventListener("click",btnPress);
}

function reset(){
    started=false;
    gameseq=[];
    userseq=[];
    level=0;
}
