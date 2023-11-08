function showClock()
{
    let date = new Date();
    let nowYear = date.getFullYear();
    let nowMonth = twoDigit(date.getMonth() + 1);
    let nowDate = twoDigit(date.getDate());
    let nowDay  = date.getDay();
    let nowHour = twoDigit(date.getHours());
    let nowMin  = twoDigit(date.getMinutes());
    let nowSec  = twoDigit(date.getSeconds());
    let nowDayStr = ["日", "月", "火", "水", "木", "金", "土"][nowDay];

    let msg = nowYear + "年" + nowMonth + "月" + nowDate + "日" + "(" + nowDayStr + ")"
            + " " + nowHour + ":" + nowMin + ":" + nowSec;

    document.getElementById("clock-time").innerHTML = msg;
}

function twoDigit(num) {
    let ret;
    if( num < 10 )
        ret = "0" + num;
    else
        ret = num;
    return ret;
}

function showDate()
{
    let date = new Date();
    let nowMonth = twoDigit(date.getMonth() + 1);
    let nowDate = twoDigit(date.getDate());
    let nowDay  = date.getDay();
    let nowDayStr = ["日", "月", "火", "水", "木", "金", "土"][nowDay];

    let msg = nowMonth + "月" + nowDate + "日" + "(" + nowDayStr + ")";

    document.getElementById("today").innerHTML = msg;
}