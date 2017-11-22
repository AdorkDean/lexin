var PName = [];
PName[300] = '大小单双';
PName[301] = '一星直选';
PName[302] = '二星直选';
PName[303] = '二星和值';
PName[304] = '二星组选';
PName[305] = '二组分位';
PName[306] = '二组包点';
PName[307] = '二组包胆';
PName[308] = '三星直选';
PName[309] = '三星和值';
PName[310] = PName[114] = '组三';
PName[322] = PName[120] = '组三单式';
PName[311] = PName[117] = '组六';
PName[312] = '三组包点';
PName[313] = '三组包胆';
PName[314] = '直选组合';
PName[315] = '四星直选';
PName[316] = '五星直选';
PName[317] = '五星通选';
PName[318] = '任选一';
PName[319] = '任选二';
PName[320] = PName[116] = '组三胆拖';
PName[321] = PName[119] = '组六胆拖';

PName[110] = PName[112] = '直选';
PName[111] = '单式';
PName[113] = '和值';
PName[115] = '组三和值';
PName[118] = '组六和值';

PName[350] = '前一';
PName[351] = '前二直选';
PName[352] = '前二组选';
PName[353] = '前三直选';
PName[354] = '前三组选';
PName[355] = '任选二';
PName[356] = '任选三';
PName[357] = '任选四';
PName[358] = '任选五';
PName[359] = '任选六';
PName[360] = '任选七';
PName[361] = '任选八';
PName[361] = '任选八';
PName[362] = '二组胆拖';
PName[363] = '三组胆拖';
PName[364] = '任二胆拖';
PName[365] = '任三胆拖';
PName[366] = '任四胆拖';
PName[367] = '任五胆拖';
PName[368] = '任六胆拖';
PName[369] = '任七胆拖';
PName[370] = '任八胆拖';

PName[400] = '和值';
PName[401] = '三同号通选';
PName[402] = '三同号单选';
PName[403] = '三不同号';
PName[405] = '三连号通选';
PName[406] = '二同号复选';
PName[407] = '二同号单选';
PName[408] = '二不同号';
PName[409] = '二不同胆拖';
PName[410] = '三不同胆拖';

PName[501] = '猜冠军';
PName[502] = '猜前三名';
PName[503] = '定位胆选';
local = {
    /**得到随机数*/
    getRandom: function (n) {
        var s = '';
        var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'j', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        for (var i = 0; i < Number(n); i++) {
            var id = Math.ceil(Math.random() * 35);
            s += chars[id];
        }
        return s;
    },
    getRandom: function (min, max) {//生成一个min到max的随机整数
        return Math.round((max - min) * Math.random() + min);
    },
    login: function () {//js登录
        location.href = '/login.html';
    },
    /**检查是否是邮箱*/
    CheckEmail: function (o) {
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return !reg.test(o);
    },
    /**当期时间毫秒*/
    TimeLong: function () {
        return (new Date()).getTime();
    },
    /**html获取页面参数*/
    GetID: function (s) {
        var reg = new RegExp("(^|&)" + s + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI((r[2]));
        return null;
    },
    /**获取字符串长度*/
    GetLenth: function (s) {
        var reg = /^[\u0391-\uFFE5]+$/;
        var len = s.length;
        var l = 0;
        for (var i = 0; i < len; i++) {
            var tmp = s.slice(0, 1);
            if (tmp.match(reg)) {
                l += 2;
            } else {
                l += 1;
            }
            s = s.substring(1);
        }
        return l;
    },
    /**头部导航栏*/
    NavInit: function (u, m) {
        var h = '<div>';
        if (u.length > 0) {
            h += '欢迎您，<a href="/my/Index.jzh" target="_blank">' + u + '</a>&nbsp;&nbsp;[&nbsp;余额：<font color="#ba2636">' + m + '</font>&nbsp;]&nbsp;&nbsp;[&nbsp;<a target="_blank" href="/my/Rechange.jzh">充值</a>&nbsp;]&nbsp;&nbsp;[&nbsp;<a target="_blank" href="/my/Rechange!Draw.jzh">提款</a>&nbsp;]&nbsp;&nbsp;[&nbsp;<a href="/my/Index!Out.jzh">退出</a>&nbsp;]</div>';
        } else {
            location.href = '/login.html';
            h += '欢迎来到网易彩票！<a onclick="local.login()" href="javascript:void(0);">请登录</a> <a target="_blank" href="/regname.html">免费注册</a></div>';
        }
        $('#topNavLeft').html(h);
    },
    ScrollImg: function () {//图片轮询
        var PicTotal = 5;// 当前图片总数
        var CurrentIndex;// 当前鼠标点击图片索引
        var ToDisplayPicNumber = 0;// 自动播放时的图片索引
        $('#cpIndexAdBox div.ctrol a').mouseover(function () {
            CurrentIndex = $(this).index();
            $(this).parent().children().removeClass("active");
            $(this).addClass("active");
            $('#cpIndexAdBox .adList').animate({left: '-' + CurrentIndex * 520 + 'px'}, 200);
        });
        var p = function () {
            $('#cpIndexAdBox div.ctrol a').eq(ToDisplayPicNumber).trigger("mouseover");
            ToDisplayPicNumber = (ToDisplayPicNumber + 1) % PicTotal;
            setTimeout(p, 4000);
        }
        setTimeout(p, 2000);
    },
    scrollMaquee: function (div, time, num, m) {//div元素,time延时时间，num一次轮换多少，m出现速度
        var s = false;
        var o = $(div);
        o.hover(function () {
            s = true
        }, function () {
            s = false
        });
        setInterval(scrol, time * 1000);
        var oh = o.height();
        var childh = o.children(':first').height();
        var ph = o.parent().height();
        var point = 0;

        function scrol() {
            if (!s) {
                point += num * childh;
                o.animate({'margin-top': '-' + point + 'px'}, m);
                if (point + ph >= oh - childh) {
                    point = 0 - num * childh;
                }
            }
        }
    }
}
Show = {
    NewsType: function (s) {//新闻的类型
        var r;
        switch (s) {
            case '0':
                r = '公告';
                break;
            case '1':
                r = '新闻';
                break;
            case '2':
                r = '攻略';
                break;
            case '20':
                r = '重庆时时彩';
                break;
            case '21':
                r = '江西时时彩';
                break;
            case '22':
                r = '山东11选5';
                break;
            case '23':
                r = '江西11选5';
                break;
            case '24':
                r = '广东11选5';
                break;
            case '25':
                r = '重庆11选5';
                break;
            case '26':
                r = '河内时时彩';
                break;
            case '27':
                r = '江苏快三';
                break;
            case '50':
                r = '双色球';
                break;
            case '51':
                r = '福彩3D';
                break;
            case '52':
                r = '大乐透';
                break;
            case '53':
                r = '排列三';
                break;
            case '54':
                r = '排列五';
                break;
            default:
                r = '--';
        }
        return r;
    },
    BuyStatus: function (s) {//订单状态------
        var r;
        switch (Number(s)) {
            case -1:
                r = '<font color="#FE5400">未满员</font>';
                break;
            case 0:
                r = '<font color="#0091D1">进行中</font>';
                break;
            case 1:
                r = '<font color="red">已中奖</font>';
                break;
            case 2:
                r = '<font color="#727171">未中奖</font>';
                break;
            default:
                r = '<font color="#727171">已撤单</font>';
        }
        return r;
    },
    LotStatus: function (s) {//期号的状态------
        var r;//-1等待满员 0等待出票 1等待开奖 2已中奖 3未中奖 4撤单
        switch (Number(s)) {
            case -1:
                r = '<font color="#1E50A2">等待满员</font>';
                break;
            case 0:
                r = '<font color="#71B200">等待出票</font>';
                break;
            case 1:
                r = '<font color="#FE5400">等待开奖</font>';
                break;
            case 2:
                r = '<font color="#ba2636">已中奖</font>';
                break;
            case 4:
                r = '<font color="#717171">已撤单</font>';
                break;
            default:
                r = '未中奖';
        }
        return r;
    },
    UserLevel: function (s) {//用户战绩
        var r = '';
        var n = Number(s);
        var l = Math.floor(n / 1000);
        if (l > 0) {
            l = l > 9 ? 9 : l;
            r += '<em class="gains_imgs crown_lv' + l + '"></em>';
            n = n % 1000;
        }
        l = Math.floor(n / 100);
        if (l > 0) {
            l = l > 9 ? 9 : l;
            r += '<em class="gains_imgs sun_lv' + l + '"></em>';
            n = n % 100;
        }
        l = Math.floor(n / 10);
        if (l > 0) {
            l = l > 9 ? 9 : l;
            r += '<em class="gains_imgs moon_lv' + l + '"></em>';
            n = n % 10;
        }
        l = Math.floor(n);
        if (l > 0) {
            l = l > 9 ? 9 : l;
            r += '<em class="gains_imgs star_lv' + l + '"></em>';
        }
        return r;
    },
    LotName: function (s) {//彩种名字
        var r;
        switch (s) {
            case 'Cqssc':
                r = '重庆时时彩';
                break;
            case 'Jxssc':
                r = '江西时时彩';
                break;
            case 'Hnssc':
                r = '河内五分彩';
                break;
            case 'Jsk3':
                r = '江苏快3';
                break;
            case 'Sd11x5':
                r = '十一运夺金';
                break;
            case 'Jx11x5':
                r = '江西11选5';
                break;
            case 'Gd11x5':
                r = '广东11选5';
                break;
            case 'Cq11x5':
                r = '重庆11选5';
                break;
            case 'Ssq':
                r = '双色球';
                break;
            case 'Dlt':
                r = '大乐透';
                break;
            case 'Fc3d':
                r = '福彩3D';
                break;
            case 'Pl3':
                r = '排列三';
                break;
            case 'Pl5':
                r = '排列五';
                break;
            case 'Bjpk10':
                r = '北京PK10';
                break;
            default:
                break;
        }
        return r;
    },
    LogoName: function (s) {//logo和彩种
        var r;
        switch (s) {
            case 'Ssq':
                r = '<span class="cz_logo cz_ssq"></span><h1>双色球</h1>';
                break;
            case 'Dlt':
                r = '<span class="cz_logo cz_dlt"></span><h1>大乐透</h1>';
                break;
            case 'Pl5':
                r = '<span class="cz_logo cz_pl5"></span><h1>排列五</h1>';
                break;
            case 'Fc3d':
                r = '<span class="cz_logo cz_x3d"></span><h1>福彩3D</h1>';
                break;
            case 'Pl3':
                r = '<span class="cz_logo cz_pl3"></span><h1>排列三</h1>';
                break;
            case 'Cqssc':
                r = '<span class="cz_logo cz_ssc"></span><h1>重庆时时彩</h1>';
                break;
            case 'Jsk3':
                r = '<span class="cz_logo cz_kuai3"></span><h1>江苏快3</h1>';
                break;
            case 'Jxssc':
                r = '<span class="cz_logo cz_jxssc"></span><h1>江西时时彩</h1>';
                break;
            case 'Hnssc':
                r = '<span class="cz_logo cz_jxssc"></span><h1>河内五分彩</h1>';
                break;
            case 'Sd11x5':
                r = '<span class="cz_logo cz_d11"></span><h1>十一运夺金</h1>';
                break;
            case 'Jx11x5':
                r = '<span class="cz_logo cz_jxd11"></span><h1>江西11选5</h1>';
                break;
            case 'Gd11x5':
                r = '<span class="cz_logo cz_gdd11"></span><h1>广东11选5</h1>';
                break;
            case 'Cq11x5':
                r = '<span class="cz_logo cz_hljd11"></span><h1>重庆11选5</h1>';
                break;
            default:
                r = '<span class="cz_logo cz_d11"></span><h1></h1>';
        }
        return r;
    },
    LotHaoma: function (s) {//号码显示
        var r = '';
        var hrr = s.split(',');
        for (var i = 0; i < hrr.length; i++) {
            r += '<strong class="smallRedball">' + hrr[i] + '</strong>';
        }
        return r;
    },
    LotCode: function (s) {//认购内容显示
        var r = '';
        var codes = s.split('#');
        for (var i = 0; i < codes.length; i++) {
            var code = codes[i].split(':');
            r += '<tr><td>[' + PName[code[0]] + ']' + code[1] + '</td><td>' + code[2] + '</td></tr>';
        }
        return r;
    },
    LotCode2: function (s) {//认购内容显示
        var r = '';
        var codes = s.split('#');
        for (var i = 0; i < codes.length; i++) {
            var code = codes[i].split(':');
            r += '[' + PName[code[0]] + ']' + code[1] + ' 注数：' + code[2] + '<br>';
        }
        return r;
    },
    LotPro: function (b, h, m, s) {//进度---
        var r = '';
        if (s == -1) {
            var jd = Math.floor((m - h) * 100 / m);
            r = '<li>方案进度：<div class="progressBox baodi">';
            if (b < 1) {
                r += '<div class="progressBar"><span style="width:' + jd + '%" class="progress"><strong>' + jd + '</strong>%</span></div>';
            } else {
                var bd = Math.floor(b * 100 / m);
                r += '<div class="progressBar"><span style="width:' + jd + '%" class="progress"><strong>' + jd + '</strong>%</span><i style="right:' + bd + '%"></i></div><em class="icoBaodi">保底<strong class="c_ba2636">' + bd + '%</strong></em>';
            }
            r += '</div></li>';
        }
        return r;
    },
    MyDetType: function (t, i) {//0购彩派奖 1购买彩票 2用户充值 3用户提款 4取消提款 5积分兑换 6方案保底 7保底返还 8方案撤单 9用户返利
        var r = '<td>';
        var u = '</td><td><a target="_blank" href="/';
        switch (Number(t)) {
            case 0:
                r += '购彩派奖';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            case 1:
                r += '购买彩票';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            case 2:
                r += '用户充值';
                u + '';
                break;
            case 3:
                r += '用户提款';
                u + '';
                break;
            case 4:
                r += '取消提款';
                u + '';
                break;
            case 5:
                r += '积分兑换';
                u + '';
                break;
            case 6:
                r += '方案保底';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            case 7:
                r += '保底返还';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            case 8:
                r += '方案撤单';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            case 9:
                r += '用户返利';
                u += 'lottery/BuyLot.jzh?spm=';
                break;
            default:
                r += '--';
        }
        r += u + i + '">' + i + '</a></td>';
        return r;
    },
    MyDetTypeOne: function (t) {//0购彩派奖 1购买彩票 2用户充值 3用户提款 4取消提款 5积分兑换 6方案保底 7保底返还 8方案撤单 9用户返利
        var r = '';
        switch (Number(t)) {
            case 0:
                r = '购彩派奖';
                break;
            case 1:
                r = '购买彩票';
                break;
            case 2:
                r = '用户充值';
                break;
            case 3:
                r = '用户提款';
                break;
            case 4:
                r = '取消提款';
                break;
            case 5:
                r = '积分兑换';
                break;
            case 6:
                r = '方案保底';
                break;
            case 7:
                r = '保底返还';
                break;
            case 8:
                r = '方案撤单';
                break;
            case 9:
                r = '用户返利';
                break;
            default:
                r = '--';
        }
        return r;
    },
    MyRechType: function (s) {
        var r = '';
        switch (s) {
            case '0':
                r = '手工充值';
                break;
            case '1':
                r = '网银在线';
                break;
            default:
                r = '--';
        }
        return r;
    },
    MyRechStatu: function (s) {
        var r = '';
        switch (s) {
            case '0':
                r = '待支付';
                break;
            case '1':
                r = '<font color="red">已支付</font>';
                break;
            default:
                r = '--';
        }
        return r;
    },
    MyDrawStatu: function (s) {
        var r = '';
        switch (s) {
            case '0':
                r = '<font color="#1E50A2">申请中</font>';
                break;
            case '1':
                r = '<font color="red">处理中</font>';
                break;
            case '2':
                r = '已成功';
                break;
            case '3':
                r = '用户撤销';
                break;
            case '4':
                r = '拒绝提款';
                break;
            default:
                r = '--';
        }
        return r;
    },
    MyPointType: function (t, i) {
        var r = '<td>';
        var u = '</td><td><a target="_blank" href="/';
        switch (t) {
            case '0':
                r += '中奖派发' + u + 'lottery/Item.jzh?spm=';
                break;
            case '1':
                r += '积分兑换' + u + 'lottery/Item.jzh?spm=';
                break;
            default:
                r += '--' + u + 'index.html?';
        }
        r += i + '">' + i + '</a></td>';
        return r;
    },
    MyPointTypeOne: function (t) {
        var r = '';
        switch (t) {
            case '0':
                r = '中奖派发';
                break;
            case '1':
                r = '积分兑换';
                break;
            default:
                r = '--';
        }
        return r;
    },
    MyRedType: function (t, i) {
        var r = '<td>';
        var u = '</td><td><a target="_blank" href="/';
        switch (t) {
            case '0':
                r += '活动购买' + u + 'lottery/Item.jzh?spm=';
                break;
            case '1':
                r += '购彩消费' + u + 'lottery/Item.jzh?spm=';
                break;
            default:
                r += '--' + u + 'index.html?';
        }
        r += i + '">' + i + '</a></td>';
        return r;
    },
    MyRedTypeOne: function (t) {
        var r = '';
        switch (t) {
            case '0':
                r += '活动购买';
                break;
            case '1':
                r += '购彩消费';
                break;
            default:
                r += '--';
        }
        return r;
    },
    MyLogsType: function (s) {
        var r = '';
        switch (s) {
            case '0':
                r = '用户登录';
                break;
            case '1':
                r = '用户注册';
                break;
            case '2':
                r = '密码错误';
                break;
            default:
                r = '--';
        }
        return r;
    },
}
//去重复
function distinct(k) {
    k = k.replace(/0([1-9])/g, '$1');
    k = k.split(',').sort(function (a, b) {
            return parseInt(a, 10) - parseInt(b, 10)
        }).join(',') + ',';
    var syydj = /(\d+,)\1/;
    return syydj.test(k);
}
String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
/**
 * 删除数组指定下标或指定对象
 * arr.remove(2);//删除下标为2的对象（从0开始计算）
 * arr.remove(str);//删除指定对象
 */
Array.prototype.remove = function (obj) {
    for (var i = 0; i < this.length; i++) {
        var temp = this[i];
        if (!isNaN(obj)) {
            temp = i;
        }
        if (temp == obj) {
            for (var j = i; j < this.length; j++) {
                this[j] = this[j + 1];
            }
            this.length = this.length - 1;
        }
    }
}