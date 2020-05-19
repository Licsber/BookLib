function selectSingle() {
    check();
}

function selectAll() {
    var allCheckBox = document.getElementById('allCheckBox');
    var cartCheckBox = document.getElementsByName('cartCheckBox');
    for (var i = 0; i < cartCheckBox.length; i++) {
        cartCheckBox[i].checked = allCheckBox.checked;
    }
    check();
}

var bookNos = "";

function check() {
    var cartCheckBox = document.getElementsByName('cartCheckBox');
    var all_money = document.getElementsByClassName('cart_td_6');

    var sum = 0;
    bookNos = "";
    for (var i = 0; i < cartCheckBox.length; i++) {
        if (cartCheckBox[i].checked) {
            bookNos += cartCheckBox[i].value + ' ';
            sum += Number(all_money[i].innerHTML);
        }
    }

    bookNos = bookNos.trimRight();

    var removeSelect = document.getElementById("removeSelect");
    removeSelect.href = 'DoCarServlet?action=removeselect&bookNos=' + bookNos;
    var confirm = document.getElementById("confirm");
    confirm.href = 'DoCarServlet?action=立即购买&bookNos=' + bookNos;
    document.getElementById('total').innerHTML = sum;
}
