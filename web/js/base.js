function changeColorWhite(ele) {
    ele.setAttribute("style", "color: white");
}
function changeColorDefault(ele) {
    ele.setAttribute("style", "color: #fdbd00");
}
function changeBgColorWhite(ele) {
    ele.setAttribute("style", "background-color: white");
}
function changeBgColorDefault(ele) {
    ele.setAttribute("style", "background-color: #fdbd00");
}
function changeBorderBlack(ele) {
    ele.setAttribute("style", " border-top-color: black;" +
        "    border-top-width: unset;" +
        "    border-top-style: solid;" +
        "    border-right-color: black;" +
        "    border-right-style: solid;" +
        "    border-right-width: unset;" +
        "    border-bottom-color: black;" +
        "    border-bottom-style: solid;" +
        "    border-bottom-width: unset;" +
        "    border-left-color: black;" +
        "    border-left-style: solid;" +
        "    border-left-width: unset;");
}
function changeBorderNone(ele) {
    ele.setAttribute("style", "border: none");
}