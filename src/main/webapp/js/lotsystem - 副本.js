var sys = {};
sys.endtime = '';
sys.local = '';
sys.getNowTime = function (l) {//获取彩票系统时间
    if (typeof(l) == 'string') {
        Lot.Name = l;
    }
    $.get('/lottery/Mode!Time.jzh', {lot: Lot.Name, t: local.TimeLong()}, function (data) {
        if (data == 'err') {//停售
            $('#get_data,#buy_btn_info').hide();
        } else {//20140317028#30#2014-03-17 10:32:47#2014-03-17 10:40:00#no#0
            var datas = data.split('##');
            var times = datas[0].split('#');
            Lot.IsOpen = Number(datas[2]);
            var newqh = times[0];
            if (Lot.Qihao != '-' && Lot.Qihao != newqh) {
                $('dd[pid=' + Lot.Qihao + ']').removeClass('active').addClass('disabled').find('input').attr('disabled', '');
                $('input[value=' + Lot.Qihao + ']').removeAttr("checked");
                $('input[pid=' + Lot.Qihao + ']').val('');
                $('select[name=startPeriod] option[value=' + Lot.Qihao + ']').remove();
                Base.ShowMoney();
                new $.bcbox({
                    title: '提示',
                    type: 'alert',
                    content: '<div style="padding:10px 15px;font-size:12px;text-align:left;font-family:simsun;"><div>您好，第<span class="c_ba2636"> ' + Lot.Qihao + ' </span> 期已截止，当前期是第<span class="c_ba2636"> ' + newqh + ' </span>期，<br>投注时请确认您选择的期号。</div><p style="margin:10px 0 0 0;"><span class="c_ba2636 endTime">1</span>秒后将自动关闭</p></div>',
                    autoClose: 10
                }).show();
            }
            Lot.Qihao = newqh;
            $('dd[pid=' + Lot.Qihao + ']>span.period').append('<em>(当前期)</em>');
            $('select[name=startPeriod] option[value=' + Lot.Qihao + ']').append('[当前期]');
            $('#bet_period').text(Lot.Qihao.substr(2, Lot.Qihao.length - 2));
            var date = new Date();
            sys.endtime = Date.parse(times[3].replace(/-/g, "/"));
            var server = Date.parse(times[2].replace(/-/g, "/"));
            sys.local = Math.round((Date.parse(date) - server) / 1000 - times[1]);
            if (Lot.IsOpen != 1) {//非停售
                CalculateTime();
            } else {
                $('#get_data,#buy_btn_info').hide();
                $('#end_time').html('<em>已停售</em>');
            }
            try {
                InitOmmit(datas[1], Lot.Name);
            } catch (e) {
            }

        }
    }).error(function () {
        window.setTimeout('sys.getNowTime(' + Lot.Name + ')', 5000);
    });
}
function CalculateTime() {
    var da = new Date();
    var nowtime = Math.round((sys.endtime - Date.parse(da)) / 1000) + parseInt(sys.local);
    if (nowtime > 0) {
        var t = Math.floor(nowtime / 3600);
        var y = nowtime % 3600;
        var m = Math.floor(y / 60);
        if (m < 10) {
            m = "0" + m;
        }
        var s = y % 60;
        if (s < 10) {
            s = "0" + s;
        }
        var timestr = '<em>' + m + '</em> 分 <em>' + s + '</em> 秒';
        if (t > 0) {
            timestr = '<em>' + t + '</em> 时' + timestr;
        }
        $('#end_time').html(timestr);
        window.setTimeout("CalculateTime()", 1000);
    } else {
        sys.getNowTime(Lot.Name);
    }
}
/**
 * 遗漏
 * */
function InitOmmit(o, l) {
    if ('Sd11x5' == l || 'Jx11x5' == l || 'Gd11x5' == l || 'Cq11x5' == l) {
        _11x5Ommit(o);
    } else if ('Pl3' == l || 'Fc3d' == l) {
        _FcP3Ommit(o);
    } else if ('Pl5' == l) {
        _Pl5Ommit(o);
    } else {
        _SscOmmit(o);
    }
}
function _SscOmmit(o) {
    var omms = o.split('#');
    addLine($('#box_wan>ul.fs_red_area'), omms[0]);
    addLine($('#box_qian>ul.fs_red_area'), omms[1]);
    addLine($('#box_bai>ul.fs_red_area'), omms[2]);
    addLine($('#box_shi>ul.fs_red_area'), omms[3]);
    addLine($('#box_ge>ul.fs_red_area'), omms[4]);
    addLine($('#box_zuxuan>ul.fs_red_area,#box_dt>ul.fs_red_area'), omms[5]);
    addLine($('#box_dxsd ul:eq(0)'), omms[7]);
    addLine($('#box_dxsd ul:eq(1)'), omms[8]);
}
function _11x5Ommit(o) {
    var omms = o.split('#');
    addLine($('#box_wan>ul.fs_red_area'), omms[0]);
    addLine($('#box_qian>ul.fs_red_area'), omms[1]);
    addLine($('#box_bai>ul.fs_red_area'), omms[2]);
    addLine($('#box_zuxuan>ul.fs_red_area,#box_dt>ul.fs_red_area'), omms[5]);
}
function _FcP3Ommit(o) {
    var omms = o.split('#');
    addLine($('#box_bai>ul.fs_red_area'), omms[0]);
    addLine($('#box_shi>ul.fs_red_area'), omms[1]);
    addLine($('#box_ge>ul.fs_red_area'), omms[2]);
    addLine($('#box_zuxuan>ul.fs_red_area,#box_dt>ul.fs_red_area'), omms[3]);
}
function _Pl5Ommit(o) {
    var omms = o.split('#');
    addLine($('#box_wan>ul.fs_red_area'), omms[0]);
    addLine($('#box_qian>ul.fs_red_area'), omms[1]);
    addLine($('#box_bai>ul.fs_red_area'), omms[2]);
    addLine($('#box_shi>ul.fs_red_area'), omms[3]);
    addLine($('#box_ge>ul.fs_red_area'), omms[4]);
}
function addLine(ob, omit) {
    var objs = omit.split(',');
    var max = 0;
    var maxtemp = 0;
    for (var i = 0; i < objs.length; i++) {
        var objsint = Number(objs[i]);
        if (objsint > maxtemp) {
            maxtemp = objsint;
            max = i;
        }
        ob.find('li:eq(' + i + ')>i').html(objsint);
    }
    ob.find('li:eq(' + max + ')>i').addClass('max');
}