'use strict';

var url = "ws://localhost:8080/myWebLib/default";
//WebSocketオブジェクトの生成
var ws;

if ("WebSocket" in window) {
  ws = new WebSocket(url);
} else {
  output("WebSocketは使えません");
}

ws.onmessage = function(event){
  output(event.data);
}

ws.onopen = function(event){
  output("接続しました");
}

ws.onclose = function(event){
  output("切断しました");
}

function send(){
  var str = document.querySelector("#message");
  ws.send(str.value);
  str.value="";
}

function disconnect(){
  ws.close();
  ws = null;
}

function output(str) {
  document.getElementById("log").innerHTML += str + "<hr />";
}
