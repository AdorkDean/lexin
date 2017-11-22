var hmsta = false;
$(function () {
    $('#numberUserTab li').click(function () {
        $('#numberUserTab li').removeClass('active');
        $(this).addClass('active');
        var ind = $(this).index();
        if (ind == 0) {
            $('#userBox').hide();
            $('#numberDetail').show();
        } else {
            $('#numberDetail').hide();
            $('#userBox').show();
        }
    });
    $('#rgInput').keyup(function () {
        $(this).val($(this).val().replace(/[^\d]/g, ''));
        var num = Number($(this).val() == 0 ? 1 : $(this).val());
        var max = Number($(this).attr('max'));
        num = num > max ? max : num;
        $(this).val(num);
    });
    $('#tzBtn').click(function () {
        if (hmsta) {
            alert('正在提交数据，请稍等。');
            return;
        }
        var mon = Number($('#rgInput').val());
        var max = Number($('#rgInput').attr('max'));
        if (mon == 0) {
            alert('请输入您要认购的金额。');
            return;
        } else if (mon > max) {
            alert('最多只能认购' + max + '元');
        }
        var item = $('#item').val();
        var lot = $('#lot').val();
        var fqh = $('#fqh').val();
        hmsta = true;
        $.post('/lottery/BuyLot!AddHM.jzh', {
            item: item,
            lot: lot,
            fqh: fqh,
            mon: mon,
            t: local.TimeLong()
        }, function (data) {
            switch (data) {//-1期号过期 0成功 1订单剩余金额不够 2用户余额不足 3订单不存在
                case 'no':
                    alert('您还未登录');
                    break;
                case '-1':
                    alert('期号已过期。');
                    break;
                case '0':
                    alert('购买成功。');
                    location.reload();
                    break;
                case '1':
                    alert('订单金额不够。');
                    break;
                case '2':
                    alert('您余额不足。');
                    break;
                case '3':
                    alert('订单不存在。');
                    break;
                default:
                    alert('购买失败！');
                    break;
                    hmsta = false;
            }
        });
    });
});
