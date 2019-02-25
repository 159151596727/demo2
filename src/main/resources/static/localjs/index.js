function getTime() {
    var now = new Date(),hour = now.getHours();
    var str = "";
    if(hour < 6){str = "凌晨好！"}
    else if (hour < 9){str = "早上好！"}
    else if (hour < 12){str = "上午好！"}
    else if (hour < 14){str = "中午好！"}
    else if (hour < 17){str = "下午好！"}
    else if (hour < 19){str = "傍晚好！"}
    else if (hour < 22){str = "晚上好！"}
    else {str = "夜里好！"}
    document.getElementById('time').innerText = str;
}