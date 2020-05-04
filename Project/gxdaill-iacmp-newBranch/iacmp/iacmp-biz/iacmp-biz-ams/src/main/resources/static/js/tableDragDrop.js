function colResizables(table) {
    var rows1 = table.rows.length;
    var colums1 = table.rows[0].cells.length;
    var tTD; //用来存储当前更改宽度的Table Cell,避免快速移动鼠标的问题
    if (table.rows[1].cells.length != 1 && table.rows[1].cells.length != 0) {
        for (j = 1; j < rows1; j++) {
            for (i = 0; i < colums1; i++) {
                table.rows[j].cells[i].onmousedown = function () {
                    //记录单元格
                    tTD = this;
                    if (event.offsetX > tTD.offsetWidth - 10) {
                        tTD.mouseDown = true;
                        tTD.oldX = event.x;
                        tTD.oldWidth = tTD.offsetWidth;
                    }
                    //记录Table宽度
                    //table = tTD; while (table.tagName != ‘TABLE') table = table.parentElement;
                    //tTD.tableWidth = table.offsetWidth;
                };

                table.rows[j].cells[i].onmouseup = function () {
                    //结束宽度调整
                    if (tTD == undefined) tTD = this;
                    tTD.mouseDown = false;
                    tTD.style.cursor = 'default';
                };

                table.rows[j].cells[i].onmousemove = function () {
                    //更改鼠标样式
                    if (event.offsetX > this.offsetWidth - 10)
                        this.style.cursor = 'col-resize';
                    else
                        this.style.cursor = 'default';
                    //取出暂存的Table Cell
                    if (tTD == undefined) tTD = this;
                    //调整宽度
                    if (tTD.mouseDown != null && tTD.mouseDown == true) {
                        tTD.style.cursor = 'default';
                        if (tTD.oldWidth + (event.x - tTD.oldX) > 0)
                            tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
                        //调整列宽
                        tTD.style.width = tTD.width;
                        tTD.style.cursor = 'col-resize';
                        //调整该列中的每个Cell
                        table = tTD;
                        while (table.tagName != 'TABLE') table = table.parentElement;
                        for (j = 0; j < table.rows.length; j++) {
                            table.rows[j].cells[tTD.cellIndex].width = tTD.width;
                        }
                        //调整整个表
                        //table.width = tTD.tableWidth + (tTD.offsetWidth – tTD.oldWidth);
                        //table.style.width = table.width;
                    }
                };

            }
            ;
        }
    }
}