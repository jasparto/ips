

jQuery(document).ready(function ($) {
    if (getCookie("active-menu") == 'true') {
        $('#sidebar').toggleClass('active');
        $('#logo-menu').toggleClass('active');
        $("div").data("active-menu", $('#sidebar').hasClass('active'));
    }

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
        $('#logo-menu').toggleClass('active');
        setCookie("active-menu", $('#sidebar').hasClass('active'), 1);
    });

    $('a.pageNav').click(function (event) {
        event.preventDefault();
        updateNav([{name: 'currentNav', value: $(this).attr('href')}]);
    });


});

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/ips";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}