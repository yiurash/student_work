//v.3.6 build 130417

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/
dhtmlXGridObject.prototype.enableBlockSelection=function(b){if(typeof this._bs_mode=="undefined"){var c=this;this.obj.onmousedown=function(b){c._bs_mode&&c._OnSelectionStart(b||event,this);return!0};this._CSVRowDelimiter=this.csv.row;this.attachEvent("onResize",function(){c._HideSelection();return!0});this.attachEvent("onGridReconstructed",function(){c._HideSelection();return!0});this.attachEvent("onFilterEnd",this._HideSelection)}if(b===!1)return this._bs_mode=!1,this._HideSelection();else this._bs_mode=
!0};dhtmlXGridObject.prototype.forceLabelSelection=function(b){this._strictText=convertStringToBoolean(b)};dhtmlXGridObject.prototype.disableBlockSelection=function(){this.obj.onmousedown=null};
dhtmlXGridObject.prototype._OnSelectionStart=function(b){var c=this;if(b.button!=2){var a=b.srcElement||b.target;if(this.editor){if(a.tagName&&(a.tagName=="INPUT"||a.tagName=="TEXTAREA"))return;this.editStop()}c.isActive||c.setActive(!0);var d=this.getPosition(this.obj),f=b.clientX-d[0]+(document.body.scrollLeft||(document.documentElement?document.documentElement.scrollLeft:0)),h=b.clientY-d[1]+(document.body.scrollTop||(document.documentElement?document.documentElement.scrollTop:0));this._CreateSelection(f-
4,h-4);if(a==this._selectionObj)this._HideSelection(),this._startSelectionCell=null;else{for(;a.tagName.toLowerCase()!="td";)a=a.parentNode;this._startSelectionCell=a}if(this._startSelectionCell&&!this.callEvent("onBeforeBlockSelected",[this._startSelectionCell.parentNode.idd,this._startSelectionCell._cellIndex]))return this._startSelectionCell=null;this.obj.onmousedown=null;this.obj[_isIE?"onmouseleave":"onmouseout"]=function(){c._blsTimer&&window.clearTimeout(c._blsTimer)};this.obj.onmmold=this.obj.onmousemove;
this._init_pos=[f,h];this._selectionObj.onmousemove=this.obj.onmousemove=function(a){a=a||b;a.returnValue=!1;c._OnSelectionMove(a)};this._oldDMP=document.body.onmouseup;document.body.onmouseup=function(a){a=a||b;c._OnSelectionStop(a,this);return!0};this.callEvent("onBeforeBlockSelection",[]);document.body.onselectstart=function(){return!1}}};
dhtmlXGridObject.prototype._getCellByPos=function(b,c){this._fake&&(b+=this._fake.entBox.offsetWidth);for(var a=0,d=0;d<this.obj.rows.length;d++)if(c-=this.obj.rows[d].offsetHeight,c<=0){a=this.obj.rows[d];break}if(!a||!a.idd)return null;for(d=0;d<this._cCount;d++)if(b-=this.getColWidth(d),b<=0)for(;;)if(a._childIndexes&&a._childIndexes[d+1]==a._childIndexes[d])a=a.previousSibling;else return this.cells(a.idd,d).cell;return null};
dhtmlXGridObject.prototype._OnSelectionMove=function(b){var c=this;this._ShowSelection();var a=this.getPosition(this.obj),d=b.clientX-a[0]+(document.body.scrollLeft||(document.documentElement?document.documentElement.scrollLeft:0)),f=b.clientY-a[1]+(document.body.scrollTop||(document.documentElement?document.documentElement.scrollTop:0));if(Math.abs(this._init_pos[0]-d)<5&&Math.abs(this._init_pos[1]-f)<5)return this._HideSelection();var h=this._endSelectionCell;if(this._startSelectionCell==null)this._endSelectionCell=
this._startSelectionCell=this.getFirstParentOfType(b.srcElement||b.target,"TD");else if(b.srcElement||b.target)if((b.srcElement||b.target).className=="dhtmlxGrid_selection")this._endSelectionCell=this._getCellByPos(d,f)||this._endSelectionCell;else{var l=this.getFirstParentOfType(b.srcElement||b.target,"TD");if(l.parentNode.idd)this._endSelectionCell=l}if(this._endSelectionCell&&!this.callEvent("onBeforeBlockSelected",[this._endSelectionCell.parentNode.idd,this._endSelectionCell._cellIndex]))this._endSelectionCell=
h;var j=this.objBox.scrollLeft+this.objBox.clientWidth,i=this.objBox.scrollTop+this.objBox.clientHeight,k=this.objBox.scrollLeft,e=this.objBox.scrollTop,g=!1;this._blsTimer&&window.clearTimeout(this._blsTimer);d+20>=j?(this.objBox.scrollLeft+=20,g=!0):d-20<k&&(this.objBox.scrollLeft-=20,g=!0);f+20>=i&&!this._realfake?(this.objBox.scrollTop+=20,g=!0):f-20<e&&!this._realfake&&(this.objBox.scrollTop-=20,g=!0);this._selectionArea=this._RedrawSelectionPos(this._startSelectionCell,this._endSelectionCell);
if(g){var m=b.clientX,n=b.clientY;this._blsTimer=window.setTimeout(function(){c._OnSelectionMove({clientX:m,clientY:n})},100)}};
dhtmlXGridObject.prototype._OnSelectionStop=function(b){var c=this;this._blsTimer&&window.clearTimeout(this._blsTimer);this.obj.onmousedown=function(a){c._bs_mode&&c._OnSelectionStart(a||b,this);return!0};this.obj.onmousemove=this.obj.onmmold||null;this._selectionObj.onmousemove=null;document.body.onmouseup=this._oldDMP||null;if(parseInt(this._selectionObj.style.width)<2&&parseInt(this._selectionObj.style.height)<2)this._HideSelection();else{var a=this.getFirstParentOfType(b.srcElement||b.target,
"TD");if(!a||!a.parentNode.idd)a=this._endSelectionCell;if(!a)return this._HideSelection();for(;a.tagName.toLowerCase()!="td";)a=a.parentNode;this._stopSelectionCell=a;this._selectionArea=this._RedrawSelectionPos(this._startSelectionCell,this._stopSelectionCell);this.callEvent("onBlockSelected",[])}document.body.onselectstart=function(){}};
dhtmlXGridObject.prototype._RedrawSelectionPos=function(b,c){var a={};a.LeftTopCol=b._cellIndex;a.LeftTopRow=this.getRowIndex(b.parentNode.idd);a.RightBottomCol=c._cellIndex;a.RightBottomRow=this.getRowIndex(c.parentNode.idd);var d=b.offsetWidth,f=b.offsetHeight,b=this.getPosition(b,this.obj),h=c.offsetWidth,l=c.offsetHeight,c=this.getPosition(c,this.obj);if(b[0]<c[0])var j=b[0],i=c[0]+h;else{var k=a.RightBottomCol;a.RightBottomCol=a.LeftTopCol;a.LeftTopCol=k;j=c[0];i=b[0]+d}if(b[1]<c[1])var e=b[1],
g=c[1]+l;else k=a.RightBottomRow,a.RightBottomRow=a.LeftTopRow,a.LeftTopRow=k,e=c[1],g=b[1]+f;var m=i-j,n=g-e;this._selectionObj.style.left=j+"px";this._selectionObj.style.top=e+"px";this._selectionObj.style.width=m+"px";this._selectionObj.style.height=n+"px";return a};
dhtmlXGridObject.prototype._CreateSelection=function(b,c){if(this._selectionObj==null){var a=document.createElement("div");a.style.position="absolute";a.style.display="none";a.className="dhtmlxGrid_selection";this._selectionObj=a;this._selectionObj.onmousedown=function(a){a=a||event;if(a.button==2||_isMacOS&&a.ctrlKey)return this.parentNode.grid.callEvent("onBlockRightClick",["BLOCK",a])};this._selectionObj.oncontextmenu=function(a){(a||event).cancelBubble=!0;return!1};this.objBox.appendChild(this._selectionObj)}this._selectionObj.style.width=
"0px";this._selectionObj.style.height="0px";this._selectionObj.style.left=b+"px";this._selectionObj.style.top=c+"px";this._selectionObj.startX=b;this._selectionObj.startY=c};dhtmlXGridObject.prototype._ShowSelection=function(){if(this._selectionObj)this._selectionObj.style.display=""};dhtmlXGridObject.prototype._HideSelection=function(){if(this._selectionObj)this._selectionObj.style.display="none";this._selectionArea=null};
dhtmlXGridObject.prototype.copyBlockToClipboard=function(){if(this._selectionArea!=null){var b=[];this._agetm=this._mathSerialization?"getMathValue":this._strictText?"getTitle":"getValue";for(var c=this._selectionArea.LeftTopRow;c<=this._selectionArea.RightBottomRow;c++){var a=this._serializeRowToCVS(this.rowsBuffer[c],null,this._selectionArea.LeftTopCol,this._selectionArea.RightBottomCol+1);b[b.length]=this._csvAID?a:a.substr(a.indexOf(this.csv.cell)+1)}b=b.join(this._CSVRowDelimiter);this.toClipBoard(b)}};
dhtmlXGridObject.prototype.pasteBlockFromClipboard=function(){var b=this.fromClipBoard();if(this._selectionArea!=null)var c=this._selectionArea.LeftTopRow,a=this._selectionArea.LeftTopCol;else if(this.cell!=null&&!this.editor)c=this.getRowIndex(this.cell.parentNode.idd),a=this.cell._cellIndex;else return!1;var b=this.csvParser.unblock(b,this.csv.cell,this.csv.row),d=c+b.length,f=a+b[0].length;if(f>this._cCount)f=this._cCount;for(var h=0,l=c;l<d;l++){var j=this.render_row(l);if(j!=-1){for(var i=0,
k=a;k<f;k++){var e=this.cells3(j,k);if(e.isDisabled())i++;else{this._onEditUndoRedo&&this._onEditUndoRedo(2,j.idd,k,b[h][i],e.getValue());if(e.combo){for(var g=e.combo.values,m=0;m<g.length;m++)if(b[h][i]==g[m]){e.setValue(e.combo.keys[m]);g=null;break}g!=null&&e.editable?e.setValue(b[h][i++]):i++}else e[e.setImage?"setLabel":"setValue"](b[h][i++]);e.cell.wasChanged=!0}}this.callEvent("onRowPaste",[j.idd]);h++}}};

dhtmlXGridObject.prototype.pasteBlockFromClipboardCustom = function(cStColm,restrictedColms)
{
	var serialized = this.fromClipBoard();
    if (this._selectionArea != null) {
        var startRow = this._selectionArea.LeftTopRow;
        var startCol = this._selectionArea.LeftTopCol;
        var endRow1 = this._selectionArea.RightBottomRow;
    } else if (this.cell != null && !this.editor) {
        var startRow = this.getRowIndex( this.cell.parentNode.idd );
        var startCol = this.cell._cellIndex;
        var endRow1 = startRow;
    } else {
        return false;
    }

	serialized = this.csvParser.unblock(serialized, this.csv.cell, this.csv.row);
	
	
   // if ((serialized.length >1)&&(serialized[serialized.length-1]==""))
   // serialized.splice(serialized.length-1,1);
     
   //	if (serialized[serialized.length-1]=="") serialized.pop();
 /*   for (var i=0; i<serialized.length; i++) {
        serialized[i] = serialized[i].split(this.csv.cell);
    }*/
	
	
		
    var endRow = startRow+serialized.length;
    var endCol = startCol+serialized[0].length;
    if(endRow1 > endRow){
    	endRow = endRow1+1;
    }
	var arr = restrictedColms.split(",");
   if (endCol > this._cCount)
		endCol = this._cCount;
    var k = 0;
    var alertMsg ="";
    for (var i=startRow; i<endRow; i++) {
    	try{
        var row = this.render_row(i);
        if (row==-1) continue;
        var l = 0;
        for (var j=startCol; j<endCol; j++) {
        	var ed = this.cells3(row, j);
        	var type=this.getColType(j);
        
        	if (ed.isDisabled() || type == "ro") {
        	    l++;
        	    continue;
        	}else if(cStColm != startCol){
        		for(var n=0; n<arr.length; n++){
       			 if(arr[n] == j){
       				 if(i == startRow){
       				    alertMsg = alertMsg+this.getColLabel(j)+",";
       				 }
       				l++;
       	         	continue; 
       			 }
       		   }
        	}
        	
        	var colId=this.getColumnId(j);
        	//alert(colId);
           // if(startCol != cTopColm && coldId){
        		
        		
        	//}
       
        	if(alertMsg == ""){
        	
        	if (this._onEditUndoRedo)
        		this._onEditUndoRedo(2, row.idd, j, serialized[ k ][ l ], ed.getValue());
        	if (ed.combo){
				var comboVa = ed.combo.values;
				for(var n=0; n<comboVa.length; n++)
					if (serialized[ k ][ l ] == comboVa[n]){
						ed.setValue( ed.combo.keys[ n ]);
						comboVa=null;
						break;
					}
				if (comboVa!=null && ed.editable) ed.setValue(serialized[ k ][ l++ ]);
				else l++;
        	}else
        		ed[ ed.setImage ? "setLabel" : "setValue" ]( serialized[ k ][ l++ ] );
        	ed.cell.wasChanged=true;
        	
        	}	
        }
        this.callEvent("onRowPaste",[row.idd])
        k++;
        if(serialized.length <= k){
    		k=0;
    	}
    	}catch(e){}
    }
    
    if(alertMsg != ""){
    	alertMsg = alertMsg.substring(0, alertMsg.length-1);
    	alert("Paste not allowed for the following columns from other column cells: "+alertMsg);
    }
    
};
dhtmlXGridObject.prototype.getSelectedBlock=function(){return this._selectionArea?this._selectionArea:this.getSelectedRowId()!==null?{LeftTopRow:this.getSelectedRowId(),LeftTopCol:this.getSelectedCellIndex(),RightBottomRow:this.getSelectedRowId(),RightBottomCol:this.getSelectedCellIndex()}:null};



//v.3.6 build 130417

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/