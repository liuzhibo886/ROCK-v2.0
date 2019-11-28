var Util = {
		/**
		 * 元转分
		 */
	fen : function(yuan){
		if(yuan == undefined || yuan == '' || yuan == null || isNaN(yuan)){
			return 0;
		}else{
			return Math.round(yuan*100);
		}
	},
	/**
	 * 分转元
	 */
	yuan : function(fen){
		if(fen == undefined || fen == '' || fen == null || isNaN(fen)){
			return 0;
		}else{
			return (yuan/100).toFixed(2);
		}
	},
	wyuan : function(fen){
		if(fen == undefined || fen == '' || fen == null || isNaN(fen)){
			return 0;
		}else{
			return (fen/1000000).toFixed(2);
		}
	},
	kg : function(g){
		if(g == undefined || g == '' || g == null || isNaN(g)){
			return 0;
		}else{
			return (g/1000).toFixed(2);
		}
	},
	/**
	 * 枚举格式化转换,逆向转换
	 */
	format : function(format,num){
		if(format == undefined ||  format == '' || format == null){
			return num;
		}if(num == undefined || num == '' || num == null || isNaN(num)){
			return 0;
		}else if(format == 'YUAN'){
			return num*100;
		}else if(format == 'WYUAN'){
			return num*1000000;
		}else if(format == 'PFM'){
			return num*1000;
		}else if(format == 'KG'){
			return num*1000;
		}else{
			return num;
		}
		
	},
	getUserName : function(lastUser) {
		if (lastUser == null || lastUser == undefined || lastUser == "") {
			return "-";
		} else {
			var obj = JSON.parse(lastUser);
			if (obj == null || obj == undefined || undefined == ""
					|| obj.userName == null || obj.userName == undefined
					|| obj.userName == "") {
				return "-"
			} else {
				return obj.userName;
			}

		}
	}
};
