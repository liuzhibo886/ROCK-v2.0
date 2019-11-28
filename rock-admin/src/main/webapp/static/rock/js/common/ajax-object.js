(function() {
	var $ax = function(url, success, error) {
		this.url = url;
		this.type = "post";
		this.data = {};
		this.dataType = "json";
		this.async = false;
		this.success = success;
		this.error = error;
		this.contentType = "application/x-www-form-urlencoded;charset=utf-8";
	};

	$ax.prototype = {
		start : function() {
			var me = this;

			if (this.url.indexOf("?") == -1) {
				this.url = this.url + "?jstime=" + new Date().getTime();
			} else {
				this.url = this.url + "&jstime=" + new Date().getTime();
			}


			var loading=Feng.loading();
			$.ajax({
				type : this.type,
				url : this.url,
				timeout : 10000,
				dataType : this.dataType,
				async : this.async,
				contentType : this.contentType,
				data : this.data,
				beforeSend : function(data) {

				},
				success : function(data) {
					Feng.close(loading);
					if (data.code == 0) {
						me.success(data);
					} else {
						me.error(data);
					}
				},
				error : function(data) {
					Feng.close(loading);
					Feng.alert("网络异常，请稍微再试", 2);
				}
			});
		},

		set : function(key, value) {
			if (typeof key == "object") {
				for ( var i in key) {
					if (typeof i == "function")
						continue;
					this.data[i] = key[i];
				}
			} else {
				this.data[key] = (typeof value == "undefined") ? $("#" + key)
						.val() : value;
			}
			return this;
		},

		setData : function(data) {
			this.data = data;
			return this;
		},
		setTimeout : function(data) {
			this.timeout = data;
			return this;
		},
		setBody : function(data) {
			this.contentType = "application/json";
			this.data = JSON.stringify(data);
			return this;
		},
		setContentType : function(contentType) {
			this.contentType = contentType;
			return this;
		},
		setAsync : function(async) {
			this.async = async;
			return this;
		},
		clear : function() {
			this.data = {};
			return this;
		}
	};

	window.$ax = $ax;

}());