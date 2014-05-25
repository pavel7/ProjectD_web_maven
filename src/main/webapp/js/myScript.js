/**
 * Created by Павел on 26.05.2014.
 */
function isEmpty(temp) {

    if (temp.login.value == '') {
        alert("Please enter login");
        return false;
    } else if (temp.password.value == '') {
        alert("Please enter password");
        return false;
    }
}
